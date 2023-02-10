package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HamcrestMatchersApiTest {
    @Test
    public void testSingleSpartanByChaining() {


        given().accept(ContentType.JSON).
                and().pathParam("id", 15).
                when().get("http://54.144.70.64:8000/api/spartans/{id}").
                then().assertThat().statusCode(200).
                and().assertThat().contentType("application/json").
                and().assertThat().body(
                        "id", equalTo(15),
                        "name", equalTo("Meta"),
                        "gender", equalTo("Female"),
                        "phone", equalTo(1938695106)).
                and().assertThat().headers("Transfer-Encoding", equalTo("chunked"),
                        "Connection", equalTo("keep-alive"),
                        "Keep-Alive", equalTo("timeout=60"));
    }

    @Test
    public void testTeacherData() {


        given().accept(ContentType.JSON).
                and().pathParam("name", "Eliza").
                when().get("http://api.cybertektraining.com/teacher/name/{name}").
                then().assertThat().statusCode(200).
                and().assertThat().contentType("application/json").
                and().assertThat().body(
                        "teachers.teacherId[0]",equalTo(20652),
                        "teachers.firstName[0]",equalTo("Eliza"),
                        "teachers.lastName[0]",equalTo("Runolfsson"),
                        "teachers.emailAddress[0]", equalTo("eliza.runolfsson@gmail.com"),
                        "teachers.phone",contains("939.474.8318"),
                        "teachers.birthDate",contains("09/06/1996"));

    }
    @Test
    public void testTeachersAllDataWithHamcrest() {


        given().accept(ContentType.JSON).
                and().pathParam("department", "Computer").
                when().get("http://api.cybertektraining.com/teacher/department/{department}").
                then().assertThat().statusCode(200).
                and().assertThat().contentType("application/json").
                and().assertThat().body(
                "teachers.teacherId[0]",equalTo(16401),
                 "teachers.teacherId",hasItems(16401, 20444, 20442, 20443, 20446, 20447, 20448, 20449, 20450, 20451, 20452, 20841, 20842, 20445),
                "teachers.firstName[0]",equalTo("Rabia"),
                 "teachers.firstName",hasItems("Rabia","Camry","Kudra","Jack","Jack","Jack","Jack","Jack","Alexander","Alexander","Alexander","Dilovar","Dilovar","Jack"),
                "teachers.lastName[0]",equalTo("Kadioglu"),
                "teachers.emailAddress[0]", equalTo("r123@gmail.com"));


    }

    @Test
    public void testSpartanMap() {

        Map<String,Object> spartan=new HashMap<>();

        spartan.put("id",15);
        spartan.put("name","Meta");
        spartan.put("gender","female");
        spartan.put("phone",1938695106);

        System.out.println("spartan = " + spartan);
    }
}
