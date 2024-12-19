package test;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.User;

import static endpoints.UserEndPoints.*;

public class UserTests {

    Faker faker;
    User userPayloads;
    public Logger logger;

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

        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("**********Create user*************");
        Response response = createUser(userPayloads);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("************User created*******************");
    }

    @Test(priority = 2)
    public void testGetUserWithName() {
        logger.info("**********Reading user info *************");
        Response response = readUser(this.userPayloads.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("**********User information read*************");
    }

    @Test(priority = 3)
    public void testUpdateUserWithName() {
        logger.info("**********update user*************");
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
        logger.info("**********User updated*************");

    }

    @Test(priority = 4)
    public void deleteUserByName() {
        logger.info("**********delete user*************");
        Response response = deleteUser(this.userPayloads.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("**********User deleted*************");
    }
}