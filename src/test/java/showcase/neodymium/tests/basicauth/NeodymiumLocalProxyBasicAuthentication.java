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

/*
 * If the system under test requires HTTP Basic Authentication and the standard way (see: SelenideBasicAuthentication showcase) 
 * is not applicable due to the used WebDriver then using a local proxy provided by Neodymium is one possible approach. 
 * The proxy is passed to the WebDriver and all requests are automatically routed through the local proxy running in the
 * Neodymium test execution process.
 * The local proxy provided by Neodymium can perform an automatic authorizations when the credentials and the matching url are provided.
 * 
 * The neodymium.url.host setting is taken by default to invoke the proxy for a host, but can also be set during
 * runtime. (see: NeodymiumLocalProxyBasicAuthenticationHost showcase) 
 * 
 * REQUIRED CONFIGURATION
 * config/neodymium.properties: 
 * - neodymium.localproxy = true # to activate the local proxy 
 * - neodymium.basicauth.username = User # HTTP basic authorization user name 
 * - neodymium.basicauth.password = Pass # HTTP basic authorization password 
 * - neodymium.url.host = authenticationtest.com # HTTP basic authorization host (taken by default) 
 * 
 * CAUTION - This test case fails with the shipped default configuration. 
 * The configuration neodymium.localproxy has to be set from false to true to run it successfully.
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("show case")
public class NeodymiumLocalProxyBasicAuthentication extends AbstractTest
{
    @Before
    public void configurationCheck()
    {
        // required configuration checks
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy is not set to true",
                            true, Neodymium.configuration().useLocalProxy());

        // validate the host of https://authenticationtest.com/HTTPAuth/
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.url.host is not set",
                            "authenticationtest.com", Neodymium.configuration().host());

        // validate username for https://authenticationtest.com/HTTPAuth/
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.basicauth.username is not set",
                            "User", Neodymium.configuration().basicAuthUsername());

        // valid password for user at https://authenticationtest.com/HTTPAuth/
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.basicauth.password is not set",
                            "Pass", Neodymium.configuration().basicAuthPassword());
    }

    @Test
    @Description(value = "Showcase for basic authentication using Neodymium Local Proxy")
    public void test()
    {
        // open the webpage which requires authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");

        // validate the page title
        new Title().validateTitle("Authentication Test");

        // Check that basic alert message is visible
        $(".alert-success").shouldBe(visible);
    }
}
