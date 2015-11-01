/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.Account;
import javax.ejb.Local;

/**
 *
 * @author franz
 */
@Local
public interface LoginServiceLocal
{
   /**
    * Return the total number of existing accounts.
    * @return The total number of existing accounts.
    */
   public long countTotalAccounts();
   
    Account verifyLogin(String login, String password);
    
    void passwordValidation(String password, String passwordConfirmation) throws Exception;
    
    void firstnameAndLastnameValidation (String firstname, String lastname) throws Exception;
   
    void emailValidation(String email) throws Exception;
    
    boolean isValidEmailAddress(String email);
    
    /**
     * Edit the firstname, lastename and password of a given account
     * @param firstname the new firstname
     * @param lastname the new lastname
     * @param password  the new password
     */
    public void editAccount(long id, String firstname, String lastname, String password);
}


