package org.denis.pages;

import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AbstractPage {
    public WebElement chooseRandomWebElementFromList(List<WebElement> webElement) {
        Random randomizer = new Random();
        return webElement.get(randomizer.nextInt(webElement.size()));
    }

    public void sortList(String typePriceSort, List<Integer> priceListInt) {
        switch (typePriceSort) {
            case "ASC":
                Collections.sort(priceListInt);
                break;
            case "DESC":
                Collections.sort(priceListInt, Collections.reverseOrder());
                break;
        }
    }


}
