    // Test des events

package com.mycompany.testsconcurrence;

//import ch.heigvd.amt.gary.models.entities;
 

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
    
    private Client client;
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
    

    private void test()
    {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfConcurrentThreads);
        
        final WebTarget target = client.target("http://localhost:8080/Gary/api").path("");
            
        
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
        
        (new TestClient(10)).test();        
    }
    
}
