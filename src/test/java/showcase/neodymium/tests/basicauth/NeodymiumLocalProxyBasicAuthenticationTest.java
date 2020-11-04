package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.components.Title;

/**
 * If the system under test requires HTTP Basic Authentication then using an embedded local proxy provided by Neodymium
 * is one possible approach.<br>
 * Especially if the standard way (see: {@link SelenideBasicAuthenticationTest} show case) is not applicable due to
 * restrictions of the used WebDriver. The proxy is created on the fly and passed to the WebDriver.<br>
 * All requests running in the Neodymium test execution process are automatically routed through this proxy. The local
 * proxy provided by Neodymium can perform automatic authorizations.<br>
 * The host, username and password settings (from the Neodymium configuration) are taken by default to invoke the auto
 * authorization via the proxy for this host. It is also possible to configure this manually and add additional systems
 * during runtime. (see: {@link NeodymiumLocalProxyBasicAuthenticationHostTest} show case)<br>
 * <br>
 * <b>REQUIRED CONFIGURATION<b> config/neodymium.properties:
 * <ul>
 * <li>neodymium.localproxy = true # to activate the local proxy</li>
 * <li>neodymium.basicauth.username = User # HTTP basic authorization user name</li>
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
    @Before
    public void configurationCheck()
    {
        // required configuration checks
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthenticationTest: neodymium.localproxy is not set to true",
                            true, Neodymium.configuration().useLocalProxy());

        // validate the host of https://authenticationtest.com/HTTPAuth/
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthenticationTest: neodymium.url.host is not set",
                            "authenticationtest.com", Neodymium.configuration().host());

        // validate username for https://authenticationtest.com/HTTPAuth/
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthenticationTest: neodymium.basicauth.username is not set",
                            "User", Neodymium.configuration().basicAuthUsername());

        // validate password for user at https://authenticationtest.com/HTTPAuth/
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthenticationTest: neodymium.basicauth.password is not set",
                            "Pass", Neodymium.configuration().basicAuthPassword());
    }

    @Test
    @Description(value = "Showcase for basic authentication using Neodymium Local Proxy")
    public void test()
    {
        // open the web page which requires authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");

        // validate the page title
        new Title().validateTitle("Authentication Test");

        // check that basic alert message is visible
        $(".alert-success").shouldBe(visible);
    }
}
