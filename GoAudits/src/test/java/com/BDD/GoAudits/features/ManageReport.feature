Feature: ManageReport Smoke

    @smoke @ManageReport @ManageReport_003
  Scenario: ManageReport_003_Verify user can able to download the report for all kind of status audits
    Given I Logged in with valid credentials for Manage Report
    Then I search for completed audit
    Then I download report
    Then I search for submit for approval audit
    Then I download report
    Then I search for rejected audit
    Then I download report
    Then I search for inprogress audit
    Then I download report
    
    @smoke @ManageReport @ManageReport_004
  Scenario: ManageReport_004_Verify user can able to regenerate the report for all kind of status audits
    Given I Logged in with valid credentials for Manage Report
    Then I search for completed audit
    Then I regenerate report
    Then I search for submit for approval audit
    Then I regenerate report
    Then I search for rejected audit
    Then I regenerate report
    Then I search for inprogress audit
    Then I regenerate report
    
       @smoke @ManageReport @ManageReport_005
  Scenario: ManageReport_005_Verify user can able to delete the audits
    Given I Logged in with valid credentials for Manage Report
    Then I delete report
    
         @smoke @ManageReport @ManageReport_006
  Scenario: ManageReport_006_Verify user can able to restore the audits
    Given I Logged in with valid credentials for Manage Report
    Then I restore report
    
  @smoke @ManageReport @ManageReport_007
  Scenario: ManageReport_007_Verify user can able to search valid data through search box
    Given I Logged in with valid credentials for Manage Report
    Then I search and verify for audit with report name
    
        @smoke @ManageReport @ManageReport_009
  Scenario: ManageReport_009_Verify user can able to approve the submitted for approval audits
    Given I Logged in with valid credentials for Manage Report
    Then I search for submit for approval audit
    Then I change the status to approve and verify
    
           @smoke @ManageReport @ManageReport_010
  Scenario: ManageReport_010_Verify user can able to reject the submitted for approval audits
    Given I Logged in with valid credentials for Manage Report
    Then I search for submit for approval audit
    Then I change the status to reject and verify
    
             @smoke @ManageReport @ManageReport_011
  Scenario: ManageReport_011_Verify user can able to reject the completed audits
    Given I Logged in with valid credentials for Manage Report
    Then I search for completed audit
    Then I change the status from complete to reject and verify
    
               @smoke @ManageReport @ManageReport_012
  Scenario: ManageReport_012_Verify user can able to inprogress the completed audits
    Given I Logged in with valid credentials for Manage Report
    Then I search for completed audit
    Then I change the status from complete to in progress and verify
    