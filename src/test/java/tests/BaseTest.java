package tests;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.HomePage;
import utils.logs.Log;

public class BaseTest {
    private WebDriver driver;
    public HomePage homePage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void setupProxy(ITestContext iTestContext) {

    }

    @BeforeClass
    public void classLevelSetup(ITestContext iTestContext) {
        Log.info("Tests is starting!");

        FirefoxOptions firefoxOptions = new FirefoxOptions();

        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");

        firefoxOptions.setCapability("proxy", proxy);
        driver = new FirefoxDriver(firefoxOptions);
        iTestContext.setAttribute("WebDriver", driver);
    }

    @BeforeMethod
    public void methodLevelSetup() {
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void teardown() {
        Log.info("Tests are ending!");
        driver.quit();
    }
}