package showcase.neodymium.tests.screenshot;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import showcase.neodymium.tests.AbstractTest;

import java.util.Map;

import static showcase.flows.PropertiesFlow.addTempProperty;
import static showcase.flows.PropertiesFlow.deleteTempPropertiesFile;

/**
 * This showcase demonstrates how to take full page screenshots and how to customise them.
 */
@Tag("AdvancedScreenshot")
@Tag("FullPageScreenshot")
public class AdvancedScreenshotTest extends AbstractTest
{
    public static final String TEMP_PROPERTIES_FILE = "temp-ScreenshotTest-neodymium.properties";

    @BeforeAll
    public static void addScreenshotTempProperties()
    {
        /*
         * In general the properties should be defined directly in the neodymium.properties, but for the showcase to see some example values this is done
         * here.
         * The following call will create a temp properties file with the values:
         *
         * neodymium.screenshots.enableAdvancedScreenshots=true
         * neodymium.screenshots.fullpagecapture.enable=true
         * neodymium.screenshots.fullpagecapture.highlightViewport=true
         * neodymium.screenshots.fullpagecapture.highlightColor=#735369
         * neodymium.screenshots.enableOnSuccess=true - to take the screenshot without throwing an assertion error
         */
        addTempProperty(TEMP_PROPERTIES_FILE,
                        Map.of(
                            "neodymium.screenshots.enableAdvancedScreenshots", "true",
                            "neodymium.screenshots.fullpagecapture.enable", "true",
                            "neodymium.screenshots.fullpagecapture.highlightViewport", "true",
                            "neodymium.screenshots.fullpagecapture.highlightColor", "#735369",
                            "neodymium.screenshots.enableOnSuccess", "true"
                        ));
    }

    @NeodymiumTest
    public void testAdvancedScreenshots()
    {
        Selenide.open("https://www.xceptance.com/en/");
    }

    @AfterAll
    public static void cleanUp()
    {
        deleteTempPropertiesFile(TEMP_PROPERTIES_FILE);
    }
}
