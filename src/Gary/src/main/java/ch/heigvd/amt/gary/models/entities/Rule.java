package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity representing a rule for an application. 
 * A rule applies to all event from the same application whose name are 
 * the same as the typeOfEvent attribute of the rule.
 * 
 * The ruleParameter from this class is either a number of point either a badge
 * ID. The points or badge will be removed or added depending on the boolean penalty
 * (obviously, if penalty is true then the points or badge are removed).
 * 
 * The type attribute indicates the type of reward, points or badge.
 * 
 * The minValueParameter and maxValueParameter are used when an event's parameter
 * is not null. In that case the penalty or reward is given only if the envent's
 * parameter is within the bounds of the rules parameter :
 * (minValueParameter <= even'ts parameter <= maxValueParameter)
 * 
 * Several rules can be applied to the same events.
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
    
    
    // Create a new empty Rule.
    public Rule(){}

    /**
     * Create a new Rule.
     * @param typeOfEvent : name of the event the rule will be applied to.
     * @param ruleParameter : number of points or badge id.
     * @param penalty : true if this is a penalty. False otherwise.
     * @param minValueParameter : lower bound for the event's parameter value (if smaller the rule won't apply).
     * @param maxValueParameter : upper bound for the event's parameter value (if greater the rule won't apply).
     * @param type : indicates the type of reward.
     * @throws Exception 
     */
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
    
    
    // Getters and Setters 
    
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
