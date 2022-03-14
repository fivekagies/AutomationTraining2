import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import webPages.amazon.HomePage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TempTest {

    WebDriver driver;
    @Before
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options=new ChromeOptions();


        //options.addArguments("--disable-web-security");
        //options.addArguments("--allow-insecure-localhost");
        options.addArguments("--disable-blink-features=AutomationControlled");

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AppiumDriver<AndroidElement>(url, capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }


    @Test
    public void firstTest(){

//        driver.get("https://www.nespresso.com/fr/en/order/capsules/original/");
        driver.get("https://www.amazon.fr");
        System.out.println("seaching for cookie btn");

       // driver.findElement(By.id("_evidon-decline-button")).click();
        //driver.findElement(By.id("sp-cc-accept")).click();
        //System.out.println(driver.findElement(By.id("_evidon-decline-button")).isDisplayed() + "");


        System.out.println("cookie found");
                HomePage homePage = new HomePage(driver, true);
        Assert.assertTrue(homePage.isPageOpened());



        homePage.searchForProduct();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @After
    // @AfterEAch in JUnit 5
    public void tearDown() {
        // this method will be executed after each test method
        driver.quit();
    }
}
