package pageobjectsold;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utility.Services.WebElementService.clickOnElement;

/**
 * Created by igorp on 15/05/17.
 */
public class HeaderPagePart {

    final WebDriver driver;

    public HeaderPagePart(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "li[class*=logoff]")
    public WebElement logOffCss;

    @FindBy(css = "li.tab.admin a")
    public WebElement adminLoginLinkCss;

    public void clickOnGoToAdminPanelLink() {
        clickOnElement(adminLoginLinkCss, "Admin login link", driver);
    }
}
