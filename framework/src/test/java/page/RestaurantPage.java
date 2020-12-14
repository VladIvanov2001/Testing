package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.PageUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantPage extends AbstractPage{
    @FindBy(id = "a-lnk-appetizers_127000")
    private WebElement appetizersLink;

    @FindBy(xpath = "//div[@class='fl prod-content']/a[contains(@keywords, 'Закуски')]")
    private List<WebElement> appetizersList;

    public RestaurantPage(WebDriver driver, String url) {
        super(driver, url);
    }

    public RestaurantPage openPage() {
        driver.get(url);
        return this;
    }

    public RestaurantPage clickOnAppetizersLink() {
        PageUtils.clickOnElement(javascriptExecutor, appetizersLink);

        logger.info("clicked on appetizers link");
        return this;
    }

    public List<WebElement> getFirstFourAppetizers() {
        return appetizersList.stream().limit(4).collect(Collectors.toList());
    }
}
