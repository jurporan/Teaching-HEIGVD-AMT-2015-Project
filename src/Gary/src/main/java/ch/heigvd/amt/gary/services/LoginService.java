/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import javax.ejb.Stateless;

/**
 *
 * @author franz
 */
@Stateless
public class LoginService implements LoginServiceLocal
{
    @Override
    public boolean verifyLogin(String login, String password)
    {
        // Placeholder
        return login.equals("admin") && password.equals("1234");
    }
}
