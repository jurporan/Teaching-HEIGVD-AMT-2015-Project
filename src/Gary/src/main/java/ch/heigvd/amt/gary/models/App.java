/*
 * Represent the objet that define an application.
 * Created on 01.10.2015 by Miguel Santamaria
 */
package ch.heigvd.amt.gary.models;

/**
 *
 * @author Miguel
 */
public class App {
   private int id;
   private final String name;
   private final String description;
   private final String apiKey;
   private int numberOfUsers;
   private boolean active;
   
   public App(int id, String name, String description, String apiKey, 
              int numberOfUsers, boolean active)
   {
      this.name = name;
      this.description = description;
      this.apiKey = apiKey;
      this.numberOfUsers = numberOfUsers;
      this.active = active;
   }
   
   public int getId()
   {
      return id;
   }
   
   public String getName()
   {
      return name;
   }
   
   public String getDescription()
   {
      return description;
   }
   
   public String getApiKey()
   {
      return apiKey;
   }
   
   public int getNumberOfUsers()
   {
      return numberOfUsers;
   }
   
   public boolean isActive()
   {
      return active;
   }
}
