package showcase.neodymium.tests.shadowdom;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Condition.visible;

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
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("Texttest")
public class TextTest extends AbstractTest {
	
	@Test
	@Description(value = "Check that text field exists and control text")
	public void testTextField() {
	    //Open javadoc page
	    Selenide.open("https://javascript.info/shadow-dom");
	    
	    //check title
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
		
		//switch to iFrame of content
		switchTo().frame($("iframe[style=\"height:60px\"]"));
		
		//check text field
		$(Selectors.shadowCss("p", "show-hello[name=John]")).shouldBe(visible);
		
		//check text field has text
		$(Selectors.shadowCss("p", "show-hello[name=John]")).shouldHave(text("Hello, John"));

		//switch back to Mainframe
		switchTo().defaultContent();
	}
}
