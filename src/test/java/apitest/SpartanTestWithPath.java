package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class SpartanTestWithPath {

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("spartanApi.uri");
    }

    @Test
    public void testSpartanWithPath() {

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 10).
                when().get("/spartans/{id}");

        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");
        System.out.println("Id: " + response.body().path("id").toString());
        System.out.println(("Name: " + response.body().path("name").toString()));
        System.out.println("Gender: " + response.path("gender").toString());
        System.out.println("Phone: " + response.path("phone").toString());

        int id = response.body().path("id");
        String name = response.body().path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        assertEquals(id, 10);
        assertEquals(name, "Lorenza");
        assertEquals(gender, "Female");
        assertEquals(phone, 3312820936L);
    }

    @Test
    public void getAllSpartanWithPath() {

        Response response = get("/spartans/");

        assertEquals(response.statusCode(), 200);

        int firstId = response.path("id[0]");
        System.out.println("First id: " + firstId);

        String firstName = response.path("name[0]");
        System.out.println("First name: " + firstName);

        String lastFirstName = response.path("name[-1]");//get last index of the list
        System.out.println("Last name: " + lastFirstName);

        List<String> names = response.path("name");
        System.out.println(names);
        System.out.println("names.size() = " + names.size());

        List<Object> allPhoneNumbers = response.path("phone");
        for (Object nums : allPhoneNumbers)
            System.out.println(nums);

    }

}
