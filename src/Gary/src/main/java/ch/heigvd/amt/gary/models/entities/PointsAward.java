/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
/**
 *
 * @author lyuyhn
 */
@Entity
public class PointsAward extends Award implements Serializable
{
    private long nbPoints;

    public long getNbPoints()
    {
        return nbPoints;
    }

    public void setNbPoints(long nbPoints)
    {
        this.nbPoints = nbPoints;
    }
    
}
