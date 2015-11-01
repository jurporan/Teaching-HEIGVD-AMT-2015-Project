package ch.heigvd.amt.guat.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the applications list page in the Gary application.
 * 
 * @author Jan Purro
 */
public class AppsListPage extends AbstractGaryPage

{
    public AppsListPage(WebDriver driver)
    {
        super(driver);
        //We check that we are either on the right page.
        if (!"Your apps".equals(driver.getTitle())) 
        {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
}
