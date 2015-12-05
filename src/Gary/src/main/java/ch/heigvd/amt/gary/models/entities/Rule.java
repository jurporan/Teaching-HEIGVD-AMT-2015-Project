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

    public static final byte POINT_EVENT = 1;
    public static final byte BADGE_EVENT = 2;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String typeOfEvent;
    private long ruleParameter;
    private boolean penalty;
    private int minValueParameter;
    private int maxValueParameter;
    private byte type;
    
    public Rule(){}

    public Rule(String typeOfEvent, long ruleParameter, boolean penalty, int minValueParameter, int maxValueParameter, byte type) throws Exception
    {
        this.typeOfEvent = typeOfEvent;
        this.ruleParameter = ruleParameter;
        this.penalty = penalty;
        this.minValueParameter = minValueParameter;
        this.maxValueParameter = maxValueParameter;
        
        switch (type)
        {
            case POINT_EVENT:
            case BADGE_EVENT:
            this.type = type;
            break;
            
            default:
            throw new Exception();
        }
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
        return penalty;
    }

    public void setPenalty(boolean penalty)
    {
        this.penalty = penalty;
    }

    public int getMinValueParameter()
    {
        return minValueParameter;
    }

    public void setMinValueParameter(int minValueParameter)
    {
        this.minValueParameter = minValueParameter;
    }

    public int getMaxValueParameter()
    {
        return maxValueParameter;
    }

    public void setMaxValueParameter(int maxValueParameter)
    {
        this.maxValueParameter = maxValueParameter;
    }
    
    public byte getType()
    {
        return type;
    }

    public void setType(byte type)
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
