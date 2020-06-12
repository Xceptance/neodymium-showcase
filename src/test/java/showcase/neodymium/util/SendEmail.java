package showcase.neodymium.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import showcase.neodymium.data.EmailAccount;
import showcase.neodymium.data.SmtpAuthenticator;

public class SendEmail
{

    /**
     * Method to send message
     * 
     * @param messageContainer
     *            message to send
     * @param text
     *            message text
     * @throws MessagingException
     */
    public static void send(EmailAccount emailAccount, String recipent, String subject, String text)
    {
        // New properties for smtp
        final Properties smtpProps = new Properties();

        // Get properties from EmailAccount object to properties
        smtpProps.setProperty("mail.smtp.ssl.enable", Boolean.toString(emailAccount.isSsl()));
        smtpProps.setProperty("mail.smtp.tls.enable", Boolean.toString(emailAccount.isTls()));
        smtpProps.put("mail.smtp.host", emailAccount.getServer());
        smtpProps.put("mail.smtp.auth", "true");
        smtpProps.put("mail.stmp.port", emailAccount.getPort());
        smtpProps.setProperty("mail.user", emailAccount.getLogin());

        // Create session
        final SmtpAuthenticator smtpAuthenticator = new SmtpAuthenticator(emailAccount.getLogin(), emailAccount.getPassword());
        final Session session = Session.getInstance(smtpProps, smtpAuthenticator);

        // Create the message part
        final MimeMessage message = new MimeMessage(session);

        try
        {
            // Generate a email
            final Address[] addresses =
            {
              new InternetAddress(recipent)
            };
            final BodyPart messageBodyPartText = new MimeBodyPart();
            messageBodyPartText.setText(text);
            final BodyPart messageBodyPartHtml = new MimeBodyPart();
            messageBodyPartHtml.setContent(text, "text/html; charset=utf-8");
            final Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPartText);
            multipart.addBodyPart(messageBodyPartHtml);

            message.setRecipients(Message.RecipientType.TO, addresses);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(emailAccount.getEmail()));
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            session.getTransport().close();
        }
        catch (final MessagingException e)
        {
            throw new RuntimeException("failed to send email via smtp server", e);
        }
    }
}
