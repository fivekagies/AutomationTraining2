import io.appium.java_client.AppiumDriver;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DataReader;
import webPages.dropBox.LandingPage;
import webPages.dropBox.SignUpPage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class AppiumTest {

    WebDriver driver;

    DataReader dataReader = new DataReader();

    @Before
    public void setUp() throws MalformedURLException {

        File app = new File("C:\\Users\\yknit\\Downloads\\apk\\Dropbox-App.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("app", "PRIVATE:1645537179019_Dropbox-App.apk");



        String securityToken = null;
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


        driver = new RemoteWebDriver(new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
        //driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), capabilities);



    }

    @Test
    public void firstTest(){
        LandingPage landingPage = new LandingPage(driver);

        landingPage.clickSignUp();


        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpForm();


    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
