import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateUserTests {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    @Description("This is a sample test for Allure")
    public void testUpdateUserPositive() {
        String requestBody = """
            {
                "name": "John",
                "job": "Software Engineer"
            }
        """;

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(BASE_URL + "/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

        assertEquals(200, response.statusCode());
        assertEquals("John", response.jsonPath().get("name"));
        assertEquals("Software Engineer", response.jsonPath().get("job"));
    }

    @Test
    public void testUpdateUserNegative() {
        String requestBody = """
            {
                "name": Invalid
            }
        """;

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(BASE_URL + "/users/lllll")
                .then()
                .statusCode(400)
                .extract().response();
        assertEquals(400, response.statusCode());
    }
}
