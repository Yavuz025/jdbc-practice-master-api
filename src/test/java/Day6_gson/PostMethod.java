package Day6_gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostMethod {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("spartanApi.uri");
    }

    @Test
    public void PostNewSpartan() {

        Response response = given().accept(ContentType.JSON).and().contentType(ContentType.JSON).
                and().body("{\n" +
                        "    \"name\": \"Nike\",\n" +
                        "    \"gender\": \"Male\",\n" +
                        "    \"phone\": 1234567800\n" +
                        "}").when().post("/spartans");
        assertEquals(response.statusCode(), 201);
        assertEquals(response.contentType(), "application/json");


        String successMessage = response.path("success");

        System.out.println("successMessage = " + successMessage);
        assertEquals(successMessage, "A Spartan is Born!");

        String name = response.path("data.name");
        String gender = response.path("data.gender");
        int phone = response.path("data.phone");

        assertEquals(name, "Nike");
        assertEquals(gender, "Male");
        assertEquals(phone, 1234567800);

        System.out.println(response.path("data.id").toString());
    }
    @Test
    public void PostNewSpartanWithMap(){
        //we will only change the way of sending body part
        Map<String,Object> requestMap = new HashMap<>();
        //adding the values that we want to post
        requestMap.put("gender","Male");
        requestMap.put("name","MikeEUMAP");
        requestMap.put("phone",5478783575L);

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON)
                .and().body(requestMap).when().post("/spartans");

        //response validations
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify response body
        String successMessage = response.path("success");
        System.out.println("successMessage = " + successMessage);
        assertEquals(successMessage,"A Spartan is Born!");

        String name = response.path("data.name");
        String gender = response.path("data.gender");
        long phone = response.path("data.phone");

        assertEquals(name,"MikeEUMAP");
        assertEquals(gender,"Male");
        assertEquals(phone,5478783575l);
        //printing the id
        System.out.println(response.path("data.id").toString());
        int idFromPost = response.path("data.id");

        System.out.println("-----------END OF POST REQUEST--------------");
        //after post I want to send get request to new spartan
        given().pathParam("id",idFromPost)
                .when().get("/spartans/{id}")
                .then().assertThat().statusCode(200).log().all();



    }
    @Test
    public void PostNewSpartanWithPOJO(){
        //get name and gender from uinames
        //put some phone number then send request

        //we will only change the way of sending body part
        Spartan spartan = new Spartan();
        spartan.setGender("Female");
        spartan.setName("Jasminen");
        spartan.setPhone((int) 4321232123L);

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON)
                .and().body(spartan).when().post("/spartans");

        //response validations
        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        //verify response body
        String successMessage = response.path("success");
        System.out.println("successMessage = " + successMessage);
        assertEquals(successMessage,"A Spartan is Born!");

        String name = response.path("data.name");
        String gender = response.path("data.gender");
        long phone = response.path("data.phone");

        assertEquals(name,"Jasmine");
        assertEquals(gender,"Female");
        assertEquals(phone,4321232123L);
        //printing the id
        System.out.println(response.path("data.id").toString());
        int idFromPost = response.path("data.id");

        System.out.println("-----------END OF POST REQUEST--------------");
        //after post I want to send get request to new spartan
        given().pathParam("id",idFromPost)
                .when().get("/spartans/{id}")
                .then().assertThat().statusCode(200).log().all();

        //break until 1:15

    }
}
