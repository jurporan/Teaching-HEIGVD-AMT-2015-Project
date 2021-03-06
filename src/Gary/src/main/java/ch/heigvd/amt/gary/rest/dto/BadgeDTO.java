/*
* Author     : Benoist Wolleb
* Goal       : This class represents a badge, very similar to the entity, but will wrap the properties so it can be transparently serialized into JSON. It is then usable to communicate between the client and the server.
*/

package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.Badge;

public class BadgeDTO
{
    // Properties
    private long id;
    private String imageUrl;
    private String name;
    private String description;
    
    // Getters and Setters, not very interessant

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    
    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    
    /**
    * Creates the entity corresponding to the dto content
    *
    * @return the Badge entity
    */
    public Badge toEntity()
    {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setImageUrl(imageUrl);
        badge.setDescription(description);
        return badge;
    }
    
    /**
    * Initialize the DTO with the content of the associated entity
    *
    * @param badge the corresponding Badge
    * @return the badge DTO.
    */
    public static BadgeDTO fromEntity(Badge badge)
    {
        BadgeDTO dto = new BadgeDTO();
        dto.id = badge.getId();
        dto.imageUrl = badge.getImageUrl();
        dto.name = badge.getName();
        dto.description = badge.getDescription();
        return dto;
    }
    
}
