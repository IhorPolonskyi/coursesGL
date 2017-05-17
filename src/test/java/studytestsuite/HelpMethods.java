package studytestsuite;

import businessobjects.User;

import java.util.Map;

import static utility.Services.FileReaderService.getMap;

/**
 * Created by user on 14.05.17.
 */
public interface HelpMethods {

    User returningUser = new User("properties/user/defaultUser.properties");
    User adminUser = new User("properties/user/adminUser.properties");
    User newUser = new User("properties/user/newUser.properties");

    Map<String, String> studyTexts = getMap("properties/studysuite/texts.txt");
}
