package studytestsuitenew;

import org.testng.annotations.Test;
import pageobjectsnew.IndexPage;
import utility.DataProviders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static studytestsuitenew.HelpMethodsNew.studyTexts;
import static studytestsuiteold.HelpMethods.returningUser;
import static utility.Services.AdditionalService.getCookieValue;
import static utility.Services.AdditionalService.verifyCookieIsSet;
import static utility.Services.WaiterService.waitForCookie;

/**
 * Created by user on 05.06.17.
 */
public class AuthTests extends DefaultTestCaseNew {

    @Test(dataProvider = "loginMethod", dataProviderClass = DataProviders.class)
    public void UserShouldBeLoginUserPart(String method) {
        IndexPage indexPage = new IndexPage(driver)
                .openSite()
                .clickOnLoginButton()
                .login(returningUser, method)

        //checks
        
        waitForCookie(studyTexts.get("userLoggedInCookie"), driver);
        assertTrue(verifyCookieIsSet(studyTexts.get("userLoggedInCookie"), driver));
        assertEquals(getCookieValue(studyTexts.get("userLoggedInCookie"), driver), returningUser.getEmail().replace("@", "%40"));

    }
}
