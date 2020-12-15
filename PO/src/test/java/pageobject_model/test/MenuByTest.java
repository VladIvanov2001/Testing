package pageobject_model.test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject_model.page.LandingPage;
import pageobject_model.page.VasilkyRestaurantPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MenuByTest {
    private WebDriver driver;

    @BeforeTest (alwaysRun = true)
    public void browserSetup() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "\\src\\test\\resources\\chromedriver.exe");

        driver = new ChromeDriver();
    }

    @Test
    public void openRestaurantPageTest() {
        WebElement keywordsMetaTag = new LandingPage(driver, "https://www.menu.by/")
                .openPage()
                .enterAddressInAddressInput()
                .clickOnAllRestaurantsLinkIfVasilkyIsWorking()
                .clickOnLinkToVasilkyRestaurantPage()
                .getKeywordsMetaTag();

        Assert.assertTrue(keywordsMetaTag.getAttribute("content").contains("Васильки"));
    }

    @Test
    public void chooseAppetizersFromAllRestaurantMenusTest() {
        List<WebElement> actualAppetizers = new VasilkyRestaurantPage(driver, "https://www.menu.by/minsk/takeaway/restaurant/vasilki-nezavisimosti.html")
                .openPage()
                .clickOnAppetizersLink()
                .getFirstFourAppetizers();

        List<String> expectedAppetizerNames = Arrays.asList(
                "#1 Домашние сырные палочки с пикантным соусом",
                "#2 Крылышки по-охотничьи",
                "#3 Овощи гриль",
                "#4 Пивной набор"
        );

        Assert.assertEquals(actualAppetizers.stream().map(WebElement::getText).collect(Collectors.toList()), expectedAppetizerNames);
    }

    @AfterTest (alwaysRun = true)
    public void browserQuit() {
        driver.quit();
        driver = null;
    }
}
