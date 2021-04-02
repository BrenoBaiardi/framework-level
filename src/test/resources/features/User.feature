Feature: User Management

  Scenario: Validate that it is possible to add a new user with a job
    Given I use user creation service
    And I set name "Toy"
    And I set job "Singer"
    And I validate my response is correct