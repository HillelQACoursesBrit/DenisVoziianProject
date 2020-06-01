package org.denis.pages;

import org.denis.driver.DriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketPage extends AbstractPage{
    @FindBy(css = "td.product-name")
    WebElement productName;

    @FindBy(css = "td.product-price")
    WebElement productPrice;

    public String getProductName(){
        return productName.getText();
    }

    public String getProductPrice(){
        return productPrice.getText();
    }

    public BasketPage() {
        PageFactory.initElements(DriverHolder.getINSTANCE().getDriver(), this);
    }
}

