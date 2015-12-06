/*
 * Test class
*/

package com.mycompany.testsconcurrence;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.jackson.JacksonFeature;


public class TestClient {
    
    private final Client client;
    private String apiKey;
    private final int numberOfConcurrentThreads; 
    private static final Logger LOG = Logger.getLogger(TestClient.class.getName());
    private ExpectedState expectedState = null;
    private long addedPoints = 1;
    private long deductedPoints = 5;
    
    
    /**
     * Constructor
     * @param numberOfConcurrentThreads Number of concurrent threads
     */
    public TestClient(String apiKey, int numberOfConcurrentThreads)
    {
        this.client = ClientBuilder.newClient().register(JacksonFeature.class);
        this.numberOfConcurrentThreads = numberOfConcurrentThreads;  
        this.apiKey = apiKey;
    }

    private void createEndUser(int id)
    {
        EndUserDTO endUser = new EndUserDTO();
        endUser.setId(id);
        final WebTarget target = client
                .target("http://localhost:8080/Gary/api")
                .path("applications/" + apiKey + "/users");     
        Response response = target.request().post(Entity.json(endUser));  
        if(response.getStatus() < 200 || response.getStatus() >= 300)
        {
            LOG.log(Level.INFO, "The server was not able to create a user", 
                    new Object[]{response.getStatus() + " " + response.getStatusInfo()});
        }
    }
    
    private void createRule(String typeOfEvent, long ruleParameter, boolean penalty, int minValue,
            int maxValue, byte rewardType)
    {
        RuleDTO rule = new RuleDTO(typeOfEvent, ruleParameter, 
                penalty, minValue, maxValue, rewardType);
        
        final WebTarget target = client
            .target("http://localhost:8080/Gary/api")
            .path("applications/" + apiKey + "/rules");   

        Response response = target.request().post(Entity.json(rule));
        if(response.getStatus() < 200 || response.getStatus() >= 300)
        {
            LOG.log(Level.INFO, "The server was not able to create a new rule", 
                    new Object[]{response.getStatus() + " " + response.getStatusInfo()});
        }
    }
    
    private long getPointsFromEndUser(long userID)
    {
        long points = 0;
        final WebTarget target = client
                .target("http://localhost:8080/Gary/api")
                .path("applications/" + apiKey + "/users/" 
                        + userID + "/reputation");  
      
        Response response = target.request().get();
        if(response.getStatus() < 200 || response.getStatus() >= 300)
        {
            LOG.log(Level.INFO, "Cannot get points from user", 
                    new Object[]{response.getStatus() + " " + response.getStatusInfo()});
        }
        else
        {
            System.out.println(response.toString());
            ReputationDTO reputation = ReputationDTO
                    .fromEntity((Reputation)response
                    .readEntity(Reputation.class));
            points = reputation.getPoints();
        }
        return points;
    }
    
    private void postEvent(String eventType, long userID)
    {
        EventDTO event = new EventDTO();
        final WebTarget target = client
            .target("http://localhost:8080/Gary/api")
            .path("applications/" + apiKey + "/users/" 
                        + userID + "/events");   
                
        event.setType(eventType);
        event.setParameter(null);
        
        Response response = target.request().post(Entity.json(event));
        
        if(response.getStatus() < 200 || response.getStatus() >= 300)
        {
            LOG.log(Level.INFO, "The server was not able to post an event", 
                    new Object[]{response.getStatus() + " " + response.getStatusInfo()});
        }
        else
        {
            expectedState.logEvent(event);
        }
    }
    
    private List<EndUserDTO> getUserListFromServer()
    {
        final WebTarget target = client
                .target("http://localhost:8080/Gary/api")
                .path("applications/" + apiKey +"/users"); 
        
        GenericType<List<EndUserDTO>> accountsListType = new GenericType<List<EndUserDTO>>(){};
        return target.request().get(accountsListType);
    }
    
    private void test()
    {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfConcurrentThreads);
        
        final WebTarget target = client
                .target("http://localhost:8080/Gary/api")
                .path("applications/" + apiKey + "/users");
        
        try
        {
            // Create one rule to add points
            createRule("messagePosted", addedPoints, false, 0, 0, (byte)1);
        }
        catch (Exception e) { System.err.println(e); };

        try
        {
            // Create one rule to deduct points
            createRule("insultingSomeone", deductedPoints, true, 0, 0, (byte)1);
        }
        catch (Exception e) { System.err.println(e); };

        // Check if there is at least one user, and create one if there is no user.
        List<EndUserDTO> userList = getUserListFromServer();
        if(userList.isEmpty()) 
        { 
            createEndUser(321);
        }
        
        // Get the id of the first end user of the list
        final long userID = userList.get(0).getId();
        long userPoints = getPointsFromEndUser(userID);
        
        expectedState = new ExpectedState(userPoints, addedPoints, deductedPoints);

        // Adds points to the user
        Runnable addTask = new Runnable() 
        {
            @Override 
            public void run()
            {
                for (int i = 0; i < 500; ++i)
                {
                    postEvent("messagePosted", userID);
                }
            }
        };       
        
        // Deducts points to the user
        Runnable deductTask = new Runnable() 
        {
            @Override 
            public void run()
            {
                for (int i = 0; i < 100; ++i)
                {
                    postEvent("insultingSomeone", userID);
                } 
            }
        }; 
        
        executor.execute(addTask);
        executor.execute(deductTask);
        LOG.log(Level.INFO, "All tasks have been submitted to the executor and will"
                + " be processed by {0} concurrent threads.", numberOfConcurrentThreads);
        
        try
        {
            // Try to stop the threadpool
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.HOURS);      
        }
        catch (InterruptedException e)
        {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, e);
        }
        LOG.info("Done.");
              
        List<String> errors = validateExpectedAgainstActualState(userID);
        LOG.info("Errors: " + errors.toString());
        
        LOG.info(expectedState.toString());
    }

    
    public static void main(String[] args) {
        
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try
        {         
            // Enter an existing api key
            // String apiKey = buffer.readLine().trim();
            String apiKey = "6dfc4960-a27b-4387-a1f3-c3fef11f7d13";
            // Run the test with 100 threads
            (new TestClient(apiKey, 100)).test(); 
        } catch (Exception e) { System.err.println(e); }   
    }
    
      
    private List<String> validateExpectedAgainstActualState(long userID) {

        List<String> errors = new ArrayList<>();
        
        LOG.log(Level.INFO, "Expected expected points for user: ", expectedState.getExpectedPoints());
        if(expectedState.getExpectedPoints() != getPointsFromEndUser(userID))
        {
            errors.add("The number of final points is not the one expected: " 
                    + expectedState.getExpectedPoints() + " vs "
                    + getPointsFromEndUser(userID));
        }
        return errors;
  }
}
