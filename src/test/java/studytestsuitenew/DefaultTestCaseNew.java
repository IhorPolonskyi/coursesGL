package studytestsuitenew;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utility.Log;
import utility.Services.CoolLogger;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static utility.Services.WebDriverFactory.getDriverInstance;

/**
 * Created by igorp on 11/05/17.
 */
public class DefaultTestCaseNew {

    public WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void runBrowser(@Optional("chrome") String browserValue) {

         driver = getDriverInstance(browserValue);
         driver = new EventFiringWebDriver(driver).register(new CoolLogger());

         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
         Log.info("Maximize window.");
    }


    @AfterMethod
    public void closeBrowser(ITestResult result) {
        if(!result.isSuccess()){
            if(driver!=null){
                File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                File resultFile = new File(result.getName()+ Objects.toString(System.currentTimeMillis())+".png");
                try{
                    FileUtils.copyFile(screenshot, resultFile);
                }
               catch (IOException e)
               {
                   Log.info("Unable to save screenshot");
               }


            }
        }
        driver.quit();
        Log.info("Browser closed");
    }
}
