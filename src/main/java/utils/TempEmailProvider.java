package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static utils.DriverWraper.getDriver;

public class TempEmailProvider {

    public static String getTempEmail() {
        getDriver().get("https://temp-mail.ru/api");
        WebElement we = getDriver().findElement(By.id("mail"));
        return we.getAttribute("value");
    }
}
