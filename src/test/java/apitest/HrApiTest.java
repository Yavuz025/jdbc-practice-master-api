package apitest;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HrApiTest {

    String hrUrl="http://54.144.70.64:1000/ords/hr";

    @Test
    public void getAllRegionsTest() {

        Response response = given().get(hrUrl+"/regions");

        System.out.println(response.statusCode());
        System.out.println(response.contentType());
        System.out.println(response.body().prettyPrint());

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Americas"));
        assertTrue(response.asString().contains("Europe"));

    }
    @Test
    public void getAllRegionsTest2() {

        when().get(hrUrl+"/regions");
                given().accept(ContentType.JSON)
                .then().statusCode(200)
                .and().contentType("application/json");

    }

}
