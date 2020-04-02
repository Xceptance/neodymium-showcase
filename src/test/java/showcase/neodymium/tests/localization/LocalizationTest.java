package showcase.neodymium.tests.localization;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.components.Title;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("Localizationtest")
public class LocalizationTest extends AbstractTest {
    @Test
    @Description(value = "Showcase for localization test")
    public void testLocalization() {
        //Open homepage in english
        Selenide.open("https://www.xceptance.com/en/");
        
        //Check title is correct in english
        //Note: To use localized string you have to call them like this:
        //Neodymium.localizedText(key-to-string)
        //This will search in the default language as no locale is given over.
        new Title().validateTitle(Neodymium.localizedText("homepage.title"));
        
        //Headline example
        
        //Get Headline element
        SelenideElement headLine = $("div.landing-intro > h1");
        
        //Check headline element is visible
        headLine.shouldBe(visible);
        
        //Check that headline is correct in english
        headLine.shouldHave(text(Neodymium.localizedText("homepage.headline")));
        
        Selenide.closeWindow();
        
        //Open homepage in german
        Selenide.open("https://www.xceptance.com/de/");
        
        //Check title is correct in german
        //Note: To use localized string with locale you have to call them like this:
        //Neodymium.localizedText(key-to-string, locale)
        //This will return the translated string for this locale, if it exists.
        new Title().validateTitle(Neodymium.localizedText("homepage.title", "de"));
        
        //Headline example
        
        //Check headline element is visible        
        headLine.shouldBe(visible);
        
        //Check that headline is correct in german
        headLine.shouldHave(text(Neodymium.localizedText("homepage.headline", "de")));
    }
}
