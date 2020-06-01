package org.denis.testHW;

import org.denis.driver.DriverHolder;
import org.denis.driver.DriverType;
import org.denis.pages.*;
import org.denis.utils.Constants;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    HomePage homePage;
    ShopPage shopPage;
    ProductPage productPage;
    BasketPage basketPage;
    MyAccountPage myAccountPage;
    CategoryPage categoryPage;

    @BeforeSuite
    public void beforeSuite() {
        DriverHolder.getINSTANCE().initDriver(DriverType.FIREFOX);
    }

    protected void goToUrl(String url) {
        DriverHolder.getINSTANCE().getDriver().get(url);
    }

    @BeforeMethod
    public void beforeMethod() {
        homePage = new HomePage();
        shopPage = new ShopPage();
        productPage = new ProductPage();
        basketPage = new BasketPage();
        myAccountPage = new MyAccountPage();
        categoryPage = new CategoryPage();
        goToUrl(Constants.baseURL);
    }

    @AfterMethod
    public void afterMethod() {
        DriverHolder.getINSTANCE().getDriver().manage().deleteAllCookies();
    }

    @AfterSuite
    public void afterSuite() {
        DriverHolder.getINSTANCE().getDriver().quit();
    }

}
