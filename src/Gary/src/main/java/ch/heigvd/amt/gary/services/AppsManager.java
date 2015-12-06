/*
 * Created on : 03.10.2015
 * Author     : Miguel Santamaria
 * Goal       : Implements methods to work on apps objects, from the 
 *              AppsManagerLocal interface.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.services.dao.AccountDAO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AppsManager implements AppsManagerLocal {
   
   @EJB
   AccountDAO accountDao;
   @EJB
   AppDAO appDao;
   
   // Methods' headers are in the AppsManagerLocal interface.
   @Override
   public long countTotalApps()
   {
      return appDao.count();
   }
   
   @Override
   public void createApp(String name, String description, String apiKey, 
                         int numberOfUsers, boolean active, long userId)
   {
      appDao.create(name, description,apiKey, numberOfUsers, active, userId);
   }
   
   @Override
   public void editApp(long id, String name, String description, boolean active)
   {
      App app = getApp(id);
      app.setName(name);
      app.setDescription(description);
      app.setActive(active);
   }
   
   @Override
   public void editAppStatus(long id, boolean active)
   {
      App app = getApp(id);
      app.setActive(active);
   }

   @Override
   public App getApp(long id) {
      return appDao.get(id);
   }
   
   @Override
   public long countForAccount(long userId)
   {
      Account a = accountDao.get(userId);
      return appDao.countForAccount(a);
   }
   
   @Override
   public List<App> getUserApps(long userId, int pageNo, int nbItemsPerPage)
   {
      Account a = accountDao.get(userId);
      return appDao.getUserApps(a, pageNo, nbItemsPerPage);
   }
}
