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

    // System.out.println(someObject) calls the Object.toString() method.
    // It returns a hash code representation of the object e.g.: ServiceTile@34acbc60.
    // To display the variables contained in the object, we recommend to override the toString () method.
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
