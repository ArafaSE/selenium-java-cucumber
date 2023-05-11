package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckoutPage;
import pages.HomePage;
import tests.BaseTest;

public class TestSteps extends BaseTest {
    HomePage homePage = new HomePage();
    CheckoutPage checkoutPage;
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
