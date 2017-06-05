package studytestsuiteold;

import org.testng.annotations.Test;
import pageobjectsold.CartPage;
import pageobjectsold.IndexPage;
import utility.Constants;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.testng.Assert.assertTrue;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.WaiterService.waitForElementVisible;

/**
 * Created by user on 31.05.17.
 */
public class Study_004_AddItemsToCartTestCase extends DefaultTestCase {

    @Test
    public void test_004(){

        //get index page
        getURL(Constants.URL, driver);

        IndexPage indexPage = initElements(driver, IndexPage.class);
        indexPage.addRandomItemsFromIndexPageToCart(2);

        indexPage.clickOnCartButton();
        waitForElementVisible(indexPage.cartPopUp, driver);

        //TODO upgrade to equals
        assertTrue(indexPage.getCartItemsNumber().contains(Integer.toString(2)),
                "Incorrect amount of items in cart");

        //get cart page
        getURL(Constants.CART_PAGE, driver);

        CartPage cartPage = initElements(driver, CartPage.class);
        assertTrue(cartPage.getCartItemsNumber().contains(Integer.toString(2)),
                "Incorrect amount of items in cart");

    }

}
