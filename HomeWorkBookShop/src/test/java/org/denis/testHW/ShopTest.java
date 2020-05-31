package org.denis.testHW;

import org.testng.Assert;
import org.testng.annotations.Test;

//4) Click any of the product links available in the product category
//5) Now user can view only that particular product

public class ShopTest extends BaseTest {

    @Test
    public void shopTestCheckFilterByPriceFunctionality() {
        homePage.goToShop();
        int startPrice = 150;
        int endPrice = 450;

        shopPage.filterPriceBySlider(startPrice, endPrice);
        Assert.assertTrue(shopPage.checkPriceResultByFilter(startPrice, endPrice));
    }

    @Test
    public void shopTestCheckFilterByCategoryFunctionality() throws InterruptedException {
        homePage.goToShop();
        String categoryName = shopPage.getCategoryName();
        System.out.println(categoryName);
        shopPage.chooseCategory(categoryName);
        shopPage.chooseRandomProduct();

        Thread.sleep(5000);
    }


}
