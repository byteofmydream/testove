package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverWraper {
    private static WebDriver driver;

    private DriverWraper(){

    }

    private static void initDriver(){
        String driverPath = DriverWraper.class.getResource("/chromedriver").getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        if(driver==null) {
             initDriver();
        }
            return driver;
    }
}
