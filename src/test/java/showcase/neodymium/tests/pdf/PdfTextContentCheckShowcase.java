package showcase.neodymium.tests.pdf;

import java.io.File;
import java.io.IOException;

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

@Severity(SeverityLevel.NORMAL)
@Owner("Test Developer")
@Tag("show case")
public class PdfTextContentCheckShowcase extends AbstractTest {
  /*
   * In some cases one may need to check at least the content of a PDF file issued by the
   * system under test. Since Neodymium is implemented in Java based on Maven any available
   * 3rd party technology can be added to the test automation. This show case gives an basic
   * example how to implement an PDF content check using pdfbox.
   * 
   * REQUIRED CONFIGURATION
   * 
   * pom.xml:
   * - org.apache.pdfbox must be added to the dependencies section
   * 
   */
   
  @Test
  @Description("Showcase for PDF text content")
  public void test() throws IOException, InterruptedException {
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
