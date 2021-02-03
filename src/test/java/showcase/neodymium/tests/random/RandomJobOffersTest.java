package showcase.neodymium.tests.random;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.Assert;
import org.junit.Test;

import com.codeborne.selenide.ElementsCollection;
import com.xceptance.neodymium.util.NeodymiumRandom;

import io.qameta.allure.Description;
import showcase.flows.OpenPageFlow;

public class RandomJobOffersTest
{
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

        // create a random number between 1 and job offers count in the list
        final int randomNumber = NeodymiumRandom.nextInt(jobOffersCount) + 1;

        // remember the selected headline
        final String headline = jobOffersList.get(randomNumber).getText();

        // click randomNumber'th link in the job offers list
        jobOffersList.get(randomNumber).click();

        // check if job offer detail page contains same headline
        Assert.assertEquals("The headline is not the same", headline,
                            $$(".job-listing-col.job-col-header>h2").first().getText());
    }
}
