package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.MainPage;

import java.util.Arrays;
import java.util.List;

public class SearchTests extends CommonConditions{
    @Test
    public void sendCityToSearchInput() {
        String url = "https://www.menu.by/";

        List<String> actualSearchResults = new MainPage(driver, url)
                .openPage()
                .waitForInputDropdownList("Минск")
                .getInputSuggestions();

        List<String> expectedSearchResults = Arrays.asList(
                "Минск",
                "Минская область",
                "Минск, Национальный аэропорт Минск",
                "Минская область, Минский район",
                "Минск, Национальный аэропорт Минск, 61"
        );

        Assert.assertEquals(actualSearchResults, expectedSearchResults);
    }
}
