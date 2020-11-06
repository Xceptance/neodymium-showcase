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
 * If the system under test requires HTTP Basic Authentication then using Selenide together with the Neodymium
 * configuration is a possible approach. The basic authentication credentials can be set within the Neodymium
 * configuration and used when calling
 * {@link Selenide#open(String relativeOrAbsoluteUrl, String domain, String login, String password)} as this show case
 * demonstrates.<br>
 * Selenide prefixes the basic authentication credentials to the URL. Due to security reasons this method is not
 * supported for all browsers (e.g. Edge) anymore. Some browsers require enabling this method before it can be used. If
 * your test project needs testing with such a browser you can consider the usage of the embedded local proxy provided
 * by Neodymium as a solution (see: {@link NeodymiumLocalProxyBasicAuthenticationHostTest} show case).<br>
 * <br>
 * <b>REQUIRED CONFIGURATION</b> <i>config/neodymium.properties</i>:
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

    @Before
    public void configurationCheck()
    {
        // required configuration checks
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy is not set to false",
                            false, Neodymium.configuration().useLocalProxy());

        // valid user name for https://authenticationtest.com/HTTPAuth/
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.basicauth.username is not set",
                            "User", Neodymium.configuration().basicAuthUsername());

        // valid password for user at https://authenticationtest.com/HTTPAuth/ | User
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.basicauth.password is not set",
                            "Pass", Neodymium.configuration().basicAuthPassword());
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
}
