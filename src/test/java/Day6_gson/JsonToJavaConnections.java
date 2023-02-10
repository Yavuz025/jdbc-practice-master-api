package Day6_gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class JsonToJavaConnections {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("spartanApi.uri");
    }

    @Test
    public void testSpartanJsonToMap() {
        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 15).
                when().get("/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

//        Map<String,Object> spartan=new HashMap<>();
//
//        spartan.put("id",15);
//        spartan.put("name","Meta");
//        spartan.put("gender","female");
//        spartan.put("phone",1938695106);

        Map<String, Object> spartanMap = response.body().as(Map.class);
        System.out.println("spartanMap = " + spartanMap.toString());

        String name = (String) spartanMap.get("name");
        System.out.println("name = " + name);

        assertEquals(spartanMap.get("name"), "Meta");

    }

    @Test
    public void testAllSpartanToList() {
        Response response = given().accept(ContentType.JSON).
                when().get("/spartans/");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        List<Map<String, Object>> spartanListMap = response.body().as(List.class);

        System.out.println("spartanListMap.size() = " + spartanListMap.size());
        System.out.println("spartanListMap.get(0) = " + spartanListMap.get(1));
        System.out.println("spartanListMap.get(0).get(\"name\") = " + spartanListMap.get(1).get("name"));

        assertEquals(spartanListMap.get(1).get("name"), "Nels");

    }

    @Test
    public void regionsJsonMap() {

        Response response = given().get("http://54.144.70.64:1000/ords/hr/regions");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        Map<String,Object>regionMap= response.body().as(Map.class);

        System.out.println("regionMap.get(\"limit\") = " + regionMap.get("limit"));
        System.out.println("regionMap.get(\"count\") = " + regionMap.get("count"));
        System.out.println("regionMap.get() = " + regionMap.get("items"));

        List<Map<String,Object>> itemsList= (List<Map<String, Object>>) regionMap.get("items");

        System.out.println(itemsList.get(0).get("region_name"));


        assertEquals(itemsList.get(0).get("region_name"),"Europe");



    }
}