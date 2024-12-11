package showcase.neodymium.tests.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Assert;

import com.xceptance.neodymium.common.browser.SuppressBrowsers;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import showcase.neodymium.tests.AbstractTest;

/**
 * This showcase shows the use of the Neodymium.getRandom() method.<br>
 * Random values are useful to run different test cases with different values.<br>
 * In some cases it is necessary to be able to repeat a test with the same random value (e.g. debugging).<br>
 * Here you can see how a fixed random value can be used.<br>
 * <br>
 * <b>REQUIRED CONFIGURATION:</b> <i>config/neodymium.properties</i>
 * <ul>
 * <li>A value for neodymium.context.random.initialValue must be set to 123456789.</li>
 * </ul>
 * <b>CAUTION:</b> This test case fails if initialValue is NOT set.
 */
@SuppressBrowsers
public class FixedRandomTest extends AbstractTest
{
    @NeodymiumTest
    public void configurationCheck()
    {
        // by setting the neodymium.context.random.initialValue, the random result can be kept constant
        // check if the initialValue is enabled in neodymium.properties
        Assert.assertEquals("FixedRandomTest: neodymium.context.random.initialValue is not set",
                            Long.valueOf("123456789"), Neodymium.configuration().initialRandomValue());
    }

    @NeodymiumTest
    @Description(value = "Showcase for usage of Neodymium#getRandom.")
    public void testFixedRandom()
    {
        Random random = Neodymium.getRandom();

        ArrayList<String> listOfNames = new ArrayList<String>();
        listOfNames.add("Mia");
        listOfNames.add("Emilia");
        listOfNames.add("Hannah");
        listOfNames.add("Emma");
        listOfNames.add("Sophia");
        listOfNames.add("Noah");
        listOfNames.add("Ben");
        listOfNames.add("Matteo");
        listOfNames.add("Finn");
        listOfNames.add("Leon");

        // shuffles the order in the array
        Collections.shuffle(listOfNames, random);

        Assert.assertEquals("Leon", listOfNames.get(0));
        Assert.assertEquals("Hannah", listOfNames.get(1));
        Assert.assertEquals("Emilia", listOfNames.get(2));
        Assert.assertEquals("Ben", listOfNames.get(3));
        Assert.assertEquals("Sophia", listOfNames.get(4));
        Assert.assertEquals("Finn", listOfNames.get(5));
        Assert.assertEquals("Matteo", listOfNames.get(6));
        Assert.assertEquals("Emma", listOfNames.get(7));
        Assert.assertEquals("Mia", listOfNames.get(8));
        Assert.assertEquals("Noah", listOfNames.get(9));

        // shuffles the order in the array again
        Collections.shuffle(listOfNames, random);

        Assert.assertEquals("Emilia", listOfNames.get(0));
        Assert.assertEquals("Leon", listOfNames.get(1));
        Assert.assertEquals("Noah", listOfNames.get(2));
        Assert.assertEquals("Hannah", listOfNames.get(3));
        Assert.assertEquals("Finn", listOfNames.get(4));
        Assert.assertEquals("Matteo", listOfNames.get(5));
        Assert.assertEquals("Sophia", listOfNames.get(6));
        Assert.assertEquals("Ben", listOfNames.get(7));
        Assert.assertEquals("Mia", listOfNames.get(8));
        Assert.assertEquals("Emma", listOfNames.get(9));
    }
}
