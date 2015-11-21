package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity representing a rule from an application.
 *
 */
@Entity
public class Rule implements Serializable
{

    public enum eventType {POINT_EVENT, BADGE_EVENT};

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String typeOfEvent;
    private long ruleParameter;
    private boolean isPenalty;
    private eventType type;

    public Rule(String typeOfEvent, long ruleParameter, boolean isPenalty, eventType type)
    {
        this.typeOfEvent = typeOfEvent;
        this.ruleParameter = ruleParameter;
        this.isPenalty = isPenalty;
        this.type = type;
    }
    
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
    
    public String getTypeOfEvent()
    {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent)
    {
        this.typeOfEvent = typeOfEvent;
    }

    public long getRuleParameter()
    {
        return ruleParameter;
    }

    public void setRuleParameter(long ruleParameter)
    {
        this.ruleParameter = ruleParameter;
    }

    public boolean isPenalty()
    {
        return isPenalty;
    }
    
    public void isPenalty(boolean isPenalty)
    {
        this.isPenalty = isPenalty;
    }

    public eventType getEventType()
    {
        return type;
    }

    public void setEventType(eventType type)
    {
        this.type = type;
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
        if (!(object instanceof Rule))
        {
            return false;
        }

        Rule other = (Rule) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString()
    {
        return "ch.heigvd.amt.gary.models.entities.Rules[ id=" + id + " ]";
    }

}
