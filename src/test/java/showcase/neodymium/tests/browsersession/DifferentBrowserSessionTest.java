package showcase.neodymium.tests.browsersession;

import com.codeborne.selenide.ClickOptions;
import com.xceptance.neodymium.common.browser.StartNewBrowserForCleanUp;
import com.xceptance.neodymium.common.browser.StartNewBrowserForSetUp;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.SelenideAddons;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import showcase.neodymium.tests.AbstractTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static showcase.flows.OpenPageFlow.openCollapsedMenu;

/**
 * To start the test with a fresh, clean browser its is possible to use a different browser for the setup and also for the cleanup. this can be done with the
 *
 * @StartNewBrowserForSetUp and the @StartNewBrowserForCleanUp annotation as demonstrated in this showcase.
 */
@Tag("BrowserSession")
public class DifferentBrowserSessionTest extends AbstractTest
{
    // annotation to force a new browser for setup
    @StartNewBrowserForSetUp
    @BeforeEach
    public void setUp()
    {
        open("https://posters.xceptance.io:8443/");

        // open the first product
        $(".product-tile:first-of-type img").click(ClickOptions.usingJavaScript());

        // add it to the cart
        $("#btn-add-to-cart").click(ClickOptions.usingJavaScript());
    }

    @NeodymiumTest
    public void testDifferentBrowserSession()
    {
        open("https://posters.xceptance.io:8443/");
        SelenideAddons.optionalWaitUntilCondition($("#carousel-product-display"), visible);

        // check if the nav bar is collapsed and if so open it
        openCollapsedMenu();

        // check that the product from the setup is present
        $("#header-cart-overview").click();
        $(".go-to-cart").shouldNotBe(visible);
    }

    // annotation to force a new browser for cleanup
    @StartNewBrowserForCleanUp
    @AfterEach
    public void cleanUp()
    {
        // Cleanup code using dedicated browser
        open("https://posters.xceptance.io:8443/");
        SelenideAddons.optionalWaitUntilCondition($("#carousel-product-display"), visible);

        // check if the nav bar is collapsed and if so open it
        openCollapsedMenu();

        // check that the product from the setup is present
        $("#header-cart-overview").click();
        $(".go-to-cart").shouldNotBe(visible);
    }
}
