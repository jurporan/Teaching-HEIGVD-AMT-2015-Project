package ch.heigvd.amt.gary.rest.dto;

/**
 * DTO representing an event.
 * 
 * An event only has two attributes : it's type, who is used to find rules that
 * apply to it. And parameter which, when not null, is compared with minValue and
 * maxValue of the rule to determine if the reward/penalty will be given to the
 * user or not.
 */
public class EventDTO
{
    private String type;
    private Integer parameter;

    
    // Getters and setters 
    
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Integer getParameter()
    {
        return parameter;
    }

    public void setParameter(Integer parameter)
    {
        this.parameter = parameter;
    }
}
