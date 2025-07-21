package test.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            // ✅ Handle unexpected alerts
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                System.out.println("⚠️ Alert detected: " + alert.getText());
                alert.accept(); // or alert.dismiss();
            } catch (NoAlertPresentException | org.openqa.selenium.TimeoutException ignored) {
                // No alert—carry on!
            }

            // ✅ Sanitize screenshot name
            name = name.replaceAll("[^a-zA-Z0-9-_]", "_");

            // ✅ Generate timestamped filename
            String fileName = name + "_" + timestamp() + ".png";

            // ✅ Normalize folder path
            String folderPath = ConfigReader.get("screenshot.path");
            if (!folderPath.endsWith("\\") && !folderPath.endsWith("/")) {
                folderPath += File.separator;
            }

            // ✅ Create screenshot directory if needed
            File dir = new File(folderPath);
            if (!dir.exists()) dir.mkdirs();

            // ✅ Capture and save screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            String fullPath = folderPath + fileName;
            FileUtils.copyFile(srcFile, new File(fullPath));

            System.out.println("📸 Screenshot saved: " + fullPath);

        } catch (IOException e) {
            System.out.println("❌ Screenshot failed due to I/O issue: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error while saving screenshot: " + e.getMessage());
        }
    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }
}