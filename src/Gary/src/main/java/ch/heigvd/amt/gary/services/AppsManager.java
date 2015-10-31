/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.services.dao.AccountDAO;
import ch.heigvd.amt.gary.services.dao.AppDAO;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Miguel
 */
@Stateless
public class AppsManager implements AppsManagerLocal {
   
   @EJB
   AccountDAO accountDao;
   @EJB
   AppDAO appDao;
   
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
