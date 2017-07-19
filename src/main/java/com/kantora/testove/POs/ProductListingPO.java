package com.kantora.testove.POs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;

import static utils.DriverWraper.getDriver;

public class ProductListingPO extends BasePO {
    private By searchField = By.cssSelector("input[name=text]");

    private By iPhone7Element = By.xpath("//*[@class='g-i-tile-i-title clearfix']/a[contains(.,\"Apple iPhone 7\")][not(contains(.,\"Plus\"))]");
    private By iPhone7PlusElement = By.xpath("//*[@class='g-i-tile-i-title clearfix']/a[contains(.,\"Apple iPhone 7 Plus\")]");

    public ProductListingPO() {
        super("https://rozetka.com.ua/");
    }

    public ProductListingPO searchForItem(String text) {
        getDriver().findElement(searchField).sendKeys(text);
        getDriver().findElement(searchField).sendKeys(Keys.RETURN);
        return this;
    }

    public String getIPhone7Details() {
        return getItemDetails(iPhone7Element);
    }

    public String getIPhone7PlusDetails() {
        return getItemDetails(iPhone7PlusElement);
    }

    private String getItemDetails(By by) {
        WebElement we = getDriver().findElement(by);
        return ((JavascriptExecutor) getDriver())
                .executeScript("return arguments[0].getParent().getParent().getElementsByClassName(\"g-i-tile-short-detail\")[0].children[0].innerHTML", we)
                .toString();
    }

    public BigDecimal getIPhone7Price() {
        return getItemPrice(iPhone7Element);
    }

    public BigDecimal getIPhone7PlusPrice() {
        return getItemPrice(iPhone7PlusElement);
    }

    private BigDecimal getItemPrice(By by) {
        WebElement we = getDriver().findElement(by);
        return new BigDecimal(((JavascriptExecutor) getDriver())
                .executeScript("return arguments[0].getParent().getParent().getElementsByClassName(\"g-price-uah\")[0].querySelector('[id]').innerHTML", we)
                .toString().replaceAll(" ", ""));
    }

}
