package utility.Services;

import org.openqa.selenium.*;
import utility.Constants;

import java.util.concurrent.TimeUnit;

import static utility.Log.info;
import static utility.Log.warn;
import static utility.Services.AdditionalService.catchException;

/**
 * Created by igorp on 11/05/17.
 */
public class ManageUrlService {

    public static void getURL(String url, WebDriver driver) {

        info("Navigate to \"" +url+"\".");
        try {
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.get(url);

            info("Navigate to \"" + url + "\" finished.");

        }
        catch (TimeoutException e){
            stopLoad(driver);
        }
        catch (UnhandledAlertException e){
            catchException(e);
        }
    }

    public  static void refreshPage(WebDriver driver){
        try {
            driver.navigate().refresh();
            info("Page was refreshed.");
        }
        catch (WebDriverException e){
            stopLoad(driver);
        }
    }

    public  static String getCurrentURL(WebDriver driver){
        info("Current URL:"+driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }

    public static void navigateBack (WebDriver driver){
        try {
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.navigate().back();
            info("Returned to the previous page.");
        }
        catch (TimeoutException e){
            stopLoad(driver);
        }

    }

    public static void scrollDown(WebDriver driver, int px){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.scrollBy(0,"+px+")", "");
    }

    public static void stopLoad(WebDriver driver){
        driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
        info("Timeout on loading page \""+driver.getCurrentUrl()+"\".");
    }

    public static void resizeWindow(Dimension dimension, WebDriver driver){
        driver.manage().window().setSize(dimension);
        info("Resize window to "+dimension+" size.");
    }



}
