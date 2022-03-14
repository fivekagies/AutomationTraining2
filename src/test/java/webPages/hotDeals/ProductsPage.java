package webPages.hotDeals;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    WebDriver driver;


    @FindBy(xpath = "//*[contains(@resource-id, 'nextBtn')]")
    WebElement nextBtn;

    public ProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickNext(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try{
            // wait for addToBasketBtn to be clickable
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id, 'nextBtn')]"))).click();


        }
        catch (TimeoutException e){
            System.err.println("next Btn not visible");
        }
        //nextBtn.click();
    }
}
