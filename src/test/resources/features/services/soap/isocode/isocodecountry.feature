Feature: Country Iso Code Search

  As a user of the system I need to verify the operation of the search of a iso code by its country name

  Scenario: Search for a country ISO code according to your name

    Given the user wants to search for the country named "Canada"
    When the user makes the request and confirms the new action
    Then the user should see the ISO code "CA"

  Scenario: Incorrect Search for a country ISO code according to your name

    Given the user wish to search for the country named "canada"
    When the user enters the search data and sends the request
    Then the user should be able to see the following message "No country found by that name"