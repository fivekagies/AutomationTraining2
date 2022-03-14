
import com.google.common.collect.ImmutableMap;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.DataReader;
import util.Product;
import webPages.amazon.CartPage;
import webPages.amazon.HomePage;
import webPages.amazon.ProductDetailsPage;
import webPages.amazon.ProductsPage;



import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import java.util.concurrent.TimeUnit;


public class AmazonAddToCart  extends TestRules{


    private static WebDriver driver;
    //static final String BOOK_TITLE = "Python Testing with Selenium: Learn to Implement";
    static String BOOK_TITLE;
    //static final int RANDOM_QUANTITY = 50;
    static int RANDOM_QUANTITY;


    boolean isMobile;
    String nativeApp;
    String deviceTestType;

    HomePage homePage;
    ProductsPage productsPage;
    ProductDetailsPage productDetailsPage;
    CartPage cartPage;
    DataReader dataReader = new DataReader();
    URL url;

    static ExtentTest test;
    static ExtentReports report;
    String securityToken = null;

    public static final String is_mobile = System.getProperty("isMobile");
    public static final String native_app = System.getProperty("nativeApp");
    public static final String device_test_type = System.getProperty("deviceTestType");





    @Before
    public void setUp() throws URISyntaxException, MalformedURLException {

        if(is_mobile!=null){
            System.out.println("is mobile not empty");
            isMobile = Boolean.parseBoolean(is_mobile);
        }
        else
            isMobile = Boolean.parseBoolean(dataReader.propertiesReader("isMobile"));

        if(native_app!=null){
            System.out.println("native app not empty");
            nativeApp = native_app;
        }
        else
            nativeApp = dataReader.propertiesReader("nativeApp");

        if(device_test_type!=null){
            System.out.println("device_test_typenot empty");
            deviceTestType = device_test_type;
        }
        else
            deviceTestType = dataReader.propertiesReader("deviceTestType");





        System.out.println("isMobile : "+ isMobile + " nativeApp : " + nativeApp);

        ChromeOptions options=new ChromeOptions();

        options.addArguments("--disable-blink-features=AutomationControlled");

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        DesiredCapabilities capabilities = new DesiredCapabilities();



        if(!isMobile){
            if(deviceTestType.equals("local")){
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\yknit\\Downloads\\selenium-java-4.1.0\\chromedriver_win32\\chromedriver.exe");
                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
            }
            else{

                try {
                    capabilities = dataReader.getDesiredCapabilities("WebSiteCloud", "dataTest/desiredcapabilities.json");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                url = new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub");
                driver = new RemoteWebDriver(new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
            }

        }
        else {
            // Mobile web app
            if(nativeApp.equals("webApp")){
                if(deviceTestType.equals("local")){

                    try {
                        capabilities = dataReader.getDesiredCapabilities("mobileWebAppLocal", "dataTest/desiredcapabilities.json");
                        capabilities.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
                        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    url = new URL("http://localhost:4723/wd/hub");

                    System.out.println("sip 1 mobile webApp");
                }
                else {
                    try {
                        capabilities = dataReader.getDesiredCapabilities("mobileWebAppCloud", "dataTest/desiredcapabilities.json");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    url = new URL("https://trial.perfectomobile.com/nexperience/perfectomobile/wd/hub");

                }

            }
            // Mobile app
            if(nativeApp.equals("moblieApp")){

            }
            driver = new AppiumDriver<AndroidElement>(url, capabilities);

        }

        //driver.manage().timeouts().pageLoadTimeout(Duration.of(40, ChronoUnit.SECONDS));

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            Product product = dataReader.readDataFromXslFile("dataTest/exceldata.xlsx", 1);

            BOOK_TITLE = product.getName();
            RANDOM_QUANTITY = product.getQuantity();

        } catch (Exception e) {
            e.printStackTrace();
        }


        driver.get("https://www.amazon.fr/");
    }

    @After
    public void afterAll(){
        //driver.quit();
    }





    @BeforeClass
    public static void startTest()
    {

        report = new ExtentReports("test-output/"     + "Extent.html", true);
        test = report.startTest("AmazonAddToCart");

    }




    @Test
    public void firstTest() throws InterruptedException {

        this.initialise(driver, test);
        // create object of HomePage
        homePage = new HomePage(driver, isMobile);

        //Assert.assertTrue(homePage.isPageOpened());

        test.log(LogStatus.INFO, "search for product");

        homePage.searchForProduct();

        // create object ProductsPage
        productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.bookIsPresent(BOOK_TITLE));

        test.log(LogStatus.INFO, "select the product");
        productsPage.clickOnBook();

        // create object ProductDetails
        productDetailsPage = new ProductDetailsPage(driver, isMobile);

        test.log(LogStatus.INFO, "add product to cart");
        productDetailsPage.addToCart(RANDOM_QUANTITY);

        //create object Cart
        cartPage = new CartPage(driver, isMobile);
        Assert.assertTrue(cartPage.productIsOnCart(BOOK_TITLE));

        test.log(LogStatus.INFO, "purchase product");
        cartPage.purchase();






    }

    @AfterClass
    public static void endTest()
    {
        report.endTest(test);
        report.flush();
    }
}
