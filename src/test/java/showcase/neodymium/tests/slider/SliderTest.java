package showcase.neodymium.tests.slider;

import static com.codeborne.selenide.Selenide.$;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.Neodymium;
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

    @Test
    @Description(value = "Showcase for a horizontal drag and drop of a web slider.")
    public void testhorizontalMovement()
    {
        // open demo page
        Selenide.open("https://demos.telerik.com/kendo-ui/slider/index");

        // move the slider to the right
        // until the value: 8
        // try this 5 times
        Actions moveSlider = new Actions(Neodymium.getDriver());

        int counter = 0;
        while (!$(".balSlider a[role=slider]").has(Condition.attribute("aria-valuenow", "8")))
        {
            if (counter > 5)
            {
                SelenideAddons.wrapAssertionError(() -> {
                    Assert.assertTrue("CircutBreaker: Was not able to move the element and to reach the condition. "
                                      + "Tried: " + 5 + " times to move the element.", false);
                });
            }
            Action action = moveSlider.dragAndDropBy($(".balSlider a[role=slider]").getWrappedElement(), 40, 0).build();
            action.perform();
            Selenide.sleep(3000);
            counter++;
        }
    }
}