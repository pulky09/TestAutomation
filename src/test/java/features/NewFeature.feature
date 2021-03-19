Feature: New Feature

  @NewTest
  Scenario: Login to Secret Sauce
    Given I am on secret sauce home page
    When I enter "standard_user" and "secret_sauce"
    Then I am logged in

  @DatabaseTest
  Scenario: Creating a new element in Database
    And I create a new record in the customer table with first_name "palki", second_name "sharma", email "hello123@gmail.com",phone "4164347519", address "King Street West", city "Toronto", state "Ontario", zipcode "M6K0C6"
    Then I validate the result