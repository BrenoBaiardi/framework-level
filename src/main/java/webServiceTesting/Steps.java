package webServiceTesting;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.*;

public class Steps {

    CreateUser createUser;
    String createdId;

    @Given("^I use user creation service$")
    public void useUserCreationWebService() {
        createUser = new CreateUser();
    }

    @When("^I set name \"([^\"]*)\"$")
    public void setName(String name) {
        createUser.setName(name);
    }

    @When("^I set job \"([^\"]*)\"$")
    public void setJob(String job) {
        createUser.setJob(job);
    }

    @Then("^I validate my response is correct$")
    public void validateMyResponseIsCorrect() {
        createdId = given(createUser.getRequestSpecification())
                .when().post()
                .then().statusCode(HttpStatus.SC_CREATED).extract().path("id");
    }

    @Then("^I validate the deletion of a user$")
    public void validatePreviousCreatedUserDeletion() {
        createUser.getRequestSpecification().given().delete("/"+"2");
        when().delete().then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
