package test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.User;

import static endpoints.UserEndPoints.*;

public class UserTests {

    Faker faker;
    User userPayloads;

    @BeforeClass
    public void getUserData() {
        faker = new Faker();
        userPayloads = new User();
        userPayloads.setId(faker.idNumber().hashCode());
        userPayloads.setUsername(faker.name().username());
        userPayloads.setFirstname(faker.name().firstName());
        userPayloads.setLastname(faker.name().lastName());
        userPayloads.setEmail(faker.internet().emailAddress());
        userPayloads.setPassword(faker.internet().password());
        userPayloads.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testPostUser() {
        Response response = createUser(userPayloads);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testGetUserWithName() {
        Response response = readUser(this.userPayloads.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testUpdateUserWithName() {
        userPayloads.setFirstname(faker.name().firstName());
        userPayloads.setLastname(faker.name().lastName());
        userPayloads.setEmail(faker.internet().emailAddress());
        Response response = updateUser(this.userPayloads.getUsername(), userPayloads);
//        response.then().log().all();
        response.then().log().body();
        int statusCode = response.getStatusCode();
//        response.then().log().body().statusCode(200);     // restAssured Assertion
        Assert.assertEquals(statusCode, 200);

//        checking the body after updating the payloads
        Response responseAfterUpdate = readUser(this.userPayloads.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);//testNG Assertion
    }

    @Test(priority = 4)
    public void deleteUserByName() {
        Response response = deleteUser(this.userPayloads.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}