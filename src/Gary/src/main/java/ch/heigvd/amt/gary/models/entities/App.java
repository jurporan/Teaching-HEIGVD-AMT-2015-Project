/*
 * Represent the objet that define an application.
 * Created on 01.10.2015 by Miguel Santamaria
 */
package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Miguel
 */
@Entity
public class App implements Serializable
{    
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String description;
   private String apiKey;
   private int numberOfUsers;
   private boolean active;
   
   @ManyToOne
   private Account account;
   
   public App() {}
   
   public App(String name, String description, String apiKey, 
              int numberOfUsers, boolean active, Account creator)
   {
      this.name = name;
      this.description = description;
      this.apiKey = apiKey;
      this.numberOfUsers = numberOfUsers;
      this.active = active;
      this.account = creator;
   }

   public Long getId()
   {
       return id;
   }

   public void setId(Long id)
   {
       this.id = id;
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setName(String name)
   {
       this.name = name;
   }
   
   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
       this.description = description;
   }
   
   public String getApiKey()
   {
      return apiKey;
   }

   public void setApiKey(String apiKey)
   {
       this.apiKey = apiKey;
   }
   
   public int getNumberOfUsers()
   {
      return numberOfUsers;
   }

   public void setNumberOfUsers(int numberOfUsers)
   {
       this.numberOfUsers = numberOfUsers;
   }
   
   public boolean isActive()
   {
      return active;
   }

   public void setActive(boolean active)
   {
      this.active = active;
   }
}
