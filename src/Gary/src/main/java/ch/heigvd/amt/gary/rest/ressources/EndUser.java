package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.rest.dto.EndUserDTO;
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
@Path("/applications/{apiKey}/users")
public class EndUser
{
    @EJB EndUserDAO userDAO;
    @EJB AppDAO appDAO;
    
    @POST
    @Consumes("application/json")
    public Response addUser(EndUserDTO user, @PathParam("apiKey") String apiKey)
    {
        userDAO.createUser(appDAO.get(apiKey), user.getId());
        return Response.ok().build();
    }
    
}
