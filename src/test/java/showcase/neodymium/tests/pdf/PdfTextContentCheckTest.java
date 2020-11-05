package showcase.neodymium.tests.pdf;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Test;

import com.codeborne.selenide.Selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * In some test cases may need to check the content of a PDF file provided by the system under test. Since Neodymium is
 * implemented in Java and the execution environment is configured with Maven any available 3rd party technology can be
 * added to the test automation.<br>
 * This show case gives an basic example how to implement an PDF content check using pdfbox.<br>
 * <br>
 * <b>REQUIRED CONFIGURATION</b>
 * <ul>
 * <li>pom.xml: - org.apache.pdfbox must be added to the dependencies section</li>
 * </ul>
 */
@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("pdf")
public class PdfTextContentCheckTest extends AbstractTest
{
    @Test
    @Description("Showcase for testing PDF text content")
    public void test() throws IOException, InterruptedException, URISyntaxException
    {
        // download PDF file
        File pdfFile = Selenide.download("https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf");

        // create new PDF document and load file into it
        PDDocument document = PDDocument.load(pdfFile);

        // save the text of the PDF file
        String text = new PDFTextStripper().getText(document);

        // check the text
        Assert.assertTrue(text.contains("This is a test PDF document."));

        // close the document
        document.close();
    }
}
