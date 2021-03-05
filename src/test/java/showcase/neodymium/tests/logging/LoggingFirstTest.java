package showcase.neodymium.tests.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import io.qameta.allure.Description;

public class LoggingFirstTest
{
    private static Logger logger = LogManager.getLogger(LoggingFirstTest.class);

    // log4j-api and log4j-core is included in the neodymium-library
    // it is not necessary to add an entry in the pom
    // ConfigurationFactory will look for log4j2-test.properties in the classpath
    // then it will look for log4j2.properties on the classpath
    // the same goes for yaml, json and xml files
    // as soon as the search was successful, log4j2 will stop and load the configuration file
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
