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
import javax.persistence.ManyToOne;

/**
 *
 * @author lyuyhn
 */
@Entity
public class BadgeAward extends Award implements Serializable
{
    @ManyToOne
    private Badge badge;

    public Badge getBadge()
    {
        return badge;
    }

    public void setBadge(Badge badge)
    {
        this.badge = badge;
    }
}
