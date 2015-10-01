/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.models;

/**
 *
 * @author Miguel
 */
public class App {
   private final String name;
   private final String description;
   private final String apiKey;
   private int numberOfUsers;
   
   public App(String name, String description, String apiKey, int numberOfUsers)
   {
      this.name = name;
      this.description = description;
      this.apiKey = apiKey;
      this.numberOfUsers = numberOfUsers;
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
}
