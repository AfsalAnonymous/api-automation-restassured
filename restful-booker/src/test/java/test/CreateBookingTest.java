package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;



public class CreateBookingTest {
    public static String bookingId;
    //This method will create a Booking and return the booking Id.
    public String createBooking() {
        File createNewUserJson = new File("src/test/resources/CreateUser.json");
        Response response = given().contentType("application/json")
                //This createNewUserJson json comes from the resources folder
                .when().body(createNewUserJson)
                .post("/booking")
                .then().statusCode(200).extract().response();
        String jsonResponse = response.asString();
        JsonPath jsonPath =  new JsonPath(jsonResponse);
        System.out.println(jsonResponse);
        bookingId = jsonPath.getString("bookingid");
        System.out.println(bookingId);
        return bookingId;
    }

    @Test(priority=1)
    public void testCreateBooking() {
        bookingId = createBooking();
        System.out.println("Booking Id: " + bookingId);
    }

    @Test
    public String getBookingId(){
        return bookingId;
    }
}

