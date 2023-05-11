package tests;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import pages.BasePage;
import utils.Reporter;

public class BaseTest extends AbstractTestNGCucumberTests {
    protected static WebDriver driver;
    protected BasePage basePage;
    private final String HOME_URL = "https://subscribe.jawwy.tv/eg-ar";
    public Logger logger = Logger.getLogger(this.getClass());

    @BeforeSuite
    @Parameters({"browser"})
    public void setUp(@Optional("chrome-headless") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*", "--start-maximized");

            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--remote-allow-origins=*", "--start-maximized");

            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();
        }
        else if (browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--remote-allow-origins=*", "--start-maximized");

            driver = new EdgeDriver(edgeOptions);
            driver.manage().window().maximize();
        }
        // headless browser testing with Chrome headless option
        else if (browserName.equalsIgnoreCase("chrome-headless")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(
                    "--headless",
                    "--log-level=3",
                    "--remote-allow-origins=*",
                    "--disable-gpu",
                    "--window-size=1920,1080",
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--disable-extensions",
                    "--ignore-certificate-errors");

            driver = new ChromeDriver(chromeOptions);
        }
    }

    @AfterSuite
    public void tearDown() {driver.quit();}

    @BeforeMethod
    public void loadApplication(){
        driver.get(HOME_URL);
        basePage = new BasePage();
        basePage.setDriver(driver);
    }
    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("\u001B[40m" + "\u001B[31m" + "Failed! - Taking screenshots.." + "\u001B[0m");
            Reporter.captureScreenshot(driver, result.getName());

            logger.error(result);
        }
    }

    protected String getHomURL(){
        return HOME_URL;
    }

    protected String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    protected void navigateToUrl(String url){
        driver.get(url);
    }
}
