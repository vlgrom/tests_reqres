import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTests {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void testRegisterPositive() {
        String requestBody = """
            {
                "email": "eve.holt@reqres.in",
                "password": "pistol"
            }
        """;

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/register")
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals(true, response.jsonPath().get("token") != null);
    }

    @Test
    public void testRegisterNegative() {
        String requestBody = """
            {
                "email": "sydney@fife"
            }
        """;

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/register")
                .then()
                .statusCode(400)
                .extract().response();

        assertEquals(400, response.statusCode());
        assertEquals("Missing password", response.jsonPath().get("error"));
    }
}