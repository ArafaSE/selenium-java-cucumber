package steps;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckoutPage;
import pages.HomePage;
import tests.BaseTest;
import utils.Reporter;

import java.util.ArrayList;

public class TestSteps extends BaseTest {
    HomePage homePage = new HomePage();
    CheckoutPage checkoutPage;
    @AfterStep
    public void attachToReport(Scenario scenario){
        if(scenario.isFailed()) {
            System.out.println("\u001B[40m" + "\u001B[31m" + "Failed! - Taking screenshots.." + "\u001B[0m");
            final byte[] screenshot = Reporter.captureScreenshot(driver, scenario.getName());
            // TODO: has an issue that it only add attachments if the first Example failed
            scenario.attach(screenshot, "image/png", "Screenshot");
            scenario.log("Failed step line number: " + scenario.getLine());
        }
    }

    @Given("I am a non-registered customer")
    public void i_am_a_non_registered_customer() {

    }
    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        navigateToUrl(url);
    }
    @When("I select my country {string}")
    public void i_select_my(String countryCode) {
        homePage.selectCountry(countryCode);
    }
    @Then("I can see trial price displayed in my {string}")
    public void i_can_see_trial_price_displayed_in_my(String currency) {
        String oldTrialCostText = homePage.getOldTrialCostText();
        Assert.assertTrue(oldTrialCostText.contains(currency));
    }

    @And("I select plan {string}")
    public void iSelectPlan(String plan) {
        checkoutPage = homePage.selectPlan(plan);
    }

    @Then("I get a free trial")
    public void iGetAFreeTrial() {
        Assert.assertTrue(checkoutPage.getTotalPriceTxt().contains("0.00"));
    }
}
