package showcase.neodymium.tests.xcmailr;

import static com.codeborne.selenide.Selenide.$;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.xceptance.neodymium.util.Neodymium;
import com.xceptance.neodymium.util.SelenideAddons;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import util.xcmailr.XcMailrApi;
import util.xcmailr.XcMailrHelper;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("BasicXcMailrTest")
public class XcMailrTest extends AbstractTest
{ /*
   * In case there is a need to validate that e-mail was sent and arrived in some acceptable time or/and to verify if
   * the e-mail has expected subject and content XCmailr Api can be used (more about it here
   * https://github.com/Xceptance/XCMailr#api-token)
   * 
   * Neodymium XCmailr Plugin allows reducing an effort as there is no need to build the requests by yourself and
   * increasing readability, as the request doesn't have to be formulated in the middle of the test
   * 
   * REQUIRED CONFIGURATION
   * 
   * config/xcmailr.properties: - xcmailr.apiToken = <your api token> (read how to get one in the link above)
   * 
   */

    // Define a test email address which is used through out all tests
    protected final String tempEmail = "test@varmail.de";

    // the subject of the first email
    protected final String subject = "Test";

    // the text of the first email
    protected final String textToSend = "Hi\nHow are you?\nBye";

    // email address of the sender
    protected final String sender = "noreply@send-email.org";

    // unique identifier for the e-mail, send in test
    protected String uuid;

    @BeforeClass
    public static void configureApiToken() throws ClientProtocolException, IOException
    {
        // required configuration checks
        Assert.assertNotEquals("Please enter your xcmailr api token to config/xcmailr.properties",
                               "", XcMailrApi.getConfiguration().apiToken());
    }

    @Before
    public void createTempEmailAndSendEmail()
    {
        // Create temporary email with the XcMailrApi
        XcMailrApi.createTemporaryEmail(tempEmail);

        uuid = UUID.randomUUID().toString().substring(0, 8);

        // Send email to temporary mail box from free send email service page
        sendEmail(subject + "_" + uuid, textToSend + "_" + uuid);
    }

    @Test
    public void testRetrieveLastEmail()
    {
        // As email arrived, it can be retrieved by subject,
        String firstEmailRetrievedBySubject = XcMailrApi.retrieveLastEmailBySubject(tempEmail, subject + "_" + uuid);

        // by sender address
        String firstEmailRetrievedBySender = XcMailrApi.retrieveLastEmailBySender(tempEmail, sender);

        // and by text, that this email contains

        // remember to escape regex special characters, otherwise it will come to failure
        String textToRetrieve = (textToSend + "_" + uuid).replaceAll("\\?", "\\\\?").replaceAll("\\)", "\\\\)").replaceAll("\\n", "\\\\r\\\\n");
        String firstEmailRetrievedByHtmlContent = XcMailrApi.fetchEmails(tempEmail, null, null, null, textToRetrieve, null, true);

        // all retrieve methods will return information about the same e-mail,
        // so the retrieved information itself should also be the same for every request
        Arrays.asList(firstEmailRetrievedBySender, firstEmailRetrievedBySubject, firstEmailRetrievedByHtmlContent)
              .forEach(retrievedEmail -> {
                  Assert.assertEquals(firstEmailRetrievedBySender, retrievedEmail);
              });
    }

    @Test
    public void testRetrieveAllEmails()
    {
        // If we send another e-mail,
        sendEmail(subject, textToSend);

        // wait for second mail to arrive to retrieve two e-mails
        Selenide.sleep(1000);

        // it's possible to select both sent e-mails by their sender,
        String bothEmailsRetrievedBySender = XcMailrApi.fetchEmails(tempEmail, sender, null, null, null, null, false);

        // by common parts in their subjects
        String bothEmailsRetrievedBySubject = XcMailrApi.fetchEmails(tempEmail, null, subject, null, null, null, false);

        // and common parts in their html content

        // remember to escape regex special characters, otherwise it will come to failure
        String textToRetrieve = textToSend.replaceAll("\\?", "\\\\?").replaceAll("\\)", "\\\\)").replaceAll("\\n", "\\\\r\\\\n");

        String bothEmailsRetrievedByHtmlContent = XcMailrApi.fetchEmails(tempEmail, null, null, null, textToRetrieve, null, false);

        // all retrieve methods will return information about the same e-mails,
        // so the retrieved information itself should also be the same for every request
        Arrays.asList(bothEmailsRetrievedBySender, bothEmailsRetrievedBySubject, bothEmailsRetrievedByHtmlContent)
              .forEach(retrievedEmails -> {
                  System.out.println(retrievedEmails);
                  Assert.assertEquals(bothEmailsRetrievedBySender, retrievedEmails);
              });
    }

    @Test
    public void testHelperMethods()
    {
        String informationAboutLastRecievedEmail = XcMailrApi.retrieveLastEmailBySubject(tempEmail, subject + "_" + uuid);

        // There are also methods to make server response parsing easier, e.g:

        // get HTML content of the received e-mail (there is also a corresponding method for text content, but as the
        // received e-mail doesn't have one, it cannot be demonstrated here)
        String decodedHtmlContent = XcMailrHelper.getFirstMailsHtmlContent(informationAboutLastRecievedEmail);

        // open HTML content in the current browser window
        XcMailrHelper.openHtmlContentWithCurrentWebDriver(decodedHtmlContent);
        $("body>p:first-child").shouldHave(Condition.exactText(textToSend + "_" + uuid));
    }

    private void sendEmail(String subject, String textToSend)
    {
        // open the web site
        Selenide.open("http://send-email.org/");

        // enter the values
        $("#emailTo").sendKeys(tempEmail);
        $("#subject").sendKeys(subject);
        $("#message").sendKeys(textToSend);

        // slip slider
        SelenideElement slider = $(".handle");
        // TODO replace with dragAndDropBy, as soon as it released
        Condition slipped = new Condition("slide slipped", false)
        {

            @Override
            public boolean apply(Driver driver, WebElement element)
            {
                return element.getCssValue("left") != null && !element.getCssValue("left").equals("0px") && !element.getCssValue("left").equals("auto");
            }
        };
        SelenideAddons.dragAndDropUntilCondition(slider, slider, 172, 0, 100, 0, slipped);

        // to ensure message was sent, verify if alert about successful sent is visible
        Alert successAlter = waitForAlert();

        Assert.assertNotNull(successAlter);

        // press okay button to make alert go
        successAlter.accept();
    }

    private Alert waitForAlert()
    {
        Alert successAlter = null;
        int counter = 0;
        while (successAlter == null && (counter++) < 10)
        {
            successAlter = ExpectedConditions.alertIsPresent().apply(Neodymium.getDriver());
            Selenide.sleep(500);
        }
        return successAlter;
    }
}
