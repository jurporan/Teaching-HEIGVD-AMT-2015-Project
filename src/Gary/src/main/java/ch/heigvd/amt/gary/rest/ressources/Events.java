package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.models.entities.BadgeAward;
import ch.heigvd.amt.gary.models.entities.EndUser;
import ch.heigvd.amt.gary.models.entities.PointsAward;
import ch.heigvd.amt.gary.models.entities.Rule;
import ch.heigvd.amt.gary.rest.dto.EventDTO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import ch.heigvd.amt.gary.services.dao.EndUserDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.OptimisticLockException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Endpoint used to post events for an application user.
 */
@Stateless
@Path("/applications/{apikey}/users/{userId}/events")
public class Events
{
    @EJB AppDAO appDAO;
    @EJB EndUserDAO userDAO;
    
    /**
     * Process events. If there's a rule (or several rule) defined for this event
     * it's applied. Otherwise nothing will happen. The lack of rules won't raise
     * an error.
     * @param event : the processed event
     * @param apiKey : the apiKey of the application
     * @param userId : the userId.
     * @return will return a response containing either an error an ok if the event was processed correctly.
     */
    @POST
    @Consumes("application/json")
    public Response postEvent(EventDTO event, @PathParam("apikey") String apiKey, @PathParam("userId") String userId)
    {
        // First we check the apiKey and userId are correct.
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        EndUser user = null;
        try
        { user = userDAO.getUserForApp(app, new Long(userId)); }
        catch (NumberFormatException e)
        { return Response.status(400).entity("Invalid user ID").build(); }
        if (user == null) { return Response.status(400).entity("This user doesn't seem to exist").build(); }
        
        /* We check all the rule from the application. We don't stop at the first
           rule, because several rules can be defined for one event.*/
        for(Rule rule : app.getRules())
        {
            // We check if the rule applies to the event
            if(rule.getTypeOfEvent().equals(event.getType()))
            {
                /* There is a rule for the event. Now we check if there is a parameter 
                   to the event.
                   If that's the case we check the parameter is within the bounds. */
        
                if(event.getParameter() == null || 
                  (event.getParameter() != null && event.getParameter() <= rule.getMaxValueParameter() 
                   && event.getParameter() >= rule.getMinValueParameter()))
                {
                    // Now we check the type of reward.
                    if (rule.getType() == Rule.POINT_EVENT)
                    {
                        PointsAward award = new PointsAward();
                        award.setIsPenalty(rule.isPenalty());
                        award.setNbPoints(rule.getRuleParameter());
                        award.setReason(event.getType());
                        /* We used optimistic concurency control.
                           If an exception is raised, it means there was a concurent
                           access and the transaction failed. In this case we 
                           try to rerun the transaction. */
                        while (true)
                        {
                            try
                            {
                                userDAO.givePointAward(user, award);
                            }
                            catch (OptimisticLockException e)
                            {
                                continue;
                            }
                            break;
                        }
                    }

                    else if (rule.getType() == Rule.BADGE_EVENT)
                    {
                        BadgeAward award = new BadgeAward();
                        award.setIsPenalty(rule.isPenalty());
                        award.setBadge(appDAO.getBadge(rule.getRuleParameter()));
                        award.setReason(event.getType());
                        /* We used optimistic concurency control.
                           If an exception is raised, it means there was a concurent
                           access and the transaction failed. In this case we 
                           try to rerun the transaction. */
                        while (true)
                        {
                            try
                            {
                                userDAO.giveBadgeAward(user, award);
                            }
                            catch (OptimisticLockException e)
                            {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
        }
        
        return Response.ok().entity("Event processed").build();
    }
}
