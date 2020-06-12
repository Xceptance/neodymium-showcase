package showcase.neodymium.tests.xcmailr;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.aeonbits.owner.ConfigFactory;
import org.junit.After;

import com.google.common.base.Joiner;

public class AbstractXcMailrTest
{
    // Map for all properties that need to be changed
    protected static Map<String, String> properties = new HashMap<>();

    // set filelocation where temporary config is saved
    protected static final String fileLocation = "config/temp-xcmailr.properties";

    // temporary config file
    protected static File tempConfigFile2 = new File("./" + fileLocation);

    @After
    public void deleteTempFile()
    {
        // delete temporary config file
        deleteTempFile(tempConfigFile2);
    }

    /**
     * delete a temporary test file
     */
    private static void deleteTempFile(File tempFile)
    {
        // check if file still exists
        if (tempFile.exists())
        {
            try
            {
                // delete file
                Files.delete(tempFile.toPath());
            }
            catch (final Exception e)
            {
                // double apostrophe needed, otherwise MessageFormat.format() won't work
                System.out.println(MessageFormat.format(
                                                        "Coundn''t delete temporary file: ''{0}'' caused by {1}",
                                                        tempFile.getAbsoluteFile(), e));
            }
        }
    }

    protected static void writeProperty()
    {
        // write properties in temporary config file and save it
        writeMapToPropertiesFile(properties, tempConfigFile2);

        // set temporary config file for neodymium
        ConfigFactory.setProperty("xcmailr.temporaryConfigFile", "file:" + fileLocation);
    }

    public static void writeMapToPropertiesFile(Map<String, String> map, File file)
    {
        try
        {
            // Generate a joint String from the map
            final String join = Joiner.on("\r\n").withKeyValueSeparator("=").join(map);

            // Create Outputstream to file
            final FileOutputStream outputStream = new FileOutputStream(file);

            // Write propertesmap to file
            outputStream.write(join.getBytes());

            // close stream
            outputStream.close();
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
