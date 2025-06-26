package showcase.neodymium.tests.restapi.dataobjects;

import org.junit.jupiter.api.Assertions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category
{
    Integer id;
    
    String name;
    
    String image;
    
    String creationAt;
    
    String updatedAt;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String slug;
    
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


    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    public Category cloneCategory()
    {
        Category category = new Category();
        category.creationAt = this.creationAt;
        category.id = this.id;
        category.image = this.image;
        category.name = this.name;
        category.slug = this.slug;
        category.updatedAt = this.updatedAt;
        return category;
    }

    @Override
    public String toString()
    {
        return "Category [id=" + id + ", name=" + name + ", image=" + image + ", creationAt=" + creationAt + ", updatedAt=" + updatedAt + ", slug=" + slug
               + "]";
    }

    public void validate(Category dataObject)
    {
        Assertions.assertEquals(dataObject.getName(), this.getName(), "category name validation failed");
        Assertions.assertEquals(dataObject.getImage(), this.getImage(), "category image validation failed");
    }
}