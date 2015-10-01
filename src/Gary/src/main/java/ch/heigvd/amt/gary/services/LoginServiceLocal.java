/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import javax.ejb.Local;

/**
 *
 * @author franz
 */
@Local
public interface LoginServiceLocal
{
    boolean verifyLogin(String login, String password);
}
