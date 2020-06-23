package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.Neodymium;
import com.xceptance.neodymium.util.SelenideAddons;

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
public class NeodymiumLocalProxyBasicAuthentication extends AbstractTest {
  /*
   * If the system under test requires HTTP Basic Authentication then using Neodymium Local Proxy
   * is one possible approach. All requests are automatically routed through the Local Proxy
   * running in the Neodymium test execution process. The Neodymium Local Proxy adds the
   * credentials to the HTTP header to authenticate the request when opening the site.
   * 
   * REQUIRED CONFIGURATION
   * 
   * config/neodymium.properties:
   * - neodymium.localproxy = true # to passthrough the Local Proxy
   * - neodymium.basicauth.username = User # HTTP Basic Auth username
   * - neodymium.basicauth.password = Pass # HTTP Basic Auth password
   * 
   * CAUTION
   * This test case fails with the shipped default configuration.
   * The configuration neodymium.localproxy has to be set from false to true to run it successfully.
   */

  @Before
  public void configurationCheck() {
    // required configuration checks
    SelenideAddons.wrapAssertionError(() -> {
      Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.localproxy is not set to true",
          true,
          Neodymium.configuration().useLocalProxy());

      Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.basicauth.username is not set",
          false,
          Neodymium.configuration().basicAuthUsername().matches("^$"));

      Assert.assertEquals("NeodymiumLocalProxyBasicAuthentication: neodymium.basicauth.password is not set",
          false,
          Neodymium.configuration().basicAuthPassword().matches("^$"));
    });
  }

  @Test
  @Description(value = "Showcase for basic authentication using Neodymium Local Proxy")
  public void test() {
    // Open webpage which requires authentication
    Selenide.open("https://authenticationtest.com/HTTPAuth/");

    // Validate title
    new Title().validateTitle("Authentication Test");

    // Check that basic alert message is visible
    $(".alert-success").shouldBe(visible);

    // Close the browser window
    Selenide.closeWindow();
  }

}
