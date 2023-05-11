package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public static WebDriver driver;
    public void setDriver(WebDriver driver){
        BasePage.driver = driver;
    }

    /******** Generic methods *******/
    protected WebElement find(By locator){
        return driver.findElement(locator);
    }
    protected void click(By locator) {
        find(locator).click();
    }

    protected void setText(By locator, String textValue) {
        find(locator).sendKeys(textValue);
    }
    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected void clickEnterKey(By locator) {
        find(locator).sendKeys(Keys.ENTER);
    }

    protected void waitUntilElementBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilElementNotVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitSec(Integer sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /******** Custom methods, used to reduce the test complexity *******/

}
