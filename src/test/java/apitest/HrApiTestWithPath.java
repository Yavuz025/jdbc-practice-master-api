package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import javax.swing.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HrApiTestWithPath {
    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("hrApi.uri");
    }

    @Test
    public void getCountriesWithPath() {
        Response response = given().queryParam("q", "{\"region_id\":2}").
                when().get("/countries");
        assertEquals(response.statusCode(),200);

        System.out.println(response.path("limit").toString());

        String firstCountryId = response.path("items.country_id[0]");
        System.out.println("First country id: " + firstCountryId);
        
        String firstCountryName = response.path("items.country_name[0]");
        System.out.println("firstCountryName = " + firstCountryName);
        
        Object firstCountryLink = response.path("items.links[2].href");
        System.out.println("firstCountryLink = " + firstCountryLink);
        
        List<String> allCountryNames = response.path("items.country_name");
        System.out.println("allCountryNames = " + allCountryNames);
        
        
        List<Integer> regionIds=response.path("items.region_id");

        for (int regionId : regionIds) {
            assertEquals(regionId , 2);
            
        }

        
        
        
        
        
        

    }
    @Test
    public void employeesWithQueryParamPath() {

        Response response = given().accept(ContentType.JSON).
                and().queryParam("q", "{\"job_id\":\"IT_PROG\"}").
                when().get("/employees");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        assertTrue(response.body().asString().contains("IT_PROG"));

        List<String> jobIds = response.path("items.job_id");

        for (String jobId : jobIds) {
            System.out.println("jobId = " + jobId);
            assertEquals(jobId,"IT_PROG");
        }



    }
}
