package webPages.nespresso;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class BasePage {
    WebDriver driver;

    Wait fluentWait;

    BasePage(WebDriver driver){
        this.driver = driver;
        this.fluentWait = new FluentWait(driver)
                .withTimeout(Duration.of(30, ChronoUnit.SECONDS))
                .pollingEvery(Duration.of(500, ChronoUnit.MILLIS)) // freq check < 1s
                .ignoring(NoSuchElementException.class);
    }

    public Wait getFluentWit(){
        return fluentWait;
    }

    public static WebElement findElementSafe(WebDriver driver, By by){
        try {
            return driver.findElement(by);
        }
        catch (Exception e){
            return null;
        }
    }
}
