/*
* Author     : Benoist Wolleb
* Goal       : This class represents an end user, very similar to the entity, but will wrap the properties so it can be transparently serialized into JSON. It is then usable to communicate between the client and the server.
*/

package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.EndUser;

public class EndUserDTO
{
    // Property
    private long id;
    
    // Getters and Setters, not very interessant
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    
    /**
    * Initialize the DTO with the content of the associated entity
    *
    * @param user the corresponding EndUser
    */
    public static EndUserDTO fromEntity(EndUser user)
    {
        EndUserDTO dto = new EndUserDTO();
        dto.id = user.getExternalId();
        return dto;
    }
}
