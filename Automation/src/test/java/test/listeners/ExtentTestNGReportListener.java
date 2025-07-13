package test.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import test.utils.ConfigReader;
import test.utils.ScreenshotUtils;

public class ExtentTestNGReportListener implements ITestListener {

    private static ExtentReports extent = createInstance();  // ✅ Correct type
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private WebDriver driver;

    private static ExtentReports createInstance() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-reports/ExtentReport.html");
        sparkReporter.config().setReportName("Test Report");
        sparkReporter.config().setDocumentTitle("Extent Reports");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        driver = (WebDriver) result.getTestContext().getAttribute("WebDriver");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "✅ Test Passed");
        takeScreenshotWithLog(result, Status.PASS);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "❌ Test Failed");
        test.get().log(Status.FAIL, result.getThrowable());
        takeScreenshotWithLog(result, Status.FAIL);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private void takeScreenshotWithLog(ITestResult result, Status status) {
        try {
            if (driver != null) {
                String methodName = result.getMethod().getMethodName();
                String screenshotPath = ConfigReader.get("screenshot.path") + methodName + ".jpeg";

                ScreenshotUtils.takeScreenshot(driver, screenshotPath);
                test.get().addScreenCaptureFromPath(screenshotPath);
            } else {
                test.get().log(status, "⚠️ WebDriver was null, couldn't capture screenshot.");
            }
        } catch (Exception e) {
            test.get().log(status, "⚠️ Could not attach screenshot: " + e.getMessage());
        }
    }
}
