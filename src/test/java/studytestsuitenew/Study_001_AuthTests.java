package studytestsuitenew;

import org.testng.annotations.Test;
import pageobjectsnew.AdminPageNew;
import pageobjectsnew.IndexPageNew;
import studytestsuiteold.HelpMethods;
import utility.DataProviders;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static studytestsuitenew.HelpMethodsNew.adminUser;
import static studytestsuitenew.HelpMethodsNew.studyTexts;
import static studytestsuiteold.HelpMethods.newUser;
import static studytestsuiteold.HelpMethods.returningUser;
import static utility.Services.AdditionalService.getCookieValue;
import static utility.Services.AdditionalService.verifyCookieIsSet;
import static utility.Services.ManageUrlService.getCurrentURL;
import static utility.Services.WaiterService.waitForCookie;

/**
 * Created by user on 05.06.17.
 */
public class Study_001_AuthTests extends DefaultTestCaseNew {

    @Test(dataProvider = "loginMethod", dataProviderClass = DataProviders.class)
    public void test_001_UserShouldBeLoginUserPart(String method) {
        IndexPageNew indexPage = new IndexPageNew(driver)
                .openSite()
                .clickOnLoginButton()
                .login(returningUser, method)
                .waitForPageTitle()
                .waitForCookieIsPresent(studyTexts.get("userLoggedInCookie"));

        //asserts
        assertTrue(verifyCookieIsSet(studyTexts.get("userLoggedInCookie"), driver));
        assertEquals(getCookieValue(studyTexts.get("userLoggedInCookie"), driver), returningUser.getEmail().replace("@", "%40"));

    }

    @Test(dataProvider = "loginMethod", dataProviderClass = DataProviders.class)
    public void test_002_UserShouldBeLoginAdminPart(String method) {
        IndexPageNew indexPage = new IndexPageNew(driver)
                .openSite()
                .clickOnGoToAdminPanelLink();

        AdminPageNew adminPage = new AdminPageNew(driver)
                .login(adminUser, method)
                .waitAdminPageLoad(studyTexts.get("adminPagePartUrl"))
                .clickOnCloseTrialPopupCross();

        //asserts
        assertTrue(indexPage.logOffCss.isEnabled());
        assertTrue(adminPage.leftMenu.isDisplayed());

        //cookies asserts
        waitForCookie(HelpMethods.studyTexts.get("adminLoggedInCookie"), driver);
        assertTrue(verifyCookieIsSet(HelpMethods.studyTexts.get("adminLoggedInCookie"), driver));
    }

    @Test
    public void test_003_UserCreateNew() {
        IndexPageNew indexPage = new IndexPageNew(driver)
                .openSite()
                .clickOnLoginButton()
                .clickOnCreateAccountLink()
                .createAccount(newUser)
                .waitForPageTitle()
                .waitForCookieIsPresent(studyTexts.get("userLoggedInCookie"));

        //asserts
        assertTrue(getCurrentURL(driver).contains(studyTexts.get("createdUserPagePartUrl").replace("+","=")));
        assertEquals(indexPage.getPageTitleText(), studyTexts.get("pageTitle"));
        assertTrue(indexPage.logOffCss.isEnabled());

        //cookies asserts
        assertTrue(verifyCookieIsSet(HelpMethods.studyTexts.get("userLoggedInCookie"), driver));
        assertEquals(getCookieValue(HelpMethods.studyTexts.get("userLoggedInCookie"), driver), newUser.getEmail()
                .replace("@", "%40").replace("+", "%2B"));

    }

}
