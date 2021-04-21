package showcase.neodymium.tests.random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.xceptance.neodymium.module.statement.browser.multibrowser.SuppressBrowsers;
import com.xceptance.neodymium.util.NeodymiumRandom;

import io.qameta.allure.Description;
import showcase.neodymium.tests.AbstractTest;
import showcase.neodymium.tests.logging.LoggingFirstTest;

/**
 * The NeodymiumRandom class extends the functionality of java.util.Random.<br>
 * In this case the use of <i>nextBoolean(trueCaseProbability)</i> is shown.<br>
 * With trueCaseProbability you can determine how often true or false is returned.<br>
 * Please note that the result can only be an approximation of the specified percentage.<br>
 * To get to know more possibilities take a closer look at the class.<br>
 */
@SuppressBrowsers
public class RandomProbabilityTest extends AbstractTest
{
    private static final Logger logger = LogManager.getLogger(LoggingFirstTest.class);

    @Test
    @Description(value = "Showcase for usage of NeodymiumRandom#nextBoolean(int trueCaseProbability).")
    public void testRandomProbability()
    {
        // Part 1: create a random boolean with probability 0
        // this means that only false is returned
        int trueCount = 0;
        int falseCount = 0;
        logger.error("Part 1: Probability = 0  -  100% false will be returned");
        for (int i = 0; i < 100; i++)
        {
            final boolean randomBoolean = NeodymiumRandom.nextBoolean(0);
            if (randomBoolean)
                trueCount++;
            if (!randomBoolean)
                falseCount++;
        }
        logger.error("Count of true:  " + trueCount + "    Count of false:  " + falseCount);

        // Part 2: create a random boolean with probability 50
        // this means that true and false should be returned approximately equally
        trueCount = 0;
        falseCount = 0;
        logger.error("Part 2: Probability = 50  -  50% true and false will be returned");
        for (int i = 0; i < 1000; i++)
        {
            final boolean randomBoolean = NeodymiumRandom.nextBoolean(50);
            if (randomBoolean)
                trueCount++;
            if (!randomBoolean)
                falseCount++;
        }
        logger.error("Count of true:  " + trueCount + "    Count of false:  " + falseCount);

        // Part 3: create a random boolean with probability 80
        // this means the ratio of true and false should be approximately about 80 to 20 in favor of true
        trueCount = 0;
        falseCount = 0;
        logger.error("Part 3: Probability = 80  -  80% true and 20% false will be returned");
        for (int i = 0; i < 1000; i++)
        {
            final boolean randomBoolean = NeodymiumRandom.nextBoolean(80);
            if (randomBoolean)
                trueCount++;
            if (!randomBoolean)
                falseCount++;
        }
        logger.error("Count of true:  " + trueCount + "    Count of false:  " + falseCount);

        // Part 4: create a random boolean with probability 100
        // this means that only true is returned
        trueCount = 0;
        falseCount = 0;
        logger.error("Part 4: Probability = 100  -  100% true will be returned");
        for (int i = 0; i < 100; i++)
        {
            final boolean randomBoolean = NeodymiumRandom.nextBoolean(100);
            if (randomBoolean)
                trueCount++;
            if (!randomBoolean)
                falseCount++;
        }
        logger.error("Count of true:  " + trueCount + "    Count of false:  " + falseCount);
    }
}
