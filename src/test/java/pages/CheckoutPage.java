package pages;

import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {
    /*********** Web Elements **********/
    private By orderTotalPrice = By.id("order-total-price");

    /********** Page Methods ************/
    public String getTotalPriceTxt(){
        return getText(orderTotalPrice);
    }
}
