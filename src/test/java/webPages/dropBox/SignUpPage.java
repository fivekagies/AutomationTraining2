package webPages.dropBox;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    WebDriver driver;


    @FindBy(xpath = "//*[contains(@resource-id, 'new_account_first_name')]/descendant::android.widget.EditText")
    WebElement firstName;

    @FindBy(xpath = "//*[contains(@resource-id, 'new_account_last_name')]/descendant::android.widget.EditText")
    WebElement lastName;

    @FindBy(xpath = "//*[contains(@resource-id, 'new_account_email')]/descendant::android.widget.EditText")
    WebElement email;

    @FindBy(xpath = "//*[contains(@resource-id, 'new_account_password')]/descendant::android.widget.EditText")
    WebElement password;

    @FindBy(xpath = "//*[contains(@resource-id, 'new_account_submit')]")
    WebElement signUpBtn;

    @FindBy(xpath = "//*[contains(@resource-id, 'button2')]")
    WebElement agreeConditionBtn;


    public SignUpPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private String generateEmail(){
        String text = "";
        String pattern = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for(int i=0;i<5;i++){
            text += pattern.charAt((int)Math.floor(Math.random() * pattern.length()));
        }
        return text + "@mail.jp";
    }

    public void signUpForm(){
        firstName.sendKeys("krusti");
        lastName.sendKeys("boy");
        email.sendKeys(generateEmail());
        password.sendKeys("password123Password");

        signUpBtn.click();

        //accept condition
        agreeConditionBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, 30);

        try{
            // wait for addToBasketBtn to be clickable
            //wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[contains(@resource-is, 'content')]"), "Creating account, please wait…"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id, 'dbx_toolbar')]/descendant::android.widget.ImageButton"))).click();
            //driver.findElement(By.xpath("//*[contains(@resource-id, 'dbx_toolbar')]/descendant::android.widget.ImageButton")).click();


        }
        catch (TimeoutException e){
            System.err.println("home not accessible");
        }
    }

}
