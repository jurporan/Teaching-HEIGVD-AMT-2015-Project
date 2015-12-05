/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services.dao;

import ch.heigvd.amt.gary.models.entities.App;
import ch.heigvd.amt.gary.models.entities.Award;
import ch.heigvd.amt.gary.models.entities.BadgeAward;
import ch.heigvd.amt.gary.models.entities.EndUser;
import ch.heigvd.amt.gary.models.entities.PointsAward;
import ch.heigvd.amt.gary.models.entities.Reputation;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author lyuyhn
 */
@Stateless
public class EndUserDAO extends DAO
{
    public EndUser createUser(App app, Long externalId)
    {
        EndUser user = new EndUser(app, externalId);
        Reputation reputation = new Reputation();
        em.persist(user);
        em.persist(reputation);
        user.setReputation(reputation);
        return user;
    }
    
    public EndUser getUserForApp(App app, Long externalId)
    {
        // Here we create a custom query to fetch the applications corresponding to the provided id, should contain 1 or 0 element
        List l = em.createQuery("SELECT u FROM EndUser u WHERE u.app = :app AND u.externalId = externalId").setParameter("app", app).setParameter("externalId", externalId).getResultList();
        
        // If the result list is empty, no account exists with this ID, we return null
        if (l.isEmpty()) {return null;}
        
        // Otherwise, we return the first (and only) app of the list
        EndUser u = (EndUser) l.get(0);
        return u;
    }
    
    public void givePointAward(EndUser user, PointsAward award)
    {
        user = em.merge(user);
        em.persist(award);
        user.getReputation().addAward(award);
        
        user.getReputation().setPoints(user.getReputation().getPoints() + award.getNbPoints() * (award.isIsPenalty()? -1 : 1));
    }
    
    public void giveBadgeAward(EndUser user, BadgeAward award)
    {
        user = em.merge(user);
        em.persist(award);
        user.getReputation().addAward(award);
        
        if (award.isIsPenalty()) {user.getReputation().removeBadge(award.getBadge());}
        else {user.getReputation().addBadge(award.getBadge());}
    }
}
