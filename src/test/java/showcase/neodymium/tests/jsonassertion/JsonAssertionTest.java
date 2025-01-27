package showcase.neodymium.tests.jsonassertion;

import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.JsonAssert;
import io.qameta.allure.junit4.Tag;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Comparing JSON files can be hard sometimes. To simplify it, JSONAssert was used and the differences between the files are added to the allure report. This
 * showcase is used to demonstrate basic examples of comparing JSON files, where one shows the errors, which can be easily seen in the allure report.
 */
@Tag("JsonAssertion")
public class JsonAssertionTest
{
    @NeodymiumTest
    public void failingTestEqualOnDifferingFiles() throws IOException
    {
        // read the JSON files to compare
        Path expectedJsonPath = Path.of("src/test/resources/showcase/neodymium/tests/jsonassertion/expected.json");
        String expectedJson = Files.readString(expectedJsonPath, StandardCharsets.UTF_8);

        Path actualJsonPath = Path.of("src/test/resources/showcase/neodymium/tests/jsonassertion/actual.json");
        String actualJson = Files.readString(actualJsonPath, StandardCharsets.UTF_8);

        // check that the files are equal
        // expected error is caught to make the test pass
        // the differences are still added to the report and can be seen
        JsonAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT_ORDER);
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
