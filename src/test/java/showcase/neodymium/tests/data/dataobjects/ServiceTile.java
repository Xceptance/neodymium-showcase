package showcase.neodymium.tests.data.dataobjects;

/**
 * POJO class to map data set test data The class specification has to match the test data structure to perform the
 * mapping.
 */
public class ServiceTile
{
    private String heading;

    private String explanation;

    private Integer position;

    public String getHeading()
    {
        return heading;
    }

    public String getExplanation()
    {
        return explanation;
    }

    public int getPosition()
    {
        return position;
    }

    // when printing an object the Object.toString() method will be called
    // by default this returns the hash code representation of the object e.g.: ServiceTile@34acbc60
    // we recommend to override the toString() method to give more details about the object
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ServiceTile [heading=" + heading + ", ");
        builder.append("Explanation=" + explanation + ", ");
        builder.append("Position=" + position + "]");
        return builder.toString();
    }
}
