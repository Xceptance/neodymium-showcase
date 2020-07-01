package showcase.neodymium.tests.slider;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.SelenideAddons;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * @author j.sengbusch
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Johannes Sengbusch")
@Tag("smoke")
@DisplayName("Slidertest")
public class SliderTest extends AbstractTest
{
    private static final int MAX_RETRIES = 5;

    private static final int INTERACTION_PAUSE = 3000;

    @Test
    @Description(value = "Showcase for a horizontal drag and drop of a web slider to the right side.")
    public void testHorizontalRightMovement()
    {
        // open the demo page and prepare it for the test
        openSliderPage();

        // the slider element that will be used for the test
        SelenideElement elementUnderTest = $(".balSlider a[role=slider]");

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
        SelenideElement elementUnderTest = $(".balSlider a[role=slider]");

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
        SelenideElement elementUnderTest = $("#equalizer .k-slider-vertical:first-child a");

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

    @Test
    @Description(value = "Showcase for a vertical drag and drop of a web slider to the bottom.")
    public void testVerticalDownMovement()
    {
        // open the demo page and prepare it for the test
        openSliderPage();

        // the slider element that will be used for the test
        SelenideElement elementUnderTest = $("#equalizer .k-slider-vertical:first-child a");

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

    private void openSliderPage()
    {
        // open demo page
        Selenide.open("https://demos.telerik.com/kendo-ui/slider/index");
        // close GDPR overlay
        $("#onetrust-accept-btn-handler").shouldBe(visible).click();
    }
}
