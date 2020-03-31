package showcase.flows;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

import com.xceptance.neodymium.util.Neodymium;

import io.cucumber.java.en.And;
import showcase.pageobjects.pages.ShadowDomPage;

public class ShadowDomPageFlow
{
    @And("^I navigate to the home page of the project$")
    public static ShadowDomPage flow()
    {
        // clear browser cookies to remove old data
        clearBrowserCookies();

        // navigate to the home page
        open(Neodymium.configuration().setProperty("neodymium.shadowdomurl", ""));
        return new ShadowDomPage();
    };   
}
