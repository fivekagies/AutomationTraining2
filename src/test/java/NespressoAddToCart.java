
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.DataReader;
import util.Product;
import util.PropertySetter;
import webPages.nespresso.BasketPage;
import webPages.nespresso.LoginPage;
import webPages.nespresso.ProductsPage;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class NespressoAddToCart {

    private WebDriver driver;
    //static final String PRODUCT_TITLE = "Amaha awe Uganda";

    //static final int RANDOM_QUANTITY_AMOUNT = 10;

    private static String RANDOM_QUANTITY_AMOUNT;
    private static String PRODUCT_TITLE ;

    ProductsPage productsPage;

    BasketPage basketPage;

    LoginPage loginPage;

    PropertySetter propertySetter = new PropertySetter();
    DataReader dataReader = new DataReader();
    ResourceConsumer resourceConsumer;

    static ExtentTest test;
    static ExtentReports report;


    @Before
    public void setUp() throws MalformedURLException {
        //driver = propertySetter.webDriverSetter();

//        DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
//        capabilities.setCapability("platformName", "Windows");
//        capabilities.setCapability("platformVersion", "10");
//        capabilities.setCapability("browserName", "Chrome");
////        capabilities.setCapability("browserVersion", "98");
////        capabilities.setCapability("location", "EU Frankfurt");
//        capabilities.setCapability("resolution", "1024x768");
//
////        capabilities.setCapability("user", "yknit@sqli.com");
////        capabilities.setCapability("password", "741258963Yy@");
//
//        // Set other capabilities.
//        capabilities.setCapability("waitForAvailableLicense", true);
//        capabilities.setCapability("takesScreenshot", true);
//        capabilities.setCapability("screenshotOnError", true);
//        String securityToken = null;
//        try {
//            securityToken = dataReader.propertiesReader("securityToken");
//        } catch (URISyntaxException e) {
//            System.out.println("exception raises in reading security token");
//            e.printStackTrace();
//        }
//
//        capabilities.setCapability("securityToken", securityToken);
//        String cloudName = "trial";
//        driver = new RemoteWebDriver(
//                new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub"),
//                capabilities);

        //DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions capabilities=new ChromeOptions();


        capabilities.addArguments("--disable-blink-features=AutomationControlled");

        capabilities.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver(url, capabilities);

        resourceConsumer = new ResourceConsumer(driver);

        //  driver.manage().window().maximize();

        //driver.manage().timeouts().pageLoadTimeout(Duration.of(40, ChronoUnit.SECONDS));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



        try {
            Product product = dataReader.readDataFromXslFile("dataTest/exceldata.xlsx", 2);
            //Product product = readFromJsonFile("dataTest/jsondata.json");
            PRODUCT_TITLE = product.getName();
            RANDOM_QUANTITY_AMOUNT = String.valueOf(product.getQuantity());

        } catch (Exception e) {
            e.printStackTrace();
        }



        //driver = new RemoteWebDriver(new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);

    }

    @BeforeClass
    public static void startTest()
    {
        //report = new ExtentReports("C:\\Users\\yknit\\Desktop\\course\\data\\ExtentReportResults.html", true);
        report = new ExtentReports("test-output/"     + "Extent.html", true);
        test = report.startTest("NespressoAddToCArt");
    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("fail");

            test.log(LogStatus.FAIL, e.getMessage());
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println("succeeees");
            test.log(LogStatus.PASS, "test passed successfully");
        }
    };

    //	@After 			// in JUnit 4
    @After
    // @AfterEAch in JUnit 5
    public void tearDown() {
        // this method will be executed after each test method
        driver.quit();
    }

    @Test
    public void firstTest(){
            driver.get("https://www.nespresso.com/fr/en/order/capsules/original/");
        productsPage = new ProductsPage(driver);

        test.log(LogStatus.INFO, "search for product");
        System.out.println("seaching for product");
        Assert.assertTrue(productsPage.verifyProduct(PRODUCT_TITLE));

        test.log(LogStatus.INFO, "add product to basket with " + RANDOM_QUANTITY_AMOUNT + " quantity");
        productsPage.addtoBasketAndSelectQuantity(PRODUCT_TITLE);

        productsPage.addQuantity(String.valueOf(RANDOM_QUANTITY_AMOUNT));



        test.log(LogStatus.INFO, "purchase product");
        resourceConsumer.checkCartAndPurchase(PRODUCT_TITLE, RANDOM_QUANTITY_AMOUNT);




    }

    @AfterClass
    public static void endTest()
    {
        report.endTest(test);
        report.flush();
    }

}
