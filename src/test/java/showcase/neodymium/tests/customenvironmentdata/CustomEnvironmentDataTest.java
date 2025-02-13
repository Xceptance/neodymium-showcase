package showcase.neodymium.tests.customenvironmentdata;

import com.xceptance.neodymium.junit5.NeodymiumTest;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.BeforeAll;
import showcase.neodymium.tests.AbstractTest;

/**
 * It is possible to add custom values to differentiate between environments or profiles within your test runs, enhancing the granularity of your test
 * environment information.
 */
@Tag("CustomEnvironmentData")
public class CustomEnvironmentDataTest extends AbstractTest
{
    @BeforeAll
    public static void addCustomEnvironmentDataToTempProperties()
    {
        /*
         * In general the properties should be defined directly in the neodymium.properties, but for the showcase to see some example values this is done
         * here.
         * The following call will add system properties with the values:
         *
         * neodymium.report.environment.enableCustomData = true
         * neodymium.report.environment.custom.customData = customValue
         */
        System.setProperty("neodymium.report.environment.enableCustomData", "true");
        System.setProperty("neodymium.report.environment.custom.customData", "customValue");
    }

    @NeodymiumTest
    public void testCustomEnvironmentData()
    {
    }
}
