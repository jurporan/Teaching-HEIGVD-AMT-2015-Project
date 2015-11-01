package ch.heigvd.amt.guat.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the application page in the Gary application.
 * 
 * @author Jan Purro
 */
public class AppPage extends AbstractGaryPage
{

    public AppPage(WebDriver driver)
    {
        super(driver);
        if (!"Register new app".equals(driver.getTitle()) &&
            !"App details".equals(driver.getTitle())) 
        {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
}
