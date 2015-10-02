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

/**
 *
 * @author Miguel
 */

@Entity
public class App implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }
    
   private String name;
   private String description;
   private String apiKey;
   private int numberOfUsers;
   
   public App(String name, String description, String apiKey, int numberOfUsers)
   {
      this.name = name;
      this.description = description;
      this.apiKey = apiKey;
      this.numberOfUsers = numberOfUsers;
   }
   
   public App() {}
   
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
