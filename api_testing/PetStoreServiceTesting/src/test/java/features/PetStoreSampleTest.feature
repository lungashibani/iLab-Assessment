Feature: Set of Test scenarios to verify PetStore Application REST Api services.

  @PetTest @FullPack @Test1
  Scenario: Add a new pet and verify the pet is added successfully.
    Given I perform POST operation to add a new pet with body
          |  Id     |  Name    |  Status  |        Photo_Url               | Tags_Id  |  Tags_Name |  Category_Id  |  Category_Name |
          |  744267 |  Micky   |   Sold   |https://image1 ; https://image2 |   91;61  | Good;Funny |       1       |    mouse       |
    Then  I perform a GET operation with ID to verify the details are added correctly
          |  Id     |  Name    |  Status  |
          |  744267 |  Micky   |   Sold   |

  @PetTest @FullPack @Test2
  Scenario: Add a new pet and verify the user is able to remove it successfully.
    Given I perform POST operation to add a new pet with body
      |  Id     |  Name    |  Status  |        Photo_Url               | Tags_Id  |  Tags_Name |  Category_Id  |  Category_Name |
      |  744305 |  Micky   |   Sold   |https://image1 ; https://image2 |   91;61  | Good;Funny |       1       |    mouse       |
    And  I perform a DELETE operation with ID and verify the data is removed.
      |  Id     |
      |  744305 |
    Then  I verify with ID to check data is removed.
      |  Id     |  Name    |  Status  |
      |  744305 |  Micky   |   Sold   |

  @OrderTest @FullPack @Test3
  Scenario: Place an order to buy a pet and verify order status.
    Given I perform POST operation to place an order for pet.
      |  Id     |  PetId    |  Quantity  |
      |  2      |  2        |   21       |
    Then  I verify the status of the order.
      |  Id     |  Status |
      |  2      |  placed |

  @OrderTest @FullPack @NegativeCase
  Scenario: Place an order to buy a pet and verify order status is cancelled.
    Given I perform POST operation to place an order for pet.
      |  Id     |  PetId    |  Quantity  |
      |  1      |  1        |   45       |
    Then  I verify the status of the order.
      |  Id     |   Status   |
      |  1      |  cancelled |