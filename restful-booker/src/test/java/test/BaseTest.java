package test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        RestAssured.filters(
                new RequestLoggingFilter()
        );
    }
}
