Feature: Registering to CRM Application

  Scenario: Launch the Application and Signup
    Given Navigates to CRM Application
    When I Click Signup Link and Choose Free Edition
    Then I enter Personal details and Submit
      | Prasaad | Chandrasekaran | prasaad44@gmail.com | Prasaad44 | P3ar$onPC |
    And I should get Alert Popup
    

