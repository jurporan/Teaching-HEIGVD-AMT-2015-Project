package ch.heigvd.amt.guat.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The pages served by the application have a shared header. The buttons on this
 * header are used to navigate inside the site. They are captured since they 
 * can be used from any pages.
 *
 * @author Jan Purro
 */
public abstract class AbstractGaryPage extends Page 
{

  /* These locators are used to find elements in the headers. Every page
   * of Gary has one of the two headers. */
  By menuLoginLocator = By.id("btnLogin");
  By menuCreateAccountLocator = By.id("btnCreateAccount");
  By menuAppsLocator = By.id("btnHeaderApps");
  By menuAccountLocator = By.id("btnHeaderAccount");

  public AbstractGaryPage(WebDriver driver) 
  {
    super(driver);
  }
  
 /**
 * This method navigates to the login page by clicking on the button situated 
 * on the header.
 * 
 * @return A new login page.
 * 
 * @author Jan Purro
 */
  public LoginPage goToLoginPageViaButton() 
  {
    driver.findElement(menuLoginLocator).click();
    return new LoginPage(driver);
  }
  
  /**
 * This method navigates to the registration page by clicking on the button situated 
 * on the header.
 * 
 * @return A new registration page.
 * 
 * @author Jan Purro
 */
  public RegisterAccountPage goToRegistrationPageViaButton() 
  {
    driver.findElement(menuCreateAccountLocator).click();
    return new RegisterAccountPage(driver);
  }
  
  /**
 * This method navigates to the applications list page by clicking on the button situated 
 * on the header.
 * 
 * @return A new apps list page.
 * 
 * @author Jan Purro
 */
  public AppsListPage goToAppsListPageViaButton() 
  {
    driver.findElement(menuAppsLocator).click();
    return new AppsListPage(driver);
  }
  
  /**
 * This method navigates to the edit account pagee page by clicking on the button situated 
 * on the header.
 * 
 * @return A new edit account page.
 * 
 * @author Jan Purro
 */
  public EditAccountPage goToEditAccountPageViaButton() 
  {
    driver.findElement(menuAccountLocator).click();
    return new EditAccountPage(driver);
  }
  
}