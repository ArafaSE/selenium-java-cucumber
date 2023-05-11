package runner;

import io.cucumber.testng.CucumberOptions;
import tests.BaseTest;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"steps"},
        tags = "@happy-path",
        plugin = {"pretty", "html:target/cucumber-html-report.html"})
public class TestRunner extends BaseTest {}

