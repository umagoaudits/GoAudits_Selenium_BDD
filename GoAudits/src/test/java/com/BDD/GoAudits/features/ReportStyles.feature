Feature: Report Styles Smoke

#@smoke @ReportStyles @ReportStyles_006
#  Scenario: ReportStyles_006_Verify the updation of selecting another report template
#    Given I Logged in with valid credentials
#    Then I navigated to "Report Styles" menu
#    Then I verify updation of selecting another report template
    
   @smoke @ReportStyles @ReportStyles_009
  Scenario: ReportStyles_009_Verify the report style is updated with description and confidentiality
    Given I Logged in with valid credentials
    Then I navigated to "Report Styles" menu
    Then I verify updation of report style with description and confidentiality
    
#    @smoke @ReportStyles @ReportStyles_012
#  Scenario: ReportStyles_012_Verify creation of different report styles for different checklists
#    Given I Logged in with valid credentials
#    Then I navigated to "Report Styles" menu
#    Then I verify updation of selecting another report template for different Company
