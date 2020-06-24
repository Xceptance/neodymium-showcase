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
public class SelenideBasicAuthentication extends AbstractTest {

  /*
   * If the system under test requires HTTP Basic Authentication then using Selenide within Neodymium
   * is another possible approach. The Neodymium basic authentication credentials are passed
   * from the configuration to Selenide.open as this approach shows.
   * 
   * Selenide inside uses basic access authentication by prepending username:password@ to the
   * hostname in the URL. For security reasons not all browsers (e.g. Edge) support this method anymore.
   * Other browsers must be enabled before using special configurations.
   * 
   * If the test ware needs to support multiple browsers as Neodymium offers the Neodymium Local Proxy solves this.
   * 
   * REQUIRED CONFIGURATION
   * 
   * config/neodymium.properties:
   * - neodymium.localproxy = false # avoid the Local Proxy
   * - neodymium.basicauth.username = User # HTTP Basic Auth username
   * - neodymium.basicauth.password = Pass # HTTP Basic Auth password
   */

  @Before
  public void configurationCheck() {
    // required configuration checks
    Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy is not set to false",
        false,
        Neodymium.configuration().useLocalProxy());

    Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.basicauth.username is not set",
        "User", // valid username for https://authenticationtest.com/HTTPAuth/
        Neodymium.configuration().basicAuthUsername());

    Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.basicauth.password is not set",
        "Pass", // valid password for user at https://authenticationtest.com/HTTPAuth/ | User
        Neodymium.configuration().basicAuthPassword());
  }

  @Test
  @Description(value = "Showcase for basic authentication using Selenide")
  public void test() {
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
