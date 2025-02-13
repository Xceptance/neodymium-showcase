package showcase.neodymium.tests.restapi.dataobjects;

public class UpdateCategoryTestData
{
    Category categoryBeforeUpdate;
    
    Category categoryUpdate;
    
    public UpdateCategoryTestData(Category categoryBeforeUpdate, Category categoryUpdate)
    {
        this.categoryBeforeUpdate = categoryBeforeUpdate;
        this.categoryUpdate = categoryUpdate;
    }

    public Category getCategoryBeforeUpdate()
    {
        return categoryBeforeUpdate;
    }

    public Category getCategoryUpdate()
    {
        return categoryUpdate;
    }

    @Override
    public String toString()
    {
        return "UpdateCategoryTest [categoryBeforeUpdate=" + categoryBeforeUpdate + ", categoryUpdate=" + categoryUpdate + "]";
    }
}