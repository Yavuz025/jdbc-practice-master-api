package Day7;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class BookingAuthTest {
    String accessToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.lEfjcu6RpBfcZ4qWthzZU8uH8fX4FCJFfxBnPNgh4Mo";

    @BeforeClass
    public void beforeClass() {

        RestAssured.baseURI = ConfigurationReader.get("https://cybertek-reservation-qa3.herokuapp.com/api");
    }

    @Test
    public void getAllCampusWithAccessToken() {
        Response response= given().header("Authorization",accessToken).when().get("/campuses");

        assertEquals(response.statusCode(),200);
        System.out.println(response.prettyPrint());
    }
}
