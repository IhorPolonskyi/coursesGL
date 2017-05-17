package studytestsuite;

import org.testng.annotations.Test;
import pageobjects.HeaderPagePart;
import pageobjects.IndexPage;
import utility.Constants;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static studytestsuite.HelpMethods.returningUser;
import static studytestsuite.HelpMethods.studyTexts;
import static utility.Services.AdditionalService.getCookieValue;
import static utility.Services.AdditionalService.verifyCookieIsSet;
import static utility.Services.ManageUrlService.getCurrentURL;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.WaiterService.waitForCookie;
import static utility.Services.WaiterService.waitForElementVisible;

/**
 * Created by igorp on 11/05/17.
 */
public class Study_001_LoginUserPartTestCase extends DefaultTestCase {

    @Test
    public void test_001(){

        //get index page
        getURL(Constants.URL, driver);

        //check that index page opens
        assertEquals(Constants.URL, getCurrentURL(driver));

        IndexPage indexPage = initElements(driver, IndexPage.class);

        //wait for login button and click on it
        waitForElementVisible(indexPage.loginButtonCss, driver);
        indexPage.clickOnLoginButton();
        waitForElementVisible(indexPage.signInButtonCss, driver);

        //login with returning user
        indexPage.login(returningUser);

        if(indexPage.loginErrorMessage.isEnabled() && indexPage.getErrorText().equals(studyTexts.get("errorMessage"))){
            //click on create account link
            indexPage.clickOnCreateAccountLink();

            //create new account with generating new email
            waitForElementVisible(indexPage.createAccountButtonCss, driver);
            indexPage.createAccount(returningUser);
            indexPage.clickOnLoginButton();
            waitForElementVisible(indexPage.signInButtonCss, driver);
            indexPage.login(returningUser);

        }

        HeaderPagePart headerPagePart = initElements(driver, HeaderPagePart.class);
        waitForElementVisible(indexPage.pageTitle, driver);

        //check that user logged in - log off link, cookie recent_login
        assertTrue(headerPagePart.logOffCss.isEnabled());

        //cookies check
        waitForCookie(studyTexts.get("userLoggedInCookie"), driver);
        assertTrue(verifyCookieIsSet(studyTexts.get("userLoggedInCookie"), driver));
        assertEquals(getCookieValue(studyTexts.get("userLoggedInCookie"), driver), returningUser.getEmail().replace("@", "%40"));

    }


}
