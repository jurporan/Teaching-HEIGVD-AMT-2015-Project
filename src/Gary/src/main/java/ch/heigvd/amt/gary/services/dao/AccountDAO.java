/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services.dao;

import javax.ejb.Stateless;
import ch.heigvd.amt.gary.models.entities.Account;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lyuyhn
 */
@Stateless
public class AccountDAO extends DAO {

    
    public Account create(String mail, String firstName, String lastName, String password)
    {
        Account a = new Account(mail, firstName, lastName, password);
        em.persist(a);
        return a;
    }
    
    public Account login(String mail, String password)
    {
        List l = em.createQuery("SELECT a FROM Account a WHERE a.mail = :mail AND a.password = :password").setParameter("mail", mail).setParameter("password", password).getResultList();
        if (l.isEmpty()) {return null;}
        Account a = (Account) l.get(0);
        return a;
    }
    
    public Account get(Long id)
    {
        List l = em.createQuery("SELECT a FROM Account a WHERE a.id = :id").setParameter("id", id).getResultList();
        if (l.isEmpty()) {return null;}
        Account a = (Account) l.get(0);
        return a;
    }
}
