package showcase.neodymium.tests.data;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.module.statement.testdata.DataSet;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * This show case explains how to access test data using the features built into the Neodymium framework.<br>
 * By default, Neodymium looks for a data file that has the same name as the test case and is located in the same
 * package in the resources folder.<br>
 * This example shows the reading from a CSV file, although a matching JSON file exists.<br>
 * The CSV file is taken in preference to XML and JSON.<br>
 * The use of the method Neodymium.dataValue() is shown here.
 */
@Tag("direct test data access")
public class DataFilePriotityTest extends AbstractTest
{
    @Test
    @Description(value = "Get test data using Neodymium.dataValue method")
    // these data sets are defined in DataFilePriotityTest.csv not in DataFilePriotityTest.json
    @DataSet(id = "en/CSV test data access using Neodymium.dataValue method")
    @DataSet(id = "de/CSV test data access using Neodymium.dataValue method")
    public void test()
    {
        // open home page in the DataSet language
        // use Neodymium.dataValue to get the "lang" value from the actual data set
        Selenide.open("https://www.xceptance.com/" + Neodymium.dataValue("lang"));

        // use Neodymium.dataValue to get the other test data values from the actual data set
        $(".landing-intro>h1").should(matchText(Neodymium.dataValue("teaserMessage")));
        $(".landing-intro>p").should(matchText(Neodymium.dataValue("teaserComment")));
    }
}
