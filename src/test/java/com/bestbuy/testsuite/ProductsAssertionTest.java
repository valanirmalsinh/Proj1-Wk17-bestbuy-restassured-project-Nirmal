package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBaseProducts;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasSize;

public class ProductsAssertionTest extends TestBaseProducts {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI ="http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    // 11. Verify the if the total is equal to 51968
    @Test
    public void test001() {
        response.body("total", equalTo(51968));
    }

    //  12. Verify the if the stores of limit is equal to 10
    @Test
    public void test002() {
        response.body("limit", equalTo(10));
    }

    //13. Check the single ‘Name’ in the Array list (Duracell - AAA Batteries (4-Pack))
    @Test
    public void test003() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    //14. Check the multiple ‘Names’ in the ArrayList (Duracell - AA 1.5V CopperTop Batteries (4-
    //Pack), Duracell - AA Batteries (8-Pack), Energizer - MAX Batteries AA (4-Pack))
    @Test
    public void test004() {
        response.body("data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)","Energizer - MAX Batteries AA (4-Pack)",
                "Duracell - C Batteries (4-Pack)"));
    }

    //15. Verify the productId=150115 inside categories of the third name is “Household Batteries
    @Test
    public void test005() {
        response.body("data[3].categories[2].name", equalTo("Household Batteries"));
    }

    //16. Verify the categories second name = “Housewares” of productID = 333179
    @Test
    public void test006() {
        response.body("data[7].categories[1].name", equalTo("Housewares"));
    }

    //17. Verify the price = 4.99 of forth product
    @Test
    public void test007() {
        response.body("data[3].price", equalTo( 4.99f));
    }

    //18. Verify the Product name = Duracell - C Batteries (4-Pack) of 5th product
    @Test
    public void test008() {
        response.body("data[4].name", equalTo("Duracell - C Batteries (4-Pack)"));
    }

    //19. Verify the ProductId = 333179 for the 9th product
    @Test
    public void test009() {
        response.body("data[7].id", equalTo( 312290));
    }

    //20. Verify the productId = 346575 have 5 categories
    @Test
    public void test010() {
        response.body("data[9].categories", hasSize(5));
    }
}
