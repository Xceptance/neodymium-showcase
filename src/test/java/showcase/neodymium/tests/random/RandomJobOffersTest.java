package showcase.neodymium.tests.random;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;

import com.codeborne.selenide.ElementsCollection;
import com.xceptance.neodymium.junit5.NeodymiumTest;
import com.xceptance.neodymium.util.Neodymium;
import com.xceptance.neodymium.util.NeodymiumRandom;

import io.qameta.allure.Description;
import showcase.flows.OpenPageFlow;
import showcase.neodymium.tests.AbstractTest;

/**
 * This show case shows the use of the Neodymium.getRandom() method.<br>
 * Random values are useful to run different test cases with different values.<br>
 * It is also useful to test a web application with different paths like a real user would.<br>
 * The NeodymiumRandom class extends the functionality of java.util.Random.<br>
 * In this case the use of the <i>nextInt()</i> method is shown.<br>
 * To get to know more possibilities take a closer look at the class.<br>
 * <br>
 * <b>REQUIRED CONFIGURATION:</b> <i>config/neodymium.properties</i>
 * <ul>
 * <li>A value for neodymium.context.random.initialValue must NOT be set.</li>
 * </ul>
 * <b>CAUTION:</b> This test case fails if initialValue is set.
 */
public class RandomJobOffersTest extends AbstractTest
{
    @BeforeEach
    public void configurationCheck()
    {
        // by setting the neodymium.context.random.initialValue, the random result can be kept constant
        // check if the initialValue is NOT set in neodymium.properties so that a really random result is chosen
        Assert.assertNull("FixedRandomTest: neodymium.context.random.initialValue is set",
                          Neodymium.configuration().initialRandomValue());
    }

    @NeodymiumTest
    @Description(value = "Showcase for clicking a random element in the list of current job offers.")
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
