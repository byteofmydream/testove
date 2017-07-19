package com.kantora.testove.POs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.DriverWraper.getDriver;

public class RegistrationPO extends BasePO{

    private final By registrationForm = By.xpath("//*[@id='signup_form']");
    private final By name= By.xpath(".//input[@name='title']");
    private final By email= By.xpath(".//input[@name='email']");
    private final By password= By.xpath(".//input[@type='password']");
    private final By submit= By.xpath(".//button[@type='submit']");



    public static final String URL = "https://my.rozetka.com.ua/signup/";

    public RegistrationPO(){
        super(URL);
    }

    public RegistrationPO enterRegistrationData(String usrName, String usrEmail, String usrPassword){
        getDriver().findElement(registrationForm).findElement(name).sendKeys(usrName);
        getDriver().findElement(registrationForm).findElement(email).sendKeys(usrEmail);
        getDriver().findElement(registrationForm).findElement(password).sendKeys(usrPassword);
        return this;
    }

    public RegistrationPO submitRegistration(){
        getDriver().findElement(registrationForm).findElement(submit).click();
        return this;
    }



}
