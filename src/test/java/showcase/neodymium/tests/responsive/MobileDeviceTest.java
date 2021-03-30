package showcase.neodymium.tests.responsive;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.module.statement.browser.multibrowser.Browser;
import com.xceptance.neodymium.util.Neodymium;

import showcase.neodymium.tests.AbstractTest;

/**
 * Most web sites provide their content in different resolutions for different devices.<br>
 * In order to test a cell phone for example, the emulation of various mobile devices integrated in Chrome can be
 * used.<br>
 * Browser profiles are defined in the config/browser.properties file.<br>
 * There is a value for chromeEmulationProfile necessary, in our example: "iPhone X".<br>
 * Possible values can be found in the Chrome browser in the developer tools "Toggle device toolbar" drop down menu<br>
 */
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
            // validate hamburger menu is available in mobile resolution
            $(".navbar-toggle").shouldBe(visible);
        }
        else
        {
            // validate hamburger menu is NOT available in desktop resolution
            $(".navbar-toggle").shouldBe(hidden);
        }
    }
}
