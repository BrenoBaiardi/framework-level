package webServiceTesting;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.net.MalformedURLException;

import static io.restassured.RestAssured.*;

public class Steps {

    String BASE_URL = "https://reqres.in/api/users";
    CreateUser createUser;
//    String name, job;

    public Steps() throws MalformedURLException {
    }

    @Given("^I use user creation service$")
    public void useUserCreationWebService() {
        createUser = new CreateUser();
    }

    @When("^I set name \"([^\"]*)\"$")
    public void setName(String name) {
        createUser.setName(name);
//        this.name = name;
    }

    @When("^I set job \"([^\"]*)\"$")
    public void setJob(String job) {
        createUser.setJob(job);
//        this.job = job;
    }

    @Then("^I validate my response is correct$")
    public void validateMyResponseIsCorrect() {
        given(createUser.getRequestSpecification())
                .when().post()
                .then().statusCode(200);
    }
}
