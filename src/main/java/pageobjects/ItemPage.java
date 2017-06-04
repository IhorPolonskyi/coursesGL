package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utility.Log;

import static utility.Services.WebElementService.clickOnElement;

/**
 * Created by user on 04.06.17.
 */
public class ItemPage {

    final WebDriver driver;

    public ItemPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "button[class*=add2cart]")
    public WebElement addToCartButton;

    @FindBy(className = "add2cart-popup")
    public WebElement addToCartPopUp;

    @FindBy(css = ".fn.title")
    public WebElement itemName;

    public void clickOnAddToCartButton() {
        clickOnElement(addToCartButton, "Add to cart button", driver);
    }

    public String getItemName() {
        String name = itemName.getText();
        Log.info("Item name: " + name);
        return name;

    }
}
