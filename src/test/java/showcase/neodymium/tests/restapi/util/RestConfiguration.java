package showcase.neodymium.tests.restapi.util;

import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;

@LoadPolicy(LoadType.MERGE)
@Sources(
{
    "file:config/dev-api.properties",
    "system:properties",
    "system:env",
    "file:config/api.properties"
})
public interface RestConfiguration extends Mutable
{
    @Key("logging.request")
    @DefaultValue("false")
    public boolean enableRequestLogging();

    @Key("logging.response")
    @DefaultValue("false")
    public boolean enableResponseLogging();
    
    @Key("routes.users.url")
    public String usersUrl();

    @Key("routes.user.url")
    public String userUrl();

    @Key("routes.products.url")
    public String productsUrl();

    @Key("routes.product.url")
    public String productUrl();

    @Key("routes.categories.url")
    public String categoriesUrl();

    @Key("routes.category.url")
    public String categoryUrl();
    
    @Key("routes.uploadFile.url")
    public String uploadFile();
    
    @Key("routes.login.url")
    public String loginUrl();
    
    @Key("routes.refresh.url")
    public String refreshUrl();
    
    @Key("routes.access.url")
    public String accessUrl();
    
    @Key("email.domain")
    @DefaultValue("varmail.net")
    public String emailDomain();
    
    @Key("email.prefix")
    @DefaultValue("test")
    public String emailPrefix();
    
    @Key("email.amountRandomChars")
    @DefaultValue("12")
    public int emailAmountRandomChars();
}