package pageobjects;

import businessobjects.User;
import org.openqa.selenium.Keys;
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

    public WebElement password;

    @FindBy(className = "ui-dialog-titlebar-close")
    public WebElement closeTrialPopUpCross;

    public WebElement leftMenu;

    @FindBy(css = "[type=submit]")
    public WebElement logInButtonCss;

    public void login(User user, String method){
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
    }

    public void clickOnCloseTrialPopupCross(){
        clickOnElement(closeTrialPopUpCross, "Close trial popup cross", driver);
    }

    public void clickOnLogInButton() {
        clickOnElement(logInButtonCss, "Login button", driver);
    }


}
