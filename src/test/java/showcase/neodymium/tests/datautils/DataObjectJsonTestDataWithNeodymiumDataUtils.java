package showcase.neodymium.tests.datautils;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.module.statement.testdata.DataFile;
import com.xceptance.neodymium.module.statement.testdata.DataSet;
import com.xceptance.neodymium.util.AllureAddons;
import com.xceptance.neodymium.util.DataUtils;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;
import showcase.neodymium.tests.datautils.dataobjects.Thumbnail;

/**
 * This is a show case to explain how to deal with test data using the Neodymium built in features.<br>
 * The example focuses on test data retrieval based on data objects accessed with help of a JsonPath.<br>
 * In this case the test data is taken out of the JSON file as annotated to the class with @DataFile.<br>
 * <b>REQUIRED CONFIGURATION</b>
 * <i>src/test/ressources/showcase/neodymium/tests/datautils/TestDataWithNeodymium.json</i>:
 * <ul>
 * <li>Neodymium uses the annotated test data file, that contains a nested array.</li>
 * </ul>
 * <i>config/dev-neodymium.properties</i>:
 * <ul>
 * <li>neodymium.webDriver.chrome.pathToDriverServer = <local path to chrome webdriver executable> # webdriver configuration</li>
 * <li>neodymium.debugUtils.highlight = true # suggestion</li>
 * </ul>
 * <b>RUN</b>
 * To run this test case and see the report try this from within a command line
 * $ mvn -Dtest=DataObjectJsonTestDataWithNeodymiumDataUtils clean test allure:serve
 */
@Tag("data object test data access")
// force Neodymium to use a certain named data file below src/test/ressources/
@DataFile("showcase/neodymium/tests/datautils/TestDataWithNeodymium.json")
public class DataObjectJsonTestDataWithNeodymiumDataUtils extends AbstractTest {

  @Test
  @Description(value = "Get test data using Neodymium.dataValue method")
  @DataSet(id = "en/JSON test data access using Neodymium DataUtils method")
  @DataSet(id = "de/JSON test data access using Neodymium DataUtils method")
  public void test() {
    // constant Xpath parts that is reused in the selectors
    String siteMainXpath = "//div[@id='main']";
    String thumbnailXPath = siteMainXpath + "//div[@class='thumbnail']";

    // open home page in the DataSet language
    AllureAddons.step("Open Home Page", () -> {
      // use the JsonPath with Neodymium DataUtils
      Selenide.open("https://www.xceptance.com/" + DataUtils.get("$.lang", String.class) + "/");
    });

    /**
     * check for fix items on the page using test data out of the data sets
     */

    AllureAddons.step("Check Main Teaser and Comment", () -> {
      // use the JsonPath with Neodymium DataUtils
      $(By.xpath(siteMainXpath + "//h1")).should(matchText(DataUtils.get("$.teaserMessage", String.class)));
      $(By.xpath(siteMainXpath + "//h1/following-sibling::p")).should(matchText(DataUtils.get("$.teaserComment", String.class)));
    });

    AllureAddons.step("Check Services", () -> {
      // check for a data driven number of thumbnails on the page using data set test data
      // the thumbnail test data is provided within a List of Thumbnail data objects that are
      // populated by Neodymium DataUtils pointing to that test data with JsonPath
      @SuppressWarnings("unchecked")
      List<Thumbnail> dataDrivenThumbnails = DataUtils.get("$.thumbnails[*]", List.class);
      
      for (int i=0; i < dataDrivenThumbnails.size(); i++) {
        /**
         *  this would be one access only, but is however not possible due to a ClassCastException
         *       Thumbnail thumbnail = dataDrivenThumbnails.get(i);
         */
         
        // for the sake of this show case follow-up and get each object separately
        Thumbnail thumbnail = DataUtils.get("$.thumbnails[" + i + "]", Thumbnail.class);
 
        // check heading with its position
        $$(By.xpath(thumbnailXPath + "//h2")).get(i).should(matchesText(thumbnail.getHeading()));

        // check explanation test with its position
        $$(By.xpath(thumbnailXPath + "//p")).get(i).should(matchesText(thumbnail.getExplanation()));
      }
      
      // get integer test data using Neodymium DataUtils via JsonPath
      int numberServices = DataUtils.get("$.numberServices", Integer.class);
      // check the number of services
      $$(By.xpath(thumbnailXPath)).shouldHaveSize(numberServices);
    });

  }

}
