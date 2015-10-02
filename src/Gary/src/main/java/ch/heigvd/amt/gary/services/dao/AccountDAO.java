/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services.dao;

import javax.ejb.Stateless;
import ch.heigvd.amt.gary.models.entities.Account;

/**
 *
 * @author lyuyhn
 */
@Stateless
public class AccountDAO extends DAO {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Account create(String mail, String firstName, String lastName, String password)
    {
        Account a = new Account(mail, firstName, lastName, password);
        em.persist(a);
        return a;
    }
}
