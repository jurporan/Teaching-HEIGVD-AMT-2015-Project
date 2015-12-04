/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lyuyhn
 */
@Entity
public class PointsAward extends Award implements Serializable
{
    private int nbPoints;

    public int getNbPoints()
    {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints)
    {
        this.nbPoints = nbPoints;
    }
    
}
