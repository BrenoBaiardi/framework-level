package webServiceTesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateUser {

    private static final String BASE_URI = "https://reqres.in/api";
    private static final String USERS_PATH = "/users";
    private String name;
    private String surName;
    private String job;
    private RequestSpecification requestSpecification;

    public CreateUser() {
        requestSpecification = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(USERS_PATH)
                .contentType(ContentType.JSON);
        name = "";
        job = "";
        surName = "";
    }

    RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setJob(final String job) {
        this.job = job;
    }

    public void setSurName(final String surName) {
        this.surName = surName;
    }

    public JSONObject buildBody() {
        JSONObject userJson = getJsonNameAndJob(this.name, this.job);
        this.requestSpecification =
                RestAssured.given().body(userJson.toJSONString())
                        .baseUri(BASE_URI)
                        .basePath(USERS_PATH)
                        .contentType(ContentType.JSON);
        return userJson;
    }

    public final JSONObject buildBodyWithSurname() {
        final JSONObject userJson = getJsonNameAndJob(String.format("%s %s", this.name, this.surName), this.job);
        this.requestSpecification =
                RestAssured.given().body(userJson.toJSONString())
                        .baseUri(BASE_URI)
                        .basePath(USERS_PATH)
                        .contentType(ContentType.JSON);
        return userJson;
    }

    private JSONObject getJsonNameAndJob(final String name, final String job) {
        final Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("job", job);
        return new JSONObject(map);
    }
}