@tag
  Feature: Purchase the order from Ecommerce Website
  Background:
    Given I landed on Ecommerce Page
    @Regression
    Scenario Outline: Positive Test of Submitting the order
      Given Logged in with username <name> and password <password>
      When  I add product <productName> from cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." is displayed

      Examples:
      |  name           | password | productName |
      | fouad@gmail.com | Test1234 | ZARA COAT 3 |
