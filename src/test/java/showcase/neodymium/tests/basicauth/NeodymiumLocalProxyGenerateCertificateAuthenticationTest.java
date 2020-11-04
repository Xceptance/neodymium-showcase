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
 * The default usage of the embedded local proxy provided by Neodymium disables proxy certificate verification. Due to
 * this fact it possible to intercept the communication of the test cases. Some WebDriver will inform you about
 * this.<br>
 * Therefore Neodymium provides a way to configure a certificate that will be used to authenticate the communication
 * between the parties involved. All requests running in the Neodymium test execution process are automatically routed
 * through this proxy which acts as an certified MITM (Man in the Middle). The advantage of this approach is that an
 * officially signed certificate can be used to secure the communication.<br>
 * If a certificate is required that is signed by an authority for your test case this can be configured using Neodymium
 * (see: {@link NeodymiumLocalProxyCertificateAuthenticationTest} show case)<br>
 * <br>
 * <b>REQUIRED CONFIGURATION</b> config/neodymium.properties:
 * <ul>
 * <li>neodymium.localproxy = true # to activate the local proxy</li>
 * <li>neodymium.localproxy.certificate = true # to enable MITM certificate</li>
 * <li>neodymium.localproxy.certificate.generate = true #to activate automatic generation of the certificate</li>
 * </ul>
 * <b>CAUTION</b> This test case fails with the shipped default configuration. Please perform the configurations
 * mentioned above.
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("embedded local proxy")
public class NeodymiumLocalProxyGenerateCertificateAuthenticationTest extends AbstractTest
{
    @Before
    public void configurationCheck()
    {
        // required configuration checks
        Assert.assertEquals("NeodymiumLocalProxyGenerateCertificateAuthenticationTest: neodymium.localproxy is not set to true",
                            true, Neodymium.configuration().useLocalProxy());

        Assert.assertEquals("NeodymiumLocalProxyGenerateCertificateAuthenticationTest: neodymium.localproxy.certificate is not set to true",
                            true, Neodymium.configuration().useLocalProxyWithSelfSignedCertificate());

        Assert.assertEquals("NeodymiumLocalProxyGenerateCertificateAuthenticationTest: neodymium.localproxy.certificate.generate is not set to true",
                            true, Neodymium.configuration().localProxyGenerateSelfSignedCertificate());
    }

    @Test
    @Description(value = "Showcase for certificate authentication using Neodymium Local Proxy")
    public void test()
    {
        Neodymium.getLocalProxy().autoAuthorization("authenticationtest.com", "User", "Pass", AuthType.BASIC);

        // open web page which requires authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");

        // validate title
        new Title().validateTitle("Authentication Test");

        // check that basic alert message is visible
        $(".alert-success").shouldBe(visible);
    }
}
