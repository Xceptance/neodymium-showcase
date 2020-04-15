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
        ApplitoolsApi.setupGroupingOfTestsByName("Category Test");
    }

    @Before
    public void openEyes()
    {
        ApplitoolsApi.openEyes(name.getMethodName());
    }

    @After
    public void endAssertions()
    {
        ApplitoolsApi.endAssertions();
    }

    @Test
    public void testCategoryServices()
    {
        Selenide.open("https://www.xceptance.com/en/services/");

        ApplitoolsApi.assertPage("Services Category Page");
    }

    @Test
    public void testCategoryCompany()
    {
        Selenide.open("https://www.xceptance.com/en/company/");

        ApplitoolsApi.assertPage("Company Category Page");
    }
}
