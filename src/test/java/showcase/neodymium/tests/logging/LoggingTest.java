package showcase.neodymium.tests.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.Test;

import io.qameta.allure.Description;

public class LoggingTest
{
    private static Logger logger = LogManager.getLogger(LoggingTest.class);
    // log4j-core is included in the neodymium-library
    // it is not necessary to add an entry in the pom
    // ConfigurationFactory will look for log4j2-test.properties in the classpath
    // then it will look for log4j2.properties on the classpath
    // the same goes for yaml, json and xml files
    // as soon as the search was successful, log4j will stop and load the configuration file

    @Test
    @Description("Showcase for logging with log4j.")
    public void testLogging()
    {
        // change the rootLogger.level in the log4j2.properties file and run the test
        logger.fatal("Current rootLogger.level is  " + logger.getLevel());

        logger.trace("Trace");
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");
        logger.fatal("Fatal");

        // set root logger level
        Configurator.setRootLevel(Level.DEBUG);

        logger.fatal("Current rootLogger.level is  " + logger.getLevel());

        logger.trace("Trace");
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");
        logger.fatal("Fatal");
    }
}
