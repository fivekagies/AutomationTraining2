package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import util.PropertySetter;
import webPages.nespresso.BasketPage;
import webPages.nespresso.ProductsPage;
import webPages.nespresso.machine.MachineDetails;
import webPages.nespresso.machine.MachinesPage;

import java.util.concurrent.TimeUnit;


public class AddToBasketStepDefinition {

    private WebDriver driver;

    static final String PRODUCT_TITLE = "Amaha awe Uganda";

    static final int RANDOM_QUANTITY_AMOUNT = 10;

    ProductsPage productsPage = null;
    BasketPage basketPage = null;

    MachinesPage machinesPage = null;
    MachineDetails machineDetails = null;

    PropertySetter propertySetter = new PropertySetter();

    boolean isProductExist;

    public void getUrl(String path){
        driver.get("https://www.nespresso.com/fr/en/order/" + path);
    }


    @Before
    public void setUp() {
        driver = propertySetter.webDriverSetter();

        driver.manage().window().maximize();

        //driver.manage().timeouts().pageLoadTimeout(Duration.of(40, ChronoUnit.SECONDS));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//        productsPage = new ProductsPage(driver);
//        basketPage = new BasketPage(driver);
//        machinesPage = new MachinesPage(driver);
//        machineDetails = new MachineDetails(driver);


    }
    public ProductsPage getProductsPageInstance(){
        if(this.productsPage != null)
            return productsPage;
        return new ProductsPage(driver);
    }

    public BasketPage getBasketPageInstance(){
        if (this.basketPage != null)
            return basketPage;
        return new BasketPage(driver);
    }

    public MachinesPage getMachinesPageInstance(){
        if(this.machinesPage != null)
            return machinesPage;
        return new MachinesPage(driver);
    }

    public MachineDetails getMachineDetailsInstance(){
        if(this.machineDetails != null)
            return machineDetails;
        return new MachineDetails(driver);
    }


    @Given("^User in the catalog page with path \"([^\"]*)\"$")
    public void user_in_the_catalog_page_with_path_something(String path) {
        getUrl(path);
    }

    @When("^User clicks on \"([^\"]*)\" add to basket with \"([^\"]*)\" quantity$")
    public void user_clicks_on_something_add_to_basket(String productTitle, String qtyStr) {

        //productsPage = new ProductsPage(driver);
        productsPage = getProductsPageInstance();

        Assert.assertTrue(productsPage.verifyProduct(productTitle));

        productsPage.addtoBasketAndSelectQuantity(productTitle);
        productsPage.addQuantity(qtyStr);
    }



    @And("^User enter quantity \"([^\"]*)\" in the quantity input field$")
    public void user_enter_quantity_something_in_the_quantity_input_field(String qtyStr) {
        productsPage.addQuantity(qtyStr);
    }

    @Then("^\"([^\"]*)\" is in the basket with \"([^\"]*)\" quantity$")
    public void something_capsule_is_in_the_basket_with_something_quantity(String productTitle, String qtyStr) {
        basketPage = getBasketPageInstance();
        basketPage.IsProductInBasket(productTitle, qtyStr);
    }

    // 2nd Scenario
    @When("^User search for \"([^\"]*)\" product to add it to basket$")
    public void user_search_for_something_product_to_add_it_to_basket(String productTitle) {
        productsPage = getProductsPageInstance();
        isProductExist = productsPage.verifyProduct(productTitle);


    }

    @Then("^\"([^\"]*)\" product is not found$")
    public void something_product_is_not_found(String productTitle) {
        Assert.assertFalse(isProductExist);
    }

    // 3rd Scenario

    @When("^User clicks on \"([^\"]*)\" product$")
    public void user_clicks_on_something_product(String machineName) {
        machinesPage = getMachinesPageInstance();

        Assert.assertTrue(machinesPage.verifyProduct(machineName));
        machinesPage.veiwDetails(machineName);
    }

    @And("^User add product to basket with \"([^\"]*)\" quantity$")
    public void user_add_product_to_basket_with_something_quantity(String qty) {
        machineDetails = getMachineDetailsInstance();
        //Assert.assertTrue(machineDetails.IsPageOPened(machineDetails));
        machineDetails.addTocart(qty);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
