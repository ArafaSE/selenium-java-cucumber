package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    /*********** Web Elements **********/
    private By trialCost = By.cssSelector("[class=trial-cost]:first-of-type");
    private By countryName = By.id("country-name");
    private By planTitle = By.id("plan-title");

    /********** Page Methods ************/
    public String getOldTrialCostText(){
       return getText(trialCost);
    }
    public void selectCountry(String countyCode){
        click(countryName);
        String countryLabel = countyCode+"-contry-lable";
        click(By.id(countryLabel));
    }

    public CheckoutPage selectPlan(String plan){
        click(By.id(plan+"-selection"));
        return new CheckoutPage();
    }

}
