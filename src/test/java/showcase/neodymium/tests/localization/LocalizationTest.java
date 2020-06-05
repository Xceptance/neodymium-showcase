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
public class LocalizationTest extends AbstractTest
{
    @Test
    @Description(value = "Showcase for localization test")
    public void testLocalization()
    {
        // Open homepage in english
        // Note: To use localized string you have to call them like this:
        // Neodymium.localizedText(key-to-string)
        // This will search in the default language as no locale is given over.
        Selenide.open(Neodymium.localizedText("homepage.url"));

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

        // Check that headline is correct in english
        headLine.shouldHave(text(Neodymium.localizedText("homepage.headline")));

        // Check that subtitle is correct in english
        subTitle.shouldHave(text(Neodymium.localizedText("homepage.subtitle")));

        // Close the window
        Selenide.closeWindow();

        // Set locale to german(de_DE), so we can check the german version
        Neodymium.configuration().setProperty("neodymium.locale", "de");

        // Open homepage in german
        Selenide.open(Neodymium.localizedText("homepage.url"));

        // Check title is correct in german
        new Title().validateTitle(Neodymium.localizedText("homepage.title"));

        // Headline example

        // Check headline element is visible
        headLine.shouldBe(visible);
        subTitle.shouldBe(visible);

        // Check that headline is correct in german
        headLine.shouldHave(text(Neodymium.localizedText("homepage.headline")));

        // Check that subtitle is correct in german
        // Here the call to localizedText has another locale as second parameter to get
        // a specific String from that specific locale.
        subTitle.shouldHave(text(Neodymium.localizedText("homepage.subtitle", "german")));
    }
}
