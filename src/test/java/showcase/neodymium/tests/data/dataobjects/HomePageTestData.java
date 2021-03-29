package showcase.neodymium.tests.data.dataobjects;

import java.util.List;

/**
 * POJO class to map data set test data The class specification has to match the test data structure to perform the
 * mapping.
 */
public class HomePageTestData
{
    private String lang;

    private String teaserMessage;

    private String teaserComment;

    private Integer numberServices;

    public String getLang()
    {
        return lang;
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

    // example how to map a nested list of test data (e.g. JSON array)
    private List<ServiceTile> serviceTiles;

    public List<ServiceTile> getServiceTiles()
    {
        return serviceTiles;
    }

    // System.out.println(someObject) calls the Object.toString() method.
    // It returns a hash code representation of the object e.g.: HomePageTestData@12d1f1d4.
    // To display the variables contained in the object, we recommend to override the toString () method.
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("HomePageTestData [Language=" + lang + ", ");
        builder.append("TeaserMessage=" + teaserMessage + ", ");
        builder.append("TeaserComment=" + teaserComment + ", ");
        builder.append("Number of Services=" + numberServices + "]");
        return builder.toString();
    }
}
