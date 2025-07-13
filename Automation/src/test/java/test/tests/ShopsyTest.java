package test.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import test.pages.Shopsy;
import test.listeners.ExtentTestNGReportListener;
import test.utils.ConfigReader;

@Listeners(ExtentTestNGReportListener.class)
public class ShopsyTest extends BaseTest {
    Shopsy shopsypage;

    @BeforeClass
    public void login() {
        // ✅ driver is already started and navigated in BaseTest
        shopsypage = new Shopsy(driver);

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        shopsypage.ShopsyLogin(username, password);
    }

    @Test(priority = 1)
    public void showInstitudesTest() {
        shopsypage.ShowInstitudes();
    }

    // Add more tests here
}
