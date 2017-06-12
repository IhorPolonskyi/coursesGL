package pageobjectsnew;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedList;
import java.util.List;

import static utility.Services.WaiterService.waitForTextVisible;
import static utility.Services.WebElementService.clickOnElement;

/**
 * Created by user on 31.05.17.
 */
public class CartPageNew {

    protected WebDriver driver;

    public CartPageNew(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "page-title")
    public WebElement cartItemsNumber;

    @FindBy(css = ".item-title a")
    public List<WebElement> itemsNames;

    @FindBy(css = "a[class*=remove]")
    public List<WebElement> removeFromCart;

    public String getHeaderTextInCart() {
        return cartItemsNumber.getText();
    }

    public CartPageNew waitForHeaderTextInCart(String text) {
        waitForTextVisible(text, cartItemsNumber, driver);
        return this;
    }

    public CartPageNew removeFirstItemFromCart() {
        clickOnElement(removeFromCart.get(0), "Remove from cart", driver);
        return this;
    }

    public String getFirstItemName() {
        return itemsNames.get(0).getText();
    }

    public List<String> getAllItemsNames() {
        List<String> list = new LinkedList<>();
        itemsNames.forEach(element ->{
            list.add(element.getText());
        });
        return list;
    }


}
