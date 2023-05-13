package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;
import pages.BasePage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import dataProviders.ConfigFileReader;

public class BaseTest extends AbstractTestNGCucumberTests {
    protected static WebDriver driver;
    protected BasePage basePage;
    public Logger logger = Logger.getLogger(this.getClass());
    ConfigFileReader configFileReader;

    @BeforeSuite
//    @Parameters({"browser"})
    public void setUp() throws IOException {
        cleanTests();
        startDriver();
    }
    @AfterSuite
    public void tearDown() {driver.quit();}

    @BeforeMethod
    public void loadApplication(){
        driver.get(configFileReader.getData("home_url"));
        basePage = new BasePage();
        basePage.setDriver(driver);
    }
    public void cleanTests() throws IOException {
        // load data from data providers
        configFileReader = new ConfigFileReader();
        // clean old screenshots folder
        File directory = new File("./screenshots");
        FileUtils.cleanDirectory(directory);
    }
    public void startDriver(){
        String browserName = configFileReader.getData("browser");
        String headless = configFileReader.getData("headless");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*", "--start-maximized");

            if(headless.equals("true")){
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
            }
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
        }
        else if (browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--remote-allow-origins=*", "--start-maximized");

            if(headless.equals("true")){
                firefoxOptions.addArguments(
                        "--headless",
                        "--log-level=3",
                        "--remote-allow-origins=*",
                        "--disable-gpu",
                        "--window-size=1920,1080",
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--disable-extensions",
                        "--ignore-certificate-errors");
            }

            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();
        }
        else if (browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--remote-allow-origins=*", "--start-maximized");

            if(headless.equals("true")){
                edgeOptions.addArguments(
                        "--headless",
                        "--log-level=3",
                        "--remote-allow-origins=*",
                        "--disable-gpu",
                        "--window-size=1920,1080",
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--disable-extensions",
                        "--ignore-certificate-errors");
            }

            driver = new EdgeDriver(edgeOptions);
            driver.manage().window().maximize();
        }
    }

    protected String getHomURL(){
        return configFileReader.getData("home_url");
    }

    protected String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    protected void navigateToUrl(String url){
        driver.get(url);
    }
}
