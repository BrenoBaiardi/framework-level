package webServiceTesting;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

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
        given(createRegister.buildAuth())
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
