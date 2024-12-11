package showcase.neodymium.tests.urlvalidation;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.opentest4j.AssertionFailedError;
import showcase.neodymium.tests.AbstractTest;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static showcase.flows.PropertiesFlow.addTempProperty;
import static showcase.flows.PropertiesFlow.deleteTempPropertiesFile;

/**
 * Sometimes it can be useful to limit the test automation to certain URLs, especially if it does random navigation or if there could be misconfigured link. To
 * prevent that the tests wandering off into the wild west of the web, URL include lists can be defined and used as demonstrated in this showcase. If any URL
 * which is not contained in the include list is opened, the test will fail immediately.
 */
@Tag("UrlValidation")
public class UrlValidationIncludeTest extends AbstractTest
{
    public static final String TEMP_PROPERTIES_FILE = "temp-UrlValidationIncludeTest-neodymium.properties";

    @BeforeAll
    public static void setIncludeList()
    {
        /*
         * In general the properties should be defined directly in the neodymium.properties, but for the showcase to see some example values this is done
         * here.
         * The following call will create a temp properties file with the values:
         *
         * neodymium.url.includeList=https://posters.xceptance.io:8443 https://www.xceptance.com/en/$
         *
         * NOTE: The URLs needs to be provided as Regular Expressions, so be aware of special regex relevant characters!
         *
         * List of included URLS separated by whitespaces
         * neodymium.url.includeList = ^http://dev.example.com ^https://stg.example.com
         */
        addTempProperty(TEMP_PROPERTIES_FILE,
                        Map.of("neodymium.url.includeList", "https://posters.xceptance.io:8443 https://www.xceptance.com/en/$"));
    }

    @NeodymiumTest
    public void testIncludeList()
    {
        Selenide.open("https://posters.xceptance.io:8443/");
        Selenide.sleep(1000);

        $(".navbar-toggler").click();
        $(".nav-link[href*='Dining']").click();

        Selenide.open("https://www.xceptance.com/en/");

        // after opening the ULR it is validated if the URL is included and if not, an assertion error will be thrown and the test is stopped
        // clicking a link to a not included URL creates an error
        AssertionFailedError exceptionClick = assertThrows(AssertionFailedError.class, () -> {
            $("#navigation a[href*='xlt']").click();
        });
        Assertions.assertEquals(
            "Opened Link was outside permitted URLs: https://www.xceptance.com/en/xlt/ ==> expected: <true> but was: <false>",
            exceptionClick.getMessage());

        // opening a not included URL creates an error
        AssertionFailedError exceptionOpenUrl = assertThrows(AssertionFailedError.class, () -> {
            Selenide.open("https://github.com/Xceptance/neodymium");
        });
        Assertions.assertEquals(
            "Opened Link was outside permitted URLs: https://github.com/Xceptance/neodymium ==> expected: <true> but was: <false>",
            exceptionOpenUrl.getMessage());
    }

    @AfterAll
    public static void cleanUp()
    {
        deleteTempPropertiesFile(TEMP_PROPERTIES_FILE);
    }
}
