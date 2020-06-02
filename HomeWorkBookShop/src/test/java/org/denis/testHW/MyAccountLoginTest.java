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

    @Test
    public void loginPageSuccessfulLogin() {
        myAccountPage.login(usernameCorrect, passwordCorrect);

        Assert.assertTrue(myAccountPage.checkLogoutButtonIsDisplayed());
    }

    @Test
    public void loginWithIncorrectUsernameAndPassword() {
        myAccountPage.login(usernameInCorrect, passwordInCorrect);

        Assert.assertTrue(myAccountPage.checkErrorMessageIsDisplayed()
                && myAccountPage.checkLoginFormIsDisplayed());
    }

    @Test
    public void loginWithCorrectUsernameAndEmptyPassword() {
        myAccountPage.login(usernameCorrect, "");

        Assert.assertTrue(myAccountPage.checkErrorMessageIsDisplayed()
                && myAccountPage.checkLoginFormIsDisplayed());
    }

    @Test
    public void loginWithIncorrectUsernameAndCorrectPassword() {
        myAccountPage.login("", passwordCorrect);

        Assert.assertTrue(myAccountPage.checkErrorMessageIsDisplayed()
                && myAccountPage.checkLoginFormIsDisplayed());
    }

    @Test
    public void loginPasswordIsNotVisible(){
        Assert.assertTrue(myAccountPage.checkPasswordIsHidden(passwordInCorrect));
    }

}
