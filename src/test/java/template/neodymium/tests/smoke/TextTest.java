package template.neodymium.tests.smoke;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;

import com.xceptance.neodymium.util.Neodymium;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Selectors;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import template.flows.OpenHomePageFlow;
import template.neodymium.tests.AbstractTest;
import template.pageobjects.pages.HomePage;

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
	public void checkTextExists() {
		
		//Open Homepage
		HomePage homePage = OpenHomePageFlow.flow();
		
		//Get the shadowDom-element that contains the text
		SelenideElement text = $(Selectors.shadowCss("p", "show-hello[name=John]"));
		
		//check if this exists
		text.exists();//Still unsure if this actually works
		
		//Check text
		text.has(text("Hello, John"));//this does not produce any error regardless of what text is entered.
	}
}
