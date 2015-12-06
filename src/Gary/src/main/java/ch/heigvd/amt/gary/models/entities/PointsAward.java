/*
* Author     : Benoist Wolleb
* Goal       : This class extends the Award class and represent an archievement that can be given to a user as a number of points. As an award can be positive or negative, this can also get back some points
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
    
    /**
    * Get the number of points associated to this award
    *
    * @return the number of points
    */
    public long getNbPoints()
    {
        return nbPoints;
    }
    
    /**
    * Set the number of points of this award
    *
    * @param nbPoints the number of points to set
    */
    public void setNbPoints(long nbPoints)
    {
        this.nbPoints = nbPoints;
    }
    
}
