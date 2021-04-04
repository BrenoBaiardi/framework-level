Feature: User Management

  Scenario: Validate that it is possible to add a new user with a job
    Given I use user creation service
    When I set name "Toy"
    And I set job "Singer"
    And I build the request body
    Then I validate my response is correct

  Scenario: Validate that it is possible to delete a previously created user
    Given I use user creation service
    Then I validate the deletion of a user

  Scenario: Validate the submission of a register without password
    Given I use register creation service
    And I set email to "challenge@automation.com"
    And I set password to "123"
    Then I validate my submission response represents a failure