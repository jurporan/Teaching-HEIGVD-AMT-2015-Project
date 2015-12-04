/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.rest.dto.LevelDTO;
import ch.heigvd.amt.gary.rest.dto.RuleDTO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Stateless
@Path("/applications/{apikey}/rules")
public class Levels
{
    @EJB AppDAO appDAO;
    
    @POST
    @Consumes("application/json")
    public Response submitNewRule(LevelDTO level, @PathParam("apikey") String apikey)
    {
        appDAO.addLevel(appDAO.get(apikey), level.toEntity());
        return Response.ok().build();
    }
}
