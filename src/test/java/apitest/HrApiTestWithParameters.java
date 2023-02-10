package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HrApiTestWithParameters {


    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("hrApi.uri");
    }

    @Test
    public void testHrApiParam() {

        Response response = given().accept(ContentType.JSON ).
                and().queryParam("q", "{\"region_id\":2}").
                when().get("/countries");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("United States of America"));

    }
    @Test
    public void testHrApiParam2() {

        Response response = given().accept(ContentType.JSON ).
                and().queryParam("q", "{\"job_id\":\"IT_PROG\"}").
                when().get("/employees");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("IT_PROG"));
        assertFalse(response.body().asString().contains("FI_ACCOUNT"));

    }
}
