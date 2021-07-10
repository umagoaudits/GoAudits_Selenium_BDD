Feature: ActionPlan Smoke

#@smoke @ActionPlan @ActionPlan_004
#  Scenario: ActionPlan_004_Verify Click on save button without enter a Priority name
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Action Plan Settings" menu
#    Then I add action plan with out priority and verify error message
    
#   @smoke @ActionPlan @ActionPlan_006
#  Scenario: ActionPlan_006_Create a Action Plan components
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Action Plan Settings" menu
#    Then I add action plan and verify
    
#      @smoke @ActionPlan @ActionPlan_007
#  Scenario: ActionPlan_007_Verify updation of Action Plan
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Action Plan Settings" menu
#    Then I edit action plan and verify
    
#       @smoke @ActionPlan @ActionPlan_008
#  Scenario: ActionPlan_008_Verify deletion of Action Plan
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Action Plan Settings" menu
#    Then I delete action plan and verify
    
#    
#       @smoke @ActionPlan @ActionPlan_009
#  Scenario: ActionPlan_009_Verify restore of Deleted Audit
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Action Plan Settings" menu
#    Then I restore action plan and verify
    
        @smoke @ActionPlan @ActionPlan_011
  Scenario: ActionPlan_011_Create a Similar Priority for Multiple Company
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Action Plan Settings" menu
    Then I add similar priority for multiple company and verify
    
    
