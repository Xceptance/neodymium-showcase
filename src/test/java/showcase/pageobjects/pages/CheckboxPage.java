package showcase.pageobjects.pages;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Selectors;

import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

public class CheckboxPage extends AbstractPageObject {
    
    /*
     * (non-Javadoc)
     * 
     * @see com.xceptance.scripting.selenide.page.AbstractPage()
     */
    @Override
    @Step("ensure this is a the checkbox demo page")
    public void isExpectedPage()
    {
        // Verifies that header exists
        $("demo-header[component=Checkbox]").should(exist);
    }

    /*
     * (non-Javadoc)
     * 
     * @see template.pageObjects.pages.AbstractPageObject()
     */
    @Then("^The checkbox demo page should have $")
    @Step("validate the demo page")
    public void validateStructure()
    {
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
    }
}
