package webPages.nespressoMaroc;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;

public class CapsulesPage {
    WebDriver driver;

    WebDriverWait wait;

    @FindBy(id = "_evidon-decline-button")
    WebElement cookieBtn;


    WebElement productTitle;

    WebElement addToBasketBtn;


    @FindBy(xpath = "//input[@id='ta-quantity-selector__custom-field']")
    WebElement quantityField;

    @FindBy(xpath = "//button[@id='ta-quantity-selector__custom-ok']")
    WebElement confirmQuantityBtn;

    public CapsulesPage(WebDriver driver){
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean verifyProduct(String productName){
        try {
            System.out.println("about to click coockie");
            driver.findElement(By.id("_evidon-decline-button")).click();
            //cookieBtn.click();
            System.out.println("coockie clicked");
            productTitle = driver.findElement(By.xpath("//a[text()='"+ productName +"']"));
            System.out.println("product found");
            String getProductTitle = productTitle.getText();

            return productName.toLowerCase(Locale.ROOT).equals(getProductTitle.toLowerCase(Locale.ROOT));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void addtoBasketAndSelectQuantity(String productName){

        cookieBtn.click();
        //a[text()=' Miami Espresso ']/ancestor::div[contains(@class, 'product-item-details')]/div[2]
        ////div[@class='showcart-wrapper']  cart

        addToBasketBtn = driver.findElement(By.xpath("a[text()=' Miami Espresso ']/ancestor::div[contains(@class, 'product-item-details')]/div[2]"));


        wait = new WebDriverWait(driver, 30);

        try{
            // wait for addToBasketBtn to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(addToBasketBtn)).click();
        }
        catch (TimeoutException e){
            System.err.println("addToBasketBtn not yet clickable");

        }


    }

    public void addQuantity(String quantity){

        wait = new WebDriverWait(driver, 30);
        try {
            // wait for the quantity field to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ta-quantity-selector__custom-field']")));
        }
        catch (TimeoutException e){
            System.err.println("quantity input field not yet visible");

        }

        quantityField.sendKeys(quantity);

        confirmQuantityBtn.click();
    }
}
