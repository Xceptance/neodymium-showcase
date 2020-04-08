package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.browserup.bup.proxy.auth.AuthType;
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
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("BasicAuthenticationTest")
public class BasicAuthenticationTest extends AbstractTest {

    @Test
    @Description(value = "Showcase for basic authentication")
    public void testBasicAuthentication() {
        //Neodymium.getLocalProxy().autoAuthorization("https://localhost:8443/webdav/", "webdav", "webdav", AuthType.BASIC);
        Selenide.open("https://localhost:8443/webdav/");
        //Selenide.open("https://localhost:8443/webdav/", "", Neodymium.configuration().basicAuthUsername(), Neodymium.configuration().basicAuthPassword());
        new Title().validateTitle("Content of folder/");
        $("h1").should(exist);
    }
}
