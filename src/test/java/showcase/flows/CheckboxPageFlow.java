package showcase.flows;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import com.xceptance.neodymium.util.Neodymium;

import io.cucumber.java.en.And;
import showcase.pageobjects.pages.CheckboxPage;

public class CheckboxPageFlow
{
    @And("^I navigate to the home page of the project$")
    public static CheckboxPage flow()
    {
        // clear browser cookies to remove old data
        clearBrowserCookies();

        // navigate to the home page
        open(Neodymium.configuration().setProperty("neodymium.checkboxurl", ""));
        return new CheckboxPage();
    }; 
}
