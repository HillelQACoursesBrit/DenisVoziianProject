package org.denis.testHW;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyAccountLoginTest extends BaseTest {

    String usernameCorrect = "kiltacotro@enayu.com";
    String usernameInCorrect = "incorrectUsername";
    String passwordCorrect = "kiltacotro@enayu.com";
    String passwordInCorrect = "incorrectPassword";

    @BeforeMethod
    public void BeforeMethod(){
        homePage.goToMyAccount();
    }

    @Test (priority = 1)
    public void loginPageSuccessfulLogin() {
//        myAccountPage.enterUsername(usernameCorrect)
//                .enterPassword(passwordCorrect)
//                .clickOnLoginButton();
        myAccountPage.login(usernameCorrect, passwordCorrect);

        Assert.assertTrue(myAccountPage.checkLogoutButtonIsDisplayed());
    }

    @Test (priority = 2)
    public void loginWithIncorrectUsernameAndPassword() {
//        myAccountPage.enterUsername(usernameInCorrect)
//                .enterPassword(passwordInCorrect)
//                .clickOnLoginButton();
        myAccountPage.login(usernameInCorrect, passwordInCorrect);

        Assert.assertTrue(myAccountPage.checkErrorMessageIsDisplayed()
                && myAccountPage.checkLoginFormIsDisplayed());
    }

    @Test (priority = 3)
    public void loginWithCorrectUsernameAndEmptyPassword() {
//        myAccountPage.enterUsername(usernameCorrect)
//                .enterPassword("")
//                .clickOnLoginButton();
        myAccountPage.login(usernameCorrect, "");

        Assert.assertTrue(myAccountPage.checkErrorMessageIsDisplayed()
                && myAccountPage.checkLoginFormIsDisplayed());
    }

    @Test (priority = 4)
    public void loginWithIncorrectUsernameAndCorrectPassword() {
//        myAccountPage.enterUsername("")
//                .enterPassword(passwordCorrect)
//                .clickOnLoginButton();
        myAccountPage.login("", passwordCorrect);

        Assert.assertTrue(myAccountPage.checkErrorMessageIsDisplayed()
                && myAccountPage.checkLoginFormIsDisplayed());
    }

    @Test (priority = 5)
    public void loginPasswordIsNotVisible(){
        Assert.assertTrue(myAccountPage.checkPasswordIsHidden(passwordInCorrect));
    }

}
