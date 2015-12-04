/*
* Author     : Benoist Wolleb
* Goal       : -
*/
package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    
    public EndUser(){}
    
    public EndUser(App app, Long id)
    {
        this.app = app;
        externalId = id;
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
}
