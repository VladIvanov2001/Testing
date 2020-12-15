package pageobject_model.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.Calendar;

public class LandingPage extends AbstractPage{
    @FindBy(xpath = "//input[@id='new_header_address_search']")
    private WebElement addressInput;

    @FindBy(xpath = "//a[@class='last ']")
    private WebElement allRestaurantsLink;

    @FindBy(xpath = "//a[@class='title'][contains(text(), 'Васильки')]")
    private WebElement linkToVasilkyRestaurantPage;

    @FindBy(xpath = "//head/meta[@name='keywords']")
    private WebElement keywordsMetaTag;

    public LandingPage(WebDriver driver, String pageURL) {
        super(driver, pageURL);
    }

    public LandingPage openPage() {
        driver.get(pageURL);

        return this;
    }

    public LandingPage enterAddressInAddressInput() {
        addressInput.sendKeys("Беларусь, Минск, Андреевская улица, 7к2");
        addressInput.sendKeys(Keys.ENTER);

        return this;
    }

    public LandingPage clickOnAllRestaurantsLinkIfVasilkyIsWorking() {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (currentHour < 11 || currentHour >= 22) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click()", allRestaurantsLink);
        }

        return this;
    }

    public LandingPage clickOnLinkToVasilkyRestaurantPage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", linkToVasilkyRestaurantPage);

        return this;
    }

    public WebElement getKeywordsMetaTag() {
        return keywordsMetaTag;
    }
}
