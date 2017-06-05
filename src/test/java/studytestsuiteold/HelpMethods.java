package studytestsuite;

import businessobjects.User;
import org.openqa.selenium.WebDriver;
import pageobjects.IndexPage;
import utility.Constants;

import java.util.Map;

import static org.openqa.selenium.support.PageFactory.initElements;
import static utility.Services.FileReaderService.getMap;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.ManageUrlService.refreshPage;
import static utility.Services.WaiterService.waitForElementVisible;

/**
 * Created by user on 14.05.17.
 */
public interface HelpMethods {

    User returningUser = new User("properties/user/defaultUser.properties");
    User adminUser = new User("properties/user/adminUser.properties");
    User newUser = new User("properties/user/newUser.properties");

    Map<String, String> studyTexts = getMap("src/test/resources/properties/studysuite/texts.txt");

    default void huckToCreateAccount(WebDriver driver){

        IndexPage indexPage = initElements(driver, IndexPage.class);
        if(!indexPage.loginErrorMessage.isEmpty( )&& indexPage.getErrorText().equals(studyTexts.get("errorMessage"))){
            //click on create account link
            indexPage.clickOnCreateAccountLink();

            //create new account with generating new email
            waitForElementVisible(indexPage.createAccountButtonCss, driver);
            indexPage.createAccount(returningUser);
            driver.manage().deleteAllCookies();
            refreshPage(driver);
            getURL(Constants.URL, driver);

            waitForElementVisible(indexPage.loginButtonCss, driver);
            indexPage.clickOnLoginButton();
            waitForElementVisible(indexPage.signInButtonCss, driver);
            indexPage.login(returningUser, "enter");

        }
    }


}
