package hooks;


import database.util.DatabaseConnectionManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

public class Hooks {

    private Connection connection;
    public static final String remote_url = "http://localhost:4444/wd/hub";
    public static final String remote_url_chrome = "http://localhost:4445/wd/hub";
    public static final String remote_url_firefox = "http://localhost:4446/wd/hub";
    //protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private WebDriver driver;


    @Before
    public void setup() throws MalformedURLException,SQLException {
        /*String browserName = System.getProperty("browser");*/
        System.setProperty("webdriver.gecko.driver", "/Users/pulkitbhatia/Downloads/Drivers/geckodriver");
        /*if(browserName.equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions();
        driver.set(new RemoteWebDriver(new URL(remote_url),options));}
        if(browserName.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
        driver.set(new RemoteWebDriver(new URL(remote_url),options));}*/

        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport", "postgres", "password");
        connection = dcm.getConnection();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        }

    public WebDriver getDriver(){
        return driver;
    }

    public Connection returnConnection(){
        return connection;
    }

    @After
    public void tear_down() {
        getDriver().quit();
    }
}
