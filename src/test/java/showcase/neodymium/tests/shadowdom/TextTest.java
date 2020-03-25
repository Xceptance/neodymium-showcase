package showcase.neodymium.tests.shadowdom;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Condition.exist;

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
import showcase.flows.OpenHomePageFlow;
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
    @Description(value = "Check that Textfield exists and")
    public void testLandingPage() {
        
        // Goto the javadoc page
        ShadowDomPage shadowdomPage = new ShadowDomPage();
        Selenide.open("https://javascript.info/shadow-dom");

        // short validation to check that the correct page was opened
        shadowdomPage.isExpectedPage();
        
        // basic validation
        shadowdomPage.validateStructure();
        shadowdomPage.title.validateTitle("Shadow DOM");
    }
	
	@Test
	@Description(value = "Check that Textfield exists and")
	public void testTextExists() {
		
		//Open javadoc page
	    ShadowDomPage shadowdomPage = new ShadowDomPage();
	    Selenide.open("https://javascript.info/shadow-dom");
	    
	    // short validation to check that the correct page was opened
	    shadowdomPage.isExpectedPage();
		
		//switch to iFrame of content
		switchTo().frame($("iframe[style=\"height:60px\"]"));
		
		//Get the shadowDom-element that contains the text
		//Syntax is shadowCss(element to be found, element that has the shadow Dom)
		SelenideElement text = $(Selectors.shadowCss("p", "show-hello[name=John]"));
		
		//check if this exists
		text.should(exist);
		
		//Check text
		text.shouldHave(text("Hello, John"));

		//switch back to Mainframe
		switchTo().defaultContent();
	}
}
