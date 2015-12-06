
package com.mycompany.testsconcurrence;

public class RuleDTO
{
    private String typeOfEvent;
    private long ruleParameter;
    private boolean penalty;
    private int minValue;
    private int maxValue;
    private byte rewardType;
    
    public RuleDTO(String typeOfEvent, long ruleParameter, boolean penalty, int minValue,
            int maxValue, byte rewardType)
    {
        this.typeOfEvent = typeOfEvent;
        this.ruleParameter = ruleParameter;
        this.penalty = penalty;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.rewardType = rewardType;
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

    public int getMinValue()
    {
        return minValue;
    }

    public void setMinValue(int minValue)
    {
        this.minValue = minValue;
    }

    public int getMaxValue()
    {
        return maxValue;
    }

    public void setMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
    }

    public int getRewardType()
    {
        return rewardType;
    }

    public void setRewardType(byte rewardType)
    {
        this.rewardType = rewardType;
    }
}
