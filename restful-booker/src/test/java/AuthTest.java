import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AuthTest {

    @Test(priority=0)
    public void generateToken() {
        File Authfile = new File("src/test/resources/AuthFile.json");
        String token =
                given().header("Content-Type", "application/json")
                        .body(Authfile)
                        .when().post("/auth")
                        .then().statusCode(200).extract().jsonPath().getString("token");

        System.out.println("Auth Token Is: " + token);
    }
}
