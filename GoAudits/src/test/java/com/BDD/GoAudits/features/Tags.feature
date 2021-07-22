Feature: Tags Smoke

@smoke @Tags @Tags_006
  Scenario: Tags_006_Verify creation of Tag Category with selection for Location
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I create of Tag Category with selection for Location and verify
    
    @smoke @Tags @Tags_007
  Scenario: Tags_007_Verify creation of Tag Category with selection for User
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I create of Tag Category with selection for User and verify
    
       @smoke @Tags @Tags_009
  Scenario: Tags_009_Verify updation of Tag Category with selection for Location
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I update of Tag Category with selection for Location and verify
    
         @smoke @Tags @Tags_010
  Scenario: Tags_010_Verify updation of Tag Category with selection for User
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I update of Tag Category with selection for User and verify
    
    @smoke @Tags @Tags_012
  Scenario: Tags_0012_Verify creation of Tag Category with selection for Location
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I create inactive Tag Category with selection for Location and verify

          @smoke @Tags @Tags_015
  Scenario: Tags_015_Verify deletion of Tag Category with selection for Location
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I delete of Tag Category with selection for Location and verify
    
         @smoke @Tags @Tags_016
  Scenario: Tags_016_Verify deletion of Tag Category with selection for User
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I delete of Tag Category with selection for User and verify

         @smoke @Tags @Tags_020
  Scenario: Tags_020_Verify creation of Add Tag
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I add Tag and verify
    
            @smoke @Tags @Tags_022
  Scenario: Tags_022_Verify updation of Add Tag
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I update Tag and verify
    
            @smoke @Tags @Tags_026
  Scenario: Tags_026_Verify creation of multiple Tags for a Tag Category
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I add multiple Tag and verify
    
            @smoke @Tags @Tags_027
  Scenario: Tags_027_Verify deletion of Add Tag
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I delete Tag and verify
    
   @Tags @Tags_028
  Scenario: Tags_028_Verify deletion of Tag Category deletes all Tags
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Tags" menu
    Then I delete Tag Category and verify tag category deleted with all tags 
    
