package showcase.pageobjects.pages;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

public class ShadowDomPage extends AbstractPageObject
{
    /*
     * (non-Javadoc)
     * 
     * @see com.xceptance.scripting.selenide.page.AbstractPage()
     */
    @Override
    @Step("ensure this is a the javadoc page")
    public void isExpectedPage()
    {
        // Verifies that toolbar exists
        $("div.sitetoolbar").should(exist);
    }

    /*
     * (non-Javadoc)
     * 
     * @see template.pageObjects.pages.AbstractPageObject()
     */
    @Then("^The javadoc page should have sitetoolbar, navigation sidebar, content page and footer$")
    @Step("validate the javadoc page")
    public void validateStructure()
    {
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
        
        title.validateTitle("Shadow DOM");
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see template.pageObjects.pages.AbstractPageObject()
     */
    @Then("^The Text should be visible and have the given text.$")
    @Step("validate text")
    public void validateShadowTextfield(String target, String shadowHost, String text) {
        
        //Get the shadowDom-element that contains the text
        //Syntax is shadowCss(element to be found, element that has the shadow Dom)
        SelenideElement textField = $(Selectors.shadowCss(target, shadowHost));
        
        //check if this exists
        textField.shouldBe(visible);
        
        //Check text
        textField.shouldHave(text(text));
    }
}
