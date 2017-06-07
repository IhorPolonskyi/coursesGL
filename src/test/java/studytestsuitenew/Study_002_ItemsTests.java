package studytestsuitenew;

import businessobjects.Item;
import org.testng.annotations.Test;
import pageobjectsnew.CartPageNew;
import pageobjectsnew.IndexPageNew;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static studytestsuitenew.HelpMethodsNew.studyTexts;

/**
 * Created by user on 06.06.17.
 */
public class Study_002_ItemsTests extends DefaultTestCaseNew {

    @Test
    public void test_001_AddRandomItemsToCart() {
        new IndexPageNew(driver)
                .openSite()
                .addRandomItemsFromIndexPageToCart(2)
                .getCartPage();

        assertTrue(new CartPageNew(driver)
                .getHeaderTextInCart().contains(Integer.toString(2)),
                "Incorrect amount of items in cart");
    }

    @Test
    public void test_002_AddItemAndDeleteFromCart() {
        Item item = new Item();

        new IndexPageNew(driver)
                .openSite()
                .addRandomItemFromIndexPageToCart(item)
                .getCartPage();

        assertEquals(new CartPageNew(driver).getFirstItemName(), item.getName());

        String emptyCartPageText = new CartPageNew(driver)
                .removeFirstItemFromCart()
                .waitForHeaderTextInCart(studyTexts.get("emptyCartText"))
                .getHeaderTextInCart();
        assertEquals(emptyCartPageText, studyTexts.get("emptyCartText"),
                "Incorrect title text");
    }
}
