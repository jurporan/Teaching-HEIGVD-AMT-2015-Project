package ch.heigvd.amt.gary.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public interface EditAccountServiceLocal {
    
    /**
     * Edit the firstname, lastename and password of a given account
     * @param firstname the new firstname
     * @param lastname the new lastname
     * @param password  the new password
     */
    public void editAccount(long id, String firstname, String lastname, String password);
    
}
