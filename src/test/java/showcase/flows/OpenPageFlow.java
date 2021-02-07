package showcase.flows;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.ex.ElementNotFound;

public class OpenPageFlow
{
    public static void openXceptanceJobOffersPage()
    {
        // a helper function to open the job page and close the GDPR dialog to avoid duplicate code
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
                Selenide.refresh();
            }
        }
    }
}
