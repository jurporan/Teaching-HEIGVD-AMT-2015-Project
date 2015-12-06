/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testsconcurrence;

import java.io.Serializable;



public class Badge implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String imageUrl;
    private String name;
    private String description;
   
    /**
    * Creates a new empty Account
    */
    public Badge(){}
    
    /**
    * Creates a new Badge
    *
    * @param imageUrl url to the image of the badge
    * @param name name of the badge
    * @param description description of the badge
    */
    public Badge(String imageUrl, String name, String description)
    {
        this.imageUrl = imageUrl;
        this.name = name;
        this.description = description;
    }
    
    // Getters and Setters
    
    /**
    * Get the image url of the badge
    *
    * @return the url og the badge image
    */
    public String getImageUrl()
    {
        return imageUrl;
    }
    
    /**
    * Set the url of image
    *
    * @param imageUrl the url to set
    */
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
    
    /**
    * Get the name of the badge
    *
    * @return the ID of the badge
    */
    public String getName()
    {
        return name;
    }
    
    /**
    * Set the name of the badge
    *
    * @param name the name to set
    */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
    * Get the description of the badge
    *
    * @return the description of the badge
    */
    public String getDescription()
    {
        return description;
    }
    
    /**
    * Set the description of the badge
    *
    * @param description the ID to set
    */
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
    * Get the ID of the badge
    *
    * @return the ID of the badge
    */
    public Long getId()
    {
        return id;
    }
    
    /**
    * Set the ID of the badge, should be set automatically by the data store
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
        if (!(object instanceof Badge))
        {
            return false;
        }
        Badge other = (Badge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ch.heigvd.amt.gary.models.entities.Badge[ id=" + id + " ]";
    }
    
}