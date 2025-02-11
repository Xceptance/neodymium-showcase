package showcase.neodymium.tests.restapi;

import org.junit.jupiter.api.AfterEach;

import com.xceptance.neodymium.common.browser.SuppressBrowsers;
import com.xceptance.neodymium.common.testdata.DataItem;
import com.xceptance.neodymium.junit5.NeodymiumTest;

import showcase.neodymium.tests.restapi.dataobjects.Category;
import showcase.neodymium.tests.restapi.dataobjects.UpdateCategoryTestData;
import showcase.neodymium.tests.restapi.endpoints.CategoryEndPoints;

@SuppressBrowsers
public class UpdateCategoryTest extends AbstractRestapiTest
{  
    @DataItem
    private UpdateCategoryTestData updateCategoryTestData;
    
    private Category categoryAfter;
    
    @NeodymiumTest
    public void updateNewCategory()
    {      
        // create category
        Category categoryBefore = CategoryEndPoints.createCategory(updateCategoryTestData.getCategoryBeforeUpdate());

        // update category information
        Category categoryChanges = categoryBefore;
        categoryChanges.setName(updateCategoryTestData.getCategoryUpdate().getName());
        categoryChanges.setImage(updateCategoryTestData.getCategoryUpdate().getImage());
      
        // update category
        categoryAfter = CategoryEndPoints.updateCategory(categoryChanges);
         
        // validate product after update
        categoryAfter.validate(categoryChanges);
    }
    
    @AfterEach
    public void teardown() 
    {
        // delete category
        CategoryEndPoints.deleteCategory(categoryAfter.getId());
    }
}