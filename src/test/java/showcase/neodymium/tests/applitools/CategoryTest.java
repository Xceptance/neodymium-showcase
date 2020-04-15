package showcase.neodymium.tests.applitools;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import com.codeborne.selenide.Selenide;

import showcase.neodymium.tests.AbstractTest;
import util.applitools.ApplitoolsApi;

public class CategoryTest extends AbstractTest
{
    @Rule
    public TestName name = new TestName();

    @BeforeClass
    public static void setupBatch()
    {
        // in BeforeClass method always call ApplitoolsApi.setupGlobal(),
        // ApplitoolsApi.setupGroupingOfTestsByName(batchName) or
        // ApplitoolsApi.setupBasic()

        // call this method if you want tests in this test class to be grouped together in separate batch
        // if you want any other test classes to be included to this batch, please parallelize your test in threads
        // (using <parallel> and <threadCount>), not in JVM (using maven forks)
        ApplitoolsApi.setupGroupingOfTestsByName("Category Test");
    }

    @Before
    public void openEyes()
    {
        // before to make any visual assertions, call ApplitoolsApi.openEyes(testname) method
        // please mind, that the parameter 'testname' influences definition of baseline for visual compare
        // so if you dont't want to call this method individually for each test, a good option would be to pass method
        // name, retrieved by JUnit
        ApplitoolsApi.openEyes(name.getMethodName());
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
    public void testCategoryServices()
    {
        Selenide.open("https://www.xceptance.com/en/services/");

        // use this method to compare current page outlook with baseline
        // the parameter 'pageDescription' only defines how screenshot will be named in test manager and doesn't
        // influence baseline choice
        ApplitoolsApi.assertPage("Company Category Page");
    }

    @Test
    public void testCategoryCompany()
    {
        Selenide.open("https://www.xceptance.com/en/company/");

        ApplitoolsApi.assertPage("Company Category Page");
    }
}
