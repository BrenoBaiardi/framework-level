package webServiceTesting;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.*;

public class Steps {

    CreateUser createUser;
    CreateRegister createRegister;
    String createdId;

    @Given("^I use user creation service$")
    public void useUserCreationWebService() {
        createUser = new CreateUser();
    }

    @Given("^I use register creation service$")
    public void useRegisterCreationWebService() {
        createRegister = new CreateRegister();
    }

    @When("^I set name \"([^\"]*)\"$")
    public void setName(String name) {
        createUser.setName(name);
    }

    @When("^I set surname \"([^\"]*)\"$")
    public void setSurName(String surName) {
        createUser.setSurName(surName);
    }

    @When("^I set job \"([^\"]*)\"$")
    public void setJob(String job) {
        createUser.setJob(job);
    }

    @When("^I set email to \"([^\"]*)\"$")
    public void setEmail(String email) {
        createRegister.setEmail(email);
    }

    @When("^I set password to \"([^\"]*)\"$")
    public void setPassword(String password) {
        createRegister.setPassword(password);
    }

    @When("^I build the request body$")
    public void setPassword() {
        createUser.buildBody();
    }

    @Then("^I validate my response is correct$")
    public void validateMyResponseIsCorrect() {
        given(createUser.getRequestSpecification())
                .when().post()
                .then().statusCode(HttpStatus.SC_CREATED);
    }

    @Then("^I validate the deletion of a user$")
    public void validatePreviousCreatedUserDeletion() {
        createUser.getRequestSpecification().given().delete("/2");
        when().delete().then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Then("^I validate my submission response represents a failure$")
    public void validateMyResponseIsFailure() {
        given(createRegister.buildAuth())
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
