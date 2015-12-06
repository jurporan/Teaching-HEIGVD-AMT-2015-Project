    // Test des events

package com.mycompany.testsconcurrence;



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
    private final int numberOfConcurrentThreads;
    
    private static final Logger LOG = Logger.getLogger(TestClient.class.getName());
    
    
    /**
     * Constructor
     * @param numberOfConcurrentThreads Number of concurrent threads
     */
    public TestClient(int numberOfConcurrentThreads)
    {
        this.client = ClientBuilder.newClient().register(JacksonFeature.class);
        this.numberOfConcurrentThreads = numberOfConcurrentThreads;  
    }

    private void createEndUser()
    {
        EndUserDTO endUser = new EndUserDTO(2342432);
        final WebTarget target = client
                .target("http://localhost:8080/Gary/api")
                .path("applications/6dfc4960-a27b-4387-a1f3-c3fef11f7d13/users");     
        Response response = target.request().post(Entity.json(endUser));     
    }
    
    private List<EndUserDTO> getUserListFromServer()
    {
        final WebTarget target = client
                .target("http://localhost:8080/Gary/api")
                .path("applications/6dfc4960-a27b-4387-a1f3-c3fef11f7d13/users"); 
        
        GenericType<List<EndUserDTO>> accountsListType = new GenericType<List<EndUserDTO>>(){};
        return target.request().get(accountsListType);
    }
    
    private void test()
    {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfConcurrentThreads);
        
        final WebTarget target = client
                .target("http://localhost:8080/Gary/api")
                .path("applications/6dfc4960-a27b-4387-a1f3-c3fef11f7d13/users");
        
        // Create one user
        createEndUser();

        // Check if there is at least one user
        List<EndUserDTO> userList = getUserListFromServer();
        if(userList.isEmpty()) 
        { 
            createEndUser();
        }
        
        // Get the id of the first end user of the list
        EndUserDTO endUser = userList.get(0);

        // Add points to the user
        Runnable task = new Runnable() 
        {
            @Override 
            public void run()
            {
                
                
            }
        };       
        executor.execute(task);
        
        
        
        
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
    }

    
    public static void main(String[] args) {
        // Run the test with 10 threads
        (new TestClient(10)).test();        
    }
    
}
