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

@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("show case")
public class NeodymiumLocalProxyCertificateAuthentication extends AbstractTest {

  /*
   * If the system under test requires HTTPS certificated authentication then using Neodymium Local Proxy
   * as a MITM (Man in the Middle) is one possible approach. All requests are automatically routed through
   * the Local Proxy running in the Neodymium test execution process. The Neodymium Local Proxy uses the
   * provided certificate information to connect with HTTPS when opening the site.
   * 
   * REQUIRED CONFIGURATION
   * 
   * config/neodymium.properties:
   * - neodymium.localproxy = true # to passthrough the Local Proxy
   * - neodymium.localproxy.certificate = true # to enable MITM certificate
   * - neodymium.localproxy.certificate.archiveFile = ./config/Certificate.pfx # certificate archive file
   * - neodymium.localproxy.certificate.archivetype = PKCS12 # archive file format
   * - neodymium.localproxy.certificate.name = e6f60fbd-f9f3-436f-aba5-452861ae4570
   * - neodymium.localproxy.certificate.password = MITMCertificatePassword
   * 
   * CAUTION
   * This test case fails with the shipped default configuration.
   * The configurations neodymium.localproxy and neodymium.localproxy.certificate has to be set from false
   * to true to run it successfully.
   */

  @Before
  public void configurationCheck() {
    // required configuration checks
    Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy is not set to true",
        true,
        Neodymium.configuration().useLocalProxy());

    Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy.certificate is not set to true",
        true,
        Neodymium.configuration().useLocalWithSelfSignedCertificate());

    Assert.assertEquals(
        "NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy.certificate.archiveFile check failed",
        "./config/Certificate.pfx",
        Neodymium.configuration().localProxyCertificateArchiveFile());

    Assert.assertEquals(
        "NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy.certificate.archivetype check failed",
        "PKCS12",
        Neodymium.configuration().localProxyCertificateArchiveType());

    Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy.certificate.name check failed",
        "e6f60fbd-f9f3-436f-aba5-452861ae4570",
        Neodymium.configuration().localProxyCertificateName());

    Assert.assertEquals(
        "NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy.certificate.password check failed",
        "MITMCertificatePassword",
        Neodymium.configuration().localProxyCertificatePassword());
  }

  @Test
  @Description(value = "Showcase for certificate authentication using Neodymium Local Proxy")
  public void test() {
    // Open HTTPS webpage
    Selenide.open("https://docs.mitmproxy.org/stable/");

    // Validate title
    new Title().validateTitle("Introduction");

    // Check that mainframe is visible
    $("#main").shouldBe(visible);

    // Check that sidebar is visible
    $("#sidebar").shouldBe(visible);

    // Close the browser window
    Selenide.closeWindow();
  }

}
