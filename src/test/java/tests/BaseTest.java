package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import pages.BasePage;
import pages.HomePage;
import utils.Reporter;


public class BaseTest {
    private WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;
    public Logger logger = Logger.getLogger(this.getClass());
    private final String HOME_URL = "https://subscribe.jawwy.tv/eg-ar";
    @BeforeClass
    @Parameters({"browser"})
    public void setUp(@Optional("chrome-headless") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--start-maximized");

            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
        }
        // headless browser testing with Chrome headless option
        else if (browserName.equalsIgnoreCase("chrome-headless")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--window-size=1920,1080");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--ignore-certificate-errors");

            driver = new ChromeDriver(chromeOptions);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

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
}
