package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AuthHelper;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest {

    private String token;
    private String bookingId;

    @Test(priority=2)
    public void updateBookingTest() {
        //Creates a new Booking by calling the createBookingTest method and captures the bookingId
        CreateBookingTest createBookingTest = new CreateBookingTest();
        bookingId = createBookingTest.getBookingId();
        //File path is created to use updateJson from the files
        File updateJson = new File("src/test/resources/UpdateUser.json");
        //Captures the token in runtime by calling the getToken static method
        token = AuthHelper.getToken();
        System.out.println("Update Booking Page token: " + token);
        //Passes token and booking id captured in runtime and executes put method
        Response response = given().contentType("application/json").accept("application/json").cookie("token", token)
                .when().body(updateJson).put("/booking/" + bookingId)
                .then().statusCode(200).extract().response();
        String responseBody = response.asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        System.out.println(responseBody);
        System.out.println(jsonPath.getString("firstname"));
        //Asserts whether the updation is successfull and checks the Updated values >> written notes for reference by Afsal
        Assert.assertEquals(jsonPath.getString("firstname"), "Afsal");
        Assert.assertEquals(jsonPath.getString("lastname"), "Rehman");
    }
}
