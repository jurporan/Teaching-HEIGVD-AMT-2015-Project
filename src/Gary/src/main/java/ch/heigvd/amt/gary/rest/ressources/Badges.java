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
        App a = appDAO.get(apikey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        appDAO.addBadge(a, badge.toEntity());
        return Response.ok().build();
    }
    
    @GET
    @Produces("application/json")
    public Response getAllBadges(@PathParam("apikey") String apikey)
    {
        App a = appDAO.get(apikey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        List<Badge> badges = a.getBadges();
        List<BadgeDTO> badgesDto = new LinkedList<>();
        
        for (Badge l : badges) {badgesDto.add(BadgeDTO.fromEntity(l));}
        return Response.ok().entity(badgesDto).build();
    }
}