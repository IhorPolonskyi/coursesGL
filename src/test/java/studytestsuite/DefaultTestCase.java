package studytestsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.Log;

import java.util.concurrent.TimeUnit;

import static utility.Services.WebDriverFactory.getDriverInstance;
/**
 * Created by igorp on 11/05/17.
 */
public class DefaultTestCase {

    public WebDriver driver;
    public String browser = System.getProperty("browser","ff");

    @BeforeMethod
    public void runBrowser() {

         driver = getDriverInstance(browser);

         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
         driver.manage().window().maximize();
         Log.info("Maximize window.");
    }


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
        Log.info("Browser closed");
    }
}
