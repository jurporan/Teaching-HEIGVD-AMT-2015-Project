package ch.heigvd.amt.guat.selenium.pages;

import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the user list page in the Gary application.
 * 
 * @author Jan Purro
 */
public class UserListPage extends AbstractGaryPage
{

    public UserListPage(WebDriver driver)
    {
        super(driver);
        if (!"List of users".equals(driver.getTitle())) 
        {
            throw new IllegalStateException("This is not the correct page");
        }
    }
    
}
