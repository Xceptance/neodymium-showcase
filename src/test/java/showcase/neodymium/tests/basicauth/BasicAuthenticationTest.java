package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.browserup.bup.proxy.auth.AuthType;
import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.Neodymium;
import com.xceptance.neodymium.util.WebDriverUtils;

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
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("BasicAuthenticationTest")

public class BasicAuthenticationTest extends AbstractTest {

    //@Test
    @Description(value = "Showcase for basic authentication")
    public void testBasicAuthentication() {
        Selenide.open("https://authenticationtest.com/HTTPAuth/");
        new Title().validateTitle("Authentication Test");
        $(".alert-success").shouldBe(visible);
        Selenide.closeWindow();
        WebDriverUtils.preventReuseAndTearDown();
    }
    
    //@Test
    @Description(value = "Showcase for authentication for specific page")
    public void testSpecificPageAuthentication() {
        WebDriverUtils.setUp("Chrome_1024x768");
        Neodymium.getLocalProxy().autoAuthorization("authenticationtest.com", "User", "Pass", AuthType.BASIC);
        Selenide.open("https://authenticationtest.com/HTTPAuth/");
        new Title().validateTitle("Authentication Test");
        $(".alert-success").shouldBe(visible);
        Selenide.closeWindow();
        WebDriverUtils.preventReuseAndTearDown();
    }
    
    @Test
    @Description(value = "Showcase for authentication with MITM proxy")
    public void testMITMProxyAuthentication() {
        //WebDriverUtils.setUp("Chrome_1024x768");
        Selenide.open("https://docs.mitmproxy.org/stable/");
        new Title().validateTitle("Introduction");
        $("#main").shouldBe(visible);
        $("#sidebar").shouldBe(visible);
        Selenide.closeWindow();
        WebDriverUtils.preventReuseAndTearDown();
    }
}
