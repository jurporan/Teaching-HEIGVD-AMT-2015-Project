/*
* Author     : Benoist Wolleb
* Goal       : This class represents a role of user. It is not currently used. Il is declared as an entity so that it can be stored automatically by JPA.
*/
package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
    * Get the ID of the role
    *
    * @return the ID of the role
    */
    public Long getId()
    {
        return id;
    }
    
    /**
    * Set the ID of the role, should be set automatically by the data store
    *
    * @param the ID to set
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
        if (!(object instanceof Role))
        {
            return false;
        }

        Role other = (Role) object;

        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "ch.heigvd.amt.gary.models.entities.Role[ id=" + id + " ]";
    }

}
