/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.Account;
import javax.ejb.Local;


@Local
public interface LoginServiceLocal
{
   /**
    * Return the total number of existing accounts.
    * @return The total number of existing accounts.
    */
   public long countTotalAccounts();
   
    Account verifyLogin(String login, String password);
    
    /**
     * Verify if the password has at least 8 characters and if the password
     * matches its confirmation
     * @param password
     * @param passwordConfirmation
     * @throws Exception 
     */
    void passwordValidation(String password, String passwordConfirmation) throws Exception;
    
    /**
     * Verify if the given firstname and lastname have at leat 3 characters.
     * @param firstname the firstname to verify
     * @param lastname the lastname to verify
     * @throws Exception 
     */
    void firstnameAndLastnameValidation (String firstname, String lastname) throws Exception;
   
    /**
     * Verify if the new email address is not in the database,
     * if it is a valid email string and if its length is not null.
     * @param email the address to verify
     * @throws Exception 
     */
    void emailValidation(String email) throws Exception;
    
    /**
     * Verify if the given email is a valid email string
     * @param email the address to verify
     * @return 
     */
    boolean isValidEmailAddress(String email);
    
    /**
     * Edit the firstname, lastename and password of a given account
     * @param firstname the new firstname
     * @param lastname the new lastname
     * @param password  the new password
     */
    public void editAccount(long id, String firstname, String lastname, String password);
}


