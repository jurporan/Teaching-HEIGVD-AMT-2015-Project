/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.Badge;

/**
 *
 * @author lyuyhn
 */
public class BadgeDTO
{
    private String imageUrl;
    private String name;
    private String description;

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
    
    public Badge toEntity()
    {
        Badge badge = new Badge();
        badge.setName(name);
        badge.setImageUrl(imageUrl);
        badge.setDescription(description);
        return badge;
    }
    
    public static BadgeDTO fromEntity(Badge badge)
    {
        BadgeDTO dto = new BadgeDTO();
        dto.imageUrl = badge.getImageUrl();
        dto.name = badge.getName();
        dto.description = badge.getDescription();
        return dto;
    }
    
}
