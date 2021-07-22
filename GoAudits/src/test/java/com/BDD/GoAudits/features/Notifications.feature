Feature: Notifications Smoke

@smoke @Notifications @Notifications_006
  Scenario: Notifications_006_Verify creation of Daily Add Action Notification
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I add notification
    Then I verify notification added successfully

    
    @smoke @Notifications @Notifications_007
  Scenario: Notifications_007_Verify creation of Weekly Add Action Notification
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I add notification
    Then I verify notification added successfully
    
      @smoke @Notifications @Notifications_010
  Scenario: Notifications_010_Verify updation of Daily Action Task in Action Task tab
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I update notification
    Then I verify notification updated successfully
    
      @smoke @Notifications @Notifications_011
  Scenario: Notifications_011_Verify updation of Weeky Action Task in Action Task tab
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I update notification
    Then I verify notification updated successfully
    
     @smoke @Notifications @Notifications_014
  Scenario: Notifications_014_Verify deletion of Daily Action Task in Action Task tab
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I delete notification and verify
    
      @smoke @Notifications @Notifications_015
  Scenario: Notifications_015_Verify deletion of Weekly Action Task in Action Task tab
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I delete notification and verify
    
    @smoke @Notifications @Notifications_020
  Scenario: Notifications_020_Verify creation of Action notifications with different company
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I add notification
    Then I verify notification added successfully
    
     @smoke @Notifications @Notifications_036
  Scenario: Notifications_036_Verify Daily creation of multiple Summay notifications with same company and checklist with different time
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I add multiple notification and verify
    
        @smoke @Notifications @Notifications_037
  Scenario: Notifications_037_Verify Weekly creation of multiple Summay notifications with same company and checklist with different time
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Notifications" menu
    Then I add multiple notification and verify
