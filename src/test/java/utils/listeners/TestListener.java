package utils.listeners;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.extentreports.ExtentManager;
import utils.logs.Log;

import java.util.Objects;

import static utils.extentreports.ExtentTestManager.getTest;

public class TestListener implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is failed.");

        //Get an instance of WebDriver out of the ITestResult
        if (iTestResult.getTestContext().getAttribute("WebDriver") != null) {
            WebDriver driver =
                    (WebDriver) iTestResult.getTestContext()
                            .getAttribute("WebDriver");

            //Take base64Screenshot screenshot for extent reports
            String base64Screenshot =
                    "data:image/png;base64," +
                            ((TakesScreenshot) Objects.requireNonNull(driver))
                            .getScreenshotAs(OutputType.BASE64);

            //ExtentReports log and screenshot operations for failed tests.
            getTest().log(Status.FAIL, "Test Failed",
                    getTest().addScreenCaptureFromBase64String(base64Screenshot)
                            .getModel().getMedia().get(0));
        } else {
            throw new RuntimeException(
                    "Unable to get a WebDriver instance out of the ITestResult. " +
                    " Your Test class is supposed to set it by calling " +
                            "ITestContext.addAttribute(\"WebDriver\", driver)");
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
