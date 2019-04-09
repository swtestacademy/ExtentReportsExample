package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    // Extra information:
    // 1) @BeforeClass we declared driver and wait variables
    // 2) We send these driver and wait variables to the page class with below declaration
    //    Homepage homepage = new HomePage(driver,wait);
    // 3) super () method in page class transfer the driver and wait variables values to the BasePage class.

    @Test (priority = 0, description="Invalid Login Scenario with wrong username and password.")
    public void invalidLoginTest_InvalidUserNameInvalidPassword (Method method) {
        //ExtentReports Description
        ExtentTestManager.startTest(method.getName(),"Invalid Login Scenario with empty username and password.");

        //*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(driver,wait);
        LoginPage loginPage = new LoginPage(driver,wait);

        //*************PAGE METHODS********************
        //Open N11 HomePage
        homePage.goToN11();

        //Go to LoginPage
        homePage.goToLoginPage();

        //Login to N11
        loginPage.loginToN11("onur@swtestacademy.com", "11223344");

        //*************ASSERTIONS***********************
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"loginForm\"]/div[2]/div/div ")));
        loginPage.verifyLoginPassword(("E-posta adresiniz veya şifreniz hatalı"));
    }

    @Test (priority = 1, description="Invalid Login Scenario with empty username and password.")
    public void invalidLoginTest_EmptyUserEmptyPassword (Method method)  {
        //ExtentReports Description
        ExtentTestManager.startTest(method.getName(),"Invalid Login Scenario with empty username and password.");

        //*************PAGE INSTANTIATIONS*************
        HomePage homePage = new HomePage(driver,wait);
        LoginPage loginPage = new LoginPage(driver,wait);

        //*************PAGE METHODS********************
        homePage.goToN11();
        homePage.goToLoginPage();
        loginPage.loginToN11("","");

        //*************ASSERTIONS***********************
        wait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"loginForm\"]/div[2]/div/div ")));
        loginPage.verifyLoginUserName("Lütfen e-posta adresinizi girin.");
        loginPage.verifyLoginPassword("WRONG MESSAGE FOR FAILURE!");
    }

}
