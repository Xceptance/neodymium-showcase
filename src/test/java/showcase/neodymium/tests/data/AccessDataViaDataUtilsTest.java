package showcase.neodymium.tests.data;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.Test;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.util.DataUtils;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * This is a show case to explain how to deal with test data using the Neodymium built-in features.<br>
 * The example focuses on the very basic direct test data retrieval.<br>
 * In this case the test data are taken from the XML file.<br>
 * The method DataUtils.asString() is used
 */
@Tag("direct test data access")
public class AccessDataViaDataUtilsTest extends AbstractTest
{
    @Test
    @Description(value = "Get test data using DataUtils methods")
    public void test()
    {
        // use DataUtils to get the values of the current data set
        String language = DataUtils.asString("language");
        // open home page in the DataSet language
        Selenide.open("https://www.xceptance.com/" + language);

        // check message and comment
        $(".landing-intro>h1").should(matchText(DataUtils.asString("teaserMessage")));
        $(".landing-intro>p").should(matchText(DataUtils.asString("teaserComment")));

        // check service tiles on the page
        int i = 0;
        while (DataUtils.exists("serviceTile_" + i + "_heading"))
        {
            // check heading text
            $$(".caption .icon>h2").get(i).should(matchesText(DataUtils.asString("serviceTile_" + i + "_heading")));

            // check explanation text
            $$(".caption > p").get(i).should(matchesText(DataUtils.asString("serviceTile_" + i + "_explanation")));

            // next serviceTile
            i++;
        }

        // check the number of services using an integer value provided by DataUtils
        $$(".caption").shouldHaveSize(DataUtils.asInt("numberServices"));
    }
}
