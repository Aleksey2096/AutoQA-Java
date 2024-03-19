Feature: Login functionality for a qa-course-01.andersenlab website.

  Background:
    Given User is on Login page

  @ValidCredentials
  Scenario: Login with valid credentials

    When User enters email as "test_email@gmail.com" and password as "strongpassword" and clicks sign in button
    Then Account page should be opened with url "https://qa-course-01.andersenlab.com/" and header text "My account"
    And User should be logged out

  @WrongCredentials
  Scenario Outline: Login with wrong credentials

    When User enters email as "<email>" and password as "<password>" and clicks sign in button
    Then User should be able to see 2 common error messages "<errorMessage>"

    Examples:
      | email                 | password       | errorMessage                   |
      | test_email@gmail.com  | wrongpassword  | Email or password is not valid |
      | wrong_email@gmail.com | strongpassword | Email or password is not valid |
      | wrong_email@gmail.com | wrongpassword  | Email or password is not valid |

  @InvalidEmail
  Scenario: Login with invalid email

    When User enters email as "invalid_email" and password as "strongpassword" and clicks sign in button
    Then User should be able to see specific error message "Invalid email address"  
    
