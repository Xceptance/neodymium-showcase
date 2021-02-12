package showcase.neodymium.tests.iframe;

import static com.codeborne.selenide.Selenide.switchTo;

import org.junit.Test;

import com.codeborne.selenide.Selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("iFrame")
public class SwitchToFrame extends AbstractTest
{
    @Test
    @Description(value = "Showcase for iFrames.")
    public void test()
    {
        // open demo page
        Selenide.open("https://www.w3schools.com/html/html_iframe.asp");

        // Setting id and name of 2 different frames
        String title = "W3Schools HTML Tutorial";
        String name = "";

        // Switch to Frame by title
        switchTo().frame(title);
        // Switch to Frame by name
        switchTo().frame(name);

        // Switch back to Mainframe
        switchTo().defaultContent();
    }

}
