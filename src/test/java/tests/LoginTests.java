package tests;

import java.lang.reflect.Method;
import org.testng.annotations.Test;
import utils.ExtentReports.ExtentTestManager;

public class LoginTests extends BaseTest {

    // Extra information:
    // 1) @BeforeClass we declared driver variable.
    // 2) We send driver variable to the page class with below declaration.
    //    Homepage homepage = new HomePage(driver);
    // 3) super () method in page class transfer the driver variable value to the BasePage class.

    @Test(priority = 0, description = "Invalid Login Scenario with wrong username and password.")
    public void invalidLoginTest_InvalidUserNameInvalidPassword(Method method) {
        //ExtentReports Description
        ExtentTestManager.startTest(method.getName(), "Invalid Login Scenario with empty username and password.");

        homePage
            .goToN11()
            .goToLoginPage()
            .loginToN11("onur@swtestacademy.com", "11223344")
            .verifyLoginPassword("E-posta adresiniz veya şifreniz hatalı");
    }

    @Test(priority = 1, description = "Invalid Login Scenario with empty username and password.")
    public void invalidLoginTest_EmptyUserEmptyPassword(Method method) {
        //ExtentReports Description
        ExtentTestManager.startTest(method.getName(), "Invalid Login Scenario with empty username and password.");

        homePage
            .goToN11()
            .goToLoginPage()
            .loginToN11("", "")
            .verifyLoginUserName("Lütfen e-posta adresinizi girin.")
            .verifyLoginPassword("WRONG MESSAGE FOR FAILURE!");
    }
}