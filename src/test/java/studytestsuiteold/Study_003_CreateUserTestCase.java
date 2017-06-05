package studytestsuiteold;

import org.testng.annotations.Test;
import pageobjectsold.HeaderPagePart;
import pageobjectsold.IndexPage;
import utility.Constants;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static studytestsuiteold.HelpMethods.newUser;
import static studytestsuiteold.HelpMethods.studyTexts;
import static utility.Services.AdditionalService.getCookieValue;
import static utility.Services.AdditionalService.verifyCookieIsSet;
import static utility.Services.ManageUrlService.getCurrentURL;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.WaiterService.waitForCookie;
import static utility.Services.WaiterService.waitForElementVisible;

/**
 * Created by user on 16.05.17.
 */
public class Study_003_CreateUserTestCase extends DefaultTestCase {

    @Test
    public void test_003(){

        //get index page
        getURL(Constants.URL, driver);

        //check that index page opens
        assertEquals(Constants.URL, getCurrentURL(driver));

        IndexPage indexPage = initElements(driver, IndexPage.class);

        //wait for login button and click on it
        waitForElementVisible(indexPage.loginButtonCss, driver);
        indexPage.clickOnLoginButton();
        waitForElementVisible(indexPage.signInButtonCss, driver);

        //click on create account link
        indexPage.clickOnCreateAccountLink();

        //create new account with generating new email
        waitForElementVisible(indexPage.createAccountButtonCss, driver);
        indexPage.createAccount(newUser);

        //check that new account crated - redirect, page title, log off button, cookie recent_login with email value
        HeaderPagePart headerPagePart = initElements(driver, HeaderPagePart.class);
        waitForElementVisible(indexPage.pageTitle, driver);
        //TODO fix huck
        assertTrue(getCurrentURL(driver).contains(studyTexts.get("createdUserPagePartUrl").replace("+","=")));
        assertEquals(indexPage.getPageTitleText(), studyTexts.get("pageTitle"));
        assertTrue(headerPagePart.logOffCss.isEnabled());

        //cookies check
        waitForCookie(studyTexts.get("userLoggedInCookie"), driver);
        assertTrue(verifyCookieIsSet(studyTexts.get("userLoggedInCookie"), driver));
        assertEquals(getCookieValue(studyTexts.get("userLoggedInCookie"), driver), newUser.getEmail()
                .replace("@", "%40").replace("+", "%2B"));

    }
}
