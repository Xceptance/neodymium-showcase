package template.neodymium.tests.smoke;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

import org.junit.Test;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

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

public class TextTest extends AbstractTest
{

    @Test
    @Description(value = "Check that Textfield exists and")
    public void checkTextExists()
    {
        HomePage homePage = OpenHomePageFlow.flow();

        switchTo().frame($("iframe[name='code-result-d3mo39']"));

        SelenideElement text = $(Selectors.shadowCss("p", "show-hello[name=John]"));
        text.exists();
        text.shouldHave(text("Hello"));

        switchTo().defaultContent();
    }
}
