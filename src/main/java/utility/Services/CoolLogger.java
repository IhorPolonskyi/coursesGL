package utility.Services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import utility.LogUtils;

/**
 * Created by user on 09.06.17.
 */
public class CoolLogger extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver){
        LogUtils.info("Searching for: "+ by);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver){
        LogUtils.info("Click on: "+ element);
    }

}
