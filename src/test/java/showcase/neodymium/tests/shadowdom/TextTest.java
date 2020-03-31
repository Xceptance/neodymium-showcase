package showcase.neodymium.tests.shadowdom;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Condition.visible;

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
import showcase.flows.ShadowDomPageFlow;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.pages.ShadowDomPage;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("TextTest")

public class TextTest extends AbstractTest {
    
    @Test
    @Description(value = "Check structure of Page")
    public void testLandingPage() {
        
        // Goto the javadoc page
        ShadowDomPage shadowdomPage = ShadowDomPageFlow.flow();

        // short validation to check that the correct page was opened
        shadowdomPage.isExpectedPage();
        
        // basic validation
        shadowdomPage.validateStructure();
    }
	
	@Test
	@Description(value = "Check that Textfield exists and control text")
	public void testTextExists() {
		
		//Open javadoc page
	    ShadowDomPage shadowdomPage = ShadowDomPageFlow.flow();
	    
	    // short validation to check that the correct page was opened
	    shadowdomPage.isExpectedPage();
		
		//switch to iFrame of content
		switchTo().frame($("iframe[style=\"height:60px\"]"));
		
		//check textfield
		shadowdomPage.validateShadowTextfield("p", "show-hello[name=John]", "Hello, John");

		//switch back to Mainframe
		switchTo().defaultContent();
	}
}
