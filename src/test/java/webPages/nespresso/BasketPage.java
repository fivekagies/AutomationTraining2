package webPages.nespresso;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import java.util.function.Function;


class CartChecker implements Function<WebDriver, Boolean> {
    int quantity;
    @Override
    public Boolean apply(WebDriver driver) {
        String qtyElement = driver.findElement(By.xpath("//button[@id='ta-mini-basket__open']/span")).getText();

        return Integer.valueOf(qtyElement) >= quantity;
    }
    public CartChecker(int quantity){
        this.quantity = quantity;
    }
}
public class BasketPage {

    WebDriver driver;

    @FindBy(xpath = "//button[@id='ta-mini-basket__open']")
    WebElement cartBtn;


    WebElement productInCart;

    @FindBy(xpath = "//button[@id='ta-mini-basket__checkout']")
    WebElement checkoutBtn;

    BasePage basePage;

    public BasketPage(WebDriver driver){
        this.driver = driver;
        basePage = new BasePage(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean IsProductInBasket(String productName, String quantityStr){

        int quantity = Integer.valueOf(quantityStr);
        try{

            Wait fluentWait = basePage.getFluentWit();

            fluentWait.until(new CartChecker(quantity));

            cartBtn.click();
        }
        catch (TimeoutException e){
            System.err.println("cartBtn not yet clickable");
        }

        productInCart = driver.findElement(By.xpath("//span[text()='"+ productName +"']"));


        return  productInCart.isDisplayed();
    }

    public void purchase(){

        checkoutBtn.click();

    }
}
