/*
* Author     : Benoist Wolleb
* Goal       : This class extends the Award class and represent an archievement that can be given to a user as a badge. As an award can be positive or negative, this can also get back a badge of a user.
*/

package ch.heigvd.amt.gary.models.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BadgeAward extends Award implements Serializable
{
    @ManyToOne
    private Badge badge;
    
    /**
    * Get the Badge associated to this award
    *
    * @return the badge of the award
    */
    public Badge getBadge()
    {
        return badge;
    }
    
    /**
    * Set the badge of the award
    *
    * @param badge the badge to set
    */
    public void setBadge(Badge badge)
    {
        this.badge = badge;
    }
}
