/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.Badge;
import ch.heigvd.amt.gary.models.entities.Reputation;
import java.util.*;

/**
 *
 * @author lyuyhn
 */
public class ReputationDTO
{
    private int points;
    private List<BadgeDTO> badges = new LinkedList<>();

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
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
        
        for (Badge badge : reputation.getBadges())
        {
            dto.badges.add(BadgeDTO.fromEntity(badge));
        }
        
        return dto;
    }
}
