
package com.mycompany.testsconcurrence;

public class ExpectedState {
    
    // Initial points from the user
    private long initialPoints = 0;
    // Total points that has been added to the user
    private long totalPoints = 0;
    // Value of one event that add points
    private long addedPoints = 0;
    // Value of one event that deducts points
    private long deductedPoints = 0;
    // Count the number of events succesfully posted
    private int numberOfEventsPosted = 0;
    
    public ExpectedState(long initalPoints, long addedPoints, long deductedPoints)
    {
        this.initialPoints = initalPoints;
        this.addedPoints = addedPoints;
        this.deductedPoints = deductedPoints;
    }
    
    /**
     * Log a successfully posted event
     * @param event the event posted
     */
    public synchronized void logEvent(EventDTO event)
    {
        ++numberOfEventsPosted;
        // Event that adds points
        if(event.getType().equals("messagePosted"))
        {   
            totalPoints += addedPoints;
        }
        // Event that deducts points
        else if (event.getType().equals("insultingSomeone"))
        {
            totalPoints -= deductedPoints;
        }
    } 
    
    /**
     * Number of points if everything went fine
     * @return The number of expected points
     */
    public long getExpectedPoints()
    {
        return initialPoints + totalPoints;
    }
    
    public String toString()
    {
        return  "=========== Expected state ===========\n"
                + "Initial user points : " + initialPoints + "\n"
                + "Final number of points added : " + totalPoints + "\n"
                + "Number of events posted : " + numberOfEventsPosted + "\n"
                + "======================================";       
    }
}
