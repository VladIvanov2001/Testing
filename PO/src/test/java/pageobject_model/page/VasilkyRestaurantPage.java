package pageobject_model.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class VasilkyRestaurantPage extends AbstractPage{
    public static final String HOMEPAGE_URL = "https://www.menu.by/minsk/takeaway/restaurant/vasilki-nezavisimosti.html";

    @FindBy(id = "a-lnk-appetizers_127000")
    private WebElement appetizersLink;

    @FindBy(xpath = "//div[@class='fl prod-content']/a[contains(@keywords, 'Закуски')]")
    private List<WebElement> appetizersList;

    public VasilkyRestaurantPage(WebDriver driver) {
        super(driver);
    }

    public VasilkyRestaurantPage openPage() {
        driver.get(HOMEPAGE_URL);

        return this;
    }

    public VasilkyRestaurantPage clickOnAppetizersLink() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", appetizersLink);

        return this;
    }

    public List<WebElement> getFirstFourAppetizers() {
        return appetizersList.stream().limit(4).collect(Collectors.toList());
    }
}
