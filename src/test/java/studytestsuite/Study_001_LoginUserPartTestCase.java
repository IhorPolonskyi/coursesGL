package studytestsuite;

import org.testng.annotations.Test;
import pageobjects.HeaderPagePart;
import pageobjects.IndexPage;
import utility.Constants;
import utility.DataProviders;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utility.Services.AdditionalService.getCookieValue;
import static utility.Services.AdditionalService.verifyCookieIsSet;
import static utility.Services.ManageUrlService.getCurrentURL;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.WaiterService.waitForCookie;
import static utility.Services.WaiterService.waitForElementVisible;

/**
 * Created by igorp on 11/05/17.
 */
public class Study_001_LoginUserPartTestCase extends DefaultTestCase implements HelpMethods {

    @Test(dataProvider = "loginMethod", dataProviderClass = DataProviders.class)
    public void test_001(String method){

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
        indexPage.login(returningUser, method);

        //if db was cleared, this method try to recreate this user
        huckToCreateAccount(driver);

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
