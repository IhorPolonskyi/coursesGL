package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Services.WebElementService;

import java.util.LinkedList;
import java.util.List;

import static utility.Services.WebElementService.clickOnElement;

/**
 * Created by user on 31.05.17.
 */
public class CartPage {

    final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "page-title")
    public WebElement cartItemsNumber;

    @FindBy(css = ".item-title a")
    public List<WebElement> itemsNames;

    @FindBy(xpath = "//*[@class='remove next-previous-assigned']")
    public List<WebElement> removeFromCart;

    public String getCartItemsNumber() {
        return cartItemsNumber.getText();
    }

    public void removeFirstItemFromCart() {
        clickOnElement(removeFromCart.get(0), "Remove from cart", driver);
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
