package showcase.neodymium.tests.applitools;

import static com.codeborne.selenide.Selenide.$;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;

import com.codeborne.selenide.Selenide;

import showcase.neodymium.tests.AbstractTest;
import util.applitools.ApplitoolsApi;

public class HomePageTest extends AbstractTest
{
    @Rule
    public TestName name = new TestName();

    @BeforeClass
    public static void setupBatch()
    {
        // in BeforeClass method always call ApplitoolsApi.setupGlobal(),
        // ApplitoolsApi.setupGroupingOfTestsByName(batchName) or
        // ApplitoolsApi.setupBasic()

        // call this method if you want test from this test class to be grouped to default batch, the name of which you
        // entered in config/applitools.properties under property applitools.batch
        // please mind, in case you run test in parallel using maven forks, global batch will only be global for this
        // test class
        // if you want any other test classes to be included to global batch, please parallelize your test in threads
        // (using <parallel> and <threadCount>)
        ApplitoolsApi.setupGlobal();
    }

    @Before
    public void openEyes()
    {
        // before to make any visual assertions, call ApplitoolsApi.openEyes(testname) method
        // please mind, that the parameter 'testname' influences definition of baseline for visual compare
        // so if you dont't want to call this method individually for each test, a good option would be to pass method
        // name, retrieved by JUnit
        ApplitoolsApi.openEyes(name.getMethodName());

        // if you want to group tests within one batch, use ApplitoolsApi.addProperty(name,value) method. Afterwards you
        // can group tests by this property in test manager
        ApplitoolsApi.addProperty("purpose", "home page");
    }

    @After
    public void endAssertions()
    {
        // call this method to end all visual assertion for current test
        // if you don't call this method, you'll receive exception trying to call ApplitoolsApi.openEyes(testname) for
        // the new test
        ApplitoolsApi.endAssertions();
    }

    @Test
    public void testHomePage()
    {
        Selenide.open("https://www.xceptance.com/en/");

        // if the page you test contains some dynamic content, like the page we test here, there are two options to
        // avoid failure of visual compare
        // first is to ignore the region with dynamic content in test manager
        // or you can set match level to LAYOUT2 to make visual compare concentrate on page layout and not on the
        // content of the page
        ApplitoolsApi.setMatchLevel("LAYOUT2");

        // use this method to compare current page outlook with baseline
        // the parameter 'pageDescription' only defines how screenshot will be named in test manager and doesn't
        // influence baseline choice
        ApplitoolsApi.assertPage("Home Page");

        // use this method to compare current outlook of specific element with baseline
        // the parameter 'imageDescription' only defines how screenshot will be named in test manager and doesn't
        // influence baseline choice
        ApplitoolsApi.assertElement(By.cssSelector("#navigation"), "top navigation menu");
    }

    @Ignore
    @Test
    public void testCloseCookiesOnHomePage()
    {
        Selenide.open("https://www.xceptance.com/en/");

        // close cookies dialog
        $(".text-right .btn.btn-primary").click();

        ApplitoolsApi.assertPage("Home Page without cookies");

        // use this method to compare current outlook of specific elements collection with baseline
        // the parameter 'description' only defines how screenshots will be named in test manager and doesn't
        // influence baseline choice
        ApplitoolsApi.assertElements(By.cssSelector("#navigation li"), "top navigation menu elements");

    }
}
