package webServiceTesting;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.ReportFilterService;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import report.CustomExtentReporter;
import report.ReportManager;

import static io.restassured.RestAssured.*;

public class Steps {

    private static final String NOT_MISSING_PASSWORD = "The reason of the 400 Status code was not Missing password";
    private CreateUser createUser = null;
    private CreateRegister createRegister = null;
    private CustomExtentReporter reporter;
    private ExtentTest test = null;

    @Before
    public final void beforeScenario(final Scenario scenario) {
        reporter = ReportManager.getReporterInstance();
        System.out.println(scenario.getId());
        test = reporter.createTest(scenario);
    }

    @After
    public final void afterScenario(final Scenario scenario){
        reporter.writeToReport();
    }

    @Given("^I use user creation service$")
    public final void useUserCreationWebService() {
        createUser = new CreateUser();
        test.info("User creation service started");
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
        final RequestSpecification request = given(createRegister.buildAuth());
        request.header("Content-Type", "application/json");
        test.info("Register specification created");
        final Response response = request.post();
        test.info("Register submission request sent");
        test.info("Register submission response received");
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);
        final String errorMessage = response.then().extract().jsonPath().getString("error");
        Assert.assertEquals(NOT_MISSING_PASSWORD, "Missing password", errorMessage);

    }
}
