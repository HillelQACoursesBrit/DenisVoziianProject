package org.denis.pages;

import org.denis.driver.DriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractPage {
    @FindBy(xpath = "//*[text()='Shop']")
    WebElement shopButton;

    @FindBy(xpath = "//*[@class='products']")
    List<WebElement> arrivalsList;

    @FindBy(xpath = "//*[@class='products']//img")
    List<WebElement> arrivalsImgList;

    @FindBy(xpath = "//*[text()='My Account']")
    WebElement myAccountButton;

    public ShopPage goToShop() {
        shopButton.click();
        return new ShopPage();
    }

    public MyAccountPage goToMyAccount() {
        myAccountButton.click();
        return new MyAccountPage();
    }

    public ProductPage goToProductRandom() {
        chooseRandomWebElementFromList(arrivalsList).click();
        return new ProductPage();
    }

    public ProductPage goToProduct(int index) {
        arrivalsImgList.get(index).click();
        return new ProductPage();
    }

    public int countArrivals() {
        return arrivalsList.size();
    }

    public HomePage() {
        PageFactory.initElements(DriverHolder.getINSTANCE().getDriver(), this);
    }
}
