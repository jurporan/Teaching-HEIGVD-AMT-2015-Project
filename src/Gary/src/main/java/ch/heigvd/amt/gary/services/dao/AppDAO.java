/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services.dao;

import ch.heigvd.amt.gary.models.entities.Account;
import ch.heigvd.amt.gary.models.entities.App;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author lyuyhn
 */
@Stateless
public class AppDAO extends DAO {
    
    @EJB
    AccountDAO accountDao;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public App create(String name, String description, String apiKey, int numberOfUsers, boolean active, Long creatorId)
    {
        return create(name, description, apiKey, numberOfUsers, active, accountDao.get(creatorId));
    }
    
    public App create(String name, String description, String apiKey, int numberOfUsers, boolean active, Account creator)
    {
        App app = new App(name, description, apiKey, numberOfUsers, active, creator);
        creator.addApp(app);
        em.persist(app);
        return app;
    }
    
    public long count()
    {
        long count = (long) em.createQuery("SELECT COUNT(a) FROM App a").getSingleResult();
        return count;
    }
    
    public App get(Long id)
    {
        List l = em.createQuery("SELECT a FROM App a WHERE a.id = :id").setParameter("id", id).getResultList();
        if (l.isEmpty()) {return null;}
        App a = (App) l.get(0);
        return a;
    }
    
    public List<App> getUserApps(Account a, int pageNo, int nbPerPage)
    {
        pageNo--;
        List<App> l = em.createQuery("SELECT a FROM App a WHERE a.creator = :creator").setParameter("creator", a).setFirstResult(pageNo * nbPerPage).setMaxResults(nbPerPage).getResultList();
        return l;
    }
}