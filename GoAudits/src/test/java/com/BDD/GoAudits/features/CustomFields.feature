Feature: Custom Fields Smoke

#@smoke @CustomFields @CustomFields_004
#  Scenario: CustomFields_004_Verify All Error message in the add custom fields page
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Custom Fields" menu
#    Then I add custom fields without filling mandatory fields and verify error message
    
#@smoke @CustomFields @CustomFields_008
#  Scenario: CustomFields_008_Verify user can able to create the custom fields with clicking on save button
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Custom Fields" menu
#    Then I add custom fields and verify message
    
#@smoke @CustomFields @CustomFields_009
#  Scenario: CustomFields_009_Verify User can able to delete the existing custom fields
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Custom Fields" menu
#    Then I delete custom fields and verify message
    
    
#    @smoke @CustomFields @CustomFields_010
#  Scenario: CustomFields_010_Verify user can able to restore the deleted custom fields
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Custom Fields" menu
#    Then I restore custom fields and verify message
    
#     @smoke @CustomFields @CustomFields_014
#  Scenario: CustomFields_014_Verify User can able to edit the existing custom fields
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Custom Fields" menu
#    Then I edit custom fields and verify message
    
       @smoke @CustomFields @CustomFields_016
  Scenario: CustomFields_016_Verify user can able to create the custom fields with different companies
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Custom Fields" menu
    Then I add the custom fields with different companies and verify message
    
