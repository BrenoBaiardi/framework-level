package webServiceTesting;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class CreateRegister {

    private RequestSpecification requestSpecification;
    private String email;
    private String password;
    PreemptiveBasicAuthScheme authScheme;

    public CreateRegister() {
        this.requestSpecification = RestAssured.given()
                .baseUri("https://reqres.in/api")
                .basePath("/register")
                .contentType(ContentType.JSON);
        authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("challenge@automation.com");
//        authScheme.setPassword("");
        RestAssured.authentication = authScheme;
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification.given();
    }

    public RequestSpecification buildSpecification() {
        Map<String, Object> map = new HashMap<>();
        map.put("email", this.email);
        map.put("password", this.password);
        JSONObject userJson = new JSONObject(map);
        this.requestSpecification =
                RestAssured.given().body(userJson.toJSONString())
                        .baseUri("https://reqres.in/api")
                        .basePath("/register")
                        .contentType(ContentType.JSON);
        return requestSpecification;
    }

    public String createSampleEntry() {
        return "{\"id\": 7,\"name\": \"ceeee\",\"year\": 2000,\"color\": \"#98B2D1\",\"pantone_value\": \"15-4020\" }";
    }

    public RequestSpecification buildAuth() {
        this.requestSpecification =
                RestAssured.given().auth()
                        .basic(this.email, this.password)
                        .baseUri("https://reqres.in/api")
                        .basePath("/register")
                        .body(createSampleEntry())
                        .contentType(ContentType.JSON);
        ;
        return requestSpecification;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
