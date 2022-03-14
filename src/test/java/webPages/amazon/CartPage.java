package webPages.amazon;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;
    //static final String PRODUCT_TITLE = "Selenium WebDriver Quick Start Guide: Write clear, readable, and reliable tests with Selenium WebDriver 3";


    WebElement productTitle;

//    @FindBy(xpath = "//span[@id='sc-buy-box-ptc-button']")
//    @FindBy(xpath = "//div[@id='sc-mini-buy-box']/descendant::button")
    WebElement buyBtn;

    boolean isMobile;


    public CartPage(WebDriver driver, boolean isMobile){
        this.driver = driver;
        this.isMobile = isMobile;
        PageFactory.initElements(driver, this);
        if(isMobile){
            buyBtn = driver.findElement(By.xpath("//div[@id='sc-mini-buy-box']/descendant::input"));
        }
        else {
            buyBtn = driver.findElement(By.xpath("//span[@id='sc-buy-box-ptc-button']"));
        }
    }

    public boolean productIsOnCart(String bookTitle){
        productTitle = driver.findElement(By.xpath("//span[contains(text(), '" + bookTitle + "')][2]"));
        return productTitle.getText().contains(bookTitle);
    }

    public void purchase(){
        // click purchase
        buyBtn.click();

        // check if it redirect to login page
        WebDriverWait wait = new WebDriverWait(driver, 30);
        Boolean loginInputIsPresqent;
        if(isMobile){
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ap_email_login']")));
                loginInputIsPresqent = driver.findElement(By.xpath("//input[@id='ap_email_login']")).isDisplayed();

                Assert.assertTrue(loginInputIsPresqent);
            }
            catch (TimeoutException e){
                System.err.println("login not visible");
            }
        }
        else {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ap_email']")));
                loginInputIsPresqent = driver.findElement(By.xpath("//input[@id='ap_email']")).isDisplayed();

                Assert.assertTrue(loginInputIsPresqent);
            }
            catch (TimeoutException e){
                System.err.println("login not visible");
            }
        }



    }
}
