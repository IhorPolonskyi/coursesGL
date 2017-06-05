package pageobjectsnew;

import businessobjects.User;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utility.Services.WaiterService.waitForElementVisible;
import static utility.Services.WaiterService.waitPageLoader;
import static utility.Services.WebElementService.clickOnElement;
import static utility.Services.WebElementService.sendKeysClear;

/**
 * Created by user on 14.05.17.
 */
public class AdminPageNew {

    protected WebDriver driver;

    public AdminPageNew(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(name = "login")
    public WebElement email;

    public WebElement password;

    @FindBy(className = "ui-dialog-titlebar-close")
    public WebElement closeTrialPopUpCross;

    public WebElement leftMenu;

    @FindBy(css = "[type=submit]")
    public WebElement logInButtonCss;

    public AdminPageNew login(User user, String method){
        sendKeysClear(email, "Email form", user.getEmail(), driver);

        sendKeysClear(password, "Password form", user.getPassword(), driver);

        switch (method){
            case "button":
                clickOnLogInButton();
                break;
            case "enter":
                password.sendKeys(Keys.ENTER);
                break;
            default:
                clickOnLogInButton();
                break;
        }
        return this;
    }

    public AdminPageNew clickOnCloseTrialPopupCross(){
        waitForElementVisible(closeTrialPopUpCross, driver);
        clickOnElement(closeTrialPopUpCross, "Close trial popup cross", driver);
        return this;
    }

    public AdminPageNew clickOnLogInButton() {
        waitForElementVisible(logInButtonCss, driver);
        clickOnElement(logInButtonCss, "Login button", driver);
        return this;
    }

    public AdminPageNew waitAdminPageLoad(String url) {
        waitPageLoader(url.replace("+","="), driver);
        return this;
    }

}
