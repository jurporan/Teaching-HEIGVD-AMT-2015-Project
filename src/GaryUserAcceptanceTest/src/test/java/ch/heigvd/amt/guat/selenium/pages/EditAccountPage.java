package ch.heigvd.amt.guat.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the edit account page in the Gary application.
 * 
 * @author Jan Purro
 */
public class EditAccountPage extends AbstractGaryPage
{

    public EditAccountPage(WebDriver driver)
    {
        super(driver);
        if (!"Edit account".equals(driver.getTitle())) 
        {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
}
