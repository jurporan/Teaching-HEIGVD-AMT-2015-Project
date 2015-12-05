/*
* Author     : Benoist Wolleb
* Goal       : -
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
    
    public EndUser(){date = new Date();}
    
    public EndUser(App app, Long id)
    {
        this.app = app;
        externalId = id;
        date = new Date();
    }

    public App getApp()
    {
        return app;
    }

    public void setApp(App app)
    {
        this.app = app;
    }

    public Long getExternalId()
    {
        return externalId;
    }

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

    public Reputation getReputation()
    {
        return reputation;
    }

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
