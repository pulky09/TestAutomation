package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SauceDemoHomePage;

public class MenuManagementSteps {

    private SauceDemoHomePage sauceDemoHomePage;

    public MenuManagementSteps(SauceDemoHomePage sauceDemoHomePage){
        this.sauceDemoHomePage = sauceDemoHomePage;
    }

    @Given("I am on secret sauce home page")
    public void i_am_on_secret_sauce_home_page() {
        sauceDemoHomePage.launchURL();
    }

    @When("I enter {string} and {string}")
    public void i_enter_and(String username, String password) {
        sauceDemoHomePage.enterUserName(username);
        sauceDemoHomePage.enterPassword(password);
        sauceDemoHomePage.clickLoginButton();
    }
    @Then("I am logged in")
    public void i_am_logged_in() {
        Assert.assertEquals("https://www.saucedemo.com/inventory.html",sauceDemoHomePage.getCurrentURL());
        System.out.println("Output" + sauceDemoHomePage.getCurrentURL());
    }
}
