package showcase.neodymium.tests.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import io.qameta.allure.Description;

public class LoggingSecondTest
{
    private static Logger logger = LogManager.getLogger(LoggingSecondTest.class);

    // log4j-core is included in the neodymium-library
    // it is not necessary to add an entry in the pom
    // ConfigurationFactory will look for log4j2-test.properties in the classpath
    // then it will look for log4j2.properties on the classpath
    // the same goes for yaml, json and xml files
    // as soon as the search was successful, log4j will stop and load the configuration file
    @Test
    @Description("Showcase for logging with log4j.")
    public void testSecondLogging()
    {
        // change the Logger.level in the log4j2.properties file and run the test
        logger.fatal("Currently rootLogger.level is " + logger.getLevel());

        logger.trace("LoggingSecondTest - Trace");
        logger.debug("LoggingSecondTest - Debug");
        logger.info("LoggingSecondTest - Info");
        logger.warn("LoggingSecondTest - Warn");
        logger.error("LoggingSecondTest - Error");
        logger.fatal("LoggingSecondTest - Fatal");
    }
}
