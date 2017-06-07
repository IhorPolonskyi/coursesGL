package studytestsuitenew;

import businessobjects.User;
import org.openqa.selenium.WebDriver;
import pageobjectsold.IndexPage;
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
public interface HelpMethodsNew {

    User returningUser = new User("properties/user/defaultUser.properties");
    User adminUser = new User("properties/user/adminUser.properties");
    User newUser = new User("properties/user/newUser.properties");

    Map<String, String> studyTexts = getMap("src/test/resources/properties/studysuite/texts.txt");

}
