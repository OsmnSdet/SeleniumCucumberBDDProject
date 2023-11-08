Feature: Add Product to Cart
@n11
  Scenario: Add a product to Favorites and verify its details
  Given I navigate to "https://www.n11.com/"
    When user navigate to "Perfume & Deodorant" under the "Cosmetic & Personal Care" tab
    And user search for "Lacoste" in the Brand filter
    And user select "Lacoste"
    And user click the 7th product on the search result page
    And user add the product to "Cart"
    And user navigate to "Cart"
    Then verify that the product title is the same as the product details page
