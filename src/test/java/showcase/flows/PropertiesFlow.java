package showcase.flows;

import com.xceptance.neodymium.util.Neodymium;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertiesFlow
{
    /**
     * Helper method to add properties to a temporary properties file in the config directory. Can e.g. be used to define excluded URLs in a file named
     * 'temp-UrlValidationExcludeTest-neodymium.properties' like follows:
     * <pre><code>addTempProperty("temp-UrlValidationExcludeTest-neodymium.properties",
     * Map.of("neodymium.url.excludeList", "https://www.xceptance.com/en/"));</code></pre>
     * <br>
     * <b>Attention:</b> needs to be run in the <code>@BeforeAll</code> method, because if the test runner is already started, the new properties
     * are not applied.
     *
     * @param filename
     *     the name of the property file to create
     * @param properties
     *     a map containing all the properties and values that should be written
     */
    public static void addTempProperty(String filename, Map<String, String> properties)
    {
        // define and set the properties
        for (String key : properties.keySet())
        {
            Neodymium.configuration().setProperty(key, properties.get(key));
        }

        String propertiesString = properties.entrySet().stream()
                                            .map(entry -> entry.getKey() + "=" + entry.getValue())
                                            .collect(Collectors.joining(System.lineSeparator()));

        // create a temp properties file with the value
        String fileLocation = "config/" + filename;
        File tempConfigFile = new File("./" + fileLocation);

        try
        {
            FileUtils.writeStringToFile(tempConfigFile, propertiesString, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        ConfigFactory.setProperty(Neodymium.TEMPORARY_CONFIG_FILE_PROPERTY_NAME, "file:" + fileLocation);
    }

    /**
     * Helper method to delete the temp properties file with the given name in the default config folder <code>./config/</code>. Example usage in the
     * {@link showcase.neodymium.tests.urlvalidation.UrlValidationIncludeTest} to not block all sites except the included:
     * <pre><code>
     *     &#64AfterAll
     *     public static void cleanUp()
     *     {
     *         deleteTempPropertiesFile(TEMP_PROPERTIES_FILE);
     *     }</pre></code>
     *
     * @param filename
     *     the name of the temp properties file to delete
     */
    public static void deleteTempPropertiesFile(String filename)
    {
        Path fileToDeletePath = Paths.get("./config/" + filename);
        try
        {
            Files.delete(fileToDeletePath);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
