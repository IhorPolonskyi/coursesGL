package pageobjectsnew;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;

import static utility.Services.WebElementService.clickOnElement;

/**
 * Created by user on 04.06.17.
 */
public class ItemPageNew {

    protected WebDriver driver;

    public ItemPageNew(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[class*=add2cart]")
    public WebElement addToCartButton;

    @FindBy(className = "add2cart-popup")
    public WebElement addToCartPopUp;

    @FindBy(css = ".fn.title")
    public WebElement itemName;

    public ItemPageNew clickOnAddToCartButton() {
        clickOnElement(addToCartButton, "Add to cart button", driver);
        return this;
    }

    public String getItemName() {
        String name = itemName.getText();
        Log.info("Item name: " + name);
        return name;

    }
}
