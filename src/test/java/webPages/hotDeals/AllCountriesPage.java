package webPages.hotDeals;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllCountriesPage {

    WebDriver driver;


    @FindBy(xpath = "//*[contains(@resource-id, 'CardViewText')]")
    WebElement moroccoBtn;

    public AllCountriesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickViewAllCountries(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try{

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id, 'CardViewText')]"))).click();


        }
        catch (TimeoutException e){
            System.err.println("ViewAllCOuntries Btn not visible");
        }

    }
}
