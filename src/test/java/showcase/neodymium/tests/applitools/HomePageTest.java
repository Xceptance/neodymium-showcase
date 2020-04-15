package showcase.neodymium.tests.applitools;

import static com.codeborne.selenide.Selenide.$;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

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
        ApplitoolsApi.setupGlobal();
    }

    @Before
    public void openEyes()
    {
        ApplitoolsApi.openEyes(name.getMethodName());
        ApplitoolsApi.addProperty("purpose", "home page");
    }

    @After
    public void endAssertions()
    {
        ApplitoolsApi.endAssertions();
    }

    @Test
    public void testHomePage()
    {
        Selenide.open("https://www.xceptance.com/en/");

        ApplitoolsApi.assertPage("Home Page");
    }

    @Test
    public void testCloseCookiesOnHomePage()
    {
        Selenide.open("https://www.xceptance.com/en/");

        $(".text-right .btn.btn-primary").click();

        ApplitoolsApi.assertPage("Home Page without cookies");
    }
}
