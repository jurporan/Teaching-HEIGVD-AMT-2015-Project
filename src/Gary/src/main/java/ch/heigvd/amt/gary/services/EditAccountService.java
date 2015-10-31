/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.services.dao.AccountDAO;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ch.heigvd.amt.gary.models.entities.Account;

@Stateless
public class EditAccountService implements EditAccountServiceLocal 
{
    
    @EJB
    AccountDAO accountDao;
    
    @Override
    public void editAccount(long id, String firstname, String lastname, String password)
    {
        Account account = accountDao.get(id);
        account.setFirstName(firstname);
        account.setLastName(lastname);
        account.setPassword(password);
    }
    
}
