/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services.dao;

import ch.heigvd.amt.gary.models.entities.EndUser;
import ch.heigvd.amt.gary.models.entities.Reputation;
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
}
