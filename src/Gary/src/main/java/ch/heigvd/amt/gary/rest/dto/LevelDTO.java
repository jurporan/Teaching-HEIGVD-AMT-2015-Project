/*
* Author     : Benoist Wolleb
* Goal       : This class represents a level, very similar to the entity, but will wrap the properties so it can be transparently serialized into JSON. It is then usable to communicate between the client and the server.
*/

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
    // Properties
    private String name;
    private int minPoints;
    
    // Getters and Setters, not very interessant
    
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
    
    /**
    * Creates the entity corresponding to the dto content
    *
    * @return the Level entity
    */
    public Level toEntity()
    {
        Level level = new Level();
        level.setName(name);
        level.setMinPoints(minPoints);
        return level;
    }
    
    /**
    * Initialize the DTO with the content of the associated entity
    *
    * @param level the corresponding Level
    */
    public static LevelDTO fromEntity(Level level)
    {
        LevelDTO dto = new LevelDTO();
        dto.name = level.getName();
        dto.minPoints = level.getMinPoints();
        return dto;
    }
}
