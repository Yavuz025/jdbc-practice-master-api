package apitest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HrApiTestWithJsonPath {
    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("hrApi.uri");
    }

    @Test
    public void testCountriesWithJsonPath() {

        Response response=get("/countries");

        JsonPath jsonData=response.jsonPath();

        String secondCountryName=jsonData.getString("items.country_name[1]");
        System.out.println("secondCountryName = " + secondCountryName);

        assertEquals("Australia",secondCountryName);

        String firstCountryId=jsonData.getString("items.country_id[0]");
        System.out.println("firstCountryId = " + firstCountryId);
        assertEquals("AR",firstCountryId);
        
        
        
        List<String>countyIds=jsonData.getList("items.country_id");
        System.out.println("countyIds.size = " + countyIds.size());
        System.out.println("countyIds = " + countyIds);


        List<String>countriesRegionTwo= jsonData.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("countriesRegionTwo = " + countriesRegionTwo);




    }

    @Test
    public void testFindAllEmployeesExample() {
        
        Response response= given().queryParam("limit",150).get("/employees");
        
        JsonPath jsonData= response.jsonPath();

        List<String> firstNames = jsonData.getList("items.findAll{it.salary==2100}.first_name");
        System.out.println("firstNames = " + firstNames);

        List<String> firstNamesFromJobId = jsonData.getList("items.findAll{it.job_id==\"IT_PROG\"}.first_name");
        System.out.println("firstNamesFromJobId = " + firstNamesFromJobId);

        List<String> firstNamessalary = jsonData.getList("items.findAll{it.job_id==\"IT_PROG\"}.salary");
        System.out.println("SalaryFromJobId = " + firstNamessalary);


        List<String> firstNames2 = jsonData.getList("items.findAll{it.salary>5000}.first_name");
        System.out.println("firstNames = " + firstNames2);
    }
}
