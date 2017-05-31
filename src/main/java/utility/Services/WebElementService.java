package utility.Services;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertTrue;
import static utility.Log.info;
import static utility.Services.WaiterService.sleep;

/**
 * Created by igorp on 16/05/17.
 */
public class WebElementService {

    public static void clickOnElement(WebElement element, String elementName, WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        try {
            element.click();
            info("Click on \"" +elementName+"\".");
        }
        catch (NoSuchElementException e){
            assertTrue(false, "\"" + elementName + "\" was not found on page after timeout.");
        }
    }


    public static void sendKeysClear(WebElement element, String elementName, String inputText, WebDriver driver){
        try {
            WaiterService.waitForElementVisible(element, driver);
            element.clear();
            element.sendKeys(inputText);
            info("\"" + elementName + "\" input text: \"" + inputText + "\".");
        }
        catch (NoSuchElementException e ){
            assertTrue(false, "\"" + elementName + "\" was not found on page after timeout.");
        }
    }

    public static void sendKeys(WebElement element, String elementName, String inputText){
        try {
            element.sendKeys(inputText);
            info("\"" + elementName + "\" input text: \"" + inputText + "\".");
        }
        catch (NoSuchElementException e){
            assertTrue(false, "\"" + elementName + "\" was not found on page after timeout.");
        }
    }


    public static void clearField(WebElement element, String elementName){
        try {
            element.clear();
            info("\"" + elementName + "\" cleared \"" );
        }
        catch (NoSuchElementException e){
            assertTrue(false, "\"" + elementName + "\" was not found on page after timeout.");
        }
    }

    public static void moveToElement(WebElement element, String elementName, WebDriver driver) {
        try {
            WaiterService.waitForElementVisible(element,driver);
            Actions actions = new Actions(driver);
            actions.moveToElement(element).build().perform();
            sleep(1);
            info("\"" + elementName + "\" is active.");
        }
        catch (NoSuchElementException e){
            assertTrue(false, "\"" + elementName + "\" not found.");
        }
        catch (ElementNotVisibleException e){
            assertTrue(false, "\"" + elementName + "\" was not visible.");
        }

    }




}
