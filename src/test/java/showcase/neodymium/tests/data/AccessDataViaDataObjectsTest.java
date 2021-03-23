package showcase.neodymium.tests.data;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.Iterator;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.DataUtils;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.neodymium.tests.data.dataobjects.HomePageTestData;
import showcase.neodymium.tests.data.dataobjects.ServiceTile;

/**
 * This is a show case to explain how to deal with test data using the Neodymium built-in features.<br>
 * The example focuses on test data retrieval based on data objects.<br>
 * In this case the test data are converted from a JSON file into Java POJOs HomePageTestData and ServiceTile.<br>
 * This is an example of working with a more complex test object that contains a list of serviceTiles.
 */
@Tag("data object test data access")
public class AccessDataViaDataObjectsTest extends AbstractTest
{

    @Test
    @Description(value = "Get test data using Java Pojo")
    // by default all available data sets will be executed, so there is no need to call them explicitly via the @dataset
    // annotation
    public void test()
    {
        // use DataUtils to map the test data into the corresponding data objects HomePageTestData and ServiceTile
        HomePageTestData testDataHomePage = DataUtils.get(HomePageTestData.class);

        // open home page in the DataSet language
        // use the testDataHomePage.getLang() method
        Selenide.open("https://www.xceptance.com/" + testDataHomePage.getLang());

        // check for message and comment
        $(".landing-intro>h1").should(matchText(testDataHomePage.getTeaserMessage()));
        $(".landing-intro>p").should(matchText(testDataHomePage.getTeaserComment()));

        // check for serviceTiles on the page
        // the ServiceTile test data are provided within HomePageTestData as a list
        Iterator<ServiceTile> testDataServiceTiles = testDataHomePage.getServiceTiles().iterator();
        int i = 0;
        while (testDataServiceTiles.hasNext())
        {
            ServiceTile serviceTile = testDataServiceTiles.next();

            // check heading with its position
            $$(".caption .icon>h2").get(i).should(matchesText(serviceTile.getHeading()));

            // check explanation test with its position
            $$(".caption > p").get(i).should(matchesText(serviceTile.getExplanation()));

            // next serviceTile is expected at next position
            i++;
        }

        // check the number of services
        $$(".caption").shouldHaveSize(testDataHomePage.getNumberServices());
    }
}
