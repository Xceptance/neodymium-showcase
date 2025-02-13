package showcase.neodymium.tests.restapi.dataobjects;

import org.junit.jupiter.api.Assertions;

public class Category
{
    Integer id;
    
    String name;
    
    String image;
    
    String creationAt;
    
    String updatedAt;
    
    public Category(String name, String image)
    {
        this.name = name;
        this.image = image;
    }

    public Category()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getCreationAt()
    {
        return creationAt;
    }

    public void setCreationAt(String creationAt)
    {
        this.creationAt = creationAt;
    }

    public String getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString()
    {
        return "Category [id=" + id + ", name=" + name + ", image=" + image + ", creationAt=" + creationAt + ", updatedAt=" + updatedAt + "]";
    }

    public void validate(Category dataObject)
    {
        Assertions.assertEquals(dataObject.getName(), this.getName(), "category name validation failed");
        Assertions.assertEquals(dataObject.getImage(), this.getImage(), "category image validation failed");
    }
}