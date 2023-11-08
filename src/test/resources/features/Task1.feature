Feature: Login with Facebook
@n11
  Scenario: Verify successful login with Facebook
    Given I navigate to "https://www.n11.com/"
    When user log in with Facebook
    Then  verify that user login successfully