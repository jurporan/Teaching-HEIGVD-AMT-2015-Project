/*
* Author     : Benoist Wolleb
* Goal       : This class represents a specific user of an app. It is identified with the external number used in the remote application and the app that it is associated with. A created user has an automatic timestamp that is set to its creation date.
*/

package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.persistence.Version;

@Entity
public class EndUser implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne(optional = false)
    private App app;
    
    @OneToOne
    private Reputation reputation;
    
    @Id
    Long externalId;
    
    @Temporal(DATE)
    private Date date;
    
    @Version
    private long version;
    
    /**
    * Creates a new empty Account
    */
    public EndUser(){date = new Date();}
    
    /**
    * Creates a new Account
    *
    * @param app the app that this user is associated to
    * @param id the external id used by the app to identify it
    */
    public EndUser(App app, Long id)
    {
        this.app = app;
        externalId = id;
        date = new Date();
    }
    
    // Getters and Setters
    
    /**
    * Get the app associated with this user
    *
    * @return the app of this user
    */
    public App getApp()
    {
        return app;
    }
    
    /**
    * Set the app associated to this user, should be set automatically by the data store
    *
    * @param app the app to set
    */
    public void setApp(App app)
    {
        this.app = app;
    }
    
    /**
    * Get the external id that identifies the user
    *
    * @return the id of this user
    */
    public Long getExternalId()
    {
        return externalId;
    }
    
    /**
    * Set the id of the user, should be set automatically by the data store
    *
    * @param externalId the id to set
    */
    public void setExternalId(Long externalId)
    {
        this.externalId = externalId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (externalId != null ? externalId.hashCode() : 0) + app.hashCode();
        return hash;
    }
    
    /**
    * Get the reputation of the user
    *
    * @return the reputation of this user
    */
    public Reputation getReputation()
    {
        return reputation;
    }
    
    /**
    * Set the reputation the user, should be set once the user is created
    *
    * @param reputation the reputation to set
    */
    public void setReputation(Reputation reputation)
    {
        this.reputation = reputation;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EndUser))
        {
            return false;
        }
        EndUser other = (EndUser) object;
        if (externalId != other.externalId || !app.equals(other.app))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ch.heigvd.amt.gary.models.entities.EndUser[ id=" + externalId + ", app=" + app + " ]";
    }

    public Date getDate()
    {
        return date;
    }
}
