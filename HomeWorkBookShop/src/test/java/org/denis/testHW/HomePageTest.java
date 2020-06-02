package org.denis.testHW;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageContainsOnlyThreeArrivals() {
        homePage.goToShop();
        shopPage.goToHome();

        Assert.assertEquals(homePage.countArrivals(), 3);
    }

    @Test
    public void homePageNavigateToProductPage() {
        homePage.goToProductRandom();

        Assert.assertTrue(productPage.addToBasketButtonIsDisplayed());
    }

    @Test
    public void homePageDescriptionRegardingBookClickedOn() {
        homePage.goToProductRandom();
        productPage.clickOnDescriptionTab();

        Assert.assertTrue(productPage.compareDescription());
    }

    @Test
    public void homePageReviewsRegardingBookClickedOn() {
        homePage.goToProductRandom();
        productPage.clickOnReviewsTab();

        Assert.assertTrue(productPage.compareReviewTitleAndProductTitle());
    }

    @Test
    public void homePageAddBookToBasket() {
        homePage.goToProductRandom();
        productPage.clickOnAddToBasketButton();

        Assert.assertTrue(productPage.bookAddedToBasketMessageIsDisplayed());
        Assert.assertTrue(productPage.viewBasketButtonIsDisplayed());
    }

    @Test
    public void homePageVerifyBasket() {
        homePage.goToProduct(1);

        String productNameOnProdPage = productPage.getProductNameOnProdPage();
        String productPriceOnProdPage = productPage.getProductPriceOnBProdPage();

        productPage.clickOnAddToBasketButton()
                .clickOnCartLink();

        Assert.assertEquals(productNameOnProdPage, basketPage.getProductName());
        Assert.assertEquals(productPriceOnProdPage, basketPage.getProductPrice());
    }
}
