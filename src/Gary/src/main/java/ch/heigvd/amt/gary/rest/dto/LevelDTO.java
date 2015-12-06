package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.Level;

/**
 * DTO representing a level.
 * 
 * A level has a name and a number of minimum points the user must have to attain
 * that level.
 */
public class LevelDTO
{
    private String name;
    private int minPoints;

    // Getters et setters.
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getMinPoints()
    {
        return minPoints;
    }

    public void setMinPoints(int minPoints)
    {
        this.minPoints = minPoints;
    }
    
    public Level toEntity()
    {
        Level level = new Level();
        level.setName(name);
        level.setMinPoints(minPoints);
        return level;
    }
    
    public static LevelDTO fromEntity(Level level)
    {
        LevelDTO dto = new LevelDTO();
        dto.name = level.getName();
        dto.minPoints = level.getMinPoints();
        return dto;
    }
}
