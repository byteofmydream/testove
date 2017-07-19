package com.kantora.testove.POs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static utils.DriverWraper.getDriver;

public class ConfirmMailPO extends BasePO {

    private final By email = By.cssSelector("#email_container [class=profile-info-l-i]");
    public ConfirmMailPO(String url){
        super(url);
    }

    public String getEmail(){
        return ((JavascriptExecutor) getDriver()).executeScript("return document.querySelector(\"#email_container [class=profile-info-l-i]\").innerHTML").toString();
    }

}
