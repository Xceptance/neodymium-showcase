package showcase.neodymium.tests.basicauth;

import org.junit.Test;

import com.codeborne.selenide.Selenide;

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
@DisplayName("AuthenticationInURLTest")
public class AuthenticationInURLTest extends AbstractTest {
    
    @Test
    @Description(value = "Showcase for basic authentication")
    public void testAuthenticationInURL() {
        Selenide.open("http://www.httpwatch.com/httpgallery/authentication/authenticatedimage/default.aspx?0.7349707232788205",
                      "",
                      "httpwatch", 
                      "foo");
        new Title().validateTitle("default.aspx (300×60)");
    }
}
