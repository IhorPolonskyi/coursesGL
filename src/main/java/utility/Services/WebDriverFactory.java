package utility.Services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;
import static org.openqa.selenium.firefox.GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY;

/**
 * Created by user on 19.05.17.
 */
public class WebDriverFactory {

    public static WebDriver getDriverInstance(String browserName){
        WebDriver driver;
        switch (browserName){
            case BrowserType.CHROME:
                driver = setChromeProperty();
                break;
            case BrowserType.FIREFOX:
                driver = setFirefoxProperty();
                break;
            default:
                driver = setChromeProperty();
                break;
        }
        return driver;
    }

    private static WebDriver setChromeProperty(){
        System.setProperty(CHROME_DRIVER_EXE_PROPERTY, "chromedriver");
        return new ChromeDriver(getChromeDesiredCapabilities());
    }

    private static WebDriver setFirefoxProperty(){
        System.setProperty(GECKO_DRIVER_EXE_PROPERTY, "geckodriver");
        return  new FirefoxDriver(getFirefoxDesiredCapabilities());
    }

    private static DesiredCapabilities getChromeDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, true);
        desiredCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, addChromeOptions());

        return desiredCapabilities;
    }

    private static ChromeOptions addChromeOptions() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");

        return chromeOptions;
    }

    private static DesiredCapabilities getFirefoxDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, true);
        desiredCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        return desiredCapabilities;
    }

}
