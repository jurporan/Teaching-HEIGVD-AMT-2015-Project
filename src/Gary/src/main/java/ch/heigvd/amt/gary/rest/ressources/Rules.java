package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.rest.dto.RuleDTO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
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
@Path("/applications/{apiKey}/rules")
public class Rules
{
    @EJB AppDAO appDAO;
            
    @POST
    @Consumes("application/json")
    public Response submitNewRule(RuleDTO rule, @PathParam("apiKey") String apiKey)
    {
        try
        {
            appDAO.addRule(appDAO.get(apiKey), rule.toEntity());
        }
        catch (Exception e)
        {
            return Response.serverError().entity("Unknown award type.").build();
        }
        return Response.ok().build();
    }
}
