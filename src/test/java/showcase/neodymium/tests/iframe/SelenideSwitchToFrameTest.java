package showcase.neodymium.tests.iframe;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.junit5.NeodymiumTest;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * This showcase explains with an example implementation how to test a website using frames (iframe).<br>
 * Selenide offers a straight forward approach to handle such frames and within Neodymium this is simply applied.<br>
 */
@Tag("iFrame")
public class SelenideSwitchToFrameTest extends AbstractTest
{
    @NeodymiumTest
    @Description(value = "Showcase for iFrames.")
    public void test()
    {
        // open a page known to hold an iframe
        Selenide.open("https://www.w3schools.com/html/html_iframe.asp");

        // before executing any check within an iframe switch into it
        // the switch can be performed via id, name, index or WebElement like this
        SelenideElement iFrame = $("iframe[src='default.asp']");
        switchTo().frame(iFrame);

        // do a check for heading text within the frame
        $(".w3-col.l10.m12#main > h1").should(matchText("HTML Tutorial"));

        // switch back to the page
        switchTo().defaultContent();

        // do the same check as within the frame again, but expect to find the heading text of the main page
        $(".w3-col.l10.m12#main > h1").should(matchText("HTML Iframes"));
    }
}
