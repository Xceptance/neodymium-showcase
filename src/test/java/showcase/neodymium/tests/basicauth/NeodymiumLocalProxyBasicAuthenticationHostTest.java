package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.browserup.bup.proxy.auth.AuthType;
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
 * The local proxy adds the credentials to the HTTP header to authenticate the request when opening the site. <br>
 * <br>
 * <b>REQUIRED CONFIGURATION</b> config/neodymium.properties:
 * <ul>
 * <li>neodymium.localproxy = true # to activate the Local Proxy</li>
 * <ul>
 * <b>CAUTION:</b> This test case fails with the shipped default configuration. Please perform the configurations
 * mentioned above.
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("embedded local proxy")
@Tag("basic authorization")
public class NeodymiumLocalProxyBasicAuthenticationHostTest extends AbstractTest
{
    @Before
    public void configurationCheck()
    {
        // required configuration checks
        Assert.assertEquals("NeodymiumLocalProxyBasicAuthenticationHostTest: neodymium.localproxy is not set to true",
                            true, Neodymium.configuration().useLocalProxy());
    }

    @Test
    @Description(value = "Showcase for basic authentication using Neodymium Local Proxy")
    public void test()
    {
        // setup authentication for specific host via proxy
        // you can authenticate other host by repeating this with the respective credentials
        Neodymium.getLocalProxy().autoAuthorization("authenticationtest.com", "User", "Pass", AuthType.BASIC);

        // open web page which requires authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");

        // validate title
        new Title().validateTitle("Authentication Test");

        // check that basic alert message is visible
        $(".alert-success").shouldBe(visible);
    }
}
