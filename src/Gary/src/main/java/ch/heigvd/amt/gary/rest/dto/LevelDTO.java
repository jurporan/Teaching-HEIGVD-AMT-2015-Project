/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.rest.dto;

import ch.heigvd.amt.gary.models.entities.Level;

/**
 *
 * @author lyuyhn
 */
public class LevelDTO
{
    private String name;
    private int minPoints;

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
}
