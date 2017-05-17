package pageobjects;

import businessobjects.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utility.Services.WebElementService.clickOnElement;
import static utility.Services.WebElementService.sendKeysClear;

/**
 * Created by user on 14.05.17.
 */
public class AdminPage {

    final WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(name = "login")
    public WebElement email;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(xpath = "//button[contains(@class,'submit')]")
    public WebElement logInButtonXpath;

    @FindBy(xpath = "//div[contains(@class,'forgot')]")
    public WebElement forgotPasswordLinkXpath;

    @FindBy(className = "ui-dialog-titlebar-close")
    public WebElement closeTrialPopUpCross;

    @FindBy(id = "leftMenu")
    public WebElement leftMenu;


/////////////////////////////////////////////////////////////////////////////////////////
    @FindBy(css = "button[class*=submit]")
    public WebElement logInButtonCss;

    @FindBy(css = "div[class*=forgot]")
    public WebElement forgotPasswordLinkCss;

/////////////////////////////////////////////////////////////////////////////////////////

    public void login(User user){
        sendKeysClear(email, "Email form", user.getEmail(), driver);

        sendKeysClear(password, "Password form", user.getPassword(), driver);

        clickOnLogInButton();
    }

    public void clickOnCloseTrialPopupCross(){
        clickOnElement(closeTrialPopUpCross, "Close trial popup cross", driver);
    }

    public void clickOnLogInButton() {
        clickOnElement(logInButtonCss, "Login button", driver);
    }


}
