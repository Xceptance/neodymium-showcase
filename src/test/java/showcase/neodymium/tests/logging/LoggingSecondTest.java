package showcase.neodymium.tests.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import io.qameta.allure.Description;

public class LoggingSecondTest
{
    private static Logger logger = LogManager.getLogger(LoggingSecondTest.class);

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
