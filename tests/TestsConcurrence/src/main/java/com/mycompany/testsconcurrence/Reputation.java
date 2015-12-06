/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testsconcurrence;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Reputation implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long id;
    
    private long points;

    private List<Badge> badges = new LinkedList<>();

    private List<Award> awards = new LinkedList<>();
    
    /**
    * Get the ID of the reputation
    *
    * @return the ID of the reputation
    */
    public Long getId()
    {
        return id;
    }
    
    /**
    * Set the ID of the reputation, should be set automatically by the data store
    *
    * @param id the ID to set
    */
    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reputation))
        {
            return false;
        }
        Reputation other = (Reputation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ch.heigvd.amt.gary.models.entities.Reputation[ id=" + id + " ]";
    }
    
    /**
    * Add an award in the list
    *
    * @param award the award to add
    */
    public void addAward(Award award)
    {
        awards.add(award);
    }
    
    /**
    * Add a badge in the list
    *
    * @param badge the badge to add
    */
    public void addBadge(Badge badge)
    {
        badges.add(badge);
    }
    
    /**
    * removes a badge in the list
    *
    * @param badge the badge to remove
    */
    public void removeBadge(Badge badge)
    {
        badges.remove(badge);
    }
    
    /**
    * Get the badges of this reputation
    *
    * @return the badges of the reputation
    */
    public List<Badge> getBadges()
    {
        return badges;
    }
    
    /**
    * Get the awards of this reputation
    *
    * @return the awards of the reputation
    */
    public List<Award> getAwards()
    {
        return awards;
    }
    
    /**
    * Get the points of the reputation
    *
    * @return the points of the reputation
    */
    public long getPoints()
    {
        return points;
    }

    /**
    * Set the points of the reputation
    *
    * @param points the points to set
    */
    public void setPoints(long points)
    {
        this.points = points;
    }
    
    
}
