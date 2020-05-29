package showcase.neodymium.tests.slider;

import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Condition;
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
    private String url = "https://demos.telerik.com/kendo-ui/slider/index";

    private SelenideElement elementToMove;

    private SelenideElement elementToCheck;

    @Test
    @Description(value = "Showcase for a horizontal drag and drop of a web slider to the right side.")
    // move the slider to the right
    public void testHorizontalRightMovement()
    {
        // open demo page
        Selenide.open(url);

        elementToMove = $(".balSlider a[role=slider]");
        elementToCheck = elementToMove;

        // offset for the horizontal movement: 40
        // offset for the vertical movement: 0
        // pause between movements: 3000ms
        // retries: 5
        // until the value: 8
        SelenideAddons.dragAndDropUntilCondition(elementToMove, elementToCheck, 40, 0, 3000, 5,
                                                 Condition.attribute("aria-valuenow", "8"));
    }

    @Test
    @Description(value = "Showcase for a horizontal drag and drop of a web slider to the left side.")
    // move the slider to the left
    public void testHorizontalLeftMovement()
    {
        // open demo page
        Selenide.open(url);

        elementToMove = $(".balSlider a[role=slider]");
        elementToCheck = elementToMove;

        // element to move:
        // offset for the horizontal movement: -40
        // offset for the vertical movement: 0
        // pause between movements: 3000ms
        // retries: 5
        // until the value: -8
        SelenideAddons.dragAndDropUntilCondition(elementToMove, elementToCheck, -40, 0, 3000, 5,
                                                 Condition.attribute("aria-valuenow", "-8"));
    }

    @Test
    @Description(value = "Showcase for a vertical drag and drop of a web slider to the top.")
    // move the slider to the top
    public void testVerticalUpMovement()
    {
        // open demo page
        Selenide.open(url);

        elementToMove = $("#equalizer .k-slider-vertical:first-child a");
        elementToCheck = elementToMove;

        // element to move:
        // offset for the horizontal movement: 0
        // offset for the vertical movement: -10
        // pause between movements: 3000ms
        // retries: 5
        // until the value: 16
        SelenideAddons.dragAndDropUntilCondition(elementToMove, elementToCheck, 0, -10, 3000, 5,
                                                 Condition.attribute("aria-valuenow", "16"));
    }

    @Test
    @Description(value = "Showcase for a vertical drag and drop of a web slider to the bottom.")
    // move the slider to the bottom
    public void testVerticalDownMovement()
    {
        // open demo page
        Selenide.open(url);

        elementToMove = $("#equalizer .k-slider-vertical:first-child a");
        elementToCheck = elementToMove;

        // element to move:
        // offset for the horizontal movement: 0
        // offset for the vertical movement: 10
        // pause between movements: 3000ms
        // retries: 5
        // until the value: -6
        SelenideAddons.dragAndDropUntilCondition(elementToMove, elementToCheck, 0, 10, 3000, 5,
                                                 Condition.attribute("aria-valuenow", "-6"));
    }
}