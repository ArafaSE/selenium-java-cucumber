package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BasePage {
    /*********** Web Elements **********/
    private By trialCost = By.cssSelector("[class=trial-cost]:first-of-type");

    /********** Page Methods ************/
    public String getOldTrialCostText(){
       return getText(trialCost);
    }
}
