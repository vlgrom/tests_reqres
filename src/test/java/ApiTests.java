import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

    public class ApiTests {

        // Базовый URL для RestAssured
        static {
            RestAssured.baseURI = "https://reqres.in/api";
        }

        // POST /register
        @Test
        public void testRegisterSuccess() {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }")
                    .post("/register");

            response.then()
                    .statusCode(200)
                    .body("id", notNullValue())
                    .body("token", notNullValue());
        }

        @Test
        public void testRegisterFailure() {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .body("{ \"email\": \"eve.holt@reqres.in\" }")
                    .post("/register");

            response.then()
                    .statusCode(400)
                    .body("error", equalTo("Missing password"));
        }

        // PUT /users/{id}
        @Test
        public void testUpdateUserSuccess() {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .body("{ \"name\": \"morpheus\", \"job\": \"leader\" }")
                    .put("/users/2");

            response.then()
                    .statusCode(200)
                    .body("name", equalTo("morpheus"))
                    .body("job", equalTo("leader"));
        }

        @Test
        public void testUpdateUserFailure() {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .body("{ \"name\": \"morpheus\", \"job\": \"leader\" }")
                    .put("/users/9999");

            response.then()
                    .statusCode(404)
                    .body("error", equalTo("User not found"));
        }

        // DELETE /users/{id}
        @Test
        public void testDeleteUserSuccess() {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .delete("/users/2");

            response.then()
                    .statusCode(204);
        }

        @Test
        public void testDeleteUserFailure() {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .delete("/users/9999");

            response.then()
                    .statusCode(404)
                    .body("error", equalTo("Not Found"));
        }

        // GET /users/{id}
        @Test
        public void testGetUserSuccess() {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .get("/users/2");

            response.then()
                    .statusCode(200)
                    .body("data.id", equalTo(2))
                    .body("data.first_name", equalTo("Janet"));
        }

        @Test
        public void testGetUserFailure() {
            Response response = given()
                    .header("Content-Type", "application/json")
                    .get("/users/9999");

            response.then()
                    .statusCode(404)
                    .body("error", equalTo("User not found"));
        }
    }
