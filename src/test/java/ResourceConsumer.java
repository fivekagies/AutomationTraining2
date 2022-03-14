import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import webPages.nespresso.BasketPage;
import webPages.nespresso.LoginPage;

public  class ResourceConsumer {

    WebDriver driver;

    BasketPage basketPage;
    LoginPage loginPage;

    ResourceConsumer(WebDriver driver){
        this.driver = driver;
        basketPage = new BasketPage(driver);
        loginPage = new LoginPage(driver);
    }

    public void checkCartAndPurchase(String productTitle, String quantity){

        Assert.assertTrue(basketPage.IsProductInBasket(productTitle, quantity));
        basketPage.purchase();


        Assert.assertTrue(loginPage.IsLoginPageOpned());
    }






}
