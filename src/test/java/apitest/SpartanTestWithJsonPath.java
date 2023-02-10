package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithJsonPath {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("spartanApi.uri");
    }

    @Test
    public void verifyOneSpartanWithJsonPath() {

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 11).
                when().get("/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.headers().hasHeaderWithName("Transfer-Encoding"));

        assertTrue(response.body().asString().contains("Nona"));
        assertTrue(response.body().asString().contains("7959094216"));

        int id = response.path("id");
        String name = response.path("name");
        assertEquals(id, 11);
        assertEquals(name, "Nona");


        JsonPath json = response.jsonPath();

        int idJson = json.getInt("id");
        String firstName = json.getString("name");
        String genderJson = json.getString("gender");
        long phoneNum = json.getLong("phone");

        System.out.println("idJson = " + idJson);
        System.out.println("firstName = " + firstName);
        System.out.println("genderJson = " + genderJson);
        System.out.println("phoneNum = " + phoneNum);
    }
}
