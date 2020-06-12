package showcase.neodymium.tests.xcmailr;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

import java.io.IOException;

import javax.mail.MessagingException;

import org.aeonbits.owner.ConfigFactory;
import org.apache.http.client.ClientProtocolException;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import net.minidev.json.parser.ParseException;
import showcase.neodymium.data.Credentials;
import showcase.neodymium.data.EmailAccount;
import showcase.neodymium.util.SendEmail;
import showcase.neodymium.util.SendRequest;
import util.xcmailr.XcMailrApi;
import util.xcmailr.XcMailrHelper;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("BasicXcMailrTest")
public class BasicXcMailrTest extends AbstractXcMailrTest
{
    // Setup credentials
    // This is needed to have all needed Credentials within on place
    protected static final Credentials CREDENTIALS = ConfigFactory.create(Credentials.class, System.getenv());

    // Define a test email address which is used through out all tests
    protected static String tempEmail = "test@varmail.de";

    @BeforeClass
    public static void configureApiToken() throws ClientProtocolException, IOException
    {
        // get the apitoken for the used xcmailr app
        final String apiToken = System.getenv("XCMAILR_TOKEN");

        // put apitoken in the properties
        if (apiToken != null)
        {
            properties.put("xcmailr.apiToken", apiToken);
        }

        // set how long emails are valid
        properties.put("xcmailr.temporaryMailValidMinutes", "1");

        // write the property
        writeProperty();

        // Login to xcmailr account
        SendRequest.login(CREDENTIALS.xcmailrEmail(), CREDENTIALS.xcmailrPassword());
    }

    @Test
    public void testEmailCreated() throws IOException
    {
        // Create temporary email with the XcMailrApi
        XcMailrApi.createTemporaryEmail(tempEmail);

        // Send request to check that temporary email was created
        Assert.assertTrue(SendRequest.emailExists(tempEmail));
    }

    @Test
    public void testRetrieveLastEmailBySubject() throws MessagingException, ParseException, IOException
    {
        // Subject of email
        final String subject = "Test";

        // Text of email
        final String textToSend = "Hi\nHow are you?\nBye";

        // Create temporary email with the XcMailrApi
        XcMailrApi.createTemporaryEmail(tempEmail);

        // Create EmailAccount with credentials of test email
        final EmailAccount emailAccount = new EmailAccount(CREDENTIALS.senderEmail(), CREDENTIALS.senderLogin(), CREDENTIALS.senderPassword(), CREDENTIALS.senderServer(), CREDENTIALS.senderPort(), false, true);

        // Send email. The syntax is send(email_from, email_to, subject, text_of_mail)
        SendEmail.send(emailAccount, tempEmail, subject, textToSend);

        // Get emails from the emailserver. Syntax:
        // fetchEmails(email, senderemail, subject_to_filter, text_to_filter, htmlcontent_to_filter, format, lastMatch)
        // emails are in JSON format
        String response = XcMailrApi.retrieveLastEmailBySubject(tempEmail, subject);

        // Get HTML content from first email of response
        String decodedString = XcMailrHelper.getFirstMailsHtmlContent(response);

        // Open HTML content in browser
        XcMailrHelper.openHtmlContentWithCurrentWebDriver(decodedString);

        // check that the text is the same as was send
        $("body").shouldHave(text(textToSend));
    }

    @After
    public void deleteTempEmail() throws ClientProtocolException, IOException
    {
        // Send the request to delete the temporary test email address
        SendRequest.deleteTempEmail(tempEmail);
    }
}
