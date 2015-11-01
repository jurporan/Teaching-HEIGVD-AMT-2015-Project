package ch.heigvd.amt.guat.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the registration page in the Gary application.
 * 
 * @author Jan Purro
 */
public class RegisterAccountPage extends AbstractGaryPage
{

    public RegisterAccountPage(WebDriver driver)
    {
        super(driver);
        if (!"Registration".equals(driver.getTitle())) 
        {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
}
