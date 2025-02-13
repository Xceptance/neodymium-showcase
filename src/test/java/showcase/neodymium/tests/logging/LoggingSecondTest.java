package showcase.neodymium.tests.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.xceptance.neodymium.common.browser.SuppressBrowsers;
import com.xceptance.neodymium.junit5.NeodymiumTest;

import io.qameta.allure.Description;
import showcase.neodymium.tests.AbstractTest;

/**
 * This second logging test is added to show that a separate log file can be written for each test case.
 */
@SuppressBrowsers
public class LoggingSecondTest extends AbstractTest
{
    private static final Logger logger = LogManager.getLogger(LoggingSecondTest.class);

    @NeodymiumTest
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
