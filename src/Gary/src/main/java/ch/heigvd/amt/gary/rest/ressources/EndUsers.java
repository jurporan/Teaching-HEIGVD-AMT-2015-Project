package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.models.entities.App;
// import static ch.heigvd.amt.gary.models.entities.App_.apiKey;
import ch.heigvd.amt.gary.models.entities.EndUser;
import ch.heigvd.amt.gary.rest.dto.EndUserDTO;
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
 *
 * @author Jan Purro
 */
@Stateless
@Path("/applications/{apiKey}/users")
public class EndUsers
{
    @EJB EndUserDAO userDAO;
    @EJB AppDAO appDAO;
    
    @POST
    @Consumes("application/json")
    public Response addUser(EndUserDTO user, @PathParam("apiKey") String apiKey)
    {
        App a = appDAO.get(apiKey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        userDAO.createUser(a, user.getId());
        return Response.ok().entity("User Added").build();
    }
    
    @GET
    @Produces("application/json")
    public Response getUsers(@PathParam("apiKey") String apiKey)
    {
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        List<EndUser> users = app.getUsers();
        List<EndUserDTO> usersDto = new LinkedList<>();
        
        for (EndUser u : users) {usersDto.add(EndUserDTO.fromEntity(u));}
        return Response.ok().entity(usersDto).build();
    }
    
    @GET
    @Path("/{userId}/reputation")
    @Produces("application/json")
    public Response getReputation(@PathParam("apiKey") String apiKey, @PathParam("userId") long userId)
    {
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        EndUser user = userDAO.getUserForApp(app, userId);
        if (user == null) {return Response.status(400).entity("This user doesn't seem to exist").build();}
        
        return Response.ok().entity(ReputationDTO.fromEntity(user.getReputation())).build();
    }
}
