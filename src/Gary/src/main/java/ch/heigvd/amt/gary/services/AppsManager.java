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
   public List<App> getAllApps() {
      //return appsDataStore.getAllApps();
      Account a = accountDao.get(Long.MIN_VALUE);
      //App b = appDao.create("ULTIMATE APPLICATION 2015", "THIZ IZ DA APP", "jsdlawkjhdjérôme", 123, true, a);
      //App c = appDao.create("Chattor", "PRO", "lalalalilili", 30, true, a);
      return a.getApps();
   }
}
