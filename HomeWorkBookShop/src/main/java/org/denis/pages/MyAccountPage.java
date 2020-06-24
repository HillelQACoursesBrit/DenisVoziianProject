package org.denis.pages;

import org.denis.driver.DriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    @FindBy(id = "username")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@name='login']")
    WebElement loginButton;

    @FindBy(xpath = "//*[text()='Logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//*[@class='woocommerce-error']")
    WebElement errorMessage;

    @FindBy(xpath = "//*[@class='login']")
    WebElement loginForm;

    public MyAccountPage login(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();
        return this;
    }

    public MyAccountPage enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
        return this;
    }

    public MyAccountPage enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public boolean checkPasswordIsHidden(String password) {
        enterPassword(password);
        return passwordInput.getText().equalsIgnoreCase("")
                && passwordInput.getAttribute("type").equalsIgnoreCase("password");
    }

    public MyAccountPage clickOnLoginButton(){
        loginButton.click();
        return this;
    }

    public boolean checkLogoutButtonIsDisplayed(){
        return logoutButton.isDisplayed();
    }

    public boolean checkLoginFormIsDisplayed(){
        return loginForm.isDisplayed();
    }
    public boolean checkErrorMessageIsDisplayed(){
        return errorMessage.isDisplayed();
    }

    public MyAccountPage() {
        PageFactory.initElements(DriverHolder.getINSTANCE().getDriver(), this);
    }

}
