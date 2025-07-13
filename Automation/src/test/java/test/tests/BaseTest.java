package test.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import test.utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setup(ITestContext context) {
        driver = new ChromeDriver();        
     // ✅ Navigate to baseUrl
        String baseUrl = ConfigReader.get("url");
        driver.get(baseUrl);
        driver.manage().window().maximize();

        // ✅ Share driver with listeners (e.g. for screenshots)
        context.setAttribute("WebDriver", driver);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
