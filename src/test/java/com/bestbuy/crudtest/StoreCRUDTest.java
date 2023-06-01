package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBaseStore;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StoreCRUDTest extends TestBaseStore {
    static String name="Big Ben Store";
    static String type="Corner Shop";
    static String address="12538 Maven Drive ";
    static String address2="Wavertree";
    static String city="Bristol";
    static String state="NG";
    static String zip="56009";
    static double lat=64.7525;
    static  double lng=97.945;
    static String hours="Mon: 9-10; Tue: 9-10; Wed: 9-10; Thurs: 9-10; Fri: 9-10; Sat: 11-6; Sun: 12-5";
    static int id;
    @Test
    public void test001(){
        Map<String,Object> services = new HashMap<>();
        services.put("name","Easy and Fast Service");
        services.put("id","01");
        StorePojo storePojo=new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);
        storePojo.setServices(services);

        ValidatableResponse response= given()
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(storePojo)
                .post()
                .then().log().body().statusCode(201);
        id=response.extract().path("id");
    }
    @Test
    public void test002(){
        int sId=given()
                .pathParams("id",id)
                .when()
                .get("/{id}")
                .then().statusCode(200)
                .extract()
                .path("id");
        Assert.assertEquals(sId,id);
    }
    @Test
    public void test003(){
        StorePojo storePojo=new StorePojo();
        storePojo.setName("CityPrime Store");
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
        Response response= given()
                .header("Content-Type", "application/json")
                .pathParams("id", id)
                .when()
                .body(storePojo)
                .patch("/{id}");
        response.then().log().body().statusCode(200);
    }
    @Test
    public void test004(){
        given()
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(200);

        given()
                .pathParam("id", id)
                .when()
                .get("/{id}")
                .then().statusCode(404);
    }


}
