package showcase.neodymium.tests.data.dataobjects;

import java.util.List;

/**
 * POJO class to map data set test data The class specification has to match the test data structure to perform the
 * mapping.
 */
public class HomePageTestData
{
    private String language;

    private String teaserMessage;

    private String teaserComment;

    private Integer numberServices;

    // example how to map a nested list of test data (e.g. JSON array)
    private List<ServiceTile> serviceTiles;

    public String getLanguage()
    {
        return language;
    }

    public String getTeaserMessage()
    {
        return teaserMessage;
    }

    public String getTeaserComment()
    {
        return teaserComment;
    }

    public int getNumberServices()
    {
        return numberServices;
    }

    public List<ServiceTile> getServiceTiles()
    {
        return serviceTiles;
    }

    // when printing an object the Object.toString() method will be called
    // by default this returns the hash code representation of the object e.g.: HomePageTestData@12d1f1d4
    // we recommend to override the toString() method to give more details about the object
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("HomePageTestData [language=" + language + ", ");
        builder.append("teaser message=" + teaserMessage + ", ");
        builder.append("teaser comment=" + teaserComment + ", ");
        builder.append("number of services=" + numberServices + ", ");
        builder.append("service tiles" + serviceTiles + "]");
        return builder.toString();
    }
}
