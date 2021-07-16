Feature: Report Tag Smoke

#@smoke @ReportTag @ReportTag_004
#  Scenario: ReportTag_004_Verify All Error message in the add Report tags page
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Report Tag" menu
#    Then I add report tag without filling mandatory fields and verify error message
    
#  @smoke @ReportTag @ReportTag_007
#  Scenario: ReportTag_007_Verify user can able to create the Report tags with clicking on save button
#    Given I Logged in with valid credentials
#    Then I navigated to "Advanced-Report Tag" menu
#    Then I add report tag and verify message
    
 @smoke @ReportTag @ReportTag_014
  Scenario: ReportTag_014_Verify User can able to edit the existing Report tags
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Report Tag" menu
    Then I edit report tag and verify message
