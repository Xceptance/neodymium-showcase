package showcase.neodymium.tests.random;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.codeborne.selenide.ElementsCollection;
import com.xceptance.neodymium.util.Neodymium;
import com.xceptance.neodymium.util.NeodymiumRandom;

import io.qameta.allure.Description;
import showcase.flows.OpenPageFlow;

public class RandomJobOffersTest
{
    @Before
    public void configurationCheck()
    {
        // by setting the neodymium.context.random.initialValue, the random result can be kept constant
        // check if the initialValue is NOT set in neodymium.properties so that a really random result is chosen
        Assert.assertNull("FixedRandomTest: neodymium.context.random.initialValue is set",
                          Neodymium.configuration().initialRandomValue());
    }

    @Test
    @Description(value = "Showcase for clicking a random element in the list of urrent job offers.")
    public void testRandomJobs()
    {
        // open the demo page and prepare it for the test
        OpenPageFlow.openXceptanceJobOffersPage();

        // check if the heading "Aktuelle Stellenangebote" exists
        $(".col-sm-4.tab-content h2").shouldHave(exactText("Aktuelle Stellenangebote"));

        ElementsCollection jobOffersList = $$(".tab-content>.jobs-plain-list>li>a");

        // count the job offers in the list
        final int jobOffersCount = jobOffersList.size();

        // create a random number between 0 and job offers count in the list
        final int randomNumber = NeodymiumRandom.nextInt(jobOffersCount);

        // remember the selected headline
        final String headline = jobOffersList.get(randomNumber).getText();

        // click randomNumber'th link in the job offers list
        jobOffersList.get(randomNumber).click();

        // check if job offer detail page contains same headline
        $(".job-listing-col.job-col-header>h2").shouldHave(exactText(headline));
    }
}
