package showcase.neodymium.tests.browsersession;

import com.xceptance.neodymium.common.browser.StartNewBrowserForCleanUp;
import com.xceptance.neodymium.common.browser.StartNewBrowserForSetUp;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import showcase.neodymium.tests.AbstractTest;

import static com.codeborne.selenide.Selenide.open;

/**
 * To start the test with a fresh, clean browser its is possible to use a different browser for the setup and also for the cleanup. this can be done with the
 *
 * @StartNewBrowserForSetUp and the @StartNewBrowserForCleanUp annotation as demonstrated in this showcase.
 */
@Tag("BrowserSession")
public class DifferentBrowserSessionTest extends AbstractTest
{
    private String setUpWindowHandle;

    private String testWindowHandle;

    private String cleanUpWindowHandle;

    // annotation to force a new browser for setup
    @StartNewBrowserForSetUp
    @BeforeEach
    public void setUp()
    {
        // Setup code using dedicated browser
        open("https://www.xceptance.com/en/");
        setUpWindowHandle = Neodymium.getDriver().getWindowHandle();
    }

    @NeodymiumTest
    public void testDifferentBrowserSession()
    {
        open("https://www.xceptance.com/en/");
        testWindowHandle = Neodymium.getDriver().getWindowHandle();
    }

    // annotation to force a new browser for cleanup
    @StartNewBrowserForCleanUp
    @AfterEach
    public void cleanUp()
    {
        // Cleanup code using dedicated browser
        open("https://www.xceptance.com/en/");
        cleanUpWindowHandle = Neodymium.getDriver().getWindowHandle();

        // different browsers should generate different window handles
        Assertions.assertNotEquals(setUpWindowHandle, testWindowHandle, "setup and test window handle are the same");
        Assertions.assertNotEquals(setUpWindowHandle, cleanUpWindowHandle, "setup and cleanup window handle are the same");
        Assertions.assertNotEquals(testWindowHandle, cleanUpWindowHandle, "test and cleanup window handle are the same");
    }
}
