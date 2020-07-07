package showcase.neodymium.tests.shadowdom;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.Test;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

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
@DisplayName("Checkboxtest")
public class ShadowDomCheckboxTest extends AbstractTest
{
    /*
     * Shadow DOM is a technique for encapsulation. It allows the user to create a separate and independent DOM. This
     * second DOM can be nested into the DOM tree of a website without any side effects. This approach is used to build
     * reusable components which can be integrated on every page without introducing side effect. This show case
     * provides possible approaches how to perform tests for Shadow DOM elements.
     * 
     * REQUIRED CONFIGURATION
     * 
     * This test case does not require a specific setup(or adjustment) of the Neodymium configuration.
     */
    @Test
    @Description(value = "Showcase for nested shadow DOM.")
    public void testNestedShadowDOM()
    {
        // open demo page
        Selenide.open("https://mwc-demos.glitch.me");

        // check if the correct site was opened
        $("#header").should(exist);

        // check that the content frame exists
        $("div.sections-wrapper").shouldBe(visible);

        // check the correct amount of checkboxes
        $$("div > mwc-checkbox").shouldHaveSize(3);

        // check title
        new Title().validateTitle("MWC Playground");

        /*
         * Nested shadow DOM demo test
         * 
         * Since shadow DOM can be nested into any DOM it's possible to have a shadow DOM within a shadow DOM. The
         * following tests demonstrates how you can handle this.
         */

        // CSS selector for the element of which the shadow-root is a child
        String shadowHost = "mwc-button[label='toggle menu']";

        // CSS-selector for the element within the shadow DOM tree, which has the nested shadow DOM
        String nestedShadowHost = "#button > mwc-ripple";

        // CSS selector for the first target element
        // This has to identify the element within the nested shadow DOM tree
        String target = "div.mdc-ripple-surface";

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

    @Test
    @Description(value = "Simple showcase for usage of shadow DOM")
    public void testSimpleShadowDOM()
    {
        // open demo page
        Selenide.open("https://mwc-demos.glitch.me");

        // check if the correct site was opened
        $("#header").should(exist);

        // check that the content frame exists
        $("div.sections-wrapper").shouldBe(visible);

        // check the correct amount of checkboxes
        $$("div > mwc-checkbox").shouldHaveSize(3);

        // check title
        new Title().validateTitle("MWC Playground");

        /*
         * Basic shadow DOM demo test
         * 
         * This is a straight forward approach how to validate shadow DOM elements.
         */

        // CSS selector for the element of which the shadow-root is a child
        String shadowHost = "mwc-checkbox";

        // CSS selector for the target element
        String target = "div.mdc-checkbox";

        // Get checkbox
        // Signature for the shadowCss method for this simple case is
        // shadowCss(target-element, parent-shadow-host)
        SelenideElement checkbox = $(Selectors.shadowCss(target, shadowHost));

        // check checkbox has not the selected class
        checkbox.shouldHave(not(cssClass("mdc-checkbox--selected")));

        // click checkbox
        $(shadowHost).click();

        // check checkbox has selected class
        checkbox.shouldHave(cssClass("mdc-checkbox--selected"));
    }
}
