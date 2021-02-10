package showcase.neodymium.tests.datautils;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;

import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;
import com.xceptance.neodymium.module.statement.testdata.DataSet;
import com.xceptance.neodymium.util.AllureAddons;
import com.xceptance.neodymium.util.Neodymium;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.Tag;
import showcase.neodymium.tests.AbstractTest;

/**
 * This is a show case to explain how to deal with test data using the Neodymium built in features.<br>
 * The example focuses on the very basic direct test data retrieval.<br>
 * In this case the test data is taken out of the CSV file, although a matching JSON file exists. The CSV file is taken in preference of XML and JSON.<br>
 * <b>REQUIRED CONFIGURATION</b>
 * <i>src/test/ressources/showcase/neodymium/tests/datautils/DirectTestDataWithNeodymiumDataValue.csv</i>:
 * <ul>
 * <li>by default Neodymium uses the basic filename of the class at the location based on the package structure if @DataFile is not annotated to the class</li>
 * </ul>
 * <i>config/dev-neodymium.properties</i>:
 * <ul>
 * <li>neodymium.webDriver.chrome.pathToDriverServer = <local path to chrome webdriver executable> # webdriver configuration</li>
 * <li>neodymium.debugUtils.highlight = true # suggestion</li>
 * </ul>
 * <b>RUN</b>
 * To run this test case and see the report try this from within a command line
 * $ mvn -Dtest=DirectTestDataWithNeodymiumDataValue clean test allure:serve
 */
@Tag("direct test data access")
public class DirectTestDataWithNeodymiumDataValue extends AbstractTest {

  @Test
  @Description(value = "Get test data using Neodymium.dataValue method")
  // these data sets are defined in DirectTestDataWithNeodymiumDataValue.csv only
  @DataSet(id = "en/CSV test data access using Neodymium.dataValue method")
  @DataSet(id = "de/CSV test data access using Neodymium.dataValue method")
  public void test() {
    // constant Xpath part that is reused in the selectors
    String siteMainXpath = "//div[@id='main']";

    // open home page in the DataSet language
    AllureAddons.step("Open Home Page", () -> {
      // use Neodymium.dataValue to get the "lang" value out of the actual data set
      Selenide.open("https://www.xceptance.com/" + Neodymium.dataValue("lang") + "/");
    });

    AllureAddons.step("Check Main Teaser and Comment", () -> {
      // use Neodymium.dataValue to get the other test data values out of the actual data set
      $(By.xpath(siteMainXpath + "//h1")).should(matchText(Neodymium.dataValue("teaserMessage")));
      $(By.xpath(siteMainXpath + "//h1/following-sibling::p")).should(
          matchText(Neodymium.dataValue("teaserComment")));
    });
  }

}
