package pages;

import hooks.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemoHomePage {

    private WebDriver driver;

    @FindBy(id="user-name")
    WebElement userName;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginButton;

    public SauceDemoHomePage(Hooks hooks) {
        driver = hooks.getDriver();
        PageFactory.initElements(driver,this);
    }

    public void launchURL(){
        driver.navigate().to("https://www.saucedemo.com/");
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public void enterUserName(String username){
        userName.sendKeys(username);
    }

    public void enterPassword(String password1){
        password.sendKeys(password1);
    }

    public void clickLoginButton(){
        loginButton.click();
    }
}
