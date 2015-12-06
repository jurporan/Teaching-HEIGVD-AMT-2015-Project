/*
* Author     : Benoist Wolleb
* Goal       : This class represents an award that can be given to a user. This class won't be used as it is, but through its subclasses. An award can be positive or negative.
*/

package ch.heigvd.amt.gary.models.entities;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Award implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(DATE)
    private Date timestamp;
    private String reason;
    private boolean  isPenalty;
    
    // Getters and Setters
    
    /**
    * Get the ID of the award
    *
    * @return the ID of the award
    */
    public Long getId()
    {
        return id;
    }
    
    /**
    * Set the ID of the award, should be set automatically by the data store
    *
    * @param id the ID to set
    */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
    * Get the timestamp of the award
    *
    * @return the timestamp of the award
    */
    public Date getTimestamp()
    {
        return timestamp;
    }
    
    /**
    * Set the timestamp of the award
    *
    * @param timestamp the timestamp to set
    */
    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }
    
    /**
    * Get the reason of the award, as text
    *
    * @return the reason of the award
    */
    public String getReason()
    {
        return reason;
    }
    
    /**
    * Set the reason of the award
    *
    * @param reason the reason to set
    */
    public void setReason(String reason)
    {
        this.reason = reason;
    }
    
    /**
    * Get the penalty of the award
    *
    * @return the penalty of the award
    */
    public boolean isIsPenalty()
    {
        return isPenalty;
    }
    
    /**
    * Set the penalty of the award
    *
    * @param isPenalty the boolean to set
    */
    public void setIsPenalty(boolean isPenalty)
    {
        this.isPenalty = isPenalty;
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
        if (!(object instanceof Award))
        {
            return false;
        }
        Award other = (Award) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ch.heigvd.amt.gary.models.entities.Award[ id=" + id + " ]";
    }
    
}
