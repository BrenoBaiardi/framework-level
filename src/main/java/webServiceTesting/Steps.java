package webServiceTesting;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.*;

public class Steps {

    private CreateUser createUser = null;
    private CreateRegister createRegister = null;

    @Given("^I use user creation service$")
    public final void useUserCreationWebService() {
        createUser = new CreateUser();
    }

    @Given("^I use register creation service$")
    public final void useRegisterCreationWebService() {
        createRegister = new CreateRegister();
    }

    @When("^I set name \"([^\"]*)\"$")
    public final void setName(final String name) {
        createUser.setName(name);
    }

    @When("^I set surname \"([^\"]*)\"$")
    public final void setSurName(final String surName) {
        createUser.setSurName(surName);
    }

    @When("^I set job \"([^\"]*)\"$")
    public final void setJob(final String job) {
        createUser.setJob(job);
    }

    @When("^I set email to \"([^\"]*)\"$")
    public final void setEmail(final String email) {
        createRegister.setEmail(email);
    }

    @When("^I set password to \"([^\"]*)\"$")
    public final void setPassword(final String password) {
        createRegister.setPassword(password);
    }

    @When("^I build the request body$")
    public final void setPassword() {
        createUser.buildBody();
    }

    @Then("^I validate my response is correct$")
    public final void validateMyResponseIsCorrect() {
        given(createUser.getRequestSpecification())
                .when().post()
                .then().statusCode(HttpStatus.SC_CREATED);
    }

    @Then("^I validate the deletion of a user$")
    public final void validatePreviousCreatedUserDeletion() {
        createUser.getRequestSpecification().given().delete("/2");
        given(createUser.getRequestSpecification()).when().delete().then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Then("^I validate my submission response represents a failure$")
    public final void validateMyResponseIsFailure() {
        RequestSpecification request = given(createRegister.buildAuth());
        JSONObject requestParams = new JSONObject();
        requestParams.put("email",  "sydney@fife");
        request.body(requestParams.toJSONString());
        request.header("Content-Type","application/json");
        request.log().body();
        request.post().then().statusCode(HttpStatus.SC_BAD_REQUEST).log().body();
    }
}
