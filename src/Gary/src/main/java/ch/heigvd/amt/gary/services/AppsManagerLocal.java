/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.App;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Miguel
 */
@Local
public interface AppsManagerLocal {
   public void createApp(String name, String description, String apiKey, 
                         int numberOfUsers, boolean active, long userId);
   public void editApp(long id, String name, String description, boolean active);
   public App getApp(long id);
   public long countForAccount(long userId);
   public List<App> getUserApps(long userId, int pageNo, int nbItemsPerPage);
}
