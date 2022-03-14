package webPages.amazon;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage {

    WebDriver driver;

    // select broche
    //@FindBy(xpath = "//a[contains(@id, 'a-autoid') and contains(@href, '/Selenium-WebDriver-Quick-Start')]")
    WebElement productOption;

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    WebElement addToCartBtn;

    //@FindBy(xpath = "//select[@id='quantity']")
    @FindBy(xpath = "//select[@name='quantity']")
    WebElement quantityDrpDown;

    WebElement quantityElement;

    //@FindBy(xpath = "//a[@id='nav-cart']")
    @FindBy(xpath = "//a[@id='nav-cart' or @id='nav-button-cart']")
    WebElement cart;

    boolean isMobile;

    public ProductDetailsPage(WebDriver driver, boolean isMobile){
        this.driver = driver;
        this.isMobile = isMobile;
        PageFactory.initElements(driver, this);
    }

    public void addToCart(int quantity){

        //productOption = driver.findElement(By.xpath("//div[@id='tmmSwatches']/ul/li[2]/span/span/span/a"));


//        productOption = driver.findElement(By.xpath("//div[@id='tmmSwatches']/descendant::a[contains(@class, 'button')][2]"));
//        productOption.click();


        Select select = new Select(quantityDrpDown);
        //int quantityAvailable = checkQuantityAndTakeNearest(quantity);
        //select.selectByValue(String.valueOf(quantityAvailable));
        select.selectByValue(String.valueOf(quantity));
        if(isMobile)
            driver.findElement(By.xpath("//ul/descendant::a[contains(text(),'"+quantity+"')]")).click();



        //addToCartBtn.click();
        JavascriptExecutor js;
        if (driver instanceof JavascriptExecutor) {
            js = (JavascriptExecutor)driver;
        } else {
            throw new IllegalStateException("This driver does not support JavaScript!");
        }

        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
            js.executeScript("document.querySelector('#add-to-cart-button').click()");
        }
        catch (TimeoutException e){
            System.err.println("add to cart Btn not clickable");
        }




        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-cart' or @id='nav-button-cart']"))).click();


        }
        catch (TimeoutException e){
            System.err.println("cart not visible");
        }
        //cart.click();
    }

    private int checkQuantityAndTakeNearest(int quantity){

        Select select = new Select(quantityDrpDown);
        //get all options
        List<WebElement> options = select.getOptions();
        List<Integer> optionsQuantities = new ArrayList<>();

        for(int i=0;i<options.size();i++){
            optionsQuantities.add(Integer.valueOf(options.get(i).getText()));
        }
        // check if quantity exist in dropDown
        if(optionsQuantities.contains(quantity)){
            return quantity;
        }
        else {

            // take nearest quantity available
            int distance = Math.abs(optionsQuantities.get(0) - quantity);
            int idx = 0;
            for(int j = 1;j<optionsQuantities.size();j++){
                int jDistance = Math.abs(optionsQuantities.get(j) - quantity);
                if(jDistance < distance){
                    idx = j;
                    distance = jDistance;
                }
            }

            return optionsQuantities.get(idx);
        }


    }



}
