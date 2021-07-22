Feature: AuditWorkflow Smoke
    
        @smoke @AuditWorkflow @AuditWorkflow_004
  Scenario: AuditWorkflow_004_Verify validation messages in Create Audit Workflow page for Audit
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I create Audit Workflow without filling mandatory fields and verify error message
    
         @smoke @AuditWorkflow @AuditWorkflow_007
  Scenario: AuditWorkflow_007_Verify creation of Audit Workflow with multiple checklists one location and one Assignee for Audit
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I verify creation of Audit Workflow with multiple checklists one location and one Assignee for Audit
    
    
     @smoke @AuditWorkflow @AuditWorkflow_012
  Scenario: AuditWorkflow_012_Verify updation of Audit Workflow for Audit
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I edit Audit Workflow and verify

     @smoke @AuditWorkflow @AuditWorkflow_015
  Scenario: AuditWorkflow_015_Verify deletion of Audit Workflow for Audit
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I delete Audit Workflow and verify
    
     @smoke @AuditWorkflow @AuditWorkflow_017
  Scenario: AuditWorkflow_017_Verify validation messages in Create Audit Workflow page for Action Plan
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I create Audit Workflow without filling mandatory fields and verify error message

     @smoke @AuditWorkflow @AuditWorkflow_019
  Scenario: AuditWorkflow_019_Verify creation of Audit Workflow for Action Plan
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I create Audit Workflow and verify
    
     @smoke @AuditWorkflow @AuditWorkflow_020
  Scenario: AuditWorkflow_020_Verify creation of Audit Workflow with multiple checklists one location and one Assignee for Action Plan
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I verify creation of Audit Workflow with multiple checklists one location and one Assignee for Action Plan
    
     @smoke @AuditWorkflow @AuditWorkflow_022
  Scenario: AuditWorkflow_022_Verify creation of Audit Workflow with multiple checklists multiple locations and with multiple Assignee for Action Plan
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I verify creation of Audit Workflow with multiple checklists multiple locations and with multiple Assignee for Action Plan
    
    
         @smoke @AuditWorkflow @AuditWorkflow_025
  Scenario: AuditWorkflow_025_Verify updation of Audit Workflow for Action Plan tab
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I edit Audit Workflow and verify

     @smoke @AuditWorkflow @AuditWorkflow_028
  Scenario: AuditWorkflow_028_Verify deletion of Audit Workflow for Action Plan 
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Audit WorkFlow" menu
    Then I delete Audit Workflow and verify
    
    
    
    
    
    
    