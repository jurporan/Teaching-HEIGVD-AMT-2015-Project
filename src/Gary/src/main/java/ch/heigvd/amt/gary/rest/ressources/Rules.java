/*
* Author     : Benoist Wolleb
* Goal       : This is the JAX-RS endpoint for our REST API which handles the requests on /api/application/{apikey}/rules. See our documentation for more details.
*/

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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Class used to manage rules to an application.
 */
@Stateless
@Path("/applications/{apiKey}/rules")
public class Rules
{
    @EJB AppDAO appDAO;
    
    
    /**
     * Creation of rules
     * 
     * @param rule : the rule to be added to the application that owns the rule
     * @param apiKey : the apiKey of the application
     * @return will return a response containing either an error if the apiKey or award type is 
     * unknown or an ok if the rule was created correctly.
     */
    @POST
    @Consumes("application/json")
    public Response submitNewRule(RuleDTO rule, @PathParam("apiKey") String apiKey)
    {
        Rule r;
        // We check the apiKey is correct.
        try
        {
             App app = appDAO.get(apiKey);
             if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
            r = rule.toEntity();
             appDAO.addRule(app, r);
        }
        catch (Exception e)
        {
            return Response.serverError().entity("Unknown award type.").build();
        }
        // We tell the client everything's ok
        return Response.ok(r.getId()).build();
    }
    
    /**
     * List of rules
     * 
     * @param apiKey : the apiKey of the application that owns the rule
     * @return will return a response containing either the list of every rule of the specified application
     */
    @GET
    @Produces("application/json")
    public Response getExistingRules(@PathParam("apiKey") String apiKey)
    {
        // We first have to retrieve the application. We send an error if it is not found.
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        // We get the list of rules of this application
        List<Rule> rules = app.getRules();
        List<RuleDTO> rulesDto = new LinkedList<>();
        
        // We construct a DTO for each Rule and store it in the list
        for (Rule r : rules) {rulesDto.add(RuleDTO.fromEntity(r));}
        
        // We return the DTO list
        return Response.ok().entity(rulesDto).build();
    }
    
    /**
     * Deletion of rules
     * 
     * @param apiKey : the apiKey of the application that owns the rule
     * @param ruleId : the id of the rule to be deleted
     * @return will return a response with a status code 200 or 400 and a message, indicating if the deletion was a success
     */
    @DELETE
    @Path("/{ruleId}")
    public Response deleteExistingRule(@PathParam("apiKey") String apiKey, @PathParam("ruleId") long ruleId)
    {
        // We first have to retrieve the application. We send an error if it is not found.
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        // We try to remove the rule, the DAO will return a boolean indicating if the rule was in the app and was deleted
        if (!appDAO.removeRule(app, ruleId))
        {return Response.status(400).entity("This rule doesn't seem to be in this app").build();}
        
        // We tell the client everything's ok
        return Response.ok().build();
    }
    
    /**
     * Edition of rules
     * 
     * @param ruleId : the id of the rule to be edited
     * @param rule : the new data of the rule
     * @param apiKey : the apiKey of the application that owns the rule
     * @return will update the specified rule with the provided data
     */
    @PUT
    @Path("/{ruleId}")
    @Consumes("application/json")
    public Response editExistingRule(RuleDTO ruleUpdate, @PathParam("apiKey") String apiKey, @PathParam("ruleId") long ruleId)
    {
        // We first have to retrieve the application. We send an error if it is not found.
        App app = appDAO.get(apiKey);
        if (app == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        // We get a reference to the rule. We send an error if the reference is null (the rule couldn't be found)
        Rule rule = appDAO.getRule(app, ruleId);
        if (rule == null) {return Response.status(400).entity("This rule doesn't seem to exist").build();}
        
        // We update the rule with the data in the DTO
        ruleUpdate.updateEntity(rule);
        
        // We tell the client everything's ok
        return Response.ok().build();
    }
}
