package pageobject_model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected String pageURL;

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver, String pageURL) {
        this.driver = driver;
        this.pageURL = pageURL;
        PageFactory.initElements(driver, this);
    }
}
