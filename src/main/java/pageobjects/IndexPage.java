package pageobjects;

import businessobjects.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utility.Services.WebElementService.clickOnElement;
import static utility.Services.WebElementService.sendKeysClear;

/**
 * Created by igorp on 11/05/17.
 */
public class IndexPage {

    final WebDriver driver;

    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "login-email")
    public WebElement email;

    @FindBy(id = "login-password")
    public WebElement password;

    @FindBy(id = "login")
    public WebElement emailCreate;

    @FindBy(id = "password")
    public WebElement passwordCreate;

    @FindBy(className = "error")
    public WebElement loginErrorMessage;

    @FindBy(id = "password-conf")
    public WebElement passwordConfirmCreate;

    @FindBy(xpath = "//*[contains(@class,'header_bar-sign_in')]//button")
    public WebElement loginButtonXpath;

    @FindBy(xpath = "//td//button[contains(@class,'submit')]")
    public WebElement signInButtonXpath;

    @FindBy(xpath = "//div[contains(@class,'submit')]//button")
    public WebElement createAccountButtonXpath;

    @FindBy(xpath = "//a[contains(@class,'create-account-link')]")
    public WebElement createAccountLinkXpath;

    @FindBy(xpath = "//a[contains(@class,'forgot')]")
    public WebElement forgotPasswordLinkXpath;

    @FindBy(xpath = "//button[contains(@class,'titlebar-close')]")
    public WebElement closeSignInPopUpCrossXpath;

    @FindBy(id = "page-title")
    public WebElement pageTitle;

    ////////////////////////////////////////////////////////////////////////////////////

    @FindBy(css = "[class*=header_bar-sign_in] button")
    public WebElement loginButtonCss;

    @FindBy(css = "td button[class*=submit]")
    public WebElement signInButtonCss;

    @FindBy(css = "a[class*=create-account-link]")
    public WebElement createAccountLinkCss;

    @FindBy(css = "a[class*=forgot]")
    public WebElement forgotPasswordLinkCss;

    @FindBy(css = "button[class*=titlebar-close]")
    public WebElement closeSignInPopUpCrossCss;

    @FindBy(css = "div[class*=submit] button")
    public WebElement createAccountButtonCss;

    ////////////////////////////////////////////////////////////////////////////////////////

    public void clickOnLoginButton() {
        clickOnElement(loginButtonCss, "Login Button", driver);
    }

    public void clickOnCreateAccountLink() {
        clickOnElement(createAccountLinkCss, "Create account link", driver);
    }

    public String getPageTitleText(){
        return pageTitle.getText();
    }

    public String getErrorText(){
        return loginErrorMessage.getText();
    }

    public void login(User user){
        sendKeysClear(email, "Email form", user.getEmail(), driver);
        sendKeysClear(email, "Password form", user.getPassword(), driver);

        clickOnSignInButton();
    }

    public void createAccount(User user){
        sendKeysClear(emailCreate, "Email form create user", user.getEmail(), driver);

        sendKeysClear(passwordCreate, "Password form create user", user.getPassword(), driver);

        sendKeysClear(passwordConfirmCreate, "Second password form create user", user.getPassword(), driver);

        clickOnCreateAccountButton();
    }

    public void clickOnSignInButton() {
        clickOnElement(signInButtonCss, "SignIn button", driver);
    }

    public void clickOnCreateAccountButton() {
        clickOnElement(createAccountButtonCss, "Create account button", driver);
    }
}
