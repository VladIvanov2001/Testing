package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected JavascriptExecutor javascriptExecutor;
    protected String url;
    protected final Logger logger = LogManager.getLogger();

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver, String url) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) driver;
        this.url = url;
        PageFactory.initElements(driver, this);
    }
}
