package showcase.neodymium.tests.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.xceptance.neodymium.module.statement.browser.multibrowser.SuppressBrowsers;

import io.qameta.allure.Description;
import showcase.neodymium.tests.AbstractTest;

/**
 * Neodymium provides extended logging. The log4j-api and log4j-core are included in the Neodymium library.<br>
 * Therefore it is not necessary to add an entry in the pom.<br>
 * ConfigurationFactory class will look for <i>log4j2-test.properties</i> in the classpath.<br>
 * Then it will look for log4j2.properties on the classpath. The same goes for yaml, json and xml files.<br>
 * As soon as the search was successful, log4j2 will stop and load the configuration file.
 */
@SuppressBrowsers
public class LoggingFirstTest extends AbstractTest
{
    private static Logger logger = LogManager.getLogger(LoggingFirstTest.class);

    @Test
    @Description("Showcase for logging with log4j2.")
    public void testFirstLogging()
    {
        // change the Logger.level in the log4j2.properties file and run the test
        logger.trace("LoggingFirstTest - Trace");
        logger.debug("LoggingFirstTest - Debug");
        logger.info("LoggingFirstTest - Info");
        logger.warn("LoggingFirstTest - Warn");
        logger.error("LoggingFirstTest - Error");
        logger.fatal("LoggingFirstTest - Fatal");
    }
}
