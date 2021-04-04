# Challenge Results

Below, the results achieved for the challenge

## Challenge Description

The objective of this challenge is to implement Cucumber steps to develop automatic
functional tests on both browser and a web service.  
It is also expected that the candidate is able to understand unit testing and develop some
JAVA methods.  
First, perform some exploratory tests in the following
portal: https://qa-automation-challenge.github.io/sandbox/. If there is any defect, make a
document to report it.

## Results:

* Look at the unit tests and develop the necessary code to run them with success.
    * **_Done_**
* Develop four automated tests according to the description in the acceptanceCriteria
  directory
    * The tests have to be in .feature files described in Gherkin language
    * A report must be generated after test execution
        * **_Done_**
* Feel free to change, remove or add new Cucumber steps according to the best coding
  practices
  * **_Done_**

## How it was developed
In order to be able to develop the tests with Selenium, the chromedriver.exe file were added to the resources folder in *src/test/resources*.
Other requirements were added to the pom file as needed, including liraryes to aid in asserting, API testing, dealing with json and BDD, and generating reports. Some of those dependencies were:
* RestAssured
* json-simple
* JsonAssert
* ExtentReports
* Cucumber-Java
* Cucumber-Junit

In Order to run the developed tests all is needed is to run the TestRunner class. it already contains the necessary configurations to be executed and target all BDD tests.
After each run, the report will be available in *src/test/resources* as *index.html*

The CreateUserTest unit tests should be run independently to check for results. 
