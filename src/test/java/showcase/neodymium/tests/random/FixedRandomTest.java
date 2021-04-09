package showcase.neodymium.tests.random;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.xceptance.neodymium.module.statement.browser.multibrowser.SuppressBrowsers;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import showcase.neodymium.tests.AbstractTest;

@SuppressBrowsers
public class FixedRandomTest extends AbstractTest
{
    @Before
    public void configurationCheck()
    {
        // by setting the neodymium.context.random.initialValue, the random result can be kept constant
        // check if the initialValue is enabled in neodymium.properties
        Assert.assertEquals("FixedRandomTest: neodymium.context.random.initialValue is not set",
                            Long.valueOf("123456789"), Neodymium.configuration().initialRandomValue());
    }

    @Test
    @Description(value = "Showcase for usage of Neodymium#getRandom.")
    public void testRandom()
    {
        var random = Neodymium.getRandom();

        var<String> arrayListOfNames = new ArrayList<String>();
        arrayListOfNames.add("Mia");
        arrayListOfNames.add("Emilia");
        arrayListOfNames.add("Hannah");
        arrayListOfNames.add("Emma");
        arrayListOfNames.add("Sophia");
        arrayListOfNames.add("Noah");
        arrayListOfNames.add("Ben");
        arrayListOfNames.add("Matteo");
        arrayListOfNames.add("Finn");
        arrayListOfNames.add("Leon");

        // shuffles the order in the array
        Collections.shuffle(arrayListOfNames, random);

        Assert.assertEquals("Leon", arrayListOfNames.get(0));
        Assert.assertEquals("Hannah", arrayListOfNames.get(1));
        Assert.assertEquals("Emilia", arrayListOfNames.get(2));
        Assert.assertEquals("Ben", arrayListOfNames.get(3));
        Assert.assertEquals("Sophia", arrayListOfNames.get(4));
        Assert.assertEquals("Finn", arrayListOfNames.get(5));
        Assert.assertEquals("Matteo", arrayListOfNames.get(6));
        Assert.assertEquals("Emma", arrayListOfNames.get(7));
        Assert.assertEquals("Mia", arrayListOfNames.get(8));
        Assert.assertEquals("Noah", arrayListOfNames.get(9));

        // shuffles the order in the array again
        Collections.shuffle(arrayListOfNames, random);

        Assert.assertEquals("Emilia", arrayListOfNames.get(0));
        Assert.assertEquals("Leon", arrayListOfNames.get(1));
        Assert.assertEquals("Noah", arrayListOfNames.get(2));
        Assert.assertEquals("Hannah", arrayListOfNames.get(3));
        Assert.assertEquals("Finn", arrayListOfNames.get(4));
        Assert.assertEquals("Matteo", arrayListOfNames.get(5));
        Assert.assertEquals("Sophia", arrayListOfNames.get(6));
        Assert.assertEquals("Ben", arrayListOfNames.get(7));
        Assert.assertEquals("Mia", arrayListOfNames.get(8));
        Assert.assertEquals("Emma", arrayListOfNames.get(9));
    }
}
