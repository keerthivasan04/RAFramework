package endpoints;

public class Routes {
    /*
    URL -- https://petstore.swagger.io/#
    post -- https://petstore.swagger.io/#/user/createUsersWithListInput
    get -- https://petstore.swagger.io/#/user/getUserByName
    put -- https://petstore.swagger.io/#/user/updateUser
    delete -- https://petstore.swagger.io/#/user/deleteUser
     */

    public static String base_url = "https://petstore.swagger.io/v2";

    //user module
    public static String post_url = base_url + "/user";
    public static String get_url = base_url + "/user/{username}";
    public static String update_url = base_url + "/user/{username}";
    public static String delete_url = base_url + "/user/{username}";
}
