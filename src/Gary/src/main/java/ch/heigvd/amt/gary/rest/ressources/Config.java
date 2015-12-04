package ch.heigvd.amt.gary.rest.ressources;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Application;


// Just a temporary config class for testing JAX-RS.

@javax.ws.rs.ApplicationPath("api")
public class Config extends Application 
{

  @Override
  public Set<Class<?>> getClasses() 
  {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    
    Class jsonProvider;
    try 
    {
      jsonProvider = Class.forName("org.glassfish.jersey.jackson.JacksonFeature");
      resources.add(jsonProvider);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Config.class.getName()).log(Level.SEVERE, "*** Problem while configuring JSON!", ex);
    }
    
    
    return resources;
  }

  private void addRestResourceClasses(Set<Class<?>> resources) 
  {
    resources.add(ch.heigvd.amt.gary.rest.ressources.Badges.class);
    resources.add(ch.heigvd.amt.gary.rest.ressources.Levels.class);
        resources.add(ch.heigvd.amt.gary.rest.ressources.Rules.class);
  }

}
