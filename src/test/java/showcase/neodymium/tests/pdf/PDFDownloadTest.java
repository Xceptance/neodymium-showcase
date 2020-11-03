package showcase.neodymium.tests.pdf;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.module.statement.browser.multibrowser.SuppressBrowsers;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("PDFDownloadTest")
@SuppressBrowsers
public class PDFDownloadTest extends AbstractTest
{
    @Test
    @Description("Showcase for PDF testing")
    public void testDownloadPDF() throws IOException, InterruptedException, URISyntaxException
    {
        // Download PDF
        File pdfFile = Selenide.download("https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf");

        // Create new pdf document and load file into it
        PDDocument document = PDDocument.load(pdfFile);

        // Read text of PDF into string
        String text = new PDFTextStripper().getText(document);

        // Check the text
        Assert.assertTrue(text.contains("This is a test PDF document."));

        // close the document
        document.close();
    }
}
