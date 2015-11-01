package ch.heigvd.amt.gary.services.test;

import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.services.dao.AccountDAO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/*
 * Author     : Jan Purro
 * Goal       : This SLSB is used to generate some test data.
 */
@Stateless
public class TestDataService implements TestDataServiceLocal
{
    @EJB
    AccountDAO accountDAO;
    
    @EJB
    AppDAO appDAO;
    
    /**
     * Insert some data in the DB for testing purposes.
     */
    @Override
    public void generateTestData()
    {
        // We check if the data was already generated. In this case we simply
        // return.
        if(accountDAO.exists("abc@mail.gary"))
        {
            return;
        }
        
        // Create some accounts.
        Account albert = accountDAO.create("abc@mail.gary", "Albert", "Buc", "12341234");
        Account delphine = accountDAO.create("def@mail.gary", "Delphine", "Ernf", "12341234");
        Account gerard = accountDAO.create("ghi@mail.gary", "Gérard", "Hurli", "12341234");
        Account joanne = accountDAO.create("jkl@mail.gary", "Joanne", "Kabil", "12341234");
        
        // Create some apps.
        appDAO.create("An App", "Description", UUID.randomUUID().toString(), 0, true, albert.getId());
        appDAO.create("Some App", "Description", UUID.randomUUID().toString(), 0, true, albert.getId());
        appDAO.create("The Best App", "Description", UUID.randomUUID().toString(), 0, true, albert.getId());
        appDAO.create("My App", "Description", UUID.randomUUID().toString(), 0, true, delphine.getId());
        appDAO.create("Your App", "Description", UUID.randomUUID().toString(), 0, true, delphine.getId());
        appDAO.create("Ape", "Description", UUID.randomUUID().toString(), 0, true, gerard.getId());
        appDAO.create("No App", "Description", UUID.randomUUID().toString(), 0, true, gerard.getId());
    }
    
}
