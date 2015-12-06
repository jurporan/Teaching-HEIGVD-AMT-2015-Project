/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testsconcurrence;

import java.io.Serializable;

public class Level implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long id;
    
    private String name;
    private int minPoints;
    
    /**
    * Get the ID of the level
    *
    * @return the ID of the level
    */
    public Long getId()
    {
        return id;
    }
    
    /**
    * Set the ID of the level, should be set automatically by the data store
    *
    * @param id the ID to set
    */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
    * Get the name of the level
    *
    * @return the name of the level
    */
    public String getName()
    {
        return name;
    }
    
    /**
    * Set the name of the level
    *
    * @param id the name to set
    */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
    * Get the minimum number of points to reach the level
    *
    * @return the number of points
    */
    public int getMinPoints()
    {
        return minPoints;
    }
    
    /**
    * Set the minimum number of points to reach the level
    *
    * @param minPoints the number of points
    */
    public void setMinPoints(int minPoints)
    {
        this.minPoints = minPoints;
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
        if (!(object instanceof Level))
        {
            return false;
        }
        Level other = (Level) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ch.heigvd.amt.gary.models.entities.Level[ id=" + id + " ]";
    }
    
}