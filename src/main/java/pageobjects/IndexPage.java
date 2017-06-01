package pageobjects;

import businessobjects.User;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static utility.Services.ManageUrlService.refreshPage;
import static utility.Services.WebElementService.*;

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
    public List<WebElement> loginErrorMessage;

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

    @FindBy(className = "add-to-cart-button")
    public List<WebElement> addToCartButtonsHover;

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

    @FindBy(css = "button[class*=add-to-cart]")
    public List<WebElement> addToCartButtons;

    @FindBy(css = "div[class*=recently-updated]")
    public WebElement cartButton;

    @FindBy(css = "div[class*=items-list]")
    public WebElement cartPopUp;

    @FindBy(css = "div[href*=?target=cart]")
    public WebElement cartItemsNumber;

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
        return loginErrorMessage.get(0).getText();
    }

    public void login(User user, String method){
        sendKeysClear(email, "Email form", user.getEmail(), driver);
        sendKeysClear(password, "Password form", user.getPassword(), driver);

        switch (method){
            case "button":
                clickOnSignInButton();
                break;
            case "enter":
                password.sendKeys(Keys.ENTER);
                break;
            default:
                clickOnSignInButton();
                break;
        }


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

    public void addRandomItemsFromIndexPageToCart(int items) {

        List<WebElement> list = new LinkedList<>(addToCartButtons);
        List<WebElement> listHovers = new LinkedList<>(addToCartButtonsHover);

        for(int i = 0; i < items; i++){
            Random random = new Random();
            int randomItem = random.nextInt(list.size());
            moveToElement(listHovers.get(randomItem),
                    "Add to cart button", driver);
            clickOnElement(list.get(randomItem), "Add to cart button", driver);
            list.remove(randomItem);
            listHovers.remove(randomItem);
            refreshPage(driver);
        }
    }

    public void addRandomItemsFromIndexPageToCart() {
        Random random = new Random();
        int randomItem = random.nextInt(addToCartButtons.size());
        moveToElement(addToCartButtonsHover.get(randomItem),
                "Add to cart button", driver);
        clickOnElement(addToCartButtons.get(randomItem), "Add to cart button", driver);
        refreshPage(driver);
    }

    public void clickOnCartButton() {
        clickOnElement(cartButton, "SignIn button", driver);
    }

    public String getCartItemsNumber() {
        return cartItemsNumber.getText();
    }

}


