package atda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import po.LoginPage;
import po.ProductPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }


    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    @Test
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("dijanasazdevska", "ds");
        assertTrue(new ProductPage(driver).isLoaded());

    }

    @Test
    public void canNotLoginWithInvalidCredentials() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("dijanasazdevska", "secret");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "Invalid user credentials exception");

    }
    @Test
    public void canNotLoginWithEmptyUsername() throws InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("","123");
        String errorMessage=loginPage.getErrorMessage();
        assertEquals(errorMessage,"Invalid user credentials exception");


    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\sixth semester\\Software Quality and Testing\\Homeworks\\java\\SeleniumSimple-master\\src\\main\\resources\\drivers\\chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
