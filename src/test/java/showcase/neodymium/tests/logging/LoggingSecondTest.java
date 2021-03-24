package showcase.neodymium.tests.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.xceptance.neodymium.module.statement.browser.multibrowser.SuppressBrowsers;

import io.qameta.allure.Description;
import showcase.neodymium.tests.AbstractTest;

@SuppressBrowsers
public class LoggingSecondTest extends AbstractTest
{
    private static Logger logger = LogManager.getLogger(LoggingSecondTest.class);

    // This second test is added to show that a separate log file can be written for each test case.
    @Test
    @Description("Showcase for logging with log4j2.")
    public void testSecondLogging()
    {
        // change the Logger.level in the log4j2.properties file and run the test
        logger.trace("LoggingSecondTest - Trace");
        logger.debug("LoggingSecondTest - Debug");
        logger.info("LoggingSecondTest - Info");
        logger.warn("LoggingSecondTest - Warn");
        logger.error("LoggingSecondTest - Error");
        logger.fatal("LoggingSecondTest - Fatal");
    }
}
