import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;

public class GoToRestaurantTest {
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void clickOnLink(WebElement link) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", link);
    }

    @BeforeTest (alwaysRun = true)
    public void browserSetup() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "\\src\\drivers\\chromedriver.exe");

        driver = new ChromeDriver();
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    @Test
    public void openVasilkyRestaurantPageTest() {
        driver.get("https://www.menu.by/");

        WebElement addressInput = getElement(By.xpath("//input[@id='new_header_address_search']"));
        addressInput.sendKeys("Беларусь, Минск, Андреевская улица, 7к2");
        addressInput.sendKeys(Keys.ENTER);

        WebElement linkToBurgerKingPage = getElement(By.xpath("//a[@class='title'][contains(text(), 'Burger King')]"));
        Assert.assertTrue(linkToBurgerKingPage.getAttribute("title").contains("Burger King"));

        WebElement linkToKFCPage = getElement(By.xpath("//a[@class='title'][contains(text(), 'KFC')]"));
        Assert.assertTrue(linkToKFCPage.getAttribute("title").contains("KFC"));

        WebElement linkToVasilkyRestaurantPage = getElement(By.xpath("//a[@class='title'][contains(text(), 'Васильки')]"));
        clickOnLink(linkToVasilkyRestaurantPage);

        WebElement keywordsMetaTag = getElement(By.xpath("//head/meta[@name='keywords']"));

        Assert.assertTrue(keywordsMetaTag.getAttribute("content").contains("Васильки"));
    }

    @AfterTest (alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
