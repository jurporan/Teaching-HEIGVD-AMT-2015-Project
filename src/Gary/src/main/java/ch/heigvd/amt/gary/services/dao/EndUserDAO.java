/*
 * Author     : Benoist Wolleb
 * Goal       : This DAO inherits the DAO superclass and have access to the EntityManager of the persistence context. This DAO is used to manage end users.
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

@Stateless
public class EndUserDAO extends DAO
{
    /**
    * Creates a new end user in the data store
    *
    * @param app the app that contains this user
    * @param externalId id used by the remote application to identify the user
    * @return an EndUser object representing the newly created user
    */
    public EndUser createUser(App app, Long externalId)
    {
        EndUser user = new EndUser(app, externalId);
        
        // We automatically create a Reputation associated to this user
        Reputation reputation = new Reputation();
        em.persist(user);
        em.persist(reputation);
        user.setReputation(reputation);
        return user;
    }
    
    /**
    * Get a user providing the app and the id which bot identify it
    *
    * @param app the app that contains this user
    * @param externalId id used by the remote application to identify the user
    * @return an EndUser object representing the user
    */
    public EndUser getUserForApp(App app, Long externalId)
    {
        // Here we create a custom query to fetch the users corresponding to the provided id, should contain 1 or 0 element
        List l = em.createQuery("SELECT u FROM EndUser u WHERE u.app = :app AND u.externalId = :externalId").setParameter("app", app).setParameter("externalId", externalId).getResultList();
        
        // If the result list is empty, no account exists with this ID, we return null
        if (l.isEmpty()) {return null;}
        
        // Otherwise, we return the first (and only) app of the list
        EndUser u = (EndUser) l.get(0);
        return u;
    }
    
    /**
    * Give a point award to the user
    *
    * @param user the user that receives the award
    * @param award the award to give
    */
    public void givePointAward(EndUser user, PointsAward award)
    {
        user = em.merge(user);
        em.persist(award);
        
        // We store the award in the list
        user.getReputation().addAward(award);
        
        // We update the current number of points of the reputation
        user.getReputation().setPoints(user.getReputation().getPoints() + award.getNbPoints() * (award.isIsPenalty()? -1 : 1));
    }
    
    /**
    * Give a badge award to the user
    *
    * @param user the user that receives the award
    * @param award the award to give
    */
    public void giveBadgeAward(EndUser user, BadgeAward award)
    {
        user = em.merge(user);
        em.persist(award);
        
        // We store the award in the list
        user.getReputation().addAward(award);
        
        // We update the list of badges in the reputation
        if (award.isIsPenalty()) {user.getReputation().removeBadge(award.getBadge());}
        else {user.getReputation().addBadge(award.getBadge());}
    }
}
