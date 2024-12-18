package test;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.User;
import utilities.DataProviders;

import static endpoints.UserEndPoints.createUser;
import static endpoints.UserEndPoints.deleteUser;

public class DataDrivenTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String ID, String userName, String firstName, String lastName, String email, String password, String phoneNumber) {

        User userPayloads = new User();

        userPayloads.setUserStatus(Integer.parseInt(ID));
        userPayloads.setUsername(userName);
        userPayloads.setFirstname(firstName);
        userPayloads.setLastname(lastName);
        userPayloads.setEmail(email);
        userPayloads.setPassword(password);
        userPayloads.setPhone(phoneNumber);

        Response response = createUser(userPayloads);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "Usernames", dataProviderClass = DataProviders.class)
    public void testDeleteByUsername(String userName) {
        Response response = deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
