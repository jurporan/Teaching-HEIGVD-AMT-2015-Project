/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.services.dao.AccountDAO;
import javax.ejb.Stateless;

/**
 *
 * @author franz
 */
@Stateless
public class LoginService implements LoginServiceLocal
{
    private AccountDAO account = new AccountDAO();
    @Override
    public Account verifyLogin(String login, String password)
    {
        return account.login(login, password);
        
    }
}
