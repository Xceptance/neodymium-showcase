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
    @Description(value = "Check the checkbox site for correct structure. With inner shadow DOM.")
    public void testInnerShadowDOM() {
        //open demo page
        Selenide.open("https://mwc-demos.glitch.me/demos/checkbox.html");
        
        //check if correct site did open
        $("demo-header[component=Checkbox]").should(exist);
        
        //check the top bar is visible
        $(Selectors.shadowCss("div.mdc-top-app-bar__row", "demo-header[component=Checkbox]", "mwc-top-app-bar-fixed")).shouldBe(visible);
        
        //check navigation section is visible
        $(Selectors.shadowCss("#navigation", "demo-header[component=Checkbox]", "mwc-top-app-bar-fixed")).shouldBe(visible);
        
        //check navigation section is visible
        $(Selectors.shadowCss("#actions", "demo-header[component=Checkbox]", "mwc-top-app-bar-fixed")).shouldBe(visible);
        
        //check that the content frame exists
        $("div.demo-group").shouldBe(visible);
        
        //check amount of checkboxes
        $$("mwc-checkbox").shouldHaveSize(6);
        
        //check title
        new Title().validateTitle("checkbox demo");
    }
    
    @Test
    @Description(value = "Check the checkbox functionality")
    public void testCheckbox() { 
        //open demo page
        Selenide.open("https://mwc-demos.glitch.me/demos/checkbox.html");     
        
        //Get checkbox
        SelenideElement checkbox = $(Selectors.shadowCss("div.mdc-checkbox", "mwc-checkbox"));
        
        //check checkbox has not the selected class
        checkbox.shouldHave(not(cssClass("mdc-checkbox--selected")));
        
        //click checkbox
        $("mwc-checkbox").click();
        
        //check checkbox has selected class
        checkbox.shouldHave(cssClass("mdc-checkbox--selected"));
    }
}
