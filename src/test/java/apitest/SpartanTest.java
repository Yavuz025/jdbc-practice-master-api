package apitest;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static org.testng.Assert.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SpartanTest {

    String spartanAllUrl = "http://54.144.70.64:8000/api/spartans";
    String spartanUrl = "http://54.144.70.64:8000/api";



    @Test
    public void viewAllSpartans() {
        Response response = RestAssured.get(spartanAllUrl);

        System.out.println(response.statusCode());
        assertEquals(response.statusCode(), 200);

        System.out.println(response.body().asString());
        System.out.println(response.body().prettyPrint());

    }

    @Test
    public void viewSpartanTest1() {
        Response response = RestAssured.get(spartanAllUrl);

        assertEquals(response.statusCode(), 200);

        assertTrue(response.body().asString().contains("Orion"));

    }

    @Test
    public void viewSpartanAcceptTypeJsonTest() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanAllUrl);



        assertTrue(response.body().asString().contains("Orion"));

    }

    @Test
    public void viewSpartanAcceptTypeXmlTest() {
        Response response = given().accept(ContentType.XML).
                get(spartanAllUrl);


        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/xml");
        assertTrue(response.body().asString().contains("Orion"));


    }

    @Test
    public void viewSpartanAcceptTypeXmlTest1() {
        given().accept(ContentType.XML)
                .when().get(spartanAllUrl)
                .then().statusCode(200)
                .and().contentType("application/xml");
        //.and().body("Orion");


    }
    @Test
    public void viewSpartan3() {
        Response response = given().accept(ContentType.JSON).
                get(spartanAllUrl+"/3");


        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Fidole"));


    }
    @Test
    public void viewSpartanAcceptTypeJsonSpartan3() {
        given().accept(ContentType.JSON)
                .when().get(spartanAllUrl+"/3")
                .then().statusCode(200)
                .and().contentType("application/json")
                //.and().body()
        ;
    }
    @Test
    public void negativeContentTypeSpartan3() {
        Response response = given().accept(ContentType.XML).
                            get(spartanAllUrl+"/3");

        System.out.println(response.statusCode());
        assertEquals(response.statusCode(), 406);
    }


}