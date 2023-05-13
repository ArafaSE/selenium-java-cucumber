package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reporter extends BaseTest {
    // method to take screenshot when Test Case Failed
    static byte[] screenshot;

    public static byte[] captureScreenshot(WebDriver driver, String screenshotName) {
        // Get the failure date time
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String formattedDateTime = myDateObj.format(myFormatObj);
        // Format the screenshot name
        Path destination = Paths.get("./screenshots", screenshotName + "-" + formattedDateTime + ".png");
        try {
            Files.createDirectories(destination.getParent());
            FileOutputStream out = new FileOutputStream(destination.toString());
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            out.write(screenshot);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Exception while taking Screenshot! - " + e.getMessage());
        }
        return screenshot;
    }


}
