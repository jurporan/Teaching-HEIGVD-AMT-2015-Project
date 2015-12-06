/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 *
 * @author lyuyhn
 */
@Entity
public class Reputation implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private long points;
    
    @ManyToMany
    private List<Badge> badges = new LinkedList<>();
    @OneToMany
    private List<Award> awards = new LinkedList<>();

    public Long getId()
    {
        return id;
    }

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
    
    public void addAward(Award award)
    {
        awards.add(award);
    }
    
    public void addBadge(Badge badge)
    {
        badges.add(badge);
    }
    
    public void removeBadge(Badge badge)
    {
        badges.remove(badge);
    }

    public List<Badge> getBadges()
    {
        return badges;
    }

    public List<Award> getAwards()
    {
        return awards;
    }

    public long getPoints()
    {
        return points;
    }

    public void setPoints(long points)
    {
        this.points = points;
    }
    
    
}
