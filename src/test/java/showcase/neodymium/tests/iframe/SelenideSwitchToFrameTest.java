package showcase.neodymium.tests.iframe;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.AllureAddons;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * This show case explains with an example implementation how to test a web site with frames (iframe).<br>
 * Selenide offers a straight forward approach to handle such frames and within Neodymium this is simply applied.<br>
 * <b>REQUIRED CONFIGURATION</b>
 * <i>config/dev-neodymium.properties</i>:
 * <ul>
 * <li>neodymium.webDriver.chrome.pathToDriverServer = <local path to chrome webdriver executable> # webdriver configuration</li>
 * <li>neodymium.debugUtils.highlight = true # suggestion</li>
 * </ul>
 * <b>RUN</b>
 * To run this test case and see the report try this from within a command line
 * $ mvn -Dtest=SelenideSwitchToFrameTest clean test allure:serve
 */
@Tag("iFrame")
public class SelenideSwitchToFrameTest extends AbstractTest {
  @Test
  @Description(value = "Showcase for iFrames.")
  public void test() {
    AllureAddons.step("Open Site with Frame", () -> {
      // open a page known to hold an iframe
      Selenide.open("https://www.w3schools.com/html/html_iframe.asp");
    });

    AllureAddons.step("Check Frame Heading and Main Page Heading", () -> {
      AllureAddons.step("Check Frame Heading", () -> {
        // before executing any check within an iframe switch into it
        // the switch can be performed via id, name, index or WebElement like this
        switchTo().frame($(By.xpath("//iframe[@title='W3Schools HTML Tutorial']")));

        // do a check within the frame
        $(By.xpath("//h1")).should(matchText("HTML Tutorial"));
      });
      
      AllureAddons.step("Check Main Page Heading", () -> {
        // switch back to the page
        switchTo().defaultContent();

        // do the same check as within the frame again, but expect another heading text
        $(By.xpath("//h1")).should(matchText("HTML Iframes"));
      });
    });
  }

}