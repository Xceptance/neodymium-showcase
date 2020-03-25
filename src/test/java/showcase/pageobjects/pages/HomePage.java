package showcase.pageobjects.pages;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import io.cucumber.java.en.Then;
import io.qameta.allure.Step;

public class HomePage extends AbstractPageObject
{
    /*
     * (non-Javadoc)
     * 
     * @see com.xceptance.scripting.selenide.page.AbstractPage()
     */
    @Override
    @Step("ensure this is a homepage")
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
    @Then("^The home page should have heading, carousel, services and the company button$")
    @Step("validate the home page")
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
    }
}
