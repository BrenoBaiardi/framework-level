Feature: Product Subscription

  Background:
    Given I open Chrome and launch the application

  Scenario: Calculate the price of a full special support plan for 6 months
    And I select type "Special"
    And I select support plan "Full"
    And I write monthly duration of "6"
    When I click in calculate price button
    Then I validate price is "2249.10 $"
