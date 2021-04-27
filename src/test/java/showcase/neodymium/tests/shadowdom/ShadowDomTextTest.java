package showcase.neodymium.tests.shadowdom;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;

import org.junit.Test;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.components.Title;

/**
 * Shadow DOM is a technique for encapsulation. It allows the user to create a separate and independent DOM. This second
 * DOM can be nested into the DOM tree of a web site without any side effects. This approach is used to build reusable
 * components which can be integrated on every page without introducing side effect.<br>
 * This show case provides possible approaches how to perform tests for Shadow DOM elements.<br>
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Test Developer")
@Tag("shadow dom")
@DisplayName("Texttest")
public class ShadowDomTextTest extends AbstractTest
{
    @Test
    @Description(value = "Check that text field exists and control text")
    public void testTextField()
    {
        // Open javadoc page
        Selenide.open("https://javascript.info/shadow-dom");

        // check title
        new Title().validateTitle("Shadow DOM");

        // check that the toolbar is visible
        $("div.sitetoolbar").shouldBe(visible);

        // check that the content page is visible
        $("div.page__inner").shouldBe(visible);

        // check that the sidebar is visible
        $("div.sidebar__inner").shouldBe(visible);

        // check that the footer is visible
        $("div.page-footer").shouldBe(visible);

        // check that there are sections within the sidebar
        $$("div.sidebar__section").shouldHave(sizeGreaterThan(0));

        // switch to the iFrame
        switchTo().frame($("iframe[style=\"height:60px\"]"));

        // check that the text field is visible
        $(Selectors.shadowCss("p", "show-hello[name=John]")).shouldBe(visible);

        // check the correct content of the text field
        $(Selectors.shadowCss("p", "show-hello[name=John]")).shouldHave(text("Hello, John"));

        // leave the iFrame
        switchTo().defaultContent();
    }
}
