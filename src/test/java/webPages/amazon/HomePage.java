package webPages.amazon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;


    @FindBy(id = "sp-cc-accept")
    WebElement cookiesBtn;

//    @FindBy(id = "searchDropdownBox")
//    WebElement categoriesDrp;


    WebElement searchBar;


    WebElement submitButton;

    public HomePage(WebDriver driver, boolean isMobile){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if(isMobile){
            searchBar = driver.findElement(By.id("nav-search-keywords"));
            submitButton = driver.findElement(By.xpath("//input[@type='submit' and contains(@class, 'input') and @value='Go']"));
            System.out.println("isMobile yes");
        }
        else{
            System.out.println("isMobile NOO");
            searchBar = driver.findElement(By.id("twotabsearchtextbox"));
            submitButton = driver.findElement(By.id("nav-search-submit-button"));
        }
    }
    public boolean isPageOpened(){

        String pageTitle = "Amazon.fr : livres, DVD, jeux vidéo, musique, high-tech, informatique, jouets, vêtements, chaussures, sport, bricolage, maison, beauté, puériculture, épicerie et plus encore !";
        return pageTitle.equals(driver.getTitle());
    }

    public void searchForProduct(){

        cookiesBtn.click();

//        categoriesDrp.click();
//
//        Select select = new Select(categoriesDrp);
//        select.selectByValue("search-alias=stripbooks");

        searchBar.sendKeys("Selenium automation");


        submitButton.click();

    }
}
