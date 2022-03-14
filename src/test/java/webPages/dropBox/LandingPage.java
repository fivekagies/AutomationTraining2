package webPages.dropBox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    WebDriver driver;


    @FindBy(xpath = "//*[contains(@resource-id, 'tour_sign_up')]")
    WebElement signUpBtn;

    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSignUp(){
        signUpBtn.click();
    }
}
