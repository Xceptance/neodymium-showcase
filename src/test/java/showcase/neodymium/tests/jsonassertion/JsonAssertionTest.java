package showcase.neodymium.tests.jsonassertion;

import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.JsonAssert;
import io.qameta.allure.junit4.Tag;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Comparing JSON files can be hard sometimes. To simplify it, JSONAssert was used and the differences between the files are added to the allure report. This
 * showcase is used to demonstrate basic examples of comparing JSON files, where one shows the errors, which can be easily seen in the allure report.
 */
@Tag("JsonAssertion")
public class JsonAssertionTest
{
    // TODO fix nullpointer for getWebDriver if no browser is used or prevent start of browser which is not necessary here

    @NeodymiumTest
    public void testEqualOnDifferingFiles() throws IOException
    {
        // read the JSON files to compare
        Path expectedJsonPath = Path.of("src/test/resources/showcase/neodymium/tests/jsonassertion/expected.json");
        String expectedJson = Files.readString(expectedJsonPath, StandardCharsets.UTF_8);

        Path actualJsonPath = Path.of("src/test/resources/showcase/neodymium/tests/jsonassertion/actual.json");
        String actualJson = Files.readString(actualJsonPath, StandardCharsets.UTF_8);

        // check that the files are equal
        // expected error is caught to make the test pass
        // the differences are still added to the report and can be seen
        AssertionError exception = assertThrows(AssertionError.class, () -> {
            JsonAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT_ORDER);
        });
        assertEquals("java.lang.AssertionError: both json's are not equal (see attachment 'Json Compare') glossary.GlossDiv.GlossList.GlossEntry\n" +
                         "Expected: Acronym\n" +
                         "     but none found\n" +
                         " ; glossary.GlossDiv.GlossList.GlossEntry.GlossDef.GlossSeeAlso[]: Expected 2 values but got 3 ; glossary.GlossDiv.title\n" +
                         "Expected: S\n" +
                         "     got: T\n" +
                         " ; glossary.title\n" +
                         "Expected: example glossary\n" +
                         "     got: example glosary\n" +
                         "\n" +
                         "(Timeout: 0 ms.)", exception.getMessage());
    }

    @NeodymiumTest
    public void testNotEqualOnDifferingFiles() throws IOException
    {
        // read the JSON files to compare
        Path expectedJsonPath = Path.of("src/test/resources/showcase/neodymium/tests/jsonassertion/expected.json");
        String expectedJson = Files.readString(expectedJsonPath, StandardCharsets.UTF_8);

        Path actualJsonPath = Path.of("src/test/resources/showcase/neodymium/tests/jsonassertion/actual.json");
        String actualJson = Files.readString(actualJsonPath, StandardCharsets.UTF_8);

        // check that the files are different
        JsonAssert.assertNotEquals(expectedJson, actualJson, JSONCompareMode.STRICT_ORDER);
    }
}
