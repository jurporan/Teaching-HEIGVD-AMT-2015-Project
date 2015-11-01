package ch.heigvd.amt.guat.selenium;


import ch.heigvd.amt.guat.selenium.pages.AbstractGaryPage;
import ch.heigvd.amt.guat.selenium.pages.AppsListPage;
import ch.heigvd.amt.guat.selenium.pages.LoginPage;
import ch.heigvd.amt.guat.selenium.pages.WelcomePage;
import io.probedock.client.annotations.ProbeTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Suite of tests performed to check the site navigability.
 * 
 * @author jan purro
 */
public class GaryTest
{
    private String baseUrl = "localhost:8080/Gary/";
    private WebDriver driver;
    
    @Before
    public void openBrowser() 
    {
      driver = new FirefoxDriver();
    }
    
    @Test
    @ProbeTest(tags = "WebUI")
    public void aUserShouldBeAbleToAccessAllNonUserSpecificPagesWhenNotLoggedIn()
    {
        driver.get(baseUrl + "welcome");
        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.goToLoginPageViaButton().goToRegistrationPageViaButton();
    }
    
    @Test
    @ProbeTest(tags = "WebUI")
    public void aUserShouldBeRedirectedToLoginPageWhenTryingToAccesUserSpecificPagesWhenNotLoggedIn()
    {
        
        driver.get(baseUrl + "appslist");
        assert("Login".equals(driver.getTitle()));
        driver.get(baseUrl + "apps");
        assert("Login".equals(driver.getTitle()));
        driver.get(baseUrl + "editaccount");
        assert("Login".equals(driver.getTitle()));
        driver.get(baseUrl + "userlist");
        assert("Login".equals(driver.getTitle()));
    }
    
    @Test
    @ProbeTest(tags = "WebUI")
    public void itShouldNotBePossibleToLoginWithANonExistantAccount()
    {
        driver.get(baseUrl + "login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeEmailAddress("not@existant.void");
        loginPage.typePassword("notthepassword");
        loginPage.submitBadForm();
    }
    
    @Test
    @ProbeTest(tags = "WebUI")
    public void aUserShouldBeRedirectedToHisAppsListPageAfterLoggingIn()
    {
        driver.get(baseUrl + "login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeEmailAddress("abc@mail.gary");
        loginPage.typePassword("12341234");
        AppsListPage page = loginPage.submitForm();
    }
    
    @Test
    @ProbeTest(tags = "WebUI")
    public void aUserShouldBeAbleToAccesTheEditAccountPageAfterLoggingIn()
    {
        driver.get(baseUrl + "login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeEmailAddress("abc@mail.gary");
        loginPage.typePassword("12341234");
        AppsListPage page = loginPage.submitForm();
        page.goToEditAccountPageViaButton();
    }
    
    @After
    public void closeBrowser() 
    {
      driver.close();
    }
}
