Feature: GroupAudits Smoke

@smoke @GroupAudits @GroupAudit_004
  Scenario: GroupAudit_004_Verify validation messages in Add Group Audit page
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Group Audit" menu
    Then I add group audit with invalid logo and verify error message
    Then I add group audit with out name and verify error message
    Then I add group audit with out checklist and verify error message
    
    @smoke @GroupAudits @GroupAudit_005
  Scenario: GroupAudit_005_Verify creation of Group Audit
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Group Audit" menu
    Then I add group audit and verify
    
      @smoke @GroupAudits @GroupAudit_008
  Scenario: GroupAudit_008_Verify updation of Group Audit
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Group Audit" menu
    Then I edit group audit and verify
    
       @smoke @GroupAudits @GroupAudit_009
  Scenario: GroupAudit_009_Verify deletion of Group Audit
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Group Audit" menu
    Then I delete group audit and verify
    
    
       @smoke @GroupAudits @GroupAudit_014
  Scenario: GroupAudit_014_Verify restore of Deleted Audit
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Group Audit" menu
    Then I restore group audit and verify
    
        @smoke @GroupAudits @GroupAudit_006
  Scenario: GroupAudit_006_Verify search filter with valid values for list of Group Audit tab
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Group Audit" menu
    Then I search group audit with valid input and verify
    
     @smoke @GroupAudits @GroupAudit_007
  Scenario: GroupAudit_007_Verify search filter with invalid values for list of Group Audit tab
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Group Audit" menu
    Then I search group audit with invalid input and verify
    
