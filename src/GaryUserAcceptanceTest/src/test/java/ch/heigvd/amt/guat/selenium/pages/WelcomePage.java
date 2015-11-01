package ch.heigvd.amt.guat.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the Welcome page in the Gary application.
 * 
 * @author Jan Purro
 */
public class WelcomePage extends AbstractGaryPage 
{

  public WelcomePage(WebDriver driver) 
  {
    super(driver);

    // We check that we're on the right page.
    if (!"Welcome".equals(driver.getTitle())) {
      throw new IllegalStateException("This is not the correct page");
    }
  }

  
}
