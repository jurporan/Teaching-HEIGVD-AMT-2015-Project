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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Jan Purro
 */
@Stateless
@Path("/applications/{apikey}/users/{userId}/events")
public class Events
{
    @EJB AppDAO appDAO;
    @EJB EndUserDAO userDAO;
    
    @POST
    @Consumes("application/json")
    public Response postEvent(EventDTO event, @PathParam("apikey") String apiKey, @PathParam("userId") String userId)
    {
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        EndUser user = null;
        try
        { user = userDAO.getUserForApp(app, new Long(userId)); }
        catch (NumberFormatException e)
        { return Response.status(400).entity("Invalid user ID").build(); }
        
        if (user == null) { return Response.status(400).entity("This user doesn't seem to exist").build(); }
        
        Rule rule = null;
        for(Rule r : app.getRules())
        {
            if(r.getTypeOfEvent().equals(event.getType()))
            {
                rule = r;
                break;
            }
        }
        
        if (rule == null) { return Response.ok().entity("Event processed").build(); }
        
        /* There is a rule for the event. Now we check if there are conditions for the rule.
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
                userDAO.givePointAward(user, award);
            }
            
            else if (rule.getType() == Rule.BADGE_EVENT)
            {
                BadgeAward award = new BadgeAward();
                award.setIsPenalty(rule.isPenalty());
                award.setBadge(appDAO.getBadge(rule.getRuleParameter()));
                award.setReason(event.getType());
                userDAO.giveBadgeAward(user, award);
            }
        }
        
        return Response.ok().entity("Event processed").build();
    }
}
