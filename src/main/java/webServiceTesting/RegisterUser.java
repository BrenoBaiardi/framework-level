package webServiceTesting;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class RegisterUser {

    private static final String REGISTER_PATH = "/register";

    private static final String BASE_URI = "https://reqres.in/api";
    private RequestSpecification requestSpecification;
    private String email;
    private String password;
    PreemptiveBasicAuthScheme authScheme;

    /**
     * Creates RequestSpecification and configures the parameters
     * for sending the HTTPRequest and registering a user
     */
    public RegisterUser() {
        this.requestSpecification = RestAssured.given()
                .baseUri("https://reqres.in/api")
                .basePath("/register")
                .contentType(ContentType.JSON);
    }

    /**
     * Builds and returns body message containing only the user name, discarding the password
     * @return String - JSONString containing the user data
     */
    private String createBodyNoPassword() {
        final JSONObject requestParams = new JSONObject();
        requestParams.put("email",  this.email);
        return requestParams.toJSONString();
    }

    /**
     * Sets the internal RequestSpecification with the authentication information and returns the result
     * @return RequestSpecification - JSONString containing the user data
     */
    RequestSpecification buildAuth() {
        this.requestSpecification =
                RestAssured.given().auth()
                        .basic(this.email, this.password)
                        .baseUri(BASE_URI)
                        .basePath(REGISTER_PATH)
                        .body(createBodyNoPassword())
                        .contentType(ContentType.XML);
        return requestSpecification;
    }


    final void setEmail(final String email) {
        this.email = email;
    }

    final void setPassword(final String password) {
        this.password = password;
    }
}
