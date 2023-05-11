package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;


public class TC1_CheckCurrency extends BaseTest {
    @Test
    public void user_can_see_the_price_added_with_selected_country_currency() {
        homePage = new HomePage();
        String oldTrialCostText = homePage.getOldTrialCostText();

        Assert.assertTrue(oldTrialCostText.contains("جنيه مصري"));
    }
}
