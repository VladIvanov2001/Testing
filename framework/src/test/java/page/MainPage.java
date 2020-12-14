package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import util.PageUtils;

import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MainPage extends AbstractPage{
    private Wait<WebDriver> wait;

    private HashMap<String, Integer> languageStringsToIndexesDictionary;
    private HashMap<String, String> languageStringsToPageURLsDictionary;

    @FindBy(xpath = "//meta[@http-equiv='content-language']")
    private WebElement langMetaTag;

    @FindBy(xpath = "//div[contains(@class,'new_header_top_block_lang fl')]//a")
    private List<WebElement> languagesList;

    @FindBy(xpath = "//a[@class='_home active']")
    private WebElement mainPageLink;

    @FindBy(xpath = "//input[@id='new_header_address_search']")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@id='autocomplete_address_street_name']/div")
    private List<WebElement> dropdownItems;

    @FindBy(xpath = "//a[@class='last ']")
    private WebElement allRestaurantsLink;

    @FindBy(xpath = "//head/meta[@name='keywords']")
    private WebElement keywordsMetaTag;

    public MainPage(WebDriver driver, String url) {
        super(driver, url);

        languageStringsToIndexesDictionary = new HashMap<>();
        languageStringsToIndexesDictionary.put("by", 0);
        languageStringsToIndexesDictionary.put("ru", 1);
        languageStringsToIndexesDictionary.put("en", 2);

        languageStringsToPageURLsDictionary = new HashMap<>();
        languageStringsToPageURLsDictionary.put("by", "https://www.menu.by/by/");
        languageStringsToPageURLsDictionary.put("ru", "https://www.menu.by/");
        languageStringsToPageURLsDictionary.put("en", "https://www.menu.by/en/");

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public MainPage openPage() {
        driver.get(url);
        return this;
    }

    public MainPage chooseLanguage(String newLanguage) {
        int langIndex = languageStringsToIndexesDictionary.get(newLanguage);

        javascriptExecutor.executeScript(
                "document.querySelector('div.new_header_top_block_lang.fl.intro_js_lang > ul > li:nth-child(' + arguments[0] + ') > a').setAttribute('href', arguments[1])",
                langIndex + 1,
                languageStringsToPageURLsDictionary.get(newLanguage));

        WebElement languageLink = languagesList.get(langIndex);
        languageLink.click();

        logger.info("update page with new language: " + newLanguage);
        return this;
    }

    public String getMainPageLinkText() {
        return mainPageLink.getText();
    }

    public MainPage waitForInputDropdownList(String searchString) {
        searchInput.click();
        searchInput.sendKeys(searchString);

        while (true) {
            if (PageUtils.getWebElementsByXpath(wait, "//div[@id='autocomplete_address_street_name']/div").size() > 0) {
                break;
            }
        }

        logger.info("waited till dropdown menu appeared");
        return this;
    }

    public List<String> getInputSuggestions() {
        return dropdownItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public MainPage clickOnAllRestaurantsLinkIfRestaurantIsWorking(int restaurantOpeningTime, int restaurantClosingTime) {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (currentHour < restaurantOpeningTime || currentHour >= restaurantClosingTime) {
            PageUtils.clickOnElement(javascriptExecutor, allRestaurantsLink);
            logger.info("clicked on all restaurants link");
        }

        return this;
    }

    public MainPage clickOnLinkToRestaurantPage(String restaurantName) {
        WebElement restaurantLink = PageUtils.getWebElementByXpath(wait, String.format("//a[@class='title'][contains(text(), '%s')]", restaurantName));
        PageUtils.clickOnElement(javascriptExecutor, restaurantLink);

        logger.info("clicked on link to restaurant page");
        return this;
    }

    public WebElement getKeywordsMetaTag() {
        return keywordsMetaTag;
    }
}
