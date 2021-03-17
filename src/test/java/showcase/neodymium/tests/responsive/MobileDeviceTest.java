package showcase.neodymium.tests.responsive;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.module.statement.browser.multibrowser.Browser;
import com.xceptance.neodymium.util.Neodymium;

import showcase.neodymium.tests.AbstractTest;

@Browser("Chrome_1500x1000")
@Browser("Chrome_iPhoneX")
public class MobileDeviceTest extends AbstractTest
{
    @Test
    public void testProductListPageIsResponsive()
    {
        // go to the web page
        Selenide.open("https://www.xceptance.com/");

        if (Neodymium.isMobile())
        {
            // validate hamburger menu is available in mobile design
            $(".navbar-toggle").shouldBe(visible);
        }
        else
        {
            // validate hamburger menu is NOT available in desktop design
            $(".navbar-toggle").shouldBe(hidden);
        }
    }
}
