package webPages.nespresso.machine;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Locale;

public class MachinesPage {

    WebDriver driver;

    @FindBy(id = "_evidon-decline-button")
    WebElement cookieBtn;

    WebElement machineTitle;

    WebElement viewDetailsBtn;

    public MachinesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProduct(String machineName){
        machineTitle = driver.findElement(By.xpath("//a[text()='"+ machineName +"']"));
        String getProductTitle = machineTitle.getText();

        return machineName.toLowerCase(Locale.ROOT).equals(getProductTitle.toLowerCase(Locale.ROOT));
    }

    public void veiwDetails(String machineName){
        cookieBtn.click();

        viewDetailsBtn = driver.findElement(By.xpath("//a[text()='" + machineName + "']/ancestor::article/descendant::a[2]"));

        WebDriverWait wait = new WebDriverWait(driver, 30);
        try{
            wait.until(ExpectedConditions.elementToBeClickable(viewDetailsBtn)).click();
        }
        catch (TimeoutException e){
            System.err.println("viewDetailsBtn not yet clickable");
        }
        //viewDetailsBtn.click();





    }
}
