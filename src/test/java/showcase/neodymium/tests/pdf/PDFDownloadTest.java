package showcase.neodymium.tests.pdf;

import static com.codeborne.selenide.Selenide.sleep;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.codeborne.selenide.Selenide;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * @author kunze
 */
@Severity(SeverityLevel.TRIVIAL)
@Owner("Georg Kunze")
@Tag("smoke")
@DisplayName("PDFDownloadTest")
public class PDFDownloadTest extends AbstractTest {
    @Test
    @Description(value = "Showcase for PDF testing")
    public void testDownloadPDF() throws IOException, InterruptedException {
        File pdfFile = Selenide.download("https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf");
        sleep(1500);
        PDDocument document;
        document = PDDocument.load(pdfFile);
        String text = new PDFTextStripper().getText(document);
        Assert.assertTrue(text.contains("This is a test PDF document."));
        document.close();
    }
}
