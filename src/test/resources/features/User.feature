Feature: User Management

  Background:
    Given I use user creation service

  Scenario: Validate that it is possible to add a new user with a job
    When I set name "Toy"
    And I set job "Singer"
    Then I validate my response is correct

  Scenario: Validate that it is possible to delete a previously created user
    Then I validate the deletion of a user