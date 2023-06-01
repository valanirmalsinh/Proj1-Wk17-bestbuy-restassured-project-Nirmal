package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBaseProducts;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBaseProducts {
    static String name= "MacBook" + TestUtils.getRandomValue();
    static String type  = "laptop" + TestUtils.getRandomValue();
    static double price=1889.99;
    static double shipping=7.99;
    static String upc = TestUtils.getRandomValue();
    static String description = "Apple MacBook";
    static String model = "Macbook Air";
    static String manufacturer = "Apple";
    static String url="https://pisces.bbystatic.com/"+TestUtils.getRandomValue()+".jpg;maxHeight=640;maxWidth=550";
    static String image = TestUtils.getRandomValue();
    static int id ;
@Test
    public void test001Post(){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setModel(model);
        productPojo.setManufacturer(manufacturer);
        productPojo.setUrl(url);
        productPojo.setImage(image);
       ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post()
                .then().log().all().statusCode(201);
                id = response.extract().path("id");

    }
    @Test
    public void test002Get() {
        int productId=given()
                .pathParams("id",id)
                .when()
                .get("/{id}")
                .then().statusCode(200)
                .extract()
                .path("id");
        Assert.assertEquals(productId,id);
    }
    @Test
    public void test003Update() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Apple Watch" + TestUtils.getRandomValue());
        productPojo.setType("Watch");
        productPojo.setPrice(419.00);
        productPojo.setShipping(6.79);
        productPojo.setUpc(TestUtils.getRandomValue());
        productPojo.setDescription("iwatch");
        productPojo.setModel("series 8");
        productPojo.setManufacturer("Apple");
        productPojo.setUrl("https://pisces.bbystatic.com/.jpg;maxHeight=640;maxWidth=550");
        productPojo.setImage("https://pisces.bbystatic.com");
        Response response = given()
                .header("Accept", "application/json")
                .pathParams("id", id)
                .when()
                .body(productPojo)
                .put("/{id}");
                response.then().log().body().statusCode(200);
    }
    @Test
    public void test004Delete() {
        //Delete
        given()
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(200);
        //check if above is deleted or not
        given()
                .pathParam("id",id)
                .when()
                .get("/{id}")
                .then().statusCode(404);
    }

}


