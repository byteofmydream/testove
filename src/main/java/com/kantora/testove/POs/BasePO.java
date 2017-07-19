package com.kantora.testove.POs;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.DriverWraper.getDriver;

public abstract class BasePO {

    private final By closePopup= By.xpath("//div[@class='notificationPanelCross']");

    public BasePO closePopup(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(closePopup));
        getDriver().findElement(closePopup).click();
        return this;
    }

    public BasePO(String URL){
        getDriver().get(URL);
    }
}
