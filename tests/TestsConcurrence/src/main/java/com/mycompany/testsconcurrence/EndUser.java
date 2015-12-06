/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testsconcurrence;

import java.io.Serializable;
import java.util.Date;


public class EndUser implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private App app;

    private Reputation reputation;

    Long externalId;

    private Date date;

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
