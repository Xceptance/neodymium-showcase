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
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Test Developer")
@Tag("shadow dom")
@DisplayName("Texttest")
public class TextTest extends AbstractTest
{
    /*
     * Shadow DOM is a technique for encapsulation. It allows the user to create a separate DOM. This new DOM can added
     * to the normal DOM tree of a website without any side effects. It is a approach to create reusable components
     * which can be inserted to every DOM tree easily. This show case provides possible approaches how to handle Shadow
     * DOM elements.
     * 
     * REQUIRED CONFIGURATION
     * 
     * there are no required configurations - both tests are running without any adjustments inside the configuration
     * files
     * 
     */

    @Test
    @Description(value = "Check that text field exists and control text")
    public void testTextField()
    {
        // Open javadoc page
        Selenide.open("https://javascript.info/shadow-dom");

        // check title
        new Title().validateTitle("Shadow DOM");

        // Verifies that toolbar is visible
        $("div.sitetoolbar").shouldBe(visible);

        // Verifies that content page is visible
        $("div.page__inner").shouldBe(visible);

        // Verifies that sidebar is visible
        $("div.sidebar__inner").shouldBe(visible);

        // Verifies that footer is visible
        $("div.page-footer").shouldBe(visible);

        // Asserts there's categories in the sidebar.
        $$("div.sidebar__section").shouldHave(sizeGreaterThan(0));

        // switch to iFrame of content
        switchTo().frame($("iframe[style=\"height:60px\"]"));

        // check text field
        $(Selectors.shadowCss("p", "show-hello[name=John]")).shouldBe(visible);

        // check text field has text
        $(Selectors.shadowCss("p", "show-hello[name=John]")).shouldHave(text("Hello, John"));

        // switch back to Mainframe
        switchTo().defaultContent();
    }
}
