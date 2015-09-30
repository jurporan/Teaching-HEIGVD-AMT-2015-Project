/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.gary.services;

import ch.heigvd.amt.gary.models.App;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Miguel
 */
public class AppsDataStore implements AppsDataStoreLocal {
   private final List<App> catalog = new LinkedList<>();
   
   public AppsDataStore()
   {
      catalog.add(new App("demo1", "just a test...", "$adi684jdmcosj301#lkxm", 0));
      catalog.add(new App("a test app", "This application was...", "$lkdbaécbalbabla#lkxm", 299225));
      catalog.add(new App("my photo app", "A cool app that...", "$jérômemoret#lkxm", 1110));
   }
   
   @Override
   public List<App> getAllApps() {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }
   
}
