package apitest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class CbTrainingTestWithJson {
    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("CbApi.uri");
    }

    @Test
    public void testGetStudentWithJsonPath() {

        Response response = given().pathParam("id",32801).
                            when().get("/student/{id}");

        assertEquals(response.statusCode(),200);


        JsonPath jsonData=response.jsonPath();

        String firstName = jsonData.getString("students.firstName[0]");
        System.out.println("firstNane = " + firstName);



        String emailAddress= jsonData.getString("students.contact[0].emailAddress");
        System.out.println("emailAddress = " + emailAddress);


        String city= jsonData.getString("students.company[0].address.city");
        System.out.println("city = " + city);

        String emailAddress2= jsonData.getString("students.contact");
        System.out.println("emailAddress = " + emailAddress2);


        int zip= jsonData.getInt("students.company[0].address.zipCode");
        System.out.println("zip = " + zip);
        
    }
}
