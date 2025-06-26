package showcase.neodymium.tests.restapi.endpoints;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import showcase.neodymium.tests.restapi.dataobjects.Category;
import showcase.neodymium.tests.restapi.util.RestHelperContext;

public class CategoryEndPoints
{
    @Step("create category: '{category}'")
    public static Category createCategory(Category category)
    {     
        Response response = given()
            .filter(new AllureRestAssured())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(category)
                                   .when()
            .post(RestHelperContext.configuration().categoriesUrl());

        // validate response status code
        Assertions.assertEquals(201, response.getStatusCode(), "Unexprected response code, failed to create category with name: " + category.getName()
                                                               + " response was: " + response.getBody().asString());

        return response.getBody().as(Category.class);
    }
    
    @Step("update category: '{category}'")
    public static Category updateCategory(Category category)
    {
        Response response = given()
            .filter(new AllureRestAssured())
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("categoryId", category.getId())
            .body(category)
        .when()
            .put(RestHelperContext.configuration().categoryUrl());

        // validate response status code
        Assertions.assertEquals(200, response.getStatusCode(), "failed to update category with name: " + category.getName());

        return response.getBody().as(Category.class);
    }

    @Step("delete category: '{category}'")
    public static void deleteCategory(int categoryId)
    {
        Response response = given()
            .filter(new AllureRestAssured())
            .pathParam("categoryId", categoryId)
        .when()
            .delete(RestHelperContext.configuration().categoryUrl());

        // validate response status code
        Assertions.assertEquals(200, response.getStatusCode(), "failed to delete category with id: " + categoryId);
    }
}