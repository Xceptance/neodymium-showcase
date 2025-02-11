package showcase.neodymium.tests.restapi;

import org.junit.jupiter.api.BeforeEach;

import showcase.neodymium.tests.restapi.util.TestContext;

public abstract class AbstractRestapiTest
{
    @BeforeEach
    public void abstractSetup()
    {
        TestContext.init();
    }
}
