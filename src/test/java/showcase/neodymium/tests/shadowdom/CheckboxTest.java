package showcase.neodymium.tests.shadowdom;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.not;

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

import showcase.pageobjects.pages.CheckboxPage;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("CheckboxTest")


public class CheckboxTest extends AbstractTest {
        
    @Test
    @Description(value = "Check the checkbox site for correct Structure")
    public void testCheckboxSite() {
        
        //open demo page
        CheckboxPage page = new CheckboxPage();
        Selenide.open("https://mwc-demos.glitch.me/demos/checkbox.html");     
        
        //check if correct site did open
        page.isExpectedPage();
        
        //validate Structure
        page.validateStructure();
        
        //check Title
        page.title.validateTitle("checkbox demo");
    }
    
    @Test
    @Description(value = "Check the checkbox site for correct Structure")
    public void testFunctionality() { 
        
        //open demo page
        CheckboxPage page = new CheckboxPage();
        Selenide.open("https://mwc-demos.glitch.me/demos/checkbox.html");     
        
        //check if correct site did open
        page.isExpectedPage();
        
        //Get first checkbox
        SelenideElement checkbox = $(Selectors.shadowCss("div.mdc-checkbox", "mwc-checkbox"));
        
        //check Checkbox has not the selected class
        checkbox.shouldHave(not(cssClass("mdc-checkbox--selected")));
        
        //click Checkbox
        $("mwc-checkbox").click();
        
        //check Checkbox has selected class
        checkbox.shouldHave(cssClass("mdc-checkbox--selected"));
    }
}
