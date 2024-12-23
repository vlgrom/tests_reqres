import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserTests {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void testGetUserPositive() {
        Response response = given()
                .when()
                .get(BASE_URL + "/users/2")
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals(2, response.jsonPath().getInt("data.id"));
    }

    @Test
    public void testGetUserNegative() {
        Response response = given()
                .when()
                .get(BASE_URL + "/users/999")
                .then()
                .statusCode(404)
                .extract().response();

        assertEquals(404, response.statusCode());
    }
}