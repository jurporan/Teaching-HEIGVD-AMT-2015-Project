/*
* Author     : Benoist Wolleb
* Goal       : This class represents a level that users can reach by getting points. A level is an honorific status represented by a name and attributed to a user when he reaches minPoints.
*/

package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Level implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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