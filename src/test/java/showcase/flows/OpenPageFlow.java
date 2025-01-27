package showcase.flows;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OpenPageFlow
{
    /**
     * Helper function to open the job page and close the GDPR dialog to avoid duplicate code
     */
    public static void openXceptanceJobOffersPage()
    {
        // open demo page
        Selenide.open("https://www.xceptance.com/de/careers/");

        // close GDPR overlay if visible
        boolean overlayIsVisible = true;
        try
        {
            $(".btn-link").shouldBe(visible);
        }
        catch (ElementNotFound e)
        {
            overlayIsVisible = false;
        }

        if (overlayIsVisible)
        {
            $(".btn-link").click();
            $(".btn-link").shouldBe(hidden);
        }
    }

    /**
     * Helper function to open the menu on posters if it is collapsed
     */
    public static void openCollapsedMenu()
    {
        SelenideElement navBarToggle = $(".navbar-toggler");
        if (navBarToggle.isDisplayed())
        {
            navBarToggle.click();
        }
    }
}
