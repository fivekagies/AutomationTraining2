package webPages.nespresso.machine;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MachineDetails {

    WebDriver driver;


    WebElement machineTitle;

    @FindBy(xpath = "//button[@id='ta-product-details__add-to-bag-button']")
    WebElement addToBasketBtn;

    @FindBy(xpath = "//input[@id='ta-quantity-selector__custom-field']")
    WebElement quantityField;

    @FindBy(xpath = "//button[@id='ta-quantity-selector__custom-ok']")
    WebElement confirmQuantityBtn;

    public MachineDetails(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean IsPageOPened(String machineName){
        machineTitle = driver.findElement(By.xpath("//h1[contains(text(),'" + machineName + "')]"));

        return machineTitle.isDisplayed();
    }

    public void addTocart(String quantity) {
        addToBasketBtn.click();


        WebDriverWait wait = new WebDriverWait(driver, 30);
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ta-quantity-selector__custom-field']")));
        }
        catch (TimeoutException e){
            System.err.println("quantity input field not yet visible");
        }
        quantityField.sendKeys(quantity);

        confirmQuantityBtn.click();

    }
}
