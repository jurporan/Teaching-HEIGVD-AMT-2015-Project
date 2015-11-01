package ch.heigvd.amt.guat.selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is used to test the login page in the Gary application.
 * 
 * @author Jan Purro
 */
public class LoginPage extends AbstractGaryPage
{
    // Locators for the mail and password fields and submit button on the login page.
    By emailInputLocator = By.id("email");
    By passwordInputLocator = By.id("password");
    By loginBoutonLocator = By.id("submitLogin");

    public LoginPage(WebDriver driver)
    {
        super(driver);
        // We check that we're on the right page.
        if (!"Login".equals(driver.getTitle())) 
        {
            throw new IllegalStateException("This is not the correct page " + driver.getTitle());
        }
    }
    
    /**
    * Method used to enter an email inside the email field on the login page.
    * 
    * @param email the email to write inside the filed
    * @return the modified page.
    * 
    * @author Jan Purro
    */
    public LoginPage typeEmailAddress(String email) 
    {
        driver.findElement(emailInputLocator).sendKeys(email);
        return this;
    }
    
    /**
    * Method used to enter a password inside the password field on the login page.
    * 
    * @param password the password to write inside the filed
    * @return the modified page.
    * 
    * @author Jan Purro
    */
    public LoginPage typePassword(String password) 
    {
        driver.findElement(passwordInputLocator).sendKeys(password);
        return this;
    }
    
    /**
    * Method used to submit a form the login page.
    * 
    * @return the user apps list page the user should land on.
    * 
    * @author Jan Purro
    */
    public AppsListPage submitForm() 
    {
        driver.findElement(loginBoutonLocator).click();
        AppsListPage targetPage = null;
        
        targetPage = new AppsListPage(driver);
   
        return targetPage;
    }
    
    /**
    * Method used to submit an incorrect form the login page. The login is expected
    * to fail. 
    * 
    * @return the login page as the login should have failed.
    * 
    * @author Jan Purro
    */
    public LoginPage submitBadForm() 
    {
        driver.findElement(loginBoutonLocator).click();
        return this;
    }
}
