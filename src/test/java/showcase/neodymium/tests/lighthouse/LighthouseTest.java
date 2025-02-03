package showcase.neodymium.tests.lighthouse;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.LighthouseUtils;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * Since accessibility and WCAG gets more important and is also enforced by the law, it is good to have a metric to quantify the accessibility of a website.
 * Googles Lighthouse provides a tool to calculate this metric. This showcase demonstrates a very basic example how to use Lighthouse and generate a report.
 */
@Tag("WCAG")
@Tag("accessibility")
@Tag("Lighthouse")
public class LighthouseTest extends AbstractTest
{
    @NeodymiumTest
    public void testLighthouse() throws Exception
    {
        Selenide.open("https://www.xceptance.com/en/");

        // calling createLightHouseReport() will create a Lighthouse report for the current page
        // this will open a new tab and open the same page again in the new tab
        // after generating the report, the additional tab will be closed and the test automatically proceeds with the initial tab
        LighthouseUtils.createLightHouseReport("testReport");
    }
}
