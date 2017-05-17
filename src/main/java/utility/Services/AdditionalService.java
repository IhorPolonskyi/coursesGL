package utility.Services;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static utility.Log.info;

/**
 * Created by user on 14.05.17.
 */
public class AdditionalService {

    public static void catchException(Exception e){
        Assert.assertTrue(false, String.valueOf(e));
    }

    public static String getCookieValue(String cookieName, WebDriver driver){
        String value = driver.manage().getCookieNamed(cookieName).getValue();
        info("Cookie: \"" + cookieName + "\" has value - \"" + value + "\".");
        return value;

    }

    public static boolean verifyCookieIsSet(String cookieName, WebDriver driver){
        return driver.manage().getCookieNamed(cookieName) != null;
    }
}
