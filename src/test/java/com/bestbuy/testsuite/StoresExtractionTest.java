package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
        response = given()
                .when()
                .get()
                .then().statusCode(200);
    }
    //1. Extract the limit
    @Test
    public void test001(){
        int limit = response.extract().path("limit");
        System.out.println("Extract the limit "+limit);
    }
    //2. Extract the total
    @Test
    public void test002(){
        int total = response.extract().path("limit");
        System.out.println("Extract the total "+total);
    }
    //3. Extract the name of 5th store
    @Test
    public void test003(){
        String name = response.extract().path("data[4].name");
        System.out.println("Extract the name of 5th store "+name);
    }
    //4. Extract the names of all the store
    @Test
    public void test004(){
        System.out.println("Extract the name of all store "+ response.extract().path("data.name"));
    }
    //5. Extract the storeId of all the store
    @Test
    public void test005(){
        System.out.println("Extract the id of all store "+ response.extract().path("data.id"));
    }
    //6. Print the size of the data list
    @Test
    public void test006(){
        List<String> dataList = response.extract().path("data");
        System.out.println("Extract the size of data list "+ dataList.size());
    }
    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007(){
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("Get all the value of the store where store name = St Cloud "+values);
    }
    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008(){
        List<String> address=response.extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("Get the address of the store where store name = Rochester" + address);
    }
    //9. Get all the services of 8th store
    @Test
    public void test009(){
        List<String> services=response.extract().path("data[7].services.name");
        System.out.println("Get all the services of 8th store" + services);
    }
    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test0010(){
        List<HashMap<String ,?>> services=response.extract().path("data.services*.find{it.name=='Windows Store'}.storeservices");
        System.out.println("Get storeservices of the store where service name = Windows Store" + services);
    }
    //11. Get all the storeId of all the store
    @Test
    public void test0011(){
        List<Integer>storeId   = response.extract().path("data.services.storeservices.storeId");
        System.out.println("Get all the storeId of all the store "+storeId);
    }
    //12. Get id of all the store
    @Test
    public void test0012(){
        List<Integer>id   = response.extract().path("data.id");
        System.out.println("Get id of all the store "+id);
    }
    //13. Find the store names Where state = ND
    @Test
    public void test0013(){
        List<HashMap<String,?>>storeName  = response.extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("Find the store names Where state = ND "+storeName);
    }
    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test0014(){
        int noOfServices  = response.extract().path("data.find{it.name=='Rochester'}.services.size");
        System.out.println("Find the Total number of services for the store where store name = Rochester "+noOfServices);
    }
    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test0015(){
        List<HashMap<String ,?>> services=response.extract().path("data.services*.find{it.name=='Windows Store'}.storeservices.createdAt");
        System.out.println("Get the createdAt for all services whose name = Windows Store" + services);
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test0016(){
        List<HashMap<String,?>>nameOfServices  = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        System.out.println("Find the name of all services Where store name = “Fargo” "+nameOfServices);
    }
    //17. Find the zip of all the store
    @Test
    public void test0017(){
        List<HashMap<String,?>>zipCode  = response.extract().path("data.zip");
        System.out.println("Find the zip of all the store "+zipCode);
    }
    //18. Find the zip of store name = Roseville
    @Test
    public void test0018(){
        List<HashMap<String,?>>zipCodeName  = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("Find the zip of store name = Roseville "+zipCodeName);
    }
    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test0019(){
        List<HashMap<String,?>>storeService  = response.extract().path("data.services*.findAll{it.name=='Magnolia Home Theater'}.storeservices");
        System.out.println("Find the storeservices details of the service name = Magnolia Home Theater "+storeService);
    }
    //20. Find the lat of all the stores
    @Test
    public void test0020(){
        List<?> latName  = response.extract().path("data.lat");
        System.out.println("Find the lat of all the stores "+latName);
    }
}
