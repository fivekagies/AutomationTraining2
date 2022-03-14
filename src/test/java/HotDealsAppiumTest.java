import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.DataReader;
import webPages.hotDeals.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class HotDealsAppiumTest {

    AppiumDriver driver;

    DataReader dataReader = new DataReader();

    @Before
    public void setUp() throws MalformedURLException {

        // 1. Replace <<cloud name>> with your perfecto cloud name (for example, 'demo' is the cloudName of demo.perfectomobile.com).
        String cloudName = "trial";

        File app = new File("C:\\Users\\yknit\\Downloads\\apk\\HotDealsAndBlack Fridays_v11.0_apkpure.com 2.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();

//        capabilities.setCapability("deviceName", "device");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("app", app.getAbsolutePath());
////        capabilities.setCapability("appActivity", "net.chekitech.jumiadeals.WelcomeaActivity");
////        capabilities.setCapability("appPackage", "net.chekitech.jumiadeals");

        String securityToken = null;
        try {
            securityToken = dataReader.propertiesReader("securityToken");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        capabilities.setCapability("securityToken", securityToken);
//        capabilities.setCapability("user", "yknit@sqli.com");
//        capabilities.setCapability("password", "741258963Yy@");

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "9");
        capabilities.setCapability("manufacturer", "Samsung");
        capabilities.setCapability("model", "Galaxy S10\\+");
        capabilities.setCapability("waitForAvailableLicense", true);


        capabilities.setCapability("app", "PRIVATE:1645526628003_HotDealsAndBlack Fridays_v11.0_apkpure.com 2.apk");

        capabilities.setCapability("enableAppiumBehavior", true); // Enable the new Appium Architecture.
        capabilities.setCapability("autoLaunch", true); // Whether to install and launch the app automatically.
        capabilities.setCapability("autoInstrument", true); // To work with hybrid applications, install the iOS/Android application as instrumented.
        capabilities.setCapability("takesScreenshot", true);
        capabilities.setCapability("screenshotOnError", true);



        driver = new AppiumDriver<>(new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
        //driver = new AppiumDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), capabilities);



    }
    @Test
    public void firstTest(){

        ProductsPage productsPage = new ProductsPage(driver);

        productsPage.clickNext();
        productsPage.clickNext();
        productsPage.clickNext();

        ContinentPage continentPage = new ContinentPage(driver);
        continentPage.clickEurope();

        CountryPage countryPage = new CountryPage(driver);
        countryPage.clickMorocco();

        AllCountriesPage allCountriesPage = new AllCountriesPage(driver);
        allCountriesPage.clickViewAllCountries();

        DealsPage dealsPage = new DealsPage(driver);
        dealsPage.scrollDown();
        dealsPage.scrollUp();





    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
