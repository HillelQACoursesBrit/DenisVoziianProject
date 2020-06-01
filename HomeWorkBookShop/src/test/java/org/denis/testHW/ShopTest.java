package org.denis.testHW;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShopTest extends BaseTest {
    @BeforeMethod
    public void BeforeMethod(){
        homePage.goToShop();
    }

    @Test (priority = 1)
    public void shopTestCheckFilterByPriceFunctionality() {

        int startPrice = 150;
        int endPrice = 450;

        shopPage.filterPriceBySlider(startPrice, endPrice);

        Assert.assertTrue(shopPage.checkPriceResultByFilter(startPrice, endPrice));
    }

    @Test (priority = 2)
    public void shopTestCheckFilterByCategoryFunctionality(){
        String categoryName = shopPage.getCategoryName();
        shopPage.chooseCategory(categoryName);
        categoryPage.chooseRandomProduct();

        Assert.assertTrue(productPage.checkComplianceSelectedCategory(categoryName));
    }

    @Test (priority = 3) //"ASC"
    public void shopTestSortingLowToHigh() {
        shopPage.chooseSortBy("Sort by price: low to high");

        Assert.assertTrue( shopPage.checkSortBy("ASC"));
    }

    @Test (priority = 4) //"DESC"
    public void shopTestSortingHighToLow() {
        shopPage.chooseSortBy("Sort by price: high to low");

        Assert.assertTrue( shopPage.checkSortBy("DESC"));
    }

    @Test (priority = 5)
    public void shopTestReadMore() {
        shopPage.clickReadMore();

        Assert.assertTrue(productPage.checkDisplayedOutOfStockTag());
    }
}
