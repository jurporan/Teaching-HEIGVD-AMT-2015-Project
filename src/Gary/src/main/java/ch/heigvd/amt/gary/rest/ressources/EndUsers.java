   package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.models.entities.EndUser;
import ch.heigvd.amt.gary.models.entities.Level;
import ch.heigvd.amt.gary.rest.dto.EndUserDTO;
import ch.heigvd.amt.gary.rest.dto.LevelDTO;
import ch.heigvd.amt.gary.rest.dto.ReputationDTO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import ch.heigvd.amt.gary.services.dao.EndUserDAO;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Endpoint to post and get applications users and also post user related content.
 */
@Stateless
@Path("/applications/{apiKey}/users")
public class EndUsers
{
    @EJB EndUserDAO userDAO;
    @EJB AppDAO appDAO;
    
    /**
     * Post a new user to the application
     * @param user : the new user
     * @param apiKey : the application apiKey
     * @return will return a response containing either an error  an ok if the user was created correctly.
     */
    @POST
    @Consumes("application/json")
    public Response addUser(EndUserDTO user, @PathParam("apiKey") String apiKey)
    {
        // We check the apiKey is correct.
        App a = appDAO.get(apiKey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        userDAO.createUser(a, user.getId());
        return Response.ok().entity("User Added").build();
    }
    
    /**
     * Returns all the application current users.
     * @param apiKey the application apiKey
     * @return will return a response containing either an error a response containing the users.
     */
    @GET
    @Produces("application/json")
    public Response getUsers(@PathParam("apiKey") String apiKey)
    {
       System.out.println("Get app's user...");
        // We check the apiKey is correct.
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        // We retreive all the application users and return them to client.
        List<EndUser> users = app.getUsers();
        List<EndUserDTO> usersDto = new LinkedList<>();
        
        for (EndUser u : users) {usersDto.add(EndUserDTO.fromEntity(u));}
        return Response.ok().entity(usersDto).build();
    }
    
    /**
     * Returns the current reputation of the application's user.
     * @param apiKey the application apiKey
     * @param userId the user ID
     * @return Either a response containing an error either a response containing the reputation.
     */
    @GET
    @Path("/{userId}/reputation")
    @Produces("application/json")
    public Response getReputation(@PathParam("apiKey") String apiKey, @PathParam("userId") long userId)
    {
        // We check the userID and apiKey are both correct.
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        EndUser user = userDAO.getUserForApp(app, userId);
        if (user == null) {return Response.status(400).entity("This user doesn't seem to exist").build();}
        
        return Response.ok().entity(ReputationDTO.fromEntity(user.getReputation())).build();
    }
    
    /**
     * Returns the current level of the application's user.
     * @param apiKey : the application apiKey
     * @param userId : the user ID
     * @return Either a response containing an error either a response containing the user's current level.
     */
    @GET
    @Produces("application/json")
    @Path("/{userId}/level")
    public Response getUserLevel(@PathParam("apiKey") String apiKey, @PathParam("userId") long userId)
    {
         // We check the userID and apiKey are both correct.
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        EndUser user = userDAO.getUserForApp(app, userId);
        if (user == null) {return Response.status(400).entity("This user doesn't seem to exist").build();}
        
        LevelDTO userLevel = new LevelDTO();
        
        // We retrieve the level list and sort it so in the ascending order according to the minPoints
        List<Level> tmpList = app.getLevels();
        List<Level> levels = new LinkedList<>();
        for(Level level : tmpList)
        {
            levels.add(level);
        }
        
        Collections.sort(levels, new Comparator<Level>() 
        {
            @Override
            public int compare(Level a, Level b)
            {
               return a.getMinPoints() < b.getMinPoints() ? -1 : 
               a.getMinPoints() == b.getMinPoints() ? 0 : 1;
            }
        });
        
        long userPoints = user.getReputation().getPoints();
        
        /* For each level we check if the user has more points than what is required by the level.
           If that is the case, the user has at least that level. We do this until we reach a level
           where the condition isn't met. The last level where the user had enough point is the 
           user current level.
        */
        for(Level level : levels)
        {
            System.out.println(level.getName());
            if(level.getMinPoints() <= userPoints)
            {
                userLevel.setMinPoints(level.getMinPoints());
                userLevel.setName(level.getName());
            }
            else
            {
                break;
            }
        }
        
        return Response.ok().entity(userLevel).build();
    }
}
