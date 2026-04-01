import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class UpdatePartialTest {

    private String bookingId;
    private String token;

    @Test(priority=3)
     public void partialUpdate(){
        token = AuthHelper.getToken();
        CreateBookingTest createBookingTest = new CreateBookingTest();
        bookingId = createBookingTest.getBookingId();
        File partialUpdateFile = new File("src/test/resources/PartialUpdate.json");
        Response response = given().contentType("application/json").accept("application/json").cookie("token", token)
                .when().body(partialUpdateFile).patch("/booking/" + bookingId)
                .then().statusCode(200).extract().response();
        String responseBody = response.asString();
        System.out.println(responseBody);
        JsonPath jsonPath = new JsonPath(responseBody);
        System.out.println(jsonPath.getString("totalprice"));
        System.out.println(jsonPath.getString("depositpaid"));
        System.out.println(jsonPath.getString("additionalneeds"));
        Assert.assertEquals(jsonPath.getString("depositpaid"), "false");
    }

}
