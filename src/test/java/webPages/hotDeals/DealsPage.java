package webPages.hotDeals;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webPages.nespresso.BasePage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DealsPage {
    AppiumDriver driver;


    TouchAction action;
    int middleOfX;
    int startYCoordinate;
    int endYCoordinate;

    public DealsPage(AppiumDriver driver){
        this.driver = driver;
        action =new TouchAction(driver);
        PageFactory.initElements(driver, this);
        takeCoordinatesForScroll();
    }

    public void takeCoordinatesForScroll(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[1]")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[4]")));


        }
        catch (TimeoutException e){
            System.err.println("RecyclerView not visible");
        }

        WebElement source = driver.findElement(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[1]"));
        WebElement target = driver.findElement(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[4]"));

        Rectangle sourceRect = source.getRect();
        Rectangle targetRect = target.getRect();
        middleOfX = sourceRect.x + (sourceRect.width / 2);
        startYCoordinate= (int)(targetRect.y + (targetRect.height * 0.9));
        endYCoordinate= sourceRect.y;
    }

    public void scrollDown(){
        for(int scrolls=0;scrolls<10;scrolls++){
            System.out.println("searching for jumia mali deals Num : " + scrolls);

            scroll(middleOfX, startYCoordinate, middleOfX, endYCoordinate);


            WebElement element = BasePage.findElementSafe(driver,By.xpath("//*[contains(@resource-id, 'rTitleTv') and contains(@text, 'Jumia Deals Mali')]"));
            if(element != null){
                System.out.println("heeeere jumia deals mali");
                break;
            }
        }

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void scrollUp(){
        for(int scrolls=0;scrolls<10;scrolls++){

            scroll(middleOfX, endYCoordinate, middleOfX, startYCoordinate);


            WebElement element = BasePage.findElementSafe(driver,By.xpath("//*[contains(@resource-id, 'rTitleTv') and contains(@text, 'Jumia Black Friday 2020')]"));
            if(element != null){
                System.out.println("heeeere black friday deals");
                break;
            }
        }
    }
    public void ScrollDownAndUp(){

        WebDriverWait wait = new WebDriverWait(driver, 30);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[1]")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[4]")));


        }
        catch (TimeoutException e){
            System.err.println("RecyclerView not visible");
        }

        WebElement source = driver.findElement(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[1]"));
        WebElement target = driver.findElement(By.xpath("//*[contains(@resource-id, 'recyclerView')]/android.widget.FrameLayout[4]"));

        Rectangle sourceRect = source.getRect();
        Rectangle targetRect = target.getRect();
        int middleOfX = sourceRect.x + (sourceRect.width / 2);
        int startYCoordinate= (int)(targetRect.y + (targetRect.height * 0.9));
        int endYCoordinate= sourceRect.y;

        for(int scrolls=0;scrolls<15;scrolls++){

            System.out.println("searching for jumia mali deals Num : " + scrolls);
            scroll(middleOfX, startYCoordinate, middleOfX, endYCoordinate);


            WebElement element = BasePage.findElementSafe(driver,By.xpath("//*[contains(@resource-id, 'rTitleTv') and contains(@text, 'Jumia Deals Mali')]"));
            if(element != null){
                System.out.println("heeeere jumia deals mali");
                break;
            }

        }

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);



        // scroll up

        for(int scrolls=0;scrolls<10;scrolls++){

            scroll(middleOfX, endYCoordinate, middleOfX, startYCoordinate);


            WebElement element = BasePage.findElementSafe(driver,By.xpath("//*[contains(@resource-id, 'rTitleTv') and contains(@text, 'Jumia Black Friday 2020')]"));
            if(element != null){
                System.out.println("heeeere black friday deals");
                break;
            }
        }


    }

    public void scroll(int startX, int startY, int endX, int endY){
//        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//        Sequence dragNDrop = new Sequence(finger, 1);
//        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
//                PointerInput.Origin.viewport(), startX, startY));
//        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
//                PointerInput.Origin.viewport(),endX, endY));
//        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//
//        ((AppiumDriver)driver).perform(Arrays.asList(dragNDrop));
        action.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }
}
