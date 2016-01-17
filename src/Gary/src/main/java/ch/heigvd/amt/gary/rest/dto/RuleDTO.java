package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.Rule;

/**
 * DTO used to transfer a Rule between the client and the server.
 * This DTO has exactly the same properties as the Rule entity.
 * @author Jan Purro
 */
public class RuleDTO
{
    private long id;
    private String typeOfEvent;
    private long ruleParameter;
    private boolean penalty;
    private int minValue;
    private int maxValue;
    private byte rewardType;
    
    /**
     * Build a DTO initialized by the data in the entity
     * 
     * @param rule : the Rule entity from which we get the data
     * @return a Rule DTO initialized with the data within the Rule entity
     */
    public static RuleDTO fromEntity(Rule rule)
    {
        RuleDTO dto = new RuleDTO();
        dto.id = rule.getId();
        dto.typeOfEvent = rule.getTypeOfEvent();
        dto.ruleParameter = rule.getRuleParameter();
        dto.penalty = rule.isPenalty();
        dto.minValue = rule.getMinValueParameter();
        dto.maxValue = rule.getMaxValueParameter();
        dto.rewardType = rule.getType();
        return dto;
    }
    
    /**
     * Update a Rule entity with the data in the DTO
     * 
     * @param rule : the Rule entity to update
     */
    public void updateEntity(Rule rule)
    {
        rule.setTypeOfEvent(typeOfEvent);
        rule.setRuleParameter(ruleParameter);
        rule.setPenalty(penalty);
        rule.setMinValueParameter(minValue);
        rule.setMaxValueParameter(maxValue);
        rule.setType(rewardType);
    }
    
    /**
     * Create an entity from the DTO.
     * 
     * @return The Rule entity corresponding to the DTO
     * @throws Exception when the reward type is not valid.
     */
    public Rule toEntity() throws Exception
    {
        return new Rule(typeOfEvent, ruleParameter, penalty, minValue, maxValue, rewardType);
    }
    
    // Getters and Setters.
    
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
