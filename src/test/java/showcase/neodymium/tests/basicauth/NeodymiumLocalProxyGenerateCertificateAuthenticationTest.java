package showcase.neodymium.tests.basicauth;

import com.browserup.bup.proxy.auth.AuthType;
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
 * The default usage of the embedded local proxy provided by Neodymium disables proxy certificate verification. Due to this fact it possible to intercept the
 * communication of the test cases. Some WebDriver will inform you about this.<br> Therefore Neodymium provides a way to configure a certificate that will be
 * used to authenticate the communication between the parties involved. All requests running in the Neodymium test execution process are automatically routed
 * through this proxy which acts as a certified MITM (Man in the Middle). The advantage of this approach is that an officially signed certificate can be used to
 * secure the communication.<br> If a certificate is required that is signed by an authority for your test case this can be configured using Neodymium (see:
 * {@link NeodymiumLocalProxyCertificateAuthenticationTest} showcase)<br>
 * <br>
 * <b>REQUIRED CONFIGURATION:</b> <i>config/neodymium.properties</i>
 * <ul>
 * <li>neodymium.localproxy = true # to activate the local proxy</li>
 * <li>neodymium.localproxy.certificate = true # to enable MITM certificate</li>
 * <li>neodymium.localproxy.certificate.generate = true # to activate automatic generation of the certificate</li>
 * </ul>
 * <b>CAUTION:</b> This test case fails with the shipped default configuration. Please perform the configurations
 * mentioned above.
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("embedded local proxy")
public class NeodymiumLocalProxyGenerateCertificateAuthenticationTest extends AbstractTest
{
    public static final String TEMP_PROPERTIES_FILE = "temp-LocalProxyGenCert-neodymium.properties";

    @BeforeAll
    public static void addLocalProxyGenCertTempProperties()
    {
        /*
         * In general the properties should be defined directly in the neodymium.properties, but for the showcase to see some example values this is done
         * here.
         * The following call will create a temp properties file with the values:
         *
         * neodymium.localproxy.enable=true
         * neodymium.localproxy.certificate=true
         * neodymium.localproxy.certificate.generate=true
         */
        addTempProperty(TEMP_PROPERTIES_FILE,
                        Map.of(
                            "neodymium.localproxy", "true",
                            "neodymium.localproxy.certificate", "true",
                            "neodymium.localproxy.certificate.generate", "true"
                        ));
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

    @AfterAll
    public static void cleanUp()
    {
        deleteTempPropertiesFile(TEMP_PROPERTIES_FILE);
        deleteTempPropertiesFile("embeddedLocalProxySelfSignedRootCertificate.p12");
    }
}
