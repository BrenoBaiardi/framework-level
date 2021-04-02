package webServiceTesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateUser {

    private String name;
    private String surName;
    private String job;
    private RequestSpecification requestSpecification;

    public CreateUser() {
        this.requestSpecification = RestAssured.given()
                .baseUri("https://reqres.in/api")
                .basePath("/users")
                .contentType(ContentType.JSON);
    }

    public RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String buildBody() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", this.name);
        map.put("job", this.job);
        JSONObject userJson = new JSONObject(map);
        this.requestSpecification =
                RestAssured.given().body(userJson.toJSONString())
                        .baseUri("https://reqres.in/api")
                        .basePath("/users")
                        .contentType(ContentType.JSON);
        return userJson.toJSONString();
    }

    public String buildBodyWithSurname() {
        return null;
    }
}
