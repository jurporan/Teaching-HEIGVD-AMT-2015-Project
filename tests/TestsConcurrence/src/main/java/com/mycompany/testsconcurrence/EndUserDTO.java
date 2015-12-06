package com.mycompany.testsconcurrence;

public class EndUserDTO
{
    private long id;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    
    public static EndUserDTO fromEntity(EndUser user)
    {
        EndUserDTO dto = new EndUserDTO();
        dto.id = user.getExternalId();
        return dto;
    }
}
