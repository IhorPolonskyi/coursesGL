package pageobjectsnew;

import businessobjects.Item;
import businessobjects.User;
import org.apache.commons.collections.ListUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjectsold.ItemPage;
import utility.Constants;

import java.util.List;
import java.util.Random;

import static org.openqa.selenium.support.PageFactory.initElements;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.WaiterService.waitForCookie;
import static utility.Services.WaiterService.waitForElementVisible;
import static utility.Services.WebElementService.clickOnElement;
import static utility.Services.WebElementService.sendKeysClear;

/**
 * Created by igorp on 11/05/17.
 */
public class IndexPageNew {

    protected WebDriver driver;

    public IndexPageNew(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    @FindBy(css = "li[class*=logoff]")
    public WebElement logOffCss;

    @FindBy(css = "li.tab.admin a")
    public WebElement adminLoginLinkCss;

    public IndexPageNew clickOnGoToAdminPanelLink() {
        clickOnElement(adminLoginLinkCss, "Admin login link", driver);
        return this;
    }

    public IndexPageNew clickOnLoginButton() {
        waitForElementVisible(loginButtonCss, driver);
        clickOnElement(loginButtonCss, "Login Button", driver);
        return this;
    }

    public IndexPageNew openSite() {
        //get index page
        getURL(Constants.URL, driver);
        return this;
    }

    public IndexPageNew clickOnCreateAccountLink() {
        waitForElementVisible(createAccountLinkCss, driver);
        clickOnElement(createAccountLinkCss, "Create account link", driver);
        return this;
    }

    public String getPageTitleText(){
        return pageTitle.getText();
    }

    public String getErrorText(){
        return loginErrorMessage.get(0).getText();
    }

    public IndexPageNew login(User user, String method){
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
        return this;
    }

    public IndexPageNew createAccount(User user){
        waitForElementVisible(createAccountButtonCss, driver);

        sendKeysClear(emailCreate, "Email form create user", user.getEmail(), driver);

        sendKeysClear(passwordCreate, "Password form create user", user.getPassword(), driver);

        sendKeysClear(passwordConfirmCreate, "Second password form create user", user.getPassword(), driver);

        clickOnCreateAccountButton();

        return this;
    }

    public IndexPageNew clickOnSignInButton() {
        waitForElementVisible(signInButtonCss, driver);
        clickOnElement(signInButtonCss, "SignIn button", driver);
        return this;
    }

    public IndexPageNew clickOnCreateAccountButton() {
        waitForElementVisible(createAccountButtonCss, driver);
        clickOnElement(createAccountButtonCss, "Create account button", driver);
        return this;
    }

    public IndexPageNew addRandomItemsFromIndexPageToCart(int item) {

        ItemPage itemPage = initElements(driver, ItemPage.class);

            //TODO fix add to cart
            for (int i = 0; i < item; i++) {
                Random random = new Random();
                int randomItem = random.nextInt(featuredItems.size());

                waitForElementVisible(featuredItems.get(randomItem), driver);
                clickOnElement(featuredItems.get(randomItem), "Item page", driver);

                try {
                    itemPage.clickOnAddToCartButton();
                    waitForElementVisible(itemPage.addToCartPopUp, driver);
                    getURL(Constants.URL, driver);
                }
                catch (TimeoutException e) {
                      getURL(Constants.URL, driver);
                      i--;
                }
            }
        return this;
        }

    public IndexPageNew addRandomItemsFromIndexPageToCart(Item item) {
        ItemPage itemPage = initElements(driver, ItemPage.class);
        List<WebElement> list = ListUtils.union(bestsellersItems,featuredItems);

        //TODO fix add to cart
        Random random = new Random();
        int randomItem = random.nextInt(list.size());
        clickOnElement(list.get(randomItem), "Item page", driver);

        try{
            waitForElementVisible(itemPage.addToCartButton, driver);
            item.setName(itemPage.getItemName());
            itemPage.clickOnAddToCartButton();
            waitForElementVisible(itemPage.addToCartPopUp, driver);
            getURL(Constants.URL, driver);
        }
        catch (TimeoutException e) {
            getURL(Constants.URL, driver);
            addRandomItemsFromIndexPageToCart(item);
        }
        return this;
    }

    public IndexPageNew clickOnCartButton() {
        waitForElementVisible(cartButton, driver);
        clickOnElement(cartButton, "Cart button", driver);
        return this;
    }

    public String getCartItemsNumber() {
        return cartItemsNumber.getText();
    }

    public IndexPageNew waitForPageTitle() {
        waitForElementVisible(pageTitle, driver);
        return this;
    }

    public IndexPageNew waitForCookieIsPresent(String cookieName) {
        waitForCookie(cookieName, driver);
        return this;
    }
}


