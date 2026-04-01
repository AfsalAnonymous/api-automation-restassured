import java.io.File;

import static io.restassured.RestAssured.given;

public class AuthHelper {

    private static String token;


    private AuthHelper() {}

    public static String getToken() {
        if (token == null) {
            File Authfile = new File("src/test/resources/AuthFile.json");
            token = given().header("Content-Type", "application/json")
                    .body(Authfile)
                    .when().post("/auth")
                    .then().statusCode(200).extract().jsonPath().getString("token");

            System.out.println("Token Generated : " + token);
        }
        else {
            System.out.println("Token Already Exists" + token);
        }
        return token;
    }
}
