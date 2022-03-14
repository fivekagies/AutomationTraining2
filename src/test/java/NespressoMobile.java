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
import util.DataReader;
import webPages.amazon.HomePage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class NespressoMobile {

    WebDriver driver;
    String platformType = "emulator";
    URL url;

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
        if(platformType.equals("emulator")){
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

            url = new URL("http://localhost:4723/wd/hub");
        }
        else {
            capabilities.setCapability("useAppiumForWeb", true);
            capabilities.setCapability("browserName", "Chrome");

            String securityToken = null;
            DataReader dataReader = new DataReader();
            try {
                securityToken = dataReader.propertiesReader("securityToken");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            capabilities.setCapability("securityToken", securityToken);
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "9");
            capabilities.setCapability("manufacturer", "Samsung");
            capabilities.setCapability("model", "Galaxy S10\\+");

            capabilities.setCapability("enableAppiumBehavior", true); // Enable the new Appium Architecture.
            capabilities.setCapability("autoLaunch", true); // Whether to install and launch the app automatically.
            capabilities.setCapability("autoInstrument", true); // To work with hybrid applications, install the iOS/Android application as instrumented.
            capabilities.setCapability("takesScreenshot", true);
            capabilities.setCapability("screenshotOnError", true);
            capabilities.setCapability("waitForAvailableLicense", true);
            url = new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub");
        }



        driver = new AppiumDriver<AndroidElement>(url, capabilities);

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void firstTest(){

        System.out.println("geting url");
        try
        {
            driver.get("https://ma.buynespresso.com/");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("seaching for cookie btn");

         //driver.findElement(By.id("_evidon-decline-button")).click();
        //driver.findElement(By.id("sp-cc-accept")).click();
        //System.out.println(driver.findElement(By.id("_evidon-decline-button")).isDisplayed() + "");


        System.out.println("cookie found");


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
