package webPages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    WebDriver driver;

    //static final String bookTitile = "Python Testing with Selenium: Learn to Implement";

    //static final String bookTitleXpath = "//span[contains(text(), '" + bookTitile + "')]/parent::a";

    //@FindBy(xpath = bookTitleXpath)
    WebElement book;

    public ProductsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean bookIsPresent(String bookTitile){
//        book = driver.findElement(By.xpath("//span[contains(text(), '" + bookTitile + "')]/parent::a"));
        book = driver.findElement(By.xpath("//span[contains(text(), '" + bookTitile + "')]/ancestor::a"));
        return book.isDisplayed();
    }
    public void clickOnBook(){

        // explicit wait - to wait for the book to be click-able
        WebDriverWait wait = new WebDriverWait(driver, 30);

        try{
            wait.until(ExpectedConditions.elementToBeClickable(book)).click();
        }
        catch (TimeoutException e){
            System.err.println("book not yet clickable");
        }
        //book.click();
    }
}
