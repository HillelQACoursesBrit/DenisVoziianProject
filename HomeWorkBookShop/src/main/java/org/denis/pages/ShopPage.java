package org.denis.pages;

import org.denis.driver.DriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ShopPage extends AbstractPage {
    @FindBy(xpath = "//*[text()='Home']")
    WebElement buttonHome;

    @FindBy(xpath = "//*[@class='price_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all']/span")
    List<WebElement> priceSlider;

    @FindBy(xpath = "//*[@class='price_label']/span")
    List<WebElement> priceLabel;

    @FindBy(xpath = "//button[text()='Filter']")
    WebElement filterButton;

    @FindBy(css = ".price > *:last-child")
    List<WebElement> productActualPrice;

    @FindBy(xpath = "//*[@class='product-categories']/li/a")
    List<WebElement> productCategoriesList;

    @FindBy(xpath = "*//a[text()='Read more']")
    List<WebElement> readMoreLink;

    @FindBy(xpath = "//*[@class='orderby']")
    WebElement sortList;

    Actions action = new Actions(DriverHolder.getINSTANCE().getDriver());

    public boolean checkSortBy(String typePriceSort) {
        List<Integer> priceListInt = actualProductPriceList();
        List<Integer> priceListIntSort = actualProductPriceList();
        sortList(typePriceSort, priceListIntSort);
        return priceListInt.equals(priceListIntSort);
    }

    public HomePage chooseSortBy(String sortType) {
        Select select = new Select(sortList);
        select.selectByVisibleText(sortType);
        return new HomePage();
    }

    public CategoryPage chooseCategory(String categoryName) {
        for (WebElement webElement : productCategoriesList) {
            if (webElement.getText().equalsIgnoreCase(categoryName)) {
                webElement.click();
                break;
            }
        }
        return new CategoryPage();
    }

    public String getCategoryName() {
        return chooseRandomWebElementFromList(productCategoriesList).getText();
    }

    public List<Integer> actualProductPriceList() {
        List<Integer> allPriceList = new ArrayList<>();
        allPriceList.addAll(convertPriceToInt(productActualPrice));
        return allPriceList;
    }

    public List<Integer> convertPriceToInt(List<WebElement> allPriceList) {
        List<Integer> allIntegerPriceList = new ArrayList<>();
        for (WebElement webElement : allPriceList) {
            allIntegerPriceList.add(Integer.parseInt(webElement.getText()
                    .substring(1, webElement.getText().length() - 3)));
        }
        return allIntegerPriceList;
    }

    public boolean checkPriceResultByFilter(int startPrice, int endPrice) {
        boolean conditionPrice = true;
        List<Integer> allPriceList = actualProductPriceList();
        for (Integer price : allPriceList) {
            if (price < startPrice
                    || price > endPrice) {
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

    public ProductPage clickReadMore() {
        chooseRandomWebElementFromList(readMoreLink).click();
        return new ProductPage();
    }

    public ShopPage() {
        PageFactory.initElements(DriverHolder.getINSTANCE().getDriver(), this);
    }
}
