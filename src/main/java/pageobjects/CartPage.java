package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public String getCartItemsNumber() {
        return cartItemsNumber.getText();
    }
}
