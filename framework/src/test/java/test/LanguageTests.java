package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.MainPage;

public class LanguageTests extends CommonConditions{
    @Test
    public void changeLanguageFromBelarusianToEnglishTest() {
        String url = "https://www.menu.by/";

        String mainPageLinkText = new MainPage(driver, url)
                .openPage()
                .chooseLanguage("en")
                .getMainPageLinkText();

        Assert.assertEquals(mainPageLinkText, "Home");
    }
}
