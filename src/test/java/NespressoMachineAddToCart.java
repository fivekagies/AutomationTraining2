
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import util.DataReader;
import util.Product;
import util.PropertySetter;
import webPages.nespresso.BasketPage;
import webPages.nespresso.LoginPage;
import webPages.nespresso.machine.MachineDetails;
import webPages.nespresso.machine.MachinesPage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class NespressoMachineAddToCart {

    private WebDriver driver;

//    private static  String MACHINE_TITLE = "Vertuo Next";
//    private static  int RANDOM_QUANTITY_AMOUNT = 1;
    private static  String MACHINE_TITLE;
    private static  String RANDOM_QUANTITY_AMOUNT;

    MachinesPage machinesPage;
    MachineDetails machineDetails;
    BasketPage basketPage;
    LoginPage loginPage;

    PropertySetter propertySetter = new PropertySetter();
    DataReader dataReader = new DataReader();
    ResourceConsumer resourceConsumer;


    @Before
    public void setUp() {
        driver = propertySetter.webDriverSetter();

        resourceConsumer = new ResourceConsumer(driver);
        driver.manage().window().maximize();

        //driver.manage().timeouts().pageLoadTimeout(Duration.of(40, ChronoUnit.SECONDS));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            Product product = dataReader.readDataFromXslFile("dataTest/exceldata.xlsx", 3);
            //Product product = readFromJsonFile("dataTest/jsondata.json");
            MACHINE_TITLE = product.getName();
            RANDOM_QUANTITY_AMOUNT = String.valueOf(product.getQuantity());

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.get("https://www.nespresso.com/fr/en/order/machines/vertuo");
    }

    @After
    public void tearDown() {
        // this method will be executed after each test method
        driver.quit();
    }

    @Test
    public void firstTest(){

        machinesPage = new MachinesPage(driver);

        Assert.assertTrue(machinesPage.verifyProduct(MACHINE_TITLE));
        machinesPage.veiwDetails(MACHINE_TITLE);

        machineDetails = new MachineDetails(driver);

        Assert.assertTrue(machineDetails.IsPageOPened(MACHINE_TITLE));
        machineDetails.addTocart(RANDOM_QUANTITY_AMOUNT);


        resourceConsumer.checkCartAndPurchase(MACHINE_TITLE, RANDOM_QUANTITY_AMOUNT);

    }
}
