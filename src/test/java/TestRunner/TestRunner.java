package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty",
                "html:target/SystemTestReport/html",
                "json:target/SystemTestReport/json/report.json"
        }, monochrome = true)
public class TestRunner {


}