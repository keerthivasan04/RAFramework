package endpoints;


// UserEndPoints.java class
// Created to perform Create, Read, Update, Delete requests of the user API

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static endpoints.Routes.get_url;
import static io.restassured.RestAssured.given;

public class UserEndPoints {

    @Test
    void getTest() {
        given().contentType(ContentType.JSON)
                .when()
                .get(get_url)
                .then().log().all();
    }

}
