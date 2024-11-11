Feature: Product bag modifications

  Scenario: Product can be removed from the bag
    Given there are products in the bag
    When user removes a product
    Then the product is removed from the bag

  Scenario: Product quantity is increased when added to the bag
    Given there are products in the bag
    When User adds quantity
    Then product quantity is increased

#  It's not possible to 'decrease' quantity to check that 'product quantity is removed from the bag' (minimum available option is 1),
#  so this case checks decreasing the quantity to 1
  Scenario: Product quantity is decreased when removed from the bag
    Given there are products in the bag
    When User adds quantity
    Then product quantity is increased
    When User decreases quantity
    Then product quantity is decreased