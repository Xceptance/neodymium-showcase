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
import com.xceptance.neodymium.module.statement.browser.multibrowser.Browser;

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
    @Browser("FF_1024x768")
    @Browser("Chrome_1024x768")
    public void testNestedShadowDOM() {
        //open demo page
        Selenide.open("https://mwc-demos.glitch.me");
        
        //check if correct site did open
        $("#header").should(exist);
        
        //check that the content frame exists
        $("div.sections-wrapper").shouldBe(visible);
        
        //check amount of checkboxes
        $$("div > mwc-checkbox").shouldHaveSize(3);
        
        //check title
        new Title().validateTitle("MWC Playground");
        
        //Nested shadow DOM demo test
        
        //Css-selector for the element of which the shadow-root is a child
        String shadowHost = "mwc-button[label='toggle menu']";
        
        //Css-selector for the element within the shadow DOM tree, which has the nested shadow DOM
        String nestedShadowHost = "#button > mwc-ripple";
        
        //Css-selector for the first target element
        //This has to identify the element within the nested shadow DOM tree
        String target = "div.mdc-ripple-surface";      
        
        //Scroll to button
        $(shadowHost).scrollTo();
        
        //check if target is not visible
        //Signature of the method shadowCss is 
        //shadowCss(target-element, parent-shadow-host, list-of-nested-shadow-hosts)
        //Note: the last parameter excepts either a simple string or a list of strings
        //the list has to contain in order all the nested shadow hosts that need to be
        //found, to be able to find the target element.
        $(Selectors.shadowCss(target, shadowHost, nestedShadowHost)).shouldNotBe(visible);
    }
    
    //@Test
    @Description(value = "Simple showcase for usage of shadow DOM")
    public void testSimpleShadowDOM() { 
        //open demo page
        Selenide.open("https://mwc-demos.glitch.me");   
        
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
