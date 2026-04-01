import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class GetBookingTest{

    private String bookingId;

    @Test(priority=4,dependsOnMethods = {"testCreateBooking"})
    public void getFirstBookingTest() {
        CreateBookingTest createBookingTest = new CreateBookingTest();
        bookingId = createBookingTest.getBookingId();
        Response response = given().when().get("/booking/" + bookingId).
                then().statusCode(200).extract().response();
        String jsonResponse = response.asString();
        //Create a JSONPath object and pass the JSON response into the object
        JsonPath jsonPath = new JsonPath(jsonResponse);
        //Prints the whole JSON Body which we get as Response.
        System.out.println(jsonResponse);

        System.out.println("FistName: " + jsonPath.getString("firstname"));
        System.out.println(jsonPath.getString("bookingdates.checkin"));

        //Assert whether it's not Null assertEquals compares two strings.
        Assert.assertNotNull(jsonPath.getString("bookingdates.checkin"));
        Assert.assertNotNull(jsonPath.getString("bookingdates.checkout"));
    }
}
