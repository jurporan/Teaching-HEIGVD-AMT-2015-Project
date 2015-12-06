/*
* Author     : Benoist Wolleb
* Goal       : This class represents an end user, very similar to the entity, but will wrap the properties so it can be transparently serialized into JSON. It is then usable to communicate between the client and the server.
*/

package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.EndUser;

/**
 * DTO representing an application's new user.
 * It contains only an id. This id can be the same in the client's application and in
 * ours as it has a composite key inside our DB
 */
public class EndUserDTO
{
    // Property
    private long id;


    /**
     * Create a new DTO from an entity.
     * 
     * @param user : the user entity we wish to create a DTO from.
     * @return The Rule entity corresponding to the DTO
     */
    public static EndUserDTO fromEntity(EndUser user)
    {
        EndUserDTO dto = new EndUserDTO();
        dto.id = user.getExternalId();
        return dto;
    }

    
    // Getters and Setters, not very interessant
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
}
