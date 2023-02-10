package Day8;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class JsonSchemaValidation {
    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("spartanApi.uri");
    }

    @Test
    public void jsonSchemeValidatingForSpartan() {

        given().accept(ContentType.JSON).
                and().pathParam("id", 15).
                when().get("/spartans/{id}").then().
                statusCode(200).body(matchesJsonSchemaInClasspath("SingleSpartanSchema.json"));

    }
}