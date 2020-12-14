package util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;

public class PageUtils {
    public static List<WebElement> getWebElementsByXpath(Wait<WebDriver> wait, String xpath) {
        return wait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public static WebElement getWebElementByXpath(Wait<WebDriver> wait, String xpath) {
        return wait
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public static void clickOnElement(JavascriptExecutor javascriptExecutor, WebElement element) {
        javascriptExecutor.executeScript("arguments[0].click()", element);
    }
}
