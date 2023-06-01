package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {

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

    // 21) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    // 22)Extract the total
    @Test
    public void test002() {
       int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    // 23) Extract the name of 5th product
    @Test
    public void test003() {
        String produstName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : " + produstName);
        System.out.println("------------------End of Test---------------------------");
    }

    //24) Extract the names of all the products
    @Test
    public void test004() {

        List<String> listOfProducts =  response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all the products are : " + listOfProducts);
        System.out.println("------------------End of Test---------------------------");

    }

    // 25) Extract the productId of all the products
    @Test
    public void test005() {
        List<Integer> listOfProductIds =  response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  productId of all the products: + listOfProductIds");
        System.out.println("------------------End of Test---------------------------");
    }

    ///26. Print the size of the data list
    @Test
    public void test006() {
        List<?> listOfDataSize  = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list : " + listOfDataSize);
        System.out.println("------------------End of Test---------------------------");
    }

    //  //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String,?>>values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for product name 'Energizer - MAX Batteries AA (4-Pack)' are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<HashMap<String,?>> modelName = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.price");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  product name 'Energizer - N Cell E90 Batteries (2-Pack)' is : " +modelName);
        System.out.println("------------------End of Test---------------------------");
    }

    //29. Get all the categories of 8th product
    @Test
    public void test009() {
        List<?> listOfCategories = response.extract().path("data[8].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The categories of 8th product : " + listOfCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 30) Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<String> categories = response.extract().path("data.findAll{it.id==150115}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store where product id = 150115 : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 31. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<?> listOfDescription = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  descriptions of all the products are: " + listOfDescription);
        System.out.println("------------------End of Test---------------------------");
    }

     //32) Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<HashMap<String, ?>> listsOfId = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all Id of all  categories of all the products : " + listsOfId);
        System.out.println("------------------End of Test---------------------------");
    }
    //33. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<HashMap<String, ?>> type = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The the product names Where type = HardGood : " + type);
        System.out.println("------------------End of Test---------------------------");
    }
    //34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014() {
        List<HashMap<String,?>> totalNo =response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack) " + totalNo.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List<HashMap<String, ?>> price = response.extract().path("data.findAll{it.price<5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all products whose price < 5.49 " + price);
        System.out.println("------------------End of Test---------------------------");
    }
    //  36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016() {
        List<HashMap<String, ?>>name = response.extract().path("data.findAll{it.name=='Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack) " + name);
        System.out.println("------------------End of Test---------------------------");
    }
    // 37. Find the manufacturer of all the products
    @Test
    public void test017() {
        List<HashMap<String, ?>>manufacturerName = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The name of all manufacturer of all the products  " + manufacturerName);
        System.out.println("------------------End of Test---------------------------");
    }
    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List<HashMap<String, ?>>image = response.extract().path("data.findAll{it.manufacturer=='Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The image of products whose manufacturer is = Energizer " + image);
        System.out.println("------------------End of Test---------------------------");
    }
    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List<HashMap<String, ?>>price = response.extract().path("data.findAll{it.price>5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  The createdAt for all categories products whose price > 5.99 " + price);
        System.out.println("------------------End of Test---------------------------");
    }
    // 40. Find the uri of all the product
    @Test
    public void test020() {
        List<HashMap<String, ?>> urlName = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" The name of all manufacturer of all the products  " + urlName);
        System.out.println("------------------End of Test---------------------------");
    }



}



