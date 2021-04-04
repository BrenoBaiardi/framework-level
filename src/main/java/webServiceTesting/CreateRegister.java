package webServiceTesting;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class CreateRegister {

    private static final String REGISTER_PATH = "/register";

    private static final String BASE_URI = "https://reqres.in/api";
    private RequestSpecification requestSpecification;
    private String email;
    private String password;
    PreemptiveBasicAuthScheme authScheme;

    public CreateRegister() {
        this.requestSpecification = RestAssured.given()
                .baseUri("https://reqres.in/api")
                .basePath("/register")
                .contentType(ContentType.JSON);
    }

    private String createBodyNoPassword() {
        final JSONObject requestParams = new JSONObject();
        requestParams.put("email",  this.email);
        return requestParams.toJSONString();
    }

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


    final void setEmail(String email) {
        this.email = email;
    }

    final void setPassword(String password) {
        this.password = password;
    }
}
