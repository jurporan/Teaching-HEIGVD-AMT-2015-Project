package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.models.entities.Rule;
import ch.heigvd.amt.gary.rest.dto.RuleDTO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Class used to add rules to an application.
 */
@Stateless
@Path("/applications/{apiKey}/rules")
public class Rules
{
    @EJB AppDAO appDAO;
    
    
    /**
     * 
     * @param rule : the rule to be added to the application
     * @param apiKey : the apiKey of the application
     * @return will return a response containing either an error if the apiKey or award type is 
     * unknown or an ok if the rule was created correctly.
     */
    @POST
    @Consumes("application/json")
    public Response submitNewRule(RuleDTO rule, @PathParam("apiKey") String apiKey)
    {
        // We check the apiKey is correct.
        try
        {
             App app = appDAO.get(apiKey);
             if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
            appDAO.addRule(app, rule.toEntity());
        }
        catch (Exception e)
        {
            return Response.serverError().entity("Unknown award type.").build();
        }
        // We tell the client everything's ok
        return Response.ok().entity("Rule was added succesfully").build();
    }
    
    @GET
    @Produces("application/json")
    public Response getExistingRules(@PathParam("apiKey") String apiKey)
    {
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        List<Rule> rules = app.getRules();
        List<RuleDTO> rulesDto = new LinkedList<>();
        
        for (Rule r : rules) {rulesDto.add(RuleDTO.fromEntity(r));}
        
        // We tell the client everything's ok
        return Response.ok().entity(rulesDto).build();
    }
    
    @DELETE
    @Path("/{ruleId}")
    public Response deleteExistingRule(@PathParam("apiKey") String apiKey, @PathParam("ruleId") long ruleId)
    {
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        appDAO.removeRule(app, ruleId);
        
        // We tell the client everything's ok
        return Response.ok().build();
    }
    
}
