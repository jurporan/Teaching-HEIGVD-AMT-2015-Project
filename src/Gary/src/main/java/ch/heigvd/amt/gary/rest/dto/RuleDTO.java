package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.Rule;

/**
 *
 * @author Jan Purro
 */
public class RuleDTO
{
    private String typeOfEvent;
    private long ruleParameter;
    private boolean penalty;
    private byte rewardType;

    public Rule toEntity() throws Exception
    {
        return new Rule(typeOfEvent, ruleParameter, penalty, rewardType);
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

    public int getRewardType()
    {
        return rewardType;
    }

    public void setRewardType(int rewardType)
    {
        this.rewardType = rewardType;
    }
}
