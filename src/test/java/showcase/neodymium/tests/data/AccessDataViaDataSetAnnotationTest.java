package showcase.neodymium.tests.data;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.module.statement.testdata.DataSet;
import com.xceptance.neodymium.module.statement.testdata.SuppressDataSets;
import com.xceptance.neodymium.util.DataUtils;

import io.qameta.allure.Description;
import showcase.neodymium.tests.AbstractTest;

/**
 * This is a show case to explain how to deal with test data using the Neodymium built in features.<br>
 * The example shows how to select test data with @DataSet annotation
 */
public class AccessDataViaDataSetAnnotationTest extends AbstractTest
{
    // disables data set support for the whole class
    @SuppressDataSets

    @Test
    @Description(value = "Get test data using @DataSet annotation")
    // this method will run only once and without any data set because of the SuppressDataSets annotation on the class
    public void noDataSets()
    {
        // open home page
        Selenide.open("https://www.xceptance.com/en");

        // check headline
        $(".landing-intro>h1").should(matchText("Committed to Software Quality"));
    }

    @Test
    // overrides the class level @SuppressDataSets to run with the first data set
    @DataSet(1)
    public void onlyFirstDataSet()
    {
        // open home page in the right language
        Selenide.open("https://www.xceptance.com/" + DataUtils.asString("lang"));

        // // check headline and description
        $(".landing-intro>h1").should(matchText(DataUtils.asString("headline")));
        $(".landing-intro>p").should(matchText(DataUtils.asString("description")));
    }

    @Test
    // overrides the class level @SuppressDataSets to run with all data sets
    @DataSet
    public void allDataSets()
    {
        // open home page in the right language
        Selenide.open("https://www.xceptance.com/" + DataUtils.asString("lang"));

        // check headline and description
        $(".landing-intro>h1").should(matchText(DataUtils.asString("headline")));
        $(".landing-intro>p").should(matchText(DataUtils.asString("description")));
    }
}