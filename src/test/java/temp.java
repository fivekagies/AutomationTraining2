import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DataReader;


public class temp {

    public static void main(String[] args) throws Exception {

        //DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions capabilities=new ChromeOptions();


        capabilities.addArguments("--disable-blink-features=AutomationControlled");

        capabilities.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

//        capabilities.setCapability("useAppiumForWeb", true);
//        capabilities.setCapability("browserName", "Chrome");
//
//        String securityToken = null;
//        DataReader dataReader = new DataReader();
//        try {
//            securityToken = dataReader.propertiesReader("securityToken");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        capabilities.setCapability("securityToken", securityToken);
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("platformVersion", "9");
//        capabilities.setCapability("manufacturer", "Samsung");
//        capabilities.setCapability("model", "Galaxy S10\\+");
//
//        capabilities.setCapability("enableAppiumBehavior", true); // Enable the new Appium Architecture.
//        capabilities.setCapability("autoLaunch", true); // Whether to install and launch the app automatically.
//        capabilities.setCapability("autoInstrument", true); // To work with hybrid applications, install the iOS/Android application as instrumented.
//        capabilities.setCapability("takesScreenshot", true);
//        capabilities.setCapability("screenshotOnError", true);
//        capabilities.setCapability("waitForAvailableLicense", true);

        URL url = new URL("http://localhost:4723/wd/hub");
        //URL url = new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub");
        AppiumDriver driver = new AppiumDriver(url, capabilities);

        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        System.out.println("geting url");
        driver.get("https://www.google.com");

        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("appium");

        Thread.sleep(5000);

        driver.quit();



    }


}
