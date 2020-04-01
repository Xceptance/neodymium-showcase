package showcase.neodymium.tests.shadowdom;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;

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
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("Checkboxtest")
public class CheckboxTest extends AbstractTest {
        
    @Test
    @Description(value = "Showcase for nested shadow DOM.")
    public void testNestedShadowDOM() {
        //open demo page
        Selenide.open("https://mwc-demos.glitch.me/demos/checkbox.html");
        
        //check if correct site did open
        $("demo-header[component=Checkbox]").should(exist);
        
        //check that the content frame exists
        $("div.demo-group").shouldBe(visible);
        
        //check amount of checkboxes
        $$("mwc-checkbox").shouldHaveSize(6);
        
        //check title
        new Title().validateTitle("checkbox demo");
        
        //Nested shadow DOM demo test
        
        //Css-selector for the element of which the shadow-root is a child
        String shadowHost = "demo-header[component=Checkbox]";
        
        //Css-selector for the element within the shadow DOM tree, which has the nested shadow DOM
        String nestedShadowHost = "mwc-top-app-bar-fixed";
        
        //Css-selector for the first target element
        //This has to identify the element within the nested shadow DOM tree
        String target1 = "div.mdc-top-app-bar__row";        
        
        //check the top bar is visible
        //Signature of the method shadowCss is 
        //shadowCss(target-element, parent-shadow-host, list-of-nested-shadow-hosts)
        //Note: the last parameter excepts either a simple string or a list of strings
        //the list has to contain in order all the nested shadow hosts that need to be
        //found, to be able to find the target element.
        $(Selectors.shadowCss(target1, shadowHost, nestedShadowHost)).shouldBe(visible);
        
        //Additional examples
        
        //Css-selector for the second target element
        String target2 = "#navigation";
        
        //check navigation section is visible
        $(Selectors.shadowCss(target2, shadowHost, nestedShadowHost)).shouldBe(visible);
        
        //Css-selector for the second target element
        String target3 = "#actions";
        
        //check navigation section is visible
        $(Selectors.shadowCss(target3, shadowHost, nestedShadowHost)).shouldBe(visible);
    }
    
    @Test
    @Description(value = "Simple showcase for usage of shadow DOM")
    public void testSimpleShadowDOM() { 
        //open demo page
        Selenide.open("https://mwc-demos.glitch.me/demos/checkbox.html");   
        
        //Css-selector for the element of which the shadow-root is a child
        String shadowHost = "mwc-checkbox";
        
        //Css-selector for the target element
        String target = "div.mdc-checkbox";
        
        //Get checkbox
        //Signature for the shadowCss method for this simple case is
        //shadowCss(target-element, parent-shadow-host)
        SelenideElement checkbox = $(Selectors.shadowCss(target, shadowHost));
        
        //check checkbox has not the selected class
        checkbox.shouldHave(not(cssClass("mdc-checkbox--selected")));
        
        //click checkbox
        $(shadowHost).click();
        
        //check checkbox has selected class
        checkbox.shouldHave(cssClass("mdc-checkbox--selected"));
    }
}
