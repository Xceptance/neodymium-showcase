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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static showcase.flows.PropertiesFlow.addTempProperty;
import static showcase.flows.PropertiesFlow.deleteTempPropertiesFile;

/**
 * Sometimes it can be useful to limit the test automation to certain URLs, especially if it does random navigation or if there could be misconfigured link. To
 * prevent that the tests wandering off into the wild west of the web, URL exclude lists can be defined and used as demonstrated in this showcase. If any of the
 * excluded URLs is opened the test will fail immediately.
 */
@Tag("UrlValidation")
public class UrlValidationExcludeTest extends AbstractTest
{
    public static final String TEMP_PROPERTIES_FILE = "temp-UrlValidationExcludeTest-neodymium.properties";

    @BeforeAll
    public static void setExcludeList()
    {
        /*
         * In general the properties should be defined directly in the neodymium.properties, but for the showcase to see some example values this is done
         * here.
         * The following call will create a temp properties file with the values:
         *
         * neodymium.url.excludeList=https://posters.xceptance.io:8443.*Dining https://www.xceptance.com/en/
         *
         * NOTE: The URLs needs to be provided as Regular Expressions, so be aware of special regex relevant characters!
         *
         * List of excluded URLS separated by whitespaces
         * neodymium.url.excludeList = ^http://prod.example.com/[?]testmode=true ^https://stg.example.com
         */
        addTempProperty(TEMP_PROPERTIES_FILE,
                        Map.of("neodymium.url.excludeList", "https://posters.xceptance.io:8443.*Dining https://www.xceptance.com/en/"));
    }

    @NeodymiumTest
    public void testExcludeList()
    {
        Selenide.open("https://posters.xceptance.io:8443/");
        Selenide.sleep(1000);

        // after opening the ULR it is validated if the URL is excluded and if so, an assertion error will be thrown and the test is stopped
        // expected error is caught to make the test pass
        AssertionFailedError exceptionRegex = assertThrows(AssertionFailedError.class, () -> {
            Selenide.open("https://posters.xceptance.io:8443/topCategory/Dining?categoryId=2");
        });
        Assertions.assertEquals(
            "Opened Link was to forbidden site: https://posters.xceptance.io:8443/topCategory/Dining?categoryId=2 ==> expected: <true> but was: <false>",
            exceptionRegex.getMessage());

        // expected error is caught to make the test pass
        AssertionFailedError exception = assertThrows(AssertionFailedError.class, () -> {
            Selenide.open("https://www.xceptance.com/en/");
        });
        Assertions.assertEquals(
            "Opened Link was to forbidden site: https://www.xceptance.com/en/ ==> expected: <true> but was: <false>",
            exception.getMessage());
    }

    @AfterAll
    public static void cleanUp()
    {
        deleteTempPropertiesFile(TEMP_PROPERTIES_FILE);
    }
}
