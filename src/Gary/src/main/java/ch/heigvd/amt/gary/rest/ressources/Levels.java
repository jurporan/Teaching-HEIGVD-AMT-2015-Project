/*
* Author     : Benoist Wolleb
* Goal       : This is the JAX-RS endpoint for our REST API which handles the requests on /api/application/{apikey}/levels. See our documentation for more details.
*/

package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.models.entities.Level;
import ch.heigvd.amt.gary.rest.dto.LevelDTO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/applications/{apikey}/levels")
public class Levels
{
    @EJB AppDAO appDAO;
    
    @POST
    @Consumes("application/json")
    public Response submitNewLevel(LevelDTO level, @PathParam("apikey") String apikey)
    {
        // We first have to retrieve the application. We send an error if it is not found.
        App a = appDAO.get(apikey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        // Then we create the badge entity and add it to the application
        appDAO.addLevel(a, level.toEntity());
        return Response.ok().entity("Level added").build();
    }
    
    @GET
    @Produces("application/json")
    public Response getAllLevels(@PathParam("apikey") String apikey)
    {
        // We first have to retrieve the application. We send an error if it is not found.
        App a = appDAO.get(apikey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        
        // We get the list of levels of this application
        List<Level> levels = a.getLevels();
        List<LevelDTO> levelsDto = new LinkedList<>();
        
        // We construct a DTO for each Level and store it in the list
        for (Level l : levels) {levelsDto.add(LevelDTO.fromEntity(l));}
        
        // We return the DTO list
        return Response.ok().entity(levelsDto).build();
    }
}
