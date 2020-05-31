package org.denis.pages;

import org.denis.driver.DriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopPage {
    @FindBy(xpath = "//*[text()='Home']")
    WebElement buttonHome;

    @FindBy(xpath = "//*[@class='price_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all']/span")
    List<WebElement> priceSlider;

    @FindBy(xpath = "//*[@class='price_label']/span")
    List<WebElement> priceLabel;

    @FindBy(xpath = "//button[text()='Filter']")
    WebElement filterButton;

    @FindBy(xpath = "//*[@class='price']/span")
    List<WebElement> productPriceWithOutDiscount;

    @FindBy(xpath = "//*[@class='price']/ins/span")
    List<WebElement> productPriceWithDiscount;

    @FindBy(xpath = "//*[@class='product-categories']/li/a")
    List<WebElement> productCategoriesList;

    @FindBy(xpath = "//*[@class='products masonry-done']/li")
    List<WebElement> productList;

    Actions action = new Actions(DriverHolder.getINSTANCE().getDriver());

    public ShopPage chooseCategory(String categoryName) {
        for (WebElement webElement : productCategoriesList) {
            if (webElement.getText().contains(categoryName)){
                webElement.click();
            }
        }
        return new ShopPage();
    }

    public int chooseRandomProduct() {
        Random randomizer = new Random();
        int random = randomizer.nextInt(productList.size());
        productList.get(random).click();
        return random;
    }

    public String getCategoryName() {
        Random randomizer = new Random();
        return productCategoriesList.get(randomizer.nextInt(productCategoriesList.size())).getText();
    }

    public List<WebElement> actualProductPriceList() {
        List<WebElement> allPriceList = new ArrayList<>();
        allPriceList.addAll(productPriceWithDiscount);
        allPriceList.addAll(productPriceWithOutDiscount);
        return allPriceList;
    }

    public boolean checkPriceResultByFilter(int startPrice, int endPrice) {
        boolean conditionPrice = true;
        List<WebElement> allPriceList = actualProductPriceList();
        for (WebElement webElement : allPriceList) {
            Integer actualPriceByInt = Integer.parseInt(webElement.getText()
                    .substring(1, webElement.getText().length() - 3));
            if (actualPriceByInt < startPrice
                    && actualPriceByInt > endPrice) {
                conditionPrice = false;
                break;
            }
        }
        return conditionPrice;
    }

    public HomePage moveStartPriceOnSlider(int startPrice) {
        int startPriceLabel = Integer.parseInt(priceLabel.get(0).getText().substring(1));
        for (; startPriceLabel < startPrice; ) {
            action.dragAndDropBy(priceSlider.get(0), 1, 0).build().perform();
            startPriceLabel = Integer.parseInt(priceLabel.get(0).getText().substring(1));
        }
        return new HomePage();
    }

    public HomePage moveEndPriceOnSlider(int endPrice) {
        int endPriceLabel = Integer.parseInt(priceLabel.get(1).getText().substring(1));
        for (; endPriceLabel > endPrice; ) {
            action.dragAndDropBy(priceSlider.get(1), -1, 0).build().perform();
            endPriceLabel = Integer.parseInt(priceLabel.get(1).getText().substring(1));
        }
        return new HomePage();
    }

    public HomePage filterPriceBySlider(int startPrice, int endPrice) {
        moveStartPriceOnSlider(startPrice);
        moveEndPriceOnSlider(endPrice);
        filterButton.submit();
        return new HomePage();
    }

    public HomePage goToHome() {
        buttonHome.click();
        return new HomePage();
    }

    public ShopPage() {
        PageFactory.initElements(DriverHolder.getINSTANCE().getDriver(), this);
    }
}
