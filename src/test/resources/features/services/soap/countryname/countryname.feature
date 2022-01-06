Feature: Country Name Search

  As a user of the system I need to verify the operation of the search of a country by its ISO code

  Scenario: Search for a country according to your iso

    Given the user wants to search for the country with ISO code "CA"
    When the user makes the request and confirms the action
    Then the user should see the name of the country "Canada"

  Scenario: Incorrect search for a country according to your iso

    Given the user wish to search for the country with ISO code "ca"
    When the user enters the search data and sends the new request
    Then the user should be able to see the message "Country not found in the database"