/*
 * Author     : Benoist Wolleb
 * Goal       : This DAO inherits the DAO superclass and have access to the EntityManager of the persistence context. This DAO is used to manage Apps.
 */
package ch.heigvd.amt.gary.services.dao;

import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.models.entities.Badge;
import ch.heigvd.amt.gary.models.entities.EndUser;
import ch.heigvd.amt.gary.models.entities.Level;
import ch.heigvd.amt.gary.models.entities.Rule;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AppDAO extends DAO {
    
    // We need a reference to the AccountDAO so that we can create a new App with the ID of the creator and then performing a lookup for his Account object
    @EJB
    AccountDAO accountDao;
    
    /**
    * Creates a new application in the data store
    *
    * @param name the name of the new app
    * @param description the description of the new app
    * @param apiKey the generated UUID used as a key
    * @param numberOfUsers the maximum number of user for the app
    * @param active indicates that the app should be enabled/disabled at creation time
    * @param creatorId the ID of the user that creates the application
    * @return an App object representing the newly created application
    */
    public App create(String name, String description, String apiKey, int numberOfUsers, boolean active, Long creatorId)
    {
        // Here we just perform a lookup in the data store to find the Account object of the creator and call the other create method
        return create(name, description, apiKey, numberOfUsers, active, accountDao.get(creatorId));
    }
    
    /**
    * Creates a new application in the data store
    *
    * @param name the name of the new app
    * @param description the description of the new app
    * @param apiKey the generated UUID used as a key
    * @param numberOfUsers the maximum number of user for the app
    * @param active indicates that the app should be enabled/disabled at creation time
    * @param creator the Account object that represents the creator of the application
    * @return an App object representing the newly created application
    */
    public App create(String name, String description, String apiKey, int numberOfUsers, boolean active, Account creator)
    {
        // We ensure that creator is a managed object, because we want to modify it
        em.merge(creator);
        
        // Create a new App object
        App app = new App(name, description, apiKey, numberOfUsers, active, creator);
        
        // We add the app in the list of the Account, so the references work in both directions
        creator.addApp(app);
        
        // Make the app persistent
        em.persist(app);
        
        // We flush so the id is assigned
        em.flush();
        return app;
    }
    
    /**
    * Count the total number of applications in the data store
    * 
    * @return a long number of applications
    */
    public long count()
    {
        long count = (long) em.createQuery("SELECT COUNT(a) FROM App a").getSingleResult();
        return count;
    }
    
    /**
    * Get an App with its ID
    *
    * @param id unique application ID to look for
    * @return an App object representing the application, or null if none exists with this ID
    */
    public App get(Long id)
    {
        // Here we create a custom query to fetch the applications corresponding to the provided id, should contain 1 or 0 element
        List l = em.createQuery("SELECT a FROM App a WHERE a.id = :id").setParameter("id", id).getResultList();
        
        // If the result list is empty, no account exists with this ID, we return null
        if (l.isEmpty()) {return null;}
        
        // Otherwise, we return the first (and only) app of the list
        App a = (App) l.get(0);
        return a;
    }
    
    /**
    * Get an App with its API key
    *
    * @param key API key to look for
    * @return an App object representing the application, or null if none exists with this ID
    */
    public App get(String key)
    {
        // Here we create a custom query to fetch the applications corresponding to the provided key, should contain 1 or 0 element
        List l = em.createQuery("SELECT a FROM App a WHERE a.apiKey = :key").setParameter("key", key).getResultList();
        
        // If the result list is empty, no account exists with this ID, we return null
        if (l.isEmpty()) {return null;}
        
        // Otherwise, we return the first (and only) app of the list
        App a = (App) l.get(0);
        return a;
    }
    
    /**
    * Get a list of applications created by a specific user, as it takes boundaries, this function is used for pagination
    *
    * @param account the creator of the apps we are looking for
    * @param pageNo the current page number of the pagination
    * @param nbPerPage number of results per page
    * @return an list of App objects representing the applications
    */
    public List<App> getUserApps(Account account, int pageNo, int nbPerPage)
    {
        // In the web interface, the first page is page 1, but here, we want a 0
        pageNo--;
        
        // We create the query to fetch all the applications created by the specified user, but we set the boundaries
        List<App> l = em.createQuery("SELECT a FROM App a WHERE a.creator = :creator").setParameter("creator", account).setFirstResult(pageNo * nbPerPage).setMaxResults(nbPerPage).getResultList();
        
        return l;
    }
    
    /**
    * Count the number of applications that a specific user have created, used for pagination to compute the total number of pages
    * 
    * @param account the creator of the apps we are looking for
    * @return a long number of applications
    */
    public long countForAccount(Account account)
    {
        long count = (long) em.createQuery("SELECT COUNT(a) FROM App a WHERE a.creator = :account").setParameter("account", account).getSingleResult();
        return count;
    }
    
    /**
    * Create a new end user of the application. The new user will be identified by the app itself and the user id used in the remote application.
    * 
    * @param app the application that will contain the user
    * @param id the internal id identifying the user in the remote app
    * @return the newly created user
    */
    public EndUser createNewUser(App app, Long id)
    {
        EndUser user = new EndUser(app, id);
        em.merge(app);
        em.persist(user);
        app.addUser(user);
        return user;
    }
    
    /**
    * Create a new badge that will be available in the application.
    * 
    * @param app the application that will contain the badge
    * @param imageUrl the url to the image of the badge
    * @param name the name of the badge
    * @param description the description of the badge
    * @return the newly created badge
    */
    public Badge createBadge(App app, String imageUrl, String name, String description)
    {
        Badge badge = new Badge(imageUrl, name, description);
        em.merge(app);
        em.persist(badge);
        app.addBadge(badge);
        return badge;
    }
    
    public void addRule(App app, Rule rule)
    {
        app = em.merge(app);
        em.persist(rule);
        app.addRule(rule);
    }
    
    public void addBadge(App app, Badge badge)
    {
        app = em.merge(app);
        em.persist(badge);
        app.addBadge(badge);
    }
    
    public void addLevel(App app, Level level)
    {
        app = em.merge(app);
        em.persist(level);
        app.addLevel(level);
    }
}
