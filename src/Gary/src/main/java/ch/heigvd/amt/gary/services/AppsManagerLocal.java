/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.App;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Miguel
 */
@Local
public interface AppsManagerLocal {
   List<App> getAllApps();
}
