Feature: Registering to CRM Application

  Scenario: Launch the Application and Signup
    Given Navigates to CRM Application
    When I Click Signup Link and Choose Free Edition
    Then I enter Mandatory details
      | Prasaad | Chandrasekaran | prasaad44@ymail.com | Prasaad44 | febU@111 |
    And I should navigate to Home Screen

  Scenario: Checking Condition
    Given Check the Condition
  
