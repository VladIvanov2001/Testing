package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.MainPage;

public class RestaurantChoiceTests extends CommonConditions{
    @Test
    public void chooseRestaurantTest() {
        String url = "https://www.menu.by/";

        WebElement keywordsMetaTag = new MainPage(driver, url)
                .openPage()
                .clickOnAllRestaurantsLinkIfRestaurantIsWorking(11, 22)
                .clickOnLinkToRestaurantPage("Васильки")
                .getKeywordsMetaTag();

        Assert.assertTrue(keywordsMetaTag.getAttribute("content").contains("Васильки"));
    }
}
