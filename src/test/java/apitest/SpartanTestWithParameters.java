package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;


public class SpartanTestWithParameters {
    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("spartanApi.uri");
    }

    @Test
    public void testHelloSparta() {

        Response response = given().get("/hello");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "text/plain;charset=UTF-8");
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals(response.body().asString(), "Hello from Sparta");
        assertTrue(response.body().asString().contains("Hello from Sparta"));
        assertEquals(response.header("Content-Length"), "17");
        System.out.println((response.headers().asList()));
    }

    @Test
    public void positiveTestIdParameter() {

        Response response = given().accept(ContentType.JSON + ";charset=UTF-8").
                and().pathParam("id", 5).
                when().get("/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Blythe"));

        System.out.println((response.headers().asList()));
    }

    @Test
    public void negativeTestIdPathParam() { //PathParam

        Response response = given().accept(ContentType.JSON + ";charset=UTF-8").
                and().pathParam("id", 500).
                when().get("/spartans/{id}");
        assertEquals(response.statusCode(), 404);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Not Found"));

        System.out.println((response.headers().asList()));
    }

    @Test
    public void PositiveQueryParamTest() {

        Response response = given().accept(ContentType.JSON + ";charset=UTF-8").
                when().get("/spartans/search?gender=Female&nameContains=e");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
        System.out.println((response.headers().asList()));
    }

    @Test
    public void PositiveQueryParamTest2() { //Param

        Response response = given().accept(ContentType.JSON + ";charset=UTF-8").
                and().queryParam("gender", "Female").
                      queryParam("nameContains", "e").
                when().get("/spartans/search");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }
    @Test
    public void PositiveQueryParamsTest() {  //Params

        Response response = given().accept(ContentType.JSON + ";charset=UTF-8").
                and().queryParams("gender", "Female","nameContains", "e").
                when().get("/spartans/search");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }
    @Test
    public void PositiveQueryParamsTestWithMap() {  // Map
        Map<String,Object> paramsMap=new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","e");


        Response response = given().accept(ContentType.JSON + ";charset=UTF-8").
                and().queryParams(paramsMap).
                when().get("/spartans/search");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }

}
