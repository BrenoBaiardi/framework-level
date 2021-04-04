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

    /**
     * Creates RequestSpecification and configures the parameters
     * for sending the HTTPRequest and creating a user
     */
    public CreateUser() {
        requestSpecification = RestAssured.given()
                .baseUri(BASE_URI)
                .basePath(USERS_PATH)
                .contentType(ContentType.JSON);
        name = "";
        job = "";
        surName = "";
    }

    /**
     * Returns previously built RequestSpecification
     * @return requestSpecification
     */
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

    /**
     * Builds and returns body message containing the elements for creating a user
     * @return JSONObject - JSONObject Representing the user
     */
    public JSONObject buildBody() {
        JSONObject userJson = getJsonNameAndJob(this.name, this.job);
        setRequestSpecificationWithBody(userJson);
        return userJson;
    }

    /**
     * Builds and returns body message containing the elements for creating a user
     * including the surname to the sent object
     * @return JSONObject Representing the user
     */
    public final JSONObject buildBodyWithSurname() {
        final JSONObject userJson = getJsonNameAndJob(String.format("%s %s", this.name, this.surName), this.job);
        setRequestSpecificationWithBody(userJson);
        return userJson;
    }

    /**
     * sets the specified user information to the RequestSpecification
     * @param userJson JSONObject conatining the user fields
     */
    private void setRequestSpecificationWithBody(JSONObject userJson){
        this.requestSpecification =
                RestAssured.given().body(userJson.toJSONString())
                        .baseUri(BASE_URI)
                        .basePath(USERS_PATH)
                        .contentType(ContentType.JSON);
    }

    /**
     * Creates and returns JSONObject with "name" and "job" pairs of Key -> Value
     * @param name user name
     * @param job user job
     * @return JSONObject from map of user name and job
     */
    private JSONObject getJsonNameAndJob(final String name, final String job) {
        final Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("job", job);
        return new JSONObject(map);
    }
}