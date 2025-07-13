package test.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            String fileName = name + "_" + timestamp() + ".jpeg";

            String folderPath = ConfigReader.get("screenshot.path");
            String fullPath = folderPath + fileName;

            FileUtils.copyFile(srcFile, new File(fullPath));
            System.out.println("📸 Screenshot saved: " + fullPath);
        } catch (IOException e) {
            System.out.println("❌ Failed to take screenshot: " + e.getMessage());
        }
    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    }
}
