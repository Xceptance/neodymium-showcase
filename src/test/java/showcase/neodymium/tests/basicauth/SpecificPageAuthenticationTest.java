package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import java.io.File;
import java.util.Map;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.aeonbits.owner.ConfigFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

import com.browserup.bup.proxy.auth.AuthType;
import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.Neodymium;

import showcase.neodymium.tests.NeodymiumTest;
import showcase.pageobjects.components.Title;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("SpecificPageAuthenticationTest")
public class SpecificPageAuthenticationTest extends AbstractTest {
    
    private static File tempConfigFile;
    
    @BeforeClass
    public static void beforeClass()
    {
// set up a temporary neodymium.properties
        
        //setup file at location
        final String fileLocation = "config/temp-SpecificPageAuthentication-neodymium.properties";
        tempConfigFile = new File("./" + fileLocation);
        
        //Setup new properties and fill them with needed info
        final Map<String, String> properties = new HashMap<>();
        properties.put("neodymium.localproxy", "true");
        
        //write properties to file
        NeodymiumTest.writeMapToPropertiesFile(properties, tempConfigFile);
        
        //fill temporary properties file with rest of properties
        ConfigFactory.setProperty(Neodymium.TEMPORARY_CONFIG_FILE_PROPERTY_NAME, "file:" + fileLocation);
        
        //assert that web driver is no loaded yet
        Assert.assertNull(Neodymium.getDriver());
    }
    
    @Test
    @Description(value = "Showcase for authentication for specific page")
    public void testSpecificPageAuthentication() {        
        //Setup authentication for authenticationtest.com
        //Note: Syntax here is autoAuthorization(host, username, password, authoraziation_type)
        Neodymium.getLocalProxy().autoAuthorization("authenticationtest.com", "User", "Pass", AuthType.BASIC);
        
        //Open authenticationtest.com with http authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");
        
        //Validate title
        new Title().validateTitle("Authentication Test");
        
        //Check that basic alert message is visible
        $(".alert-success").shouldBe(visible);
        
        //Close the browser window
        Selenide.closeWindow();
    }
    
    @AfterClass
    public static void afterClass()
    {
        //delete temporary properties file
        NeodymiumTest.deleteTempFile(tempConfigFile);
    }

}
