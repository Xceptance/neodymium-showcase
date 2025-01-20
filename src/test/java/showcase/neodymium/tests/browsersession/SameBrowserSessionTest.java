package showcase.neodymium.tests.browsersession;

import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import showcase.neodymium.tests.AbstractTest;

import static com.codeborne.selenide.Selenide.open;

/**
 * It is also possible to reuse the same browser thought the whole test including setup and cleanup. This is demonstrated here and the default behaviour.
 */
@Tag("BrowserSession")
public class SameBrowserSessionTest extends AbstractTest
{
    private String setUpWindowHandle;

    private String testWindowHandle;

    private String cleanUpWindowHandle;

    @BeforeEach
    public void setUp()
    {
        // Setup code using dedicated browser
        open("https://www.xceptance.com/en/");
        setUpWindowHandle = Neodymium.getDriver().getWindowHandle();
    }

    @NeodymiumTest
    public void testSameBrowserSession()
    {
        open("https://www.xceptance.com/en/");
        testWindowHandle = Neodymium.getDriver().getWindowHandle();
    }

    @AfterEach
    public void cleanUp()
    {
        // Cleanup code using dedicated browser
        open("https://www.xceptance.com/en/");
        cleanUpWindowHandle = Neodymium.getDriver().getWindowHandle();

        // using the same browsers and same tab should not generate different window handles
        Assertions.assertEquals(setUpWindowHandle, testWindowHandle, "setup and test window handle are different");
        Assertions.assertEquals(setUpWindowHandle, cleanUpWindowHandle, "setup and cleanup window handle are different");
        Assertions.assertEquals(testWindowHandle, cleanUpWindowHandle, "test and cleanup window handle are different");
    }
}
