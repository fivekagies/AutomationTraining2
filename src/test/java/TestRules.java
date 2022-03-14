import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class keeps stuff that can be reused across multiple test suites
 */
public class TestRules {

    WebDriver driver;
    ExtentTest test;


    public void initialise(WebDriver driver, ExtentTest test){
        this.driver = driver;
        this.test = test;
    }



    public static String getScreenshoot(WebDriver driver) throws Exception {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_hh_mm_ss");
        Date date = new Date();
        String fileName = sdf.format(date);

        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+fileName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        System.out.println("getScreen path : " + destination);
        //Returns the captured file path
        return destination;


    }

    @Rule
    public TestWatcher watcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("fail");

            test.log(LogStatus.FAIL, "Test Case Failed is "+description.getDisplayName());

            String screenshotPath = null;
            try {
                screenshotPath = getScreenshoot(driver);

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("err msg : " + ex.getMessage());
            }


            screenshotPath = StringUtils.substringAfter(screenshotPath, "AutomationTraining");


            test.log(LogStatus.FAIL, test.addScreenCapture("../" + screenshotPath));


            test.log(LogStatus.FAIL, e.getMessage());


            driver.quit();
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println("succeeees");
            test.log(LogStatus.PASS, "test passed successfully");
            driver.quit();
        }
    };
}
