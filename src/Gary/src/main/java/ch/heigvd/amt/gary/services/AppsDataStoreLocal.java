/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.App;
import java.util.List;

/**
 *
 * @author Miguel
 */
public interface AppsDataStoreLocal {
   List<App> getAllApps();
}
