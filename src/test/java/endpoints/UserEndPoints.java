package endpoints;


// UserEndPoints.java class
// Created to perform Create, Read, Update, Delete requests of the user API

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;

import static endpoints.Routes.*;
import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return response;
    }

    public static Response readUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(get_url);
        return response;
    }

    public static Response updateUser(String username, User payload) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", username)
                .body(payload)
                .when()
                .put(update_url);
        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when().delete(delete_url);
        return response;
    }
}
