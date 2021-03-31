package hooks;


import configs.ConfigFileReader;
import database.util.DatabaseConnectionManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

public class Hooks {

    private WebDriver driver;
    private ConfigFileReader configFileReader;
    private Connection connection;
    public static final String remote_url = "http://localhost:4444/wd/hub";
    private final String browser = System.getProperty("browser");

    public Hooks(){
        configFileReader = new ConfigFileReader();
        System.setProperty("webdriver.gecko.driver",configFileReader.getValue("driver_path_firefox"));
        System.setProperty("webdriver.chrome.driver",configFileReader.getValue("driver_path_chrome"));
    }

    @Before
    public void setup() {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager(
                                        configFileReader.getValue("host"),
                                        configFileReader.getValue("dbname"),
                                        configFileReader.getValue("username"),
                                        configFileReader.getValue("password"));
        try {
            connection = dcm.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if(browser.equalsIgnoreCase("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);}
        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);}
        if(browser.equalsIgnoreCase("firefoxremote")){
            FirefoxOptions options = new FirefoxOptions();
            try {
                driver = new RemoteWebDriver(new URL(remote_url),options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if (browser.equalsIgnoreCase("chromeremote")){
            ChromeOptions options = new ChromeOptions();
            try {
                driver = new RemoteWebDriver(new URL(remote_url),options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        }

    public WebDriver getDriver(){
        return driver;
    }

    public Connection returnConnection(){
        return connection;
    }

    @After
    public void tear_down(Scenario scenario) {
        if(scenario.isFailed()){
           byte[] screenshot =  ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
           scenario.attach(screenshot,"image/png","file");
        }
        getDriver().quit();
    }
}
