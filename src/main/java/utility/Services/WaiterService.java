package utility.Services;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Constants;

import static org.testng.Assert.assertTrue;
import static utility.Log.info;
import static utility.Log.warn;

/**
 * Created by igorp on 11/05/17.
 */
public class WaiterService {

    public static void waitForElementVisible(WebElement element, WebDriver driver) {

        try {
            WebDriverWait wait = new WebDriverWait(driver,20);
            wait.until(ExpectedConditions.visibilityOf(element));
        }
        catch (TimeoutException e){
            warn("ELEMENT: \"" + element + "\" is not presents.");
        }

    }

    public static void waitPageLoader(String url, WebDriver driver) {
        try {
            info("Waiting for \"" + url + "\" page.");
            int attempt = 0;
            while (!driver.getCurrentUrl().contains(url) && attempt < 20) {
                attempt++;
                sleep(1);
            }
            info("Waiting for \"" + url + "\" page during " + attempt + " seconds.");
            if (!driver.getCurrentUrl().contains(url)) {
                assertTrue(false, "Expected page hasn't loaded  by timeout.\n" +
                        "                                   Current url:" + driver.getCurrentUrl());
            }
        } catch (TimeoutException e) {
            ManageUrlService.stopLoad(driver);
        }
    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void waitForCookie(String cookieName, WebDriver driver){
        int attempt_counter = 0;
        while (driver.manage().getCookieNamed(cookieName)==null ||
                (driver.manage().getCookieNamed(cookieName) != null && driver.manage().getCookieNamed(cookieName).getValue() == null))
        {
            WaiterService.sleep(1);
            attempt_counter++;
            if (attempt_counter == Constants.ELEMENT_TIMEOUT){
                warn("Cookie: \"" + cookieName + "\" was NOT found, break by attempt counter.");
                break;}
        }
    }


    public  static void pageLoaderWaitJS(WebDriver driver){

        try {
            WebDriverWait wait = new WebDriverWait(driver, Constants.PAGE_TIMEOUT);
            wait.until((WebDriver webDriver) -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        }
        catch (WebDriverException e){
            warn(e);
        }
    }

}
