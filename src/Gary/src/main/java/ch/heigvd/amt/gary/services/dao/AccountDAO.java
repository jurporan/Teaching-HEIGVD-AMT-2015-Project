/*
 * Author     : Benoist Wolleb
 * Goal       : This DAO inherits the DAO superclass and have access to the EntityManager of the persistence context. This DAO is used to manage Accounts.
 */

package ch.heigvd.amt.gary.services.dao;

import javax.ejb.Stateless;
import ch.heigvd.amt.gary.models.entities.Account;
import java.util.List;

@Stateless
public class AccountDAO extends DAO {

    /**
    * Creates a new account in the data store
    *
    * @param mail user email address used for login
    * @param firstName first name of the user
    * @param lastName last name of the user
    * @param password password used for login
    * @return an Acccount object representing the newly created account
    */
    public Account create(String mail, String firstName, String lastName, String password)
    {
        // Create a new Account object
        Account a = new Account(mail, firstName, lastName, password);
        
        // Make it persistent
        em.persist(a);
        
        // We flush so the id is assigned
        em.flush();
        return a;
    }
    
    /**
    * Try to log a user in
    *
    * @param mail user email address used for login
    * @param password password used for login
    * @return an Acccount object representing the user, or null if authentification fails
    */
    public Account login(String mail, String password)
    {
        // Here we create a custom query to fetch the accounts corresponding to the combination email-password, should contain 1 or 0 element
        List l = em.createQuery("SELECT a FROM Account a WHERE a.mail = :mail AND a.password = :password").setParameter("mail", mail).setParameter("password", password).getResultList();
        
        // If the result list is empty, the authentification failed, we return a null
        if (l.isEmpty()) {return null;}
        
        // Otherwise, we return the first (and only) acccount of the list
        Account a = (Account) l.get(0);
        return a;
    }
    
    /**
    * Get an Account with its ID
    *
    * @param id unique account ID to look for
    * @return an Acccount object representing the user, or null if none exists with this ID
    */
    public Account get(Long id)
    {
        // Here we create a custom query to fetch the accounts corresponding to the provided id, should contain 1 or 0 element
        List l = em.createQuery("SELECT a FROM Account a WHERE a.id = :id").setParameter("id", id).getResultList();
        
        // If the result list is empty, no account exists with this ID, we return null
        if (l.isEmpty()) {return null;}
        
        // Otherwise, we return the first (and only) account of the list
        Account a = (Account) l.get(0);
        return a;
    }
    
    /**
    * Check the availability of the email address
    *
    * @param mail email address to look for
    * @return a boolean that tells if the account already exists or not
    */
    public boolean exists(String mail)
    {
        // Here we create a custom query to fetch the accounts having the provided email address, should contain 1 or 0 element
        List l = em.createQuery("SELECT a FROM Account a WHERE a.mail = :mail").setParameter("mail", mail).getResultList();
        
        return !l.isEmpty();
    }
    
    /**
    * Count the total number of accounts in the data store
    * 
    * @return a long number of accounts
    */
    public long count()
    {
        long count = (long) em.createQuery("SELECT COUNT(a) FROM Account a").getSingleResult();
        return count;
    }
}
