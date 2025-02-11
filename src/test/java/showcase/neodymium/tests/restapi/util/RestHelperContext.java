package showcase.neodymium.tests.restapi.util;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import org.aeonbits.owner.ConfigFactory;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class RestHelperContext
{
    private static final Map<Thread, RestHelperContext> CONTEXTS = Collections.synchronizedMap(new WeakHashMap<>());
    
    // global configuration
    private final RestConfiguration configuration;
    
    /**
     * Constructor
     */
    private RestHelperContext()
    {
        configuration = ConfigFactory.create(RestConfiguration.class, System.getProperties(), System.getenv());
    }
    
    /**
     * Initializes the configuration for the current Thread.
     */
    public static void init()
    {
        // make sure to have a specific test context for each test run
        clearThreadContext();

        RestConfiguration configuration = getContext().configuration;

        if (configuration.enableRequestLogging())
        {
            RestAssured.filters(new RequestLoggingFilter());
        }

        if (configuration.enableResponseLogging())
        {
            RestAssured.filters(new ResponseLoggingFilter());
        }
    }
    
    /**
     * Retrieves the context instance for the current Thread.
     * 
     * @return the context instance for the current Thread
     */
    static RestHelperContext getContext()
    {
        return CONTEXTS.computeIfAbsent(Thread.currentThread(), key -> {
            return new RestHelperContext();
        });
    }
    
    /**
     * Get the current Configuration instance
     * 
     * @return neodymiumConfiguration
     */
    public static RestConfiguration configuration()
    {
        return getContext().configuration;
    }
    
    /**
     * Clears the context instance for the current Thread. <br>
     */
    public static void clearThreadContext()
    {
        CONTEXTS.remove(Thread.currentThread());
    }
}