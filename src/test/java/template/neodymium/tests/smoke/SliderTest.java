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

import org.openqa.selenium.JavascriptExecutor;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("SliderTest")

public class SliderTest extends AbstractTest {
	
	@Test
	@Description(value = "Basic Slidertest")
	public void testSlider() {

		//Open Homepage
		HomePage homePage = OpenHomePageFlow.flow();

		//Get the shadowDom-element that contains the sliderelements
		SelenideElement slider = $(Selectors.shadowCss("#track", "p > input[type=range]"));
		
		//check if this exists
		slider.exists(); //Still unsure if this actually works
		
		//Create Javascriptexecutor
		JavascriptExecutor executor = (JavascriptExecutor)getWebDriver();
		
		//Move Slider
		executor.executeScript("document.querySelector('input[type=range]').value = 0");//This works but accesses only the parent element not the shadowDom
	}
	
}
