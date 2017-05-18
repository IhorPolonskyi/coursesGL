package studytestsuite;

import org.testng.annotations.Test;
import pageobjects.AdminPage;
import pageobjects.HeaderPagePart;
import utility.Constants;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static studytestsuite.HelpMethods.adminUser;
import static studytestsuite.HelpMethods.studyTexts;
import static utility.Services.AdditionalService.verifyCookieIsSet;
import static utility.Services.ManageUrlService.getCurrentURL;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.WaiterService.*;

/**
 * Created by igorp on 11/05/17.
 */
public class Study_002_LoginAdminPartTestCase extends DefaultTestCase {

    @Test
    public void test_002(){

        //get index page
        getURL(Constants.URL, driver);

        //check that index page opens
        assertEquals(Constants.URL, getCurrentURL(driver));

        HeaderPagePart headerPagePart = initElements(driver, HeaderPagePart.class);

        //wait for admin login button and click on it
        waitForElementVisible(headerPagePart.adminLoginLinkCss, driver);
        headerPagePart.clickOnGoToAdminPanelLink();

        //wait for admin login page opens
        waitPageLoader(Constants.ADMIN_LOGIN_PAGE, driver);

        AdminPage adminPage = initElements(driver, AdminPage.class);
        waitForElementVisible(adminPage.logInButtonCss, driver);

        //login to admin panel
        adminPage.login(adminUser);
        //TODO fix huck
        waitPageLoader(studyTexts.get("adminPagePartUrl").replace("+","="), driver);

        //wait for trial popup and close it
        waitForElementVisible(adminPage.closeTrialPopUpCross, driver);
        adminPage.clickOnCloseTrialPopupCross();

        //check that admin user logged in - log off link, menu with orders, cookie xid_admin_logged
        assertTrue(headerPagePart.logOffCss.isEnabled());
        assertTrue(adminPage.leftMenu.isDisplayed());

        //cookies check
        waitForCookie(studyTexts.get("adminLoggedInCookie"), driver);
        assertTrue(verifyCookieIsSet(studyTexts.get("adminLoggedInCookie"), driver));

    }


}
