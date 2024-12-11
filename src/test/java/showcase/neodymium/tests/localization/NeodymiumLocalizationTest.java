package showcase.neodymium.tests.localization;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.components.Title;

/**
 * Most websites provide their content in different languages to address different users. Due to the fact test
 * automation has often to work with localized content or even validate that general localized formats (e.G. the price
 * format) are correct. Neodymium provides support to handle this. This showcase demonstrates a possible approach.<br>
 * The localization file is <i>config/localization.yaml</i> and Neodymium introduces a override mechanism. The most
 * specific definition for a key and a locale will be taken. Otherwise, a default value is taken or an exception is
 * raised if no value for the key could be found within the localization file.</li>
 * </ul>
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("localization")
public class NeodymiumLocalizationTest extends AbstractTest
{
    @NeodymiumTest
    @Description(value = "Neodymium localization showcase")
    public void test()
    {
        /*
         * Testing the page in English
         */

        // open home page in English
        Selenide.open("https://www.xceptance.com/en/");

        // to retrieve a localized string you have to call Neodymium.localizedText("key-to-string")
        // this will search in the configured default language as no specific locale is provided as parameter
        String titleText = Neodymium.localizedText("homepage.title");

        // check page title is correct in English
        new Title().validateTitle(titleText);

        // get headline and subtitle element only needs to be done once
        SelenideElement headLine = $("div.landing-intro > h1");
        SelenideElement subTitle = $("div.landing-intro > p");

        // check headline and subtitle are visible
        headLine.shouldBe(visible);
        subTitle.shouldBe(visible);

        // check that headline is correct in English
        headLine.shouldHave(text(Neodymium.localizedText("homepage.headline")));

        // check that subtitle is correct in English
        subTitle.shouldHave(text(Neodymium.localizedText("homepage.subtitle")));

        /*
         * Testing the page in German
         */

        // set locale to German(de_DE), so we can check the German version
        Neodymium.configuration().setProperty("neodymium.locale", "de_DE");

        // open home page in German
        Selenide.open("https://www.xceptance.com/de/");

        // check page title is correct in German
        new Title().validateTitle(Neodymium.localizedText("homepage.title"));

        // check headline and subtitle are visible
        headLine.shouldBe(visible);
        subTitle.shouldBe(visible);

        // check that headline is correct in German
        headLine.shouldHave(text(Neodymium.localizedText("homepage.headline")));

        // here the localizedText call demonstrates how retrieve the value for a specific locale if needed
        // note: this example is a constructed normally you wouldn't divide a translation like this
        String germanSubTitle = Neodymium.localizedText("homepage.subtitle", "de_AT");

        // check that subtitle is correct in German
        subTitle.shouldHave(text(germanSubTitle));
    }
}
