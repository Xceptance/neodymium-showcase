package showcase.neodymium.tests.restapi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.xceptance.neodymium.common.browser.SuppressBrowsers;
import com.xceptance.neodymium.common.testdata.DataItem;
import com.xceptance.neodymium.junit5.NeodymiumTest;

import showcase.neodymium.tests.restapi.dataobjects.Category;
import showcase.neodymium.tests.restapi.dataobjects.UpdateCategoryTestData;
import showcase.neodymium.tests.restapi.endpoints.CategoryEndPoints;
import showcase.neodymium.tests.restapi.util.RestHelperContext;

@SuppressBrowsers
public class UpdateCategoryTest
{  
    @DataItem
    private UpdateCategoryTestData updateCategoryTestData;
    
    private Category category;
    
    @BeforeEach
    public void setup()
    {
        RestHelperContext.init();
    }

    @NeodymiumTest
    public void updateNewCategory()
    {      
        // create category
        category = CategoryEndPoints.createCategory(updateCategoryTestData.getCategoryBeforeUpdate());

        // update category information
        Category categoryChanges = category.cloneCategory();
        categoryChanges.setName(updateCategoryTestData.getCategoryUpdate().getName());
        categoryChanges.setImage(updateCategoryTestData.getCategoryUpdate().getImage());
      
        // update category
        Category categoryAfterUpdate = CategoryEndPoints.updateCategory(categoryChanges);
         
        // validate product after update
        categoryAfterUpdate.validate(categoryChanges);
    }
    
    @SuppressBrowsers
    @AfterEach
    public void teardown() 
    {
        // delete category
        CategoryEndPoints.deleteCategory(category.getId());
    }
}