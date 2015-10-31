/*
 * Created on : 03.10.2015
 * Author     : Miguel Santamaria
 * Goal       : Gives methods to work on apps objects.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.App;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AppsManagerLocal {
   /**
    * Create an app, with the given parameters.
    * @param name The app's name.
    * @param description The app's description.
    * @param apiKey The app's unique api-key.
    * @param numberOfUsers The number of users who're using the app.
    * @param active The app's status (true => enabled).
    * @param userId The creator user's ID.
    */
   public void createApp(String name, String description, String apiKey, 
                         int numberOfUsers, boolean active, long userId);
   
   /**
    * Edit a given app with the given parameters.
    * @param id The app-to-edit's ID.
    * @param name The new app's name.
    * @param description The new app's description.
    * @param active The new app's status (true => enabled).
    */
   public void editApp(long id, String name, String description, boolean active);
   
   /**
    * Edit the given app's status.
    * @param id The app-to-edit's ID.
    * @param active The new app's status.
    */
   public void editAppStatus(long id, boolean active);
   
   /**
    * Get the app which corresponds to the given ID.
    * @param id The ID of the app we're looking for.
    * @return The app whose ID is corresponding.
    */
   public App getApp(long id);
   
   /**
    * Count the total number of applications which the given user created.
    * @param userId The user's ID.
    * @return The total number of applications.
    */
   public long countForAccount(long userId);
   
   /**
    * Return the given user's apps' list, for the given page.
    * Used for the pagination system.
    * @param userId The user's ID to who the applications belong.
    * @param pageNo The current page number in which we are.
    * @param nbItemsPerPage The number of items per page we want.
    * @return The apps' list.
    */
   public List<App> getUserApps(long userId, int pageNo, int nbItemsPerPage);
}
