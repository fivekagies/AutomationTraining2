package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URISyntaxException;

public class PropertySetter {

    public WebDriver webDriverSetter(){
        DataReader dataReader = new DataReader();

        try {
            System.setProperty(dataReader.propertiesReader("driver"), dataReader.propertiesReader("path"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ChromeOptions options=new ChromeOptions();


        options.addArguments("--disable-blink-features=AutomationControlled");

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        return new ChromeDriver(options);
    }
}
