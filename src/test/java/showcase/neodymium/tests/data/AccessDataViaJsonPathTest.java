package showcase.neodymium.tests.data;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.common.testdata.DataFile;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.DataUtils;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.neodymium.tests.data.dataobjects.ServiceTile;

/**
 * This showcase explains how to access test data using the features built into the Neodymium framework.<br>
 * By default, Neodymium looks for a data file that has the same name as the test case and is located in the same
 * package in the resources folder.<br>
 * This example shows the reading from a JSON file that does not meet the naming conventions.<br>
 * Therefore it is specified using the @DataFile annotation.<br>
 * The method DataUtils.get() uses the JsonPath here.
 */
@Tag("data object test data access")
// tell Neodymium to use a different named data file under src/test/resources/ instead the standard one
@DataFile("showcase/neodymium/tests/data/TestDataFileWithDifferentName.json")
public class AccessDataViaJsonPathTest extends AbstractTest
{

    @NeodymiumTest
    @Description(value = "Get test data using DataUtils.get method with jsonPath")
    public void test()
    {

        // use the JsonPath with DataUtils.get() to retrieve the language
        String language = DataUtils.get("$.language", String.class);
        // open home page in the DataSet language
        Selenide.open("https://www.xceptance.com/" + language);

        // check for message and comment
        $(".landing-intro>h1").should(matchText(DataUtils.get("$.teaserMessage", String.class)));
        $(".landing-intro>p").should(matchText(DataUtils.get("$.teaserComment", String.class)));

        // select a service tile
        ServiceTile serviceTile = DataUtils.get("$.serviceTiles[2]", ServiceTile.class);

        // check the heading with the right text
        $$(".caption .icon>h2").get(2).should(matchText(serviceTile.getHeading()));

        // check explanation with the right text
        $$(".caption > p").get(2).should(matchText(serviceTile.getExplanation()));

        // get integer test data using DataUtils.get() via JsonPath
        int numberServices = DataUtils.get("$.numberServices", Integer.class);

        // check the number of services
        $$(".caption").shouldHave(size(numberServices));
    }
}
