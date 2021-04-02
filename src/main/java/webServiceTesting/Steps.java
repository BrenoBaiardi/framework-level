package webServiceTesting;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.*;

public class Steps {

    CreateUser createUser;

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
        given(createUser.getRequestSpecification())
                .when().post()
                .then().statusCode(201);
    }
}
