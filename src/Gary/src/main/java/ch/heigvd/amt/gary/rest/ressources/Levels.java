/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 * Endpoint to post and get levels.
 */
@Stateless
@Path("/applications/{apikey}/levels")
public class Levels
{
    @EJB AppDAO appDAO;
    
    /**
     * Create a new level for the application.
     * 
     * @param level: the new level
     * @param apikey the application key
     * @return will return a response containing either an error an ok if the level was added correctly.
     */
    @POST
    @Consumes("application/json")
    public Response submitNewLevel(LevelDTO level, @PathParam("apikey") String apikey)
    {
        // We check the apiKey is correct.
        App a = appDAO.get(apikey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        appDAO.addLevel(a, level.toEntity());
        return Response.ok().entity("Level added").build();
    }
    
    /**
     * Returns all the currently defined levels for the application.
     * @param apikey : the application key.
     * @return will return a response containing either an error or all the current levels.
     */
    @GET
    @Produces("application/json")
    public Response getAllLevels(@PathParam("apikey") String apikey)
    {
        // We check the apiKey is correct.
        App a = appDAO.get(apikey);
        if (a == null) {return Response.status(400).entity("This app doesn't seem to exist").build();}
        List<Level> levels = a.getLevels();
        List<LevelDTO> levelsDto = new LinkedList<>();
        
        // We retrieve all current levels.
        for (Level l : levels) {levelsDto.add(LevelDTO.fromEntity(l));}
        return Response.ok().entity(levelsDto).build();
    }
}
