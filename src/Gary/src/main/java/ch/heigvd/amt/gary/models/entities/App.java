/*
* Author     : Benoist Wolleb
* Goal       : This class represents an application that users create on the web interface. Il is declared as an entity so that it can be stored automatically by JPA.
*/

package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

    // Applications are linked to their creator
    @ManyToOne
    private Account creator;
    
    @OneToMany
    private List<Rule> rules = new LinkedList<>();
    
    @OneToMany(mappedBy = "app", cascade = CascadeType.ALL)
    private List<EndUser> users = new LinkedList<>();
    
    @OneToMany
    private List<Badge> badges = new LinkedList<>();
    
    @OneToMany
    private List<Level> levels = new LinkedList<>();

    /**
    * Creates a new empty App
    */
    public App() {}

    /**
    * Creates a new App
    *
    * @param name the name of the application
    * @param description the description of the application
    * @param apiKey the generated UUID used as a key
    * @param numberOfUsers the maximum number of users
    * @param active set the application as enabled/disabled
    * @param creator the Account object that represents the creator of the application
    */
    public App(String name, String description, String apiKey, int numberOfUsers, boolean active, Account creator)
    {
        this.name = name;
        this.description = description;
        this.apiKey = apiKey;
        this.numberOfUsers = numberOfUsers;
        this.active = active;
        this.creator = creator;
    }

    // Getters and Setters

    /**
    * Get the ID of the application
    *
    * @return the ID of the application
    */
    public Long getId()
    {
        return id;
    }

    /**
    * Set the ID of the application, should be set automatically by the data store
    *
    * @param id the ID to set
    */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
    * Get the application's name
    *
    * @return the name of the application
    */
    public String getName()
    {
        return name;
    }

    /**
    * Set the application's name
    *
    * @param name the name to set
    */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
    * Get the application's description
    *
    * @return the description of the application
    */
    public String getDescription()
    {
        return description;
    }
    
    /**
    * Set the application's description
    *
    * @param description the description to set
    */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
    * Get the key to use the application
    *
    * @return the key to use the application
    */
    public String getApiKey()
    {
        return apiKey;
    }
    
    /**
    * Set the key to use the application
    *
    * @param apiKey the API key to set, should be asssigned at creation time
    */
    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }
    
    /**
    * Get maximum number of users that can use the application
    *
    * @return the number of users that can use the application
    */
    public int getNumberOfUsers()
    {
        return numberOfUsers;
    }
    
    /**
    * Set the maximum number of users that can use the application
    *
    * @param numberOfUsers the new maximum number of users to set
    */
    public void setNumberOfUsers(int numberOfUsers)
    {
        this.numberOfUsers = numberOfUsers;
    }
    
    /**
    * Get the state of the application
    *
    * @return a boolean indicating if the application is enabled or not
    */
    public boolean isActive()
    {
        return active;
    }
    
    /**
    * Set the state of the application
    *
    * @param active a boolean indicating if the application is enabled or not
    */
    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    /**
    * Get the creator of the application
    *
    * @return an Account object that represents the creator of the application
    */
    public Account getCreator()
    {
        return creator;
    }
    
    /**
     * Add a rule to the application.
     * 
     * @param rule The rule to had to the application.
     */
    public void addRule(Rule rule)
    {
        rules.add(rule);
    }
    
    /**
     * Get the list of the application's rules.
     * 
     * @return the list of rules.
     */
    public List<Rule> getRules()
    {
        return rules;
    }
    
    /**
    * Get the list of every users of this application
    *
    * @return an list of EndUsers linked to this application
    */
    public List<EndUser> getUsers()
    {
        return users;
    }
    
    /**
    * Add a new user to this application
    *
    * @param user the user to add
    */
    public void addUser(EndUser user)
    {
        users.add(user);
    }
    
    /**
    * Get the list of badges available this application
    *
    * @return an list of badges
    */
    public List<Badge> getBadges()
    {
        return badges;
    }

    public List<Level> getLevels()
    {
        return levels;
    }
    
    /**
    * Add a new badge to this application
    *
    * @param badge the badge to add
    */
    public void addBadge(Badge badge)
    {
        badges.add(badge);
    }
    
    public void addLevel(Level level)
    {
        levels.add(level);
    }
}
