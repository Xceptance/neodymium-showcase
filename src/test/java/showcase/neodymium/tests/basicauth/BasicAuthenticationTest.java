package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.aeonbits.owner.ConfigFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
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
import showcase.neodymium.tests.NeodymiumTest;
import showcase.pageobjects.components.Title;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("BasicAuthenticationTest")

public class BasicAuthenticationTest extends AbstractTest
{

    private static File tempConfigFile;

    @BeforeClass
    public static void beforeClass()
    {
        // setup file at location
        final String fileLocation = "config/temp-BasicAuthentication-neodymium.properties";
        tempConfigFile = new File("./" + fileLocation);

        // Setup new properties and fill them with needed info
        final Map<String, String> properties = new HashMap<>();
        properties.put("neodymium.localproxy", "true");

        // write properties to file
        NeodymiumTest.writeMapToPropertiesFile(properties, tempConfigFile);

        // fill temporary properties file with rest of properties
        ConfigFactory.setProperty(Neodymium.TEMPORARY_CONFIG_FILE_PROPERTY_NAME, "file:" + fileLocation);

        // assert that web driver is no loaded yet
        Assert.assertNull(Neodymium.getDriver());
    }

    @Test
    @Description(value = "Showcase for basic authentication")
    public void testBasicAuthentication()
    {
        // Open webpage with authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");

        // Validate title
        new Title().validateTitle("Authentication Test");

        // Check that basic alert message is visible
        $(".alert-success").shouldBe(visible);

        // Close the browser window
        Selenide.closeWindow();
    }

    @AfterClass
    public static void afterClass()
    {
        // delete temporary properties file
        NeodymiumTest.deleteTempFile(tempConfigFile);
    }
}
