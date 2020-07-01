package showcase.neodymium.tests.localization;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.components.Title;

@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("localization")
public class NeodymiumLocalizationShowcase extends AbstractTest
{
    /*
     * Most systems under test offer different languages in their front ends that the test ware needs to deal with.
     * Localized content might be subject of the test. Neodymium offers a basic way to handle this. This show case
     * provides possible approaches how to use the Neodymium localization.
     * 
     * REQUIRED CONFIGURATION
     * 
     * config/localization.yaml: - YAML structured content to be used - upmost element is taken as the Neodymium locale
     * - below the Neodymium locale is the localized content, that may get deeper structured
     * 
     */

    @Test
    @Description(value = "Neodymium localization show case")
    public void test()
    {
        // Open homepage in english
        // Note: To use localized string you have to call them like this:
        // Neodymium.localizedText(key-to-string)
        // This will search in the default language as no locale is given over.
        Selenide.open("https://www.xceptance.com/en/");

        // Check title is correct in english
        new Title().validateTitle(Neodymium.localizedText("homepage.title"));

        // Headline example

        // Get headline element
        SelenideElement headLine = $("div.landing-intro > h1");

        // Get subtitle element
        SelenideElement subTitle = $("div.landing-intro > p");

        // Check headline element is visible
        headLine.shouldBe(visible);
        subTitle.shouldBe(visible);

        // Check that headline is correct in English
        headLine.shouldHave(text(Neodymium.localizedText("homepage.headline")));

        // Check that subtitle is correct in English
        subTitle.shouldHave(text(Neodymium.localizedText("homepage.subtitle")));

        // Set locale to German(de_DE), so we can check the German version
        Neodymium.configuration().setProperty("neodymium.locale", "de");

        // Open homepage in German
        Selenide.open("https://www.xceptance.com/de/");

        // Check title is correct in German
        new Title().validateTitle(Neodymium.localizedText("homepage.title"));

        // Headline example

        // Check headline element is visible
        headLine.shouldBe(visible);
        subTitle.shouldBe(visible);

        // Check that headline is correct in German
        headLine.shouldHave(text(Neodymium.localizedText("homepage.headline")));

        // Check that subtitle is correct in German
        // Here the call to localizedText has another locale as second parameter to get
        // a specific String from that specific locale.
        subTitle.shouldHave(text(Neodymium.localizedText("homepage.subtitle", "german")));
    }
}
