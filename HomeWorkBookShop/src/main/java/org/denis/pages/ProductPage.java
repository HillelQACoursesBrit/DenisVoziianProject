package org.denis.pages;

import org.denis.driver.DriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
    @FindBy(xpath = "//*[text()='Add to basket']")
    WebElement addToBasketButton;

    @FindBy(xpath = "//*[text()='Description']")
    WebElement descriptionTab;

    @FindBy(xpath = "//*[@itemprop='description']")
    WebElement descriptionBook;

    @FindBy(xpath = "//*[@id='tab-description']//p")
    WebElement descriptionTabText;

    @FindBy(className = "reviews_tab")
    WebElement reviewsTab;

    @FindBy(xpath = "//*[@class='product_title entry-title']")
    WebElement productTitle;

    @FindBy(xpath = "//p//*[@class='woocommerce-Price-amount amount']")
    List<WebElement> productPrice;

    @FindBy(xpath = "//*[@id='reply-title']")
    WebElement reviewTitle;

    @FindBy(className = "woocommerce-message")
    WebElement bookAddedToBasketMessage;

    @FindBy(css = ".woocommerce-message a.button")
    WebElement viewBasketButton;

    @FindBy(className = "cartcontents")
    WebElement cartQty;

    @FindBy(className = "input-text qty text")
    WebElement quantityInput;

    @FindBy(id = "wpmenucartli")
    WebElement cartLink;

    @FindBy(xpath = "//*[@class='posted_in']/a")
    WebElement categoryNameTag;

    @FindBy(xpath = "//*[@class='stock out-of-stock']")
    WebElement outOfStockTag;

    public boolean checkComplianceSelectedCategory(String categoryName){
        return categoryNameTag.getText().equalsIgnoreCase(categoryName)
                && descriptionTabText.getText().toUpperCase().contains(categoryName.toUpperCase());
    }

    public BasketPage clickOnCartLink() {
        cartLink.click();
        return new BasketPage();
    }

    public boolean addToBasketButtonIsDisplayed() {
        return addToBasketButton.isDisplayed();
    }

    public boolean bookAddedToBasketMessageIsDisplayed() {
        return bookAddedToBasketMessage.isDisplayed();
    }

    public boolean viewBasketButtonIsDisplayed() {
        return viewBasketButton.isDisplayed();
    }

    public ProductPage clickOnReviewsTab() {
        reviewsTab.click();
        return this;
    }

    public ProductPage clickOnDescriptionTab() {
        descriptionTab.click();
        return this;
    }

    public ProductPage clickOnAddToBasketButton() {
        addToBasketButton.click();
        return this;
    }

    public boolean compareDescription() {
        return descriptionTabText.getText().contains(descriptionBook.getText());
    }

    public boolean compareReviewTitleAndProductTitle() {
        return reviewTitle.getText().contains(productTitle.getText());
    }

    public String getProductNameOnProdPage() {
        return productTitle.getText();
    }

    public boolean checkDisplayedOutOfStockTag() {
        return outOfStockTag.isDisplayed();
    }

    public String getProductPriceOnBProdPage() {
        if (productPrice.size() > 1) {
            return productPrice.get(1).getText();
        } else {
            return productPrice.get(0).getText();
        }
    }

    public ProductPage() {
        PageFactory.initElements(DriverHolder.getINSTANCE().getDriver(), this);
    }

}
