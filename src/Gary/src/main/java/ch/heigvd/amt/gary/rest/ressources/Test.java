package ch.heigvd.amt.gary.rest.ressources;

import ch.heigvd.amt.gary.services.LoginServiceLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


// This class is just a test. It won't stay, it's just a mean of experimenting with JAX-RS.
@Stateless
@Path("/numberOfUsers")
public class Test
{
    @EJB
    LoginServiceLocal service;
    
    
    // We just return the number of accounts. It's totally useless it's just a test.
    @GET
    @Produces("application/json")
    public long getTest()
    {
        return service.countTotalAccounts();
        
    }
}
