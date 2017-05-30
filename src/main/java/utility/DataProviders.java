package utility;

import org.testng.annotations.DataProvider;

/**
 * Created by user on 30.05.17.
 */
public class DataProviders {

    @DataProvider(name = "loginMethod")
    public static Object[][] loginMethod() {
        return new Object[][] {{"button"}, {"enter"}};
    }
}
