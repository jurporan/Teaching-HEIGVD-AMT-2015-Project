package ch.heigvd.amt.gary.rest.dto;

/**
 *
 * @author Jan Purro
 */
public class EventDTO
{
    private String type;
    private Integer parameter;

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
