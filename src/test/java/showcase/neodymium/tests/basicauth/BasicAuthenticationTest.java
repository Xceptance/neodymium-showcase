package showcase.neodymium.tests.basicauth;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

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
    
    @Before
    public void setup() {
        //Change property in configuration
        //Activate use of local proxy
        Neodymium.configuration().setProperty("neodymium.localproxy", "true");
        
        //Teardown webdriver
        //This is needed in order to reload the configuration and load the changes
        WebDriverUtils.preventReuseAndTearDown();
    }

    @Test
    @Description(value = "Showcase for basic authentication")
    public void testBasicAuthentication() {
        //Open webdriver with browserprofil of chrome
        WebDriverUtils.setUp("Chrome_1024x768");
        
        //Open webpage with authentication
        Selenide.open("https://authenticationtest.com/HTTPAuth/");
        
        //Validate title
        new Title().validateTitle("Authentication Test");
        
        //Check that basic alert message is visible
        $(".alert-success").shouldBe(visible);
        
        //Close the browser window
        Selenide.closeWindow();
        
        //Teardown webdriver
        WebDriverUtils.preventReuseAndTearDown();
    }
    
    @Test
    @Description(value = "Showcase for authentication for specific page")
    public void testSpecificPageAuthentication() {
        //Open webdriver with browserprofil of chrome
        WebDriverUtils.setUp("Chrome_1024x768");
        
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
        
        //Teardown webdriver
        WebDriverUtils.preventReuseAndTearDown();
    }
    
    @Test
    @Description(value = "Showcase for authentication with MITM proxy")
    public void testMITMProxyAuthentication() {
        //Change property in configuration
        //Activate use of specific certificate
        Neodymium.configuration().setProperty("neodymium.localproxy.certificate", "true");
        
        //Open webdriver with browserprofil of chrome
        WebDriverUtils.setUp("Chrome_1024x768");
        
        //Open HTTPS webpage
        Selenide.open("https://docs.mitmproxy.org/stable/");
        
        //Validate title
        new Title().validateTitle("Introduction");
        
        //Check that mainframe is visible
        $("#main").shouldBe(visible);
        
        //Check that sidebar is visible
        $("#sidebar").shouldBe(visible);
        
        //Close the browser window
        Selenide.closeWindow();
        
        //Teardown webdriver
        WebDriverUtils.preventReuseAndTearDown();
        
        //Change property in configuration
        //Deactivate use of specific certificate
        Neodymium.configuration().setProperty("neodymium.localproxy.certificate", "false");
    }    
    
    @After
    public void tearDown() {
        //Change property in configuration
        //Deactivate use of local proxy
        Neodymium.configuration().setProperty("neodymium.localproxy", "false");
        
        //Open webdriver with browserprofil of chrome
        WebDriverUtils.setUp("Chrome_1024x768");
    }
}
