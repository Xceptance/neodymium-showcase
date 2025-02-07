package showcase.neodymium.tests.data;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.DataUtils;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * This is a showcase to explain how to deal with test data using the Neodymium built-in features.<br> The example focuses on the very basic direct test data
 * retrieval.<br> In this case the test data are taken from the XML file.<br> The method DataUtils.asString() is used
 */
@Tag("direct test data access")
public class AccessDataViaDataUtilsTest extends AbstractTest
{
    @NeodymiumTest
    @Description(value = "Get test data using DataUtils methods")
    public void test()
    {
        // use DataUtils to get the values of the current data set
        String language = DataUtils.asString("language");
        // open home page in the DataSet language
        Selenide.open("https://www.xceptance.com/" + language);

        // check message and comment
        $("#introduction h1").should(matchText(DataUtils.asString("teaserMessage")));
        $("#introduction p").should(matchText(DataUtils.asString("teaserComment")));

        // check service tiles on the page
        int i = 0;
        while (DataUtils.exists("serviceTile_" + i + "_heading"))
        {
            // check heading text
            $$("a h2").get(i).should(matchText(DataUtils.asString("serviceTile_" + i + "_heading")));

            // check explanation text
            $$(".bg-dark .explanation").get(i).should(matchText(DataUtils.asString("serviceTile_" + i + "_explanation")));

            // next serviceTile
            i++;
        }

        // check the number of services using an integer value provided by DataUtils
        $$(".bg-dark .explanation").shouldHave(size(DataUtils.asInt("numberServices")));
    }
}
