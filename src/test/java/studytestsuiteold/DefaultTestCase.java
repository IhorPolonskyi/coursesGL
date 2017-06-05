package studytestsuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utility.Log;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static utility.Services.WebDriverFactory.getDriverInstance;
/**
 * Created by igorp on 11/05/17.
 */
public class DefaultTestCase {

    public WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void runBrowser(@Optional("chrome") String browserValue) {

         driver = getDriverInstance(browserValue);

         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
         Log.info("Maximize window.");
    }


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
        Log.info("Browser closed");
    }
}
