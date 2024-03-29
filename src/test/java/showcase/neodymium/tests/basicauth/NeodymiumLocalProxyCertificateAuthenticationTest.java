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
 * If a self signed certificate is sufficient for your test case the can be automatically generated by Neodymium (see:
 * {@link NeodymiumLocalProxyGenerateCertificateAuthenticationTest} show case).<br>
 * <br>
 * <b>REQUIRED CONFIGURATION:</b> <i>config/neodymium.properties</i>
 * <ul>
 * <li>neodymium.localproxy = true # to activate the local proxy</li>
 * <li>neodymium.localproxy.certificate = true # to enable MITM certificate</li>
 * <li>neodymium.localproxy.certificate.generate = false # to disable automatic generation of the certificate</li>
 * <li>neodymium.localproxy.certificate.archiveFile = ./config/Certificate.pfx # certificate archive file</li>
 * <li>neodymium.localproxy.certificate.archivetype = PKCS12 # archive file format</li>
 * <li>neodymium.localproxy.certificate.name = e6f60fbd-f9f3-436f-aba5-452861ae4570</li>
 * <li>neodymium.localproxy.certificate.password = MITMCertificatePassword</li>
 * </ul>
 * <b>CAUTION:</b> This test case fails with the shipped default configuration. Please perform the configurations
 * mentioned above.
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("embedded local proxy")
public class NeodymiumLocalProxyCertificateAuthenticationTest extends AbstractTest
{
    @Before
    public void configurationCheck()
    {
        // required configuration checks
        Assert.assertEquals("NeodymiumLocalProxyCertificateAuthenticationTest: neodymium.localproxy is not set to true",
                            true, Neodymium.configuration().useLocalProxy());

        Assert.assertEquals("NeodymiumLocalProxyCertificateAuthenticationTest: neodymium.localproxy.certificate is not set to true",
                            true, Neodymium.configuration().useLocalProxyWithSelfSignedCertificate());

        Assert.assertEquals("NeodymiumLocalProxyCertificateAuthenticationTest: neodymium.localproxy.certificate.generate is not set to false",
                            false, Neodymium.configuration().localProxyGenerateSelfSignedCertificate());

        Assert.assertEquals("NeodymiumLocalProxyCertificateAuthenticationTest: neodymium.localproxy.certificate.archiveFile check failed",
                            "./config/Certificate.pfx", Neodymium.configuration().localProxyCertificateArchiveFile());

        Assert.assertEquals("NeodymiumLocalProxyCertificateAuthenticationTest: neodymium.localproxy.certificate.archivetype check failed",
                            "PKCS12", Neodymium.configuration().localProxyCertificateArchiveType());

        Assert.assertEquals("NeodymiumLocalProxyCertificateAuthenticationTest: neodymium.localproxy.certificate.name check failed",
                            "e6f60fbd-f9f3-436f-aba5-452861ae4570", Neodymium.configuration().localProxyCertificateName());

        Assert.assertEquals("NeodymiumLocalProxyCertificateAuthenticationTest: neodymium.localproxy.certificate.password check failed",
                            "MITMCertificatePassword", Neodymium.configuration().localProxyCertificatePassword());
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
