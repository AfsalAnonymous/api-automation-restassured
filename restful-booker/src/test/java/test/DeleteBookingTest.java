package test;

import org.testng.annotations.Test;
import utils.AuthHelper;

import static io.restassured.RestAssured.given;

public class DeleteBookingTest {

    private String token;
    private String bookingId;


    @Test(priority=5)
    public void deleteBooking(){
        token = AuthHelper.getToken();
        CreateBookingTest createBookingTest = new CreateBookingTest();
        bookingId = createBookingTest.getBookingId();
        System.out.println("bookingId: " + bookingId);
        given().contentType("application/json").cookie("token",token)
                .when().delete("/booking/" + bookingId)
                .then().statusCode(201);
        System.out.println(bookingId + ": Booking Deleted Successfully");

    }
}
