package showcase.neodymium.tests.basicauth;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
 * If the system under test requires HTTP Basic Authentication then using an embedded local proxy provided by Neodymium is one possible approach.<br> Especially
 * if the standard way (see: {@link SelenideBasicAuthenticationTest} showcase) is not applicable due to restrictions of the used WebDriver. The proxy is created
 * on the fly and passed to the WebDriver.<br> All requests running in the Neodymium test execution process are automatically routed through this proxy. The
 * local proxy provided by Neodymium can perform automatic authorizations.<br> The host, username and password settings (from the Neodymium configuration) are
 * taken by default to invoke the auto authorization via the proxy for this host. It is also possible to configure this manually and add additional systems
 * during runtime (see: {@link NeodymiumLocalProxyBasicAuthenticationHostTest} showcase).<br>
 * <br>
 * <b>REQUIRED CONFIGURATION:</b> <i>config/neodymium.properties</i>
 * <ul>
 * <li>neodymium.localproxy = true # to activate the local proxy</li>
 * <li>neodymium.basicauth.username = User # HTTP basic authorization username</li>
 * <li>neodymium.basicauth.password = Pass # HTTP basic authorization password</li>
 * <li>neodymium.url.host = authenticationtest.com # HTTP basic authorization host (taken by default)</li>
 * </ul>
 * <b>CAUTION:</b> This test case fails with the shipped default configuration. Please perform the configurations
 * mentioned above.
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("embedded local proxy")
@Tag("basic authorization")
public class NeodymiumLocalProxyBasicAuthenticationTest extends AbstractTest
{
    public static final String TEMP_PROPERTIES_FILE = "temp-LocalProxy-neodymium.properties";

    @BeforeAll
    public static void addLocalProxyTempProperties()
    {
        /*
         * In general the properties should be defined directly in the neodymium.properties, but for the showcase to see some example values this is done
         * here.
         * The following call will create a temp properties file with the values:
         *
         * neodymium.localproxy=true
         * neodymium.url.host=authenticationtest.com
         * neodymium.basicauth.username=User
         * neodymium.basicauth.password=Pass
         */
        addTempProperty(TEMP_PROPERTIES_FILE,
                        Map.of(
                            "neodymium.localproxy", "true",
                            "neodymium.url.host", "authenticationtest.com",
                            "neodymium.basicauth.username", "User",
                            "neodymium.basicauth.password", "Pass"
                        ));
    }

    @NeodymiumTest
    @Description(value = "Showcase for basic authentication using Neodymium Local Proxy")
    public void test()
    {
        // open the web page which requires authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");

        // validate the page title
        //        new Title().validateTitle("Authentication Test");

        // check that basic alert message is visible
        $(".alert-success").shouldBe(visible);
    }

    @AfterAll
    public static void cleanUp()
    {
        deleteTempPropertiesFile(TEMP_PROPERTIES_FILE);
        deleteTempPropertiesFile("embeddedLocalProxySelfSignedRootCertificate.p12");
    }
}
