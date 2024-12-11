package showcase.neodymium.tests.popupblocker;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import showcase.neodymium.tests.AbstractTest;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static showcase.flows.PropertiesFlow.addTempProperty;
import static showcase.flows.PropertiesFlow.deleteTempPropertiesFile;

/**
 * For test automation some popups can cause problems, if they don't behave deterministically enough to be handled without drawbacks like too long waiting
 * times. For this a JavaScript based popup blocker was implemented. An example usage can be seen in the following showcase. To use it the css selector of the
 * close button for the specific popups have to be defined in the neodymium.properties or temp.properties starting with 'neodymium.popup.' and followed by an
 * identifier for the popup like 'neodymium.popup.cookieBanner' in the example.
 */
@Tag("PopupBlocker")
public class PopupBlockerTest extends AbstractTest
{

    public static final String TEMP_PROPERTIES_FILE = "temp-PopupBlockerTest-neodymium.properties";

    @BeforeAll
    public static void addPopupBlockerSelectorToTempProperties()
    {
        /*
         * In general the properties should be defined directly in the neodymium.properties, but for the showcase to see some example values this is done
         * here.
         * The following call will create a temp properties file with the values:
         *
         * neodymium.popup.cookieBanner = #privacy-message .close
         */
        addTempProperty(TEMP_PROPERTIES_FILE,
                        Map.of("neodymium.popup.cookieBanner", "#privacy-message .close"));
    }

    @NeodymiumTest
    public void testPopUpIsBlocked()
    {
        Selenide.open("https://www.xceptance.com/");
        Selenide.sleep(1500);
        $("#privacy-message .close").shouldNotBe(visible);
    }

    @AfterAll
    public static void cleanUp()
    {
        deleteTempPropertiesFile(TEMP_PROPERTIES_FILE);
    }
}
