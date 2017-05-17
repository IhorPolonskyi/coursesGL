package studytestsuite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utility.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by igorp on 11/05/17.
 */
public class DefaultTestCase {

    public WebDriver driver;

    @BeforeTest
    public void runBrowser() {
         System.setProperty("webdriver.chrome.driver", "chromedriver");
         driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
         driver.manage().window().maximize();
        Log.info("Maximize window.");
    }


    @AfterTest
    public void closeBrowser() {
        driver.close();
        Log.info("Browser closed");
    }
}
