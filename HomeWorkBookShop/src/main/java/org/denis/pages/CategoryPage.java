package org.denis.pages;

import org.denis.driver.DriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CategoryPage extends AbstractPage{
    @FindBy(xpath = "//*[@class='products masonry-done']//img")
    List<WebElement> productList;

    public ProductPage chooseRandomProduct() {
        chooseRandomWebElementFromList(productList);
        return new ProductPage();
    }

    public CategoryPage() {
        PageFactory.initElements(DriverHolder.getINSTANCE().getDriver(), this);
    }

}
