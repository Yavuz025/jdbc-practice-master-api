package Day7;

import Day6_gson.Spartan;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanPutDelete {
    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("spartanApi.uri");
    }

    @Test
    public void testPutSpartans() {

        Map<String,Object> putMap= new HashMap<>();
        putMap.put("name","somethingnew");
        putMap.put("gender", "Female");
        putMap.put("phone",5478783575L );

        given().pathParam("id", 115).
                and().contentType(ContentType.JSON).
                and().body(putMap).
                when().put("/spartans/{id}").
                then().assertThat().statusCode(204);


    }

    @Test
    public void testDeleteSpartan() {
        Random random = new Random();
        int x= random.nextInt( 113)+2;
        System.out.println(x);
        given().pathParam("id", x).
                when().delete("/spartans/{id}").
                then().assertThat().statusCode(204);

    }
}
