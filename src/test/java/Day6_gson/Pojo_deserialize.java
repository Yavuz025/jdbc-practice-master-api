package Day6_gson;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;


public class Pojo_deserialize {
    @Test
    public void oneSpartanWithPojo() {
        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 15).
                when().get("http://54.144.70.64:8000/api/spartans/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        Spartan2 spartan15 = response.body().as(Spartan2.class);
        System.out.println("spartan15.toString() = " + spartan15.toString());

        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());


        assertEquals(spartan15.getId(), 15);
        assertEquals(spartan15.getName(), "Meta");
        assertEquals(spartan15.getGender(), "Female");
        //assertEquals(spartan15.getPhone(), 1938695106);

    }

    @Test
    public void regionWithPojo() {
        //request
        Response response = get("http://54.144.70.64:1000/ords/hr/regions");
        assertEquals(response.statusCode(), 200);

        //JSON to Region class
        //Deserizaliton
        Region regions = response.body().as(Region.class);

        System.out.println(regions.getCount());

        List<Item> regionList = regions.getItems();
        System.out.println(regionList.get(0).getRegionName());
        System.out.println("regionList.get(1).getRegionId() = " + regionList.get(1).getRegionId());

        System.out.println(regions.getItems().get(0).getRegionName());


        for (Item item : regionList) {
            System.out.println(item.getRegionName());
        }

    }
    @Test
    public void countriesWithPojo() {
        //request
        Response response = get("http://54.144.70.64:1000/ords/hr/countries");
        assertEquals(response.statusCode(), 200);
//
//        Countries countriesPojo= response.body().as(Countries.class);
//
//        System.out.println(countriesPojo.getCount());
//        System.out.println(countriesPojo.getItems().get(0).getCountryId());

    }

    @Test
    public void allSpartansWithPojo() {
        Response response = given().accept(ContentType.JSON).
                when().get("http://54.144.70.64:8000/api/spartans");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json");

        Spartan spartans = response.body().as(Spartan.class);

        System.out.println(spartans);


    }

    @Test
    public void GsonExample() {
        Gson gson = new Gson();

        String MyJson = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";
        System.out.println(MyJson);

        Spartan2 spartan15 = gson.fromJson(MyJson, Spartan2.class);
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getGender() = " + spartan15.getName());


        Spartan2 spartanEu = new Spartan2(15, "Mike", "Male", 1234567890L);

        String jsonSpartanEu = gson.toJson(spartanEu);
        System.out.println("jsonSpartanEu = " + jsonSpartanEu);


    }
}








