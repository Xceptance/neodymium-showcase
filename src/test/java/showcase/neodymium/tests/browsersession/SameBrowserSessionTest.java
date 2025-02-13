package showcase.neodymium.tests.browsersession;

import com.codeborne.selenide.ClickOptions;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.SelenideAddons;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.BeforeEach;
import showcase.neodymium.tests.AbstractTest;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static showcase.flows.OpenPageFlow.openCollapsedMenu;

/**
 * It is also possible to reuse the same browser thought the whole test including setup and cleanup. This is demonstrated here and the default behaviour.
 */
@Tag("BrowserSession")
public class SameBrowserSessionTest extends AbstractTest
{
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
    public void testSameBrowserSession()
    {
        open("https://posters.xceptance.io:8443/");
        SelenideAddons.optionalWaitUntilCondition($("#carousel-product-display"), visible);

        // check if the nav bar is collapsed and if so open it
        openCollapsedMenu();

        // check that the product from the setup is present
        $("#header-cart-overview").click();
        $(".go-to-cart").shouldBe(visible);
    }

}
