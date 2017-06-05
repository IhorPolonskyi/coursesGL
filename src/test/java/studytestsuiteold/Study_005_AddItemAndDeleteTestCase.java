package studytestsuiteold;

import businessobjects.Item;
import org.testng.annotations.Test;
import pageobjectsold.CartPage;
import pageobjectsold.IndexPage;
import utility.Constants;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static studytestsuiteold.HelpMethods.studyTexts;
import static utility.Services.ManageUrlService.getURL;
import static utility.Services.WaiterService.waitForTextVisible;

/**
 * Created by user on 31.05.17.
 */
public class Study_005_AddItemAndDeleteTestCase extends DefaultTestCase {

    @Test
    public void test_005(){

        //get index page
        getURL(Constants.URL, driver);

        Item item = new Item();

        IndexPage indexPage = initElements(driver, IndexPage.class);
        indexPage.addRandomItemsFromIndexPageToCart(item);

        //get cart page
        getURL(Constants.CART_PAGE, driver);

        CartPage cartPage = initElements(driver, CartPage.class);
        assertTrue(cartPage.getCartItemsNumber().contains(Integer.toString(1)),
                "Incorrect amount of items in cart");
        assertEquals(cartPage.getFirstItemName(), item.getName());

        cartPage.removeFirstItemFromCart();

        waitForTextVisible(studyTexts.get("emptyCartText"), cartPage.cartItemsNumber, driver);
        assertEquals(cartPage.getCartItemsNumber(), studyTexts.get("emptyCartText"),
                "Incorrect title text");


    }

}
