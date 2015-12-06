
package com.mycompany.testsconcurrence;

import java.util.LinkedList;
import java.util.List;


public class ReputationDTO
{
    private long points;
    private List<BadgeDTO> badges = new LinkedList<>();

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
    
        public static ReputationDTO fromEntity(Reputation reputation)
    {
        ReputationDTO dto = new ReputationDTO();
        dto.points = reputation.getPoints();
       
        
        return dto;
    }

}
