/*
* Author     : Benoist Wolleb
* Goal       : This class represents the reputation of a user, similar to the entity, but will wrap the properties so it can be transparently serialized into JSON. It is then usable to communicate between the client and the server. As the entity contains a list of badges, this DTO will also contain a list of BadgeDTO, so it can be used transparently.
*/

package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.Badge;
import ch.heigvd.amt.gary.models.entities.Reputation;
import java.util.*;

public class ReputationDTO
{
    // Properties
    private long points;
    private List<BadgeDTO> badges = new LinkedList<>();

    // Getters and Setters, not very interessant
    
    public long getPoints()
    {
        return points;
    }

    public void setPoints(long points)
    {
        this.points = points;
    }

    public List<BadgeDTO> getBadges()
    {
        return badges;
    }

    public void setBadges(List<BadgeDTO> badges)
    {
        this.badges = badges;
    }
    
    /**
    * Initialize the DTO with the content of the associated entity
    *
    * @param reputation the corresponding Reputation
    */
    public static ReputationDTO fromEntity(Reputation reputation)
    {
        ReputationDTO dto = new ReputationDTO();
        dto.points = reputation.getPoints();
        
        // We also have to insert each Badge from the Reputation into the BadgeDTO list
        for (Badge badge : reputation.getBadges())
        {
            dto.badges.add(BadgeDTO.fromEntity(badge));
        }
        
        return dto;
    }
}
