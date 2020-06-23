package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.pageobjects.components.Title;

/**
 * @author kunze
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("showcase")
@DisplayName("BasicAuthentication")
public class BasicAuthentication extends AbstractTest
{
    // 
    // If the system under test requires HTTP Basic Authentication then using Neodymium Local Proxy
    // is one possible approach. All requests are automatically routed through the Local Proxy
    // running in the Neodymium test execution process. The Neodymium Local Proxy adds the 
    // credentials to the HTTP header to authenticate the request when opening the site.
    // 
    // REQUIRED CONFIGURATION
    //
    // config/neodymium.properties:
    // - neodymium.localproxy = true          # to passthrough the Local Proxy 
    // - neodymium.basicauth.username = User  # HTTP Basic Auth username
    // - neodymium.basicauth.password = Pass  # HTTP Basic Auth password
    //
    // CAUTION
    // This test case fails with the default configuration shipped in this Showcase.
    // The configuration neodymium.localproxy has to be set from false to true to run it successfully.
    // 
    @Test
    @Description(value = "Showcase for basic authentication using Neodymium Local Proxy")
    public void basicAuthenticationWithNeodymiumLocalProxy()
    {
        // Open webpage which requires authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");

        // Validate title
        new Title().validateTitle("Authentication Test");

        // Check that basic alert message is visible
        $(".alert-success").shouldBe(visible);

        // Close the browser window
        Selenide.closeWindow();
    }
    
    // 
    // If the system under test requires HTTP Basic Authentication then using Selenide within Neodymium
    // is another possible approach. The Neodymium basic authentication credentials are passed
    // from the configuration to Selenide.open as this approach shows.
    //
    // Selenide inside uses basic access authentication by prepending username:password@ to the 
    // hostname in the URL. For security reasons not all browsers (e.g. Edge) support this method anymore.
    // Other browsers must be enabled before using special configurations.
    // 
    // If the test ware needs to support multiple browsers as Neodymium offers the Neodymium Local Proxy solves this.
    // 
    // REQUIRED CONFIGURATION
    //
    // config/neodymium.properties:
    // - neodymium.localproxy = false         # avoid the Local Proxy
    // - neodymium.basicauth.username = User  # HTTP Basic Auth username
    // - neodymium.basicauth.password = Pass  # HTTP Basic Auth password
    // 
    @Test
    @Description(value = "Showcase for basic authentication using Selenide")
    public void basicAuthenticationWithNeodymiumConfiguration()
    {
        // Open webpage which requires authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/",
          "",
          Neodymium.configuration().basicAuthUsername(),
          Neodymium.configuration().basicAuthPassword());

        // Validate title
        new Title().validateTitle("Authentication Test");

        // Check that basic alert message is visible
        $(".alert-success").shouldBe(visible);

        // Close the browser window
        Selenide.closeWindow();
    }


}
