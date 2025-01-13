package showcase.neodymium.tests.basicauth;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.Neodymium;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.Tag;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.components.Title;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static showcase.flows.PropertiesFlow.addTempProperty;
import static showcase.flows.PropertiesFlow.deleteTempPropertiesFile;

/**
 * If the system under test requires HTTP Basic Authentication then using Selenide together with the Neodymium configuration is a possible approach. The basic
 * authentication credentials can be set within the Neodymium configuration and used when calling
 * {@link Selenide#open(String relativeOrAbsoluteUrl, String domain, String login, String password)} as this showcase demonstrates.<br> Selenide prefixes the
 * basic authentication credentials to the URL. Due to security reasons this method is not supported for all browsers (e.g. Edge) anymore. Some browsers require
 * enabling this method before it can be used. If your test project needs testing with such a browser you can consider the usage of the embedded local proxy
 * provided by Neodymium as a solution (see: {@link NeodymiumLocalProxyBasicAuthenticationHostTest} showcase).<br>
 * <br>
 * <b>REQUIRED CONFIGURATION:</b> <i>config/neodymium.properties</i>
 * <ul>
 * <li>neodymium.localproxy = false # deactivate the embedded local proxy</li>
 * <li>neodymium.basicauth.username = User # HTTP Basic Auth username</li>
 * <li>neodymium.basicauth.password = Pass # HTTP Basic Auth password</li>
 * </ul>
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("basic authorization")
public class SelenideBasicAuthenticationTest extends AbstractTest
{
    public static final String TEMP_PROPERTIES_FILE = "temp-SelenideAuth-neodymium.properties";

    @BeforeAll
    public static void addSelenideAuthTempProperties()
    {
        /*
         * In general the properties should be defined directly in the neodymium.properties, but for the showcase to see some example values this is done
         * here.
         * The following call will create a temp properties file with the values:
         *
         * neodymium.localproxy=false
         * neodymium.basicauth.username=User
         * neodymium.basicauth.password=Pass
         */
        addTempProperty(TEMP_PROPERTIES_FILE,
                        Map.of(
                            "neodymium.localproxy", "false",
                            "neodymium.basicauth.username", "User",
                            "neodymium.basicauth.password", "Pass"
                        ));
    }

    @Test
    @Description(value = "Showcase for basic authentication using Selenide")
    public void test()
    {
        // open web page which requires authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/", "", Neodymium.configuration().basicAuthUsername(),
                      Neodymium.configuration().basicAuthPassword());

        // validate title
        new Title().validateTitle("Authentication Test");

        // check that basic alert message is visible
        $(".alert-success").shouldBe(visible);
    }

    @AfterAll
    public static void cleanUp()
    {
        deleteTempPropertiesFile(TEMP_PROPERTIES_FILE);
    }
}
