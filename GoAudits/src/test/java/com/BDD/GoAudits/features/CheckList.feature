Feature: Check List Smoke

#@smoke
#  Scenario: Checklist_004_Verify Pre-defined Template is added to a company
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I add pre-defined template
#    Then I verify pre-defined template added successfully
    
#    @smoke
#  Scenario: Checklist_005_Verify validation error for a duplicate audit template in a company
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I add duplicate audit and verify error message
#    
#      @smoke
#  Scenario: Checklist_008_Verify error for editing with invalid format
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I edit template with invalid format and verify error message
#    Then I upload invalid logo and verify error message
    
#        @smoke
#  Scenario: Checklist_010_Verify multiple sign adding 
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I edit template with adding multiple signature and verify message
    
#           @smoke
#  Scenario: Checklist_013_Verify creation of Action assignee with appoval required to a Audit Template 
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I add action assignee and verify message
    
#             @smoke
#  Scenario: Checklist_014_Verify mutiple action assignee's can be added to a Audit Template
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I add multiple action assignee and verify message
    
#       @smoke
#     Scenario: Checklist_017_Verify updation of action assignee
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I update action assignee and verify message
    
#      @smoke
#     Scenario: Checklist_018_Verify deletion of action assignee
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I delete action assignee and verify message
    
#      @smoke
#     Scenario: Checklist_033_Verify deletion of Template
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I delete template and verify message
    
#       @smoke
#     Scenario: Checklist_038_Verify Deleted Template can be restored
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I restore template and verify message
    
#      @smoke
#     Scenario: Checklist_020_Verify multiple audit templates can be created for a company
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I add multiple template and verify
    
#        @smoke
#     Scenario: Checklist_026_Verify multiple Score ranges can be created
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I add "3" score ranges and verify
    
#        @smoke
#     Scenario: Checklist_028_Verify score range rows can be deleted
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I delete score ranges and verify
    
       @smoke @Checklist_034
     Scenario: Checklist_034_Verify search filter with matching keyword populates the matching templates
    Given I Logged in with valid credentials
    Then I navigated to "Forms/Checklists" menu
    Then I search template with valid input and verify
    
#       @smoke
#     Scenario: Checklist_035_Verify search filter displays the notification message when matching results are not found
#    Given I Logged in with valid credentials
#    Then I navigated to "Forms/Checklists" menu
#    Then I search template with invalid input and verify
#    
#    
 
    

    
    
