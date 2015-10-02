/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.entities.App;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Miguel
 */
@Singleton
public class AppsDataStore implements AppsDataStoreLocal {
   private final List<App> catalog = new LinkedList<>();
   
   public AppsDataStore()
   {
      catalog.add(new App((long)1, "demo1", "just a test...", "$adi684jdmcosj301#lkxm", 0, true));
      catalog.add(new App((long)2, "a test app", "This application was...", "$lkdbaécbalbabla#lkxm", 299225, false));
      catalog.add(new App((long)5, "my photo app", "A cool app that...", "$jérômemoret#lkxm", 1110, true));
   }
   
   @Override
   public List<App> getAllApps()
   {    
      return new LinkedList<>(catalog);
   }
   
}
