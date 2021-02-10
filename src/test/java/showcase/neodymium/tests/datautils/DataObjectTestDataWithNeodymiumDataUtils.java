package showcase.neodymium.tests.datautils;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.Iterator;

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
import showcase.neodymium.tests.datautils.dataobjects.Site;
import showcase.neodymium.tests.datautils.dataobjects.Thumbnail;

/**
 * This is a show case to explain how to deal with test data using the Neodymium built in features.<br>
 * The example focuses on test data retrieval based on data objects.<br>
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
 * $ mvn -Dtest=DataObjectTestDataWithNeodymiumDataUtils clean test allure:serve
 */
@Tag("data object test data access")
// force Neodymium to use a certain named data file below src/test/ressources/
@DataFile("showcase/neodymium/tests/datautils/TestDataWithNeodymium.json")
public class DataObjectTestDataWithNeodymiumDataUtils extends AbstractTest {

  @Test
  @Description(value = "Get test data using Neodymium.dataValue method")
  @DataSet(id = "en/JSON test data access using Neodymium DataUtils method")
  @DataSet(id = "de/JSON test data access using Neodymium DataUtils method")
  public void test() {
    // constant Xpath parts that is reused in the selectors
    String siteMainXpath = "//div[@id='main']";
    String thumbnailXPath = siteMainXpath + "//div[@class='thumbnail']";

    // use Neodymium DataUtils to map the running data set test data into the corresponding data objects
    // this maps the Site test data (dataobjects.Site.class) and a list of thumbnails (Thumbnail.class)
    Site siteData = DataUtils.get(Site.class);

    // open home page in the DataSet language
    AllureAddons.step("Open Home Page", () -> {
      // use the data object populated by Neodymium DataUtils
      Selenide.open("https://www.xceptance.com/" + siteData.getLang() + "/");
    });

    /**
     * check for fix items on the page using test data out of the data sets
     */

    AllureAddons.step("Check Main Teaser and Comment", () -> {
      // use the data object populated by Neodymium DataUtils
      $(By.xpath(siteMainXpath + "//h1")).should(matchText(siteData.getTeaserMessage()));
      $(By.xpath(siteMainXpath + "//h1/following-sibling::p")).should(
          matchText(siteData.getTeaserComment()));
    });

    AllureAddons.step("Check Services", () -> {
      // check for a data driven number of thumbnails on the page using data set test data
      // the thumbnail test data is provided within data objects populated by Neodymium DataUtils
      Iterator<Thumbnail> dataDrivenThumbnails = siteData.getThumbnails().iterator();
      int i = 0;
      while (dataDrivenThumbnails.hasNext()) {
        Thumbnail thumbnail = dataDrivenThumbnails.next();
        // check heading with its position
        $$(By.xpath(thumbnailXPath + "//h2")).get(i).should(matchesText(thumbnail.getHeading()));

        // check explanation test with its position
        $$(By.xpath(thumbnailXPath + "//p")).get(i).should(matchesText(thumbnail.getExplanation()));
        
        // next thumbnail is expected at next position
        i++;
      }
      
      // check the number of services using an integer value provided by Neodymium DataUtils
      $$(By.xpath(thumbnailXPath)).shouldHaveSize(siteData.getNumberServices());
    });

  }

}
