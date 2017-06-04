package pageobjects;

import businessobjects.Item;
import businessobjects.User;
import org.apache.commons.collections.ListUtils;
import org.apache.http.impl.conn.tsccm.WaitingThreadAborter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Constants;
import utility.Services.WaiterService;

import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.PageFactory.initElements;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.WaiterService.pageLoaderWaitJS;
import static utility.Services.WaiterService.sleep;
import static utility.Services.WaiterService.waitForElementVisible;
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

    @FindBy(id = "page-title")
    public WebElement pageTitle;

    @FindBy(className = "add-to-cart-button")
    public List<WebElement> addToCartButtonsHover;

    @FindBy(css = "[class*=header_bar-sign_in] button")
    public WebElement loginButtonCss;

    @FindBy(css = "td button[class*=submit]")
    public WebElement signInButtonCss;

    @FindBy(css = "[class*=create-account-link]")
    public WebElement createAccountLinkCss;

    @FindBy(css = "[class*=submit] button")
    public WebElement createAccountButtonCss;

    @FindBy(css = "div[class*=recently-updated]")
    public WebElement cartButton;

    @FindBy(css = "div[class*=items-list]")
    public WebElement cartPopUp;

    @FindBy(xpath = "//a[@href='?target=cart']")
    public WebElement cartItemsNumber;

    @FindBy(xpath = "//*[contains(@class,'bestsellers')]/div/div/div/ul/li/div")
    public List<WebElement> bestsellersItems;

    @FindBy(xpath = "//*[contains(@class,'featured')]/div/div/div/ul/li/div")
    public List<WebElement> featuredItems;

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

    public void addRandomItemsFromIndexPageToCart(int item) {

        ItemPage itemPage = initElements(driver, ItemPage.class);

        //TODO fix add to cart
            for (int i = 0; i < item; i++) {
                Random random = new Random();
                int randomItem = random.nextInt(featuredItems.size());

                waitForElementVisible(featuredItems.get(randomItem), driver);
                clickOnElement(featuredItems.get(randomItem), "Item page", driver);

                if (!driver.getCurrentUrl().equals(Constants.URL)) {
                    itemPage.clickOnAddToCartButton();
                    waitForElementVisible(itemPage.addToCartPopUp, driver);
                    getURL(Constants.URL, driver);
                }
            }
    }

    public void addRandomItemsFromIndexPageToCart(Item item) {
        ItemPage itemPage = initElements(driver, ItemPage.class);
        List<WebElement> list = ListUtils.union(bestsellersItems,featuredItems);

        //TODO fix add to cart
        Random random = new Random();
        int randomItem = random.nextInt(list.size());
        clickOnElement(list.get(randomItem), "Item page", driver);
        waitForElementVisible(itemPage.addToCartButton, driver);
        item.setName(itemPage.getItemName());
        itemPage.clickOnAddToCartButton();
        waitForElementVisible(itemPage.addToCartPopUp, driver);
        getURL(Constants.URL, driver);
    }

    public void clickOnCartButton() {
        clickOnElement(cartButton, "Cart button", driver);
    }

    public String getCartItemsNumber() {
        return cartItemsNumber.getText();
    }

}


