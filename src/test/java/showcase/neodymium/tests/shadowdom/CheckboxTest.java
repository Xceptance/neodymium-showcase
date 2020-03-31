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
import showcase.flows.CheckboxPageFlow;
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
        CheckboxPage page = CheckboxPageFlow.flow();
        
        //check if correct site did open
        page.isExpectedPage();
        
        //validate Structure
        page.validateStructure();
    }
    
    @Test
    @Description(value = "Check the checkbox site for correct Structure")
    public void testFunctionality() { 
        
        //open demo page
        CheckboxPage page = CheckboxPageFlow.flow();     
        
        //check if correct site did open
        page.isExpectedPage();
        
        //validate checkbox works
        page.validateShadowCheckboxFunction("div.mdc-checkbox", "mwc-checkbox", "mdc-checkbox--selected");
    }
}
