/*
 * Author     : Benoist Wolleb
 * Goal       : This DAO is only used to inject the reference to the persistence context, so that subclasses have access to it.
 */

package ch.heigvd.amt.gary.services.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DAO {
    
    @PersistenceContext
    EntityManager em;
}
