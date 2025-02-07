package showcase.neodymium.tests.data;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.DataUtils;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.neodymium.tests.data.dataobjects.HomePageTestData;
import showcase.neodymium.tests.data.dataobjects.ServiceTile;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * This is a showcase to explain how to deal with test data using the Neodymium built-in features.<br> The example focuses on test data retrieval based on data
 * objects.<br> In this case the test data are converted from a JSON file into Java POJOs HomePageTestData and ServiceTile.<br> This is an example for working
 * with a more complex test object that contains a list of serviceTiles.
 */
@Tag("data object test data access")
public class AccessDataViaDataObjectsTest extends AbstractTest
{

    // by default all available data sets will be executed, so there is no need to call them
    // explicitly via the @DataSet annotation
    @NeodymiumTest
    @Description(value = "Get test data using Java Pojo")
    public void test()
    {
        // use DataUtils to map the test data into the corresponding data objects HomePageTestData and ServiceTile
        HomePageTestData testDataHomePage = DataUtils.get(HomePageTestData.class);

        // use the testDataHomePage.getLang() method to retrieve the language
        String language = testDataHomePage.getLanguage();
        // open home page in the DataSet language
        Selenide.open("https://www.xceptance.com/" + language);

        // check for message and comment
        $("#introduction h1").should(matchText(testDataHomePage.getTeaserMessage()));
        $("#introduction p").should(matchText(testDataHomePage.getTeaserComment()));

        // check for service tiles on the page
        // the ServiceTile test data are provided within HomePageTestData as a list
        for (ServiceTile serviceTile : testDataHomePage.getServiceTiles())
        {
            // check heading with its position
            $$("a h2").get(serviceTile.getPosition()).should(matchText(serviceTile.getHeading()));

            // check explanation text with its position
            $$(".bg-dark .explanation").get(serviceTile.getPosition()).should(matchText(serviceTile.getExplanation()));
        }

        // check the number of services
        $$(".bg-dark .explanation").shouldHave(size(testDataHomePage.getNumberServices()));
    }
}
