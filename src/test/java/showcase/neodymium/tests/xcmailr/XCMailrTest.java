package showcase.neodymium.tests.xcmailr;

import java.io.IOException;

import org.apache.hc.client5.http.ClientProtocolException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.plugin.xcmailr.XcMailrApi;
import com.xceptance.neodymium.util.SelenideAddons;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import xcmailr.client.Mail;

/**
 * In case there is a need to validate that e-mail was sent and arrived in some acceptable time or/and to verify if the
 * e-mail has expected subject and content XCMailr Api can be used (more about it here
 * https://github.com/Xceptance/XCMailr#api-token)<br>
 * Neodymium XCMailr Plugin allows reducing an effort as there is no need to build the requests by yourself and
 * increasing readability, as the request doesn't have to be formulated in the middle of the test.<br>
 * <br>
 * <b>ATTENTION:</b> This test case requires manual actions.<br>
 * To have this test case working you need to perform three tasks.
 * <ul>
 * <li>* The first is to configure the things mentioned below.</li>
 * <li>The second is to start the test.</li>
 * <li>The third task is to send an e-mail to the configured mailbox using the subject and the textToSend configures in
 * the class fields below. If the e-mail is received within 15 minutes your test should become successful.</li>
 * </ul>
 * <b>REQUIRED CONFIGURATION</b>:<br>
 * <i>config/xcmailr.properties</i>:
 * <ul>
 * <li>xcmailr.apiToken = [YOUR_XCMAILR_API_TOKEN] (read how to get one in the link above)</li>
 * <li>xcmailr.url = [YOUR_XCMAILR_INSTANCE_URL] (read how to get one in the link above)</li>
 * </ul>
 * <i>as a class field below</i>:
 * <ul>
 * <li>receiverEmail = the e-mail address you like to create a mailbox on your XcMailr instance</li>
 * </ul>
 * <i>pom.xml</i>:
 * <ul>
 * <li>com.xceptance/neodymium-plugin-xcmailr must be added to the dependencies section</li>
 * </ul>
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Test Developer")
@Tag("smoke")
@DisplayName("BasicXCMailrTest")
public class XCMailrTest extends AbstractTest
{

    // the subject of the first email
    protected final static String subject = "TestMailSubjectToUse";

    // the text of the first email
    protected final static String textToSend = "This is the text you should send.";

    // Define a test email address which is used through out the test
    protected final static String receiverEmail = "";

    @BeforeClass
    public static void configureApiToken() throws ClientProtocolException, IOException
    {
        // required configuration checks
        Assert.assertNotEquals("Please enter your xcmailr api token to config/xcmailr.properties",
                               "", XcMailrApi.getConfiguration().apiToken());

        Assert.assertNotEquals("Please enter your xcmailr url to config/xcmailr.properties",
                               "", XcMailrApi.getConfiguration().url());

        Assert.assertNotEquals("Please enter the e-mail address you will create at your xcmailr instance.",
                               "", receiverEmail);
    }

    @Before
    public void createTempEmailAndSendEmail()
    {
        // Create temporary email with the XcMailrApi
        XcMailrApi.createTemporaryEmail(receiverEmail, false);
    }

    @After
    public void deleteTempEmail()
    {
        // Delete temporary email with the XcMailrApi
        XcMailrApi.deleteMailbox(receiverEmail);
    }

    @Test
    public void testRetrieveLastEmail()
    {
        // Get the mail
        Mail emailRetrievedBySubject = XcMailrApi.retrieveLastEmailBySubject(receiverEmail, subject);
        // A loose validation in case you have a default signature
        Assert.assertTrue(emailRetrievedBySubject.textContent.contains(textToSend));

        // Open the HTML content in the local browser
        SelenideAddons.openHtmlContentWithCurrentWebDriver(emailRetrievedBySubject.htmlContent);
        // A loose validation in case you have a default signature
        Selenide.$("body").shouldHave(Condition.text(textToSend));
    }
}
