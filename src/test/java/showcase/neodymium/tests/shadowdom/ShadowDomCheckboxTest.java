package showcase.neodymium.tests.shadowdom;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.components.Title;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Shadow DOM is a technique for encapsulation. It allows the user to create a separate and independent DOM. This second DOM can be nested into the DOM tree of
 * a website without any side effects. This approach is used to build reusable components which can be integrated on every page without introducing side
 * effect.<br> This showcase provides possible approaches how to perform tests for Shadow DOM elements.<br>
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Test Developer")
@Tag("shadow dom")
@DisplayName("Checkboxtest")
public class ShadowDomCheckboxTest extends AbstractTest
{
    @NeodymiumTest
    @Description(value = "Showcase for nested shadow DOM.")
    public void testNestedShadowDOM()
    {
        // open demo page
        Selenide.open("https://demo.vaadin.com/invoice-editor-app/");

        // check if the correct site was opened
        $("invoice-editor-app").should(exist);

        // check title
        new Title().validateTitle("invoice-editor");

        /*
         * Nested shadow DOM demo test
         *
         * Since shadow DOM can be nested into any DOM it's possible to have a shadow DOM within a shadow DOM. The
         * following tests demonstrates how you can handle this.
         */

        // CSS selector for the element of which the shadow-root is a child
        String shadowHost = "invoice-editor-app";

        // CSS-selector for the element within the shadow DOM tree, which has the nested shadow DOM
        String nestedShadowHost = "link-banner";

        // CSS selector for the first target element
        // This has to identify the element within the nested shadow DOM tree
        String target = "#explainer";

        // Scroll to button
        $(shadowHost).scrollTo();

        // check if target is not visible
        // Signature of the method shadowCss is
        // shadowCss(target-element, parent-shadow-host, list-of-nested-shadow-hosts)
        // Note: the last parameter excepts either a simple string or a list of strings
        // the list has to contain in order all the nested shadow hosts that need to be
        // found, to be able to find the target element.
        $(Selectors.shadowCss(target, shadowHost, nestedShadowHost)).shouldNotBe(visible);
    }

    @NeodymiumTest
    @Description(value = "Simple showcase for usage of shadow DOM")
    public void testSimpleShadowDOM()
    {
        // open demo page
        Selenide.open("https://demo.vaadin.com/invoice-editor-app/");

        // check if the correct site was opened
        $("invoice-editor-app").should(exist);

        // check title
        new Title().validateTitle("invoice-editor");

        /*
         * Basic shadow DOM demo test
         *
         * This is a straight forward approach how to validate shadow DOM elements.
         */

        // CSS selector for the element of which the shadow-root is a child
        String shadowHost = "invoice-editor-app";

        // CSS selector for the target element
        String target = ".currency-selector";

        // Get currency web component
        // Signature for the shadowCss method for this simple case is shadowCss(target-element, parent-shadow-host)
        SelenideElement currencySelect = $(Selectors.shadowCss(target, shadowHost));

        // check select component has the default value
        currencySelect.shouldHave(value("EUR"));

        // click select component
        $(currencySelect).click();

        // perform an interaction to change the value of the select component
        new Actions(Neodymium.getRemoteWebDriver()).sendKeys("G", Keys.ENTER).build().perform();

        // check select component has selected value
        currencySelect.shouldHave(value("GBP"));
    }
}
