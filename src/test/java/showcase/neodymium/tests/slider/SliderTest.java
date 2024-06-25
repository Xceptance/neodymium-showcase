package showcase.neodymium.tests.slider;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.SelenideAddons;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * Sliders are control elements to give the user the opportunity to select a value on a web page. Mostly there are used
 * horizontally but there are also vertical sliders. Manipulation of slider can be a little tricky using the pure
 * Selenium API. Therefore Neodymium provides an approach that requires less knowledge about Selenium's paradigms.<br>
 * This show case provides possible approaches how to handle and interact with sliders and they indicators. The first
 * and the second test demonstrate horizontal while the other tests show case vertical movements.
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Test Developer")
@Tag("drag and drop")
@DisplayName("Slidertest")
public class SliderTest extends AbstractTest
{
    private static final int MAX_RETRIES = 5;

    private static final int INTERACTION_PAUSE = 3000;

    @NeodymiumTest
    @Description(value = "Showcase for a horizontal drag and drop of a web slider to the right side.")
    public void testHorizontalRightMovement()
    {
        // open the demo page and prepare it for the test
        openSliderPage();

        // the slider element that will be used for the test
        SelenideElement elementUnderTest = $(".balSlider a[role=slider]").scrollIntoView("{block:'center'}");

        // Interaction: move the slider to the right
        //
        // element for movement: elementUnderTest
        // element for validation: elementUnderTest
        // offset for the horizontal movement: 40
        // offset for the vertical movement: 0
        // pause between movements: 3000ms
        // retries: 5
        // condition until the movement is performed: aria-valuenow = 8
        SelenideAddons.dragAndDropUntilCondition(elementUnderTest, elementUnderTest, 40, 0, INTERACTION_PAUSE, MAX_RETRIES, attribute("aria-valuenow", "8"));
    }

    @Test
    @Description(value = "Showcase for a horizontal drag and drop of a web slider to the left side.")
    public void testHorizontalLeftMovement()
    {
        // open the demo page and prepare it for the test
        openSliderPage();

        // the slider element that will be used for the test
        SelenideElement elementUnderTest = $(".balSlider a[role=slider]").scrollIntoView("{block:'center'}");

        // Interaction: move the slider to the left
        //
        // element for movement: elementUnderTest
        // element for validation: elementUnderTest
        // offset for the horizontal movement: -40
        // offset for the vertical movement: 0
        // pause between movements: 3000ms
        // retries: 5
        // condition until the movement is performed: aria-valuenow = -8
        SelenideAddons.dragAndDropUntilCondition(elementUnderTest, elementUnderTest, -40, 0, INTERACTION_PAUSE, MAX_RETRIES, attribute("aria-valuenow", "-8"));
    }

    @Test
    @Description(value = "Showcase for a vertical drag and drop of a web slider to the top.")
    public void testVerticalUpMovement()
    {
        // open the demo page and prepare it for the test
        openSliderPage();

        // the slider element that will be used for the test
        SelenideElement elementUnderTest = $("#equalizer .k-slider-vertical:first-child a").scrollIntoView("{block:'center'}");

        // Interaction: move the slider upwards
        //
        // element for movement: elementUnderTest
        // element for validation: elementUnderTest
        // offset for the horizontal movement: 0
        // offset for the vertical movement: -10
        // pause between movements: 3000ms
        // retries: 5
        // condition until the movement is performed: aria-valuenow = 16
        SelenideAddons.dragAndDropUntilCondition(elementUnderTest, elementUnderTest, 0, -10, INTERACTION_PAUSE, MAX_RETRIES, attribute("aria-valuenow", "16"));
    }

    @NeodymiumTest
    @Description(value = "Showcase for a vertical drag and drop of a web slider to the bottom.")
    public void testVerticalDownMovement()
    {
        // open the demo page and prepare it for the test
        openSliderPage();

        // the slider element that will be used for the test
        SelenideElement elementUnderTest = $("#equalizer .k-slider-vertical:first-child a").scrollIntoView("{block:'center'}");

        // Interaction: move the slider downwards
        //
        // element for movement: elementUnderTest
        // element for validation: elementUnderTest
        // offset for the horizontal movement: 0
        // offset for the vertical movement: 10
        // pause between movements: 3000ms
        // retries: 5
        // condition until the movement is performed: aria-valuenow = -6
        SelenideAddons.dragAndDropUntilCondition(elementUnderTest, elementUnderTest, 0, 10, INTERACTION_PAUSE, MAX_RETRIES, attribute("aria-valuenow", "-6"));
    }

    // a helper function to open the page and close the GDPR dialog to avoid duplicate code
    private void openSliderPage()
    {
        // open demo page
        Selenide.open("https://demos.telerik.com/kendo-ui/slider/index");

        // close GDPR overlay if visible
        boolean overlayIsVisible = true;
        try
        {
            $("#onetrust-accept-btn-handler").shouldBe(visible);
        }
        catch (ElementNotFound e)
        {
            overlayIsVisible = false;
        }

        if (overlayIsVisible)
        {
            $("#onetrust-accept-btn-handler").click();
            $("#onetrust-consent-sdk .onetrust-pc-dark-filter").shouldBe(hidden);
            Selenide.refresh();
        }
    }
}
