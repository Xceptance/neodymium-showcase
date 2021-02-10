package showcase.neodymium.tests.datautils;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.module.statement.testdata.DataSet;
import com.xceptance.neodymium.util.AllureAddons;
import com.xceptance.neodymium.util.DataUtils;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * This is a show case to explain how to deal with test data using the Neodymium built in features.<br>
 * The example focuses on the very basic direct test data retrieval.<br>
 * In this case the test data is taken out of the XML file.<br>
 * <b>REQUIRED CONFIGURATION</b>
 * <i>src/test/ressources/showcase/neodymium/tests/datautils/DirectTestDataWithNeodymiumDataUtils.xml</i>:
 * <ul>
 * <li>Neodymium uses the test data file, because the of the matching base filename.</li>
 * </ul>
 * <i>config/dev-neodymium.properties</i>:
 * <ul>
 * <li>neodymium.webDriver.chrome.pathToDriverServer = <local path to chrome webdriver executable> # webdriver configuration</li>
 * <li>neodymium.debugUtils.highlight = true # suggestion</li>
 * </ul>
 * <b>RUN</b>
 * To run this test case and see the report try this from within a command line
 * $ mvn -Dtest=DirectTestDataWithNeodymiumDataUtils clean test allure:serve
 */
@Tag("direct test data access")
public class DirectTestDataWithNeodymiumDataUtils extends AbstractTest {

  @Test
  @Description(value = "Get test data using Neodymium DataUtils")
  @DataSet(id = "en/XML test data access using Neodymium DataUtils method")
  @DataSet(id = "de/XML test data access using Neodymium DataUtils method")
  public void test() {
  
    // constant Xpath part that is reused in the selectors
    String siteMainXpath = "//div[@id='main']";
    String thumbnailXPath = siteMainXpath + "//div[@class='thumbnail']";

    // open home page in the DataSet language
    AllureAddons.step("Open Home Page", () -> {
      // use Neodymium DataUtils to get the "lang" value of the running data set
      Selenide.open("https://www.xceptance.com/" + DataUtils.asString("lang") + "/");
    });

    /**
     * check for fix items on the page using test data out of the data sets
     */

    AllureAddons.step("Check Main Teaser and Comment", () -> {
      // use Neodymium DataUtils to get data set test data
      $(By.xpath(siteMainXpath + "//h1")).should(matchText(DataUtils.asString("teaserMessage")));
      $(By.xpath(siteMainXpath + "//h1/following-sibling::p")).should(matchText(DataUtils.asString("teaserComment")));
    });

    AllureAddons.step("Check Services", () -> {
      // check for a data driven number of thumbnails on the page using data set test data
      int i = 1;
      while (DataUtils.exists("thumbnail_" + i + "_heading")) {
        // check heading with its position
        $$(By.xpath(thumbnailXPath + "//h2")).get(i-1).should(matchesText(DataUtils.asString("thumbnail_" + i + "_heading")));

        // check explanation test with its position 
        // assuming that the test data is complete for the thumbnail explanation
        $$(By.xpath(thumbnailXPath + "//p")).get(i-1).should(matchesText(DataUtils.asString("thumbnail_" + i + "_explanation")));

        // next thumbnail and position
        i++;
      }
      
      // check the number of services using an integer value provided by Neodymium DataUtils
      $$(By.xpath(thumbnailXPath)).shouldHaveSize(DataUtils.asInt("numberServices"));
    });

  }

}
