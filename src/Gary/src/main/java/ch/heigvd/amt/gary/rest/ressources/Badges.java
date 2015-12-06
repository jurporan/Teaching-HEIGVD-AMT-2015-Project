/*
* Author     : Benoist Wolleb
* Goal       : This is the JAX-RS endpoint for our REST API which handles the requests on /api/application/{apikey}/badges. See our documentation for more details.
*/

package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.models.entities.Badge;
import ch.heigvd.amt.gary.rest.dto.BadgeDTO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.*;

@Stateless
@Path("/applications/{apikey}/badges")
public class Badges
{
    @EJB AppDAO appDAO;
    
    @POST
    @Consumes("application/json")
    public Response submitNewBadge(BadgeDTO badge, @PathParam("apikey") String apikey)
    {
        // We first have to retrieve the application. We send an error if it is not found.
        App a = appDAO.get(apikey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        // Then we create the badge entity and add it to the application
        Badge b = badge.toEntity();
        appDAO.addBadge(a, b);
        
        // We return the id of the newly created badge
        return Response.ok(b.getId()).build();
    }
    
    @GET
    @Produces("application/json")
    public Response getAllBadges(@PathParam("apikey") String apikey)
    {
        // We first have to retrieve the application. We send an error if it is not found.
        App a = appDAO.get(apikey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        // We get the list of badges of this application
        List<Badge> badges = a.getBadges();
        List<BadgeDTO> badgesDto = new LinkedList<>();
        
        // We construct a DTO for each Badge and store it in the list
        for (Badge l : badges) {badgesDto.add(BadgeDTO.fromEntity(l));}
        
        // We return the DTO list
        return Response.ok().entity(badgesDto).build();
    }
}