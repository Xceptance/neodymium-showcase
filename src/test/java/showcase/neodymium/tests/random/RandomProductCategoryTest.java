package showcase.neodymium.tests.random;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;
import com.xceptance.neodymium.util.NeodymiumRandom;
import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import showcase.neodymium.tests.AbstractTest;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * This showcase shows the use of the Neodymium.getRandom() method.<br> Random values are useful to run different test cases with different values.<br> It is
 * also useful to test a web application with different paths like a real user would.<br> The NeodymiumRandom class extends the functionality of
 * java.util.Random.<br> In this case the use of the <i>nextInt()</i> method is shown.<br> To get to know more possibilities take a closer look at the
 * class.<br>
 * <br>
 * <b>REQUIRED CONFIGURATION:</b> <i>config/neodymium.properties</i>
 * <ul>
 * <li>A value for neodymium.context.random.initialValue must NOT be set.</li>
 * </ul>
 * <b>CAUTION:</b> This test case fails if initialValue is set.
 */
public class RandomProductCategoryTest extends AbstractTest
{
    @BeforeEach
    public void configurationCheck()
    {
        // by setting the neodymium.context.random.initialValue, the random result can be kept constant
        // check if the initialValue is NOT set in neodymium.properties so that a really random result is chosen
        Assert.assertNull("FixedRandomTest: neodymium.context.random.initialValue is set",
                          Neodymium.configuration().initialRandomValue());
    }

    @NeodymiumTest
    @Description(value = "Showcase for clicking a random product")
    public void testRandomProduct()
    {
        // open the demo page and prepare it for the test
        Selenide.open("https://posters.xceptance.io:8443/");

        // check if the heading "The Poster Demo Store" exists
        $("#header-brand").shouldHave(partialText("The Poster Demo Store"));

        // get all categories
        ElementsCollection categories = $$(".category-tile .category-tile-title");

        // count the categories in the list
        final int categoriesCount = categories.size();

        // create a random number between 0 and categories count in the list
        final int randomNumber = NeodymiumRandom.nextInt(categoriesCount);

        // remember the selected headline
        final String headline = categories.get(randomNumber).getText();

        // click randomNumber'th link in the categories list
        categories.get(randomNumber).scrollTo().scrollIntoView("{block: 'center'}");
        categories.get(randomNumber).click(ClickOptions.usingJavaScript());

        // check if category page contains same headline
        $("#title-category-name").shouldHave(partialText(headline));
    }
}
