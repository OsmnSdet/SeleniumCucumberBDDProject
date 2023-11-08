Feature: Verify Footer Links
@n11

  Scenario: Write footer links to a text file and verify them
    Given I navigate to "https://www.n11.com/"
    When user write all the links at the footer to a text file
    And user click "Brands" at the footer
    Then user verify the footer links with the text file

