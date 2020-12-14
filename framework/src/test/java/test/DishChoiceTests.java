package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.RestaurantPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DishChoiceTests extends CommonConditions{
    @Test
    public void chooseAppetizersFromAllVasilkyMenusTest() {
        String url = "https://www.menu.by/minsk/takeaway/restaurant/vasilki-nezavisimosti.html";

        List<WebElement> actualAppetizers = new RestaurantPage(driver, url)
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
}
