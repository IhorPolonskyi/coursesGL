package studytestsuite;

import org.testng.annotations.Test;
import utility.Constants;

import static utility.Services.ManageUrlService.getURL;

/**
 * Created by user on 19.05.17.
 */
public class Study_004_AuthOwnTestCase extends DefaultTestCase {

    @Test
    public void test_004_AuthLogin(){

        //get index page
        getURL(Constants.URL, driver);

    }

}
