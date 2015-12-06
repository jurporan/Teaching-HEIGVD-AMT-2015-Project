
package com.mycompany.testsconcurrence;

public class ExpectedState {
    
    private long initialPoints = 0;
    private long totalPoints = 0;
    private long addedPoints = 0;
    private long deductedPoints = 0;
    private int numberOfEventsPosted = 0;
    
    public ExpectedState(long initalPoints, long addedPoints, long deductedPoints)
    {
        this.initialPoints = initalPoints;
        this.addedPoints = addedPoints;
        this.deductedPoints = deductedPoints;
    }
    
    public synchronized void logEvent(EventDTO event)
    {
        ++numberOfEventsPosted;
        
        if(event.getType().equals("messagePosted"))
        {   
            totalPoints += addedPoints;
        }
        else if (event.getType().equals("insultingSomeone"))
        {
            totalPoints -= deductedPoints;
        }
    } 
    
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
