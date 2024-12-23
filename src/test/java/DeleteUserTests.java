import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUserTests {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void testDeleteUserPositive() {
        Response response = given()
                .when()
                .delete(BASE_URL + "/users/2")
                .then()
                .statusCode(204)
                .extract().response();

        assertEquals(204, response.statusCode());
    }

    @Test
    public void testDeleteUserNegative() {
        Response response = given()
                .when()
                .delete(BASE_URL + "/users/999")
                .then()
                .statusCode(404)
                .extract().response();

        assertEquals(404, response.statusCode());
    }
}
