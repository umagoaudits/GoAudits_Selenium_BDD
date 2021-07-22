Feature: Schedule Smoke

@smoke @Schedule @Schedule_015
  Scenario: Schedule_015_Check Create New Title without name and Create New Category with name
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I create new title without name and new category with name and verify error message
    

@smoke @Schedule @Schedule_016
  Scenario: Schedule_016_Check Description text box
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    
    @smoke @Schedule @Schedule_WhatTab_TC004
  Scenario: Schedule_WhatTab_TC04_004_Check Schedule with Audit Name
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    
      @smoke @Schedule @Schedule_WhereTab_TC004_TC007_TC008
  Scenario: Schedule_WhereTab_TC004_Search Select Delete Select Navigate to Who Tab
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I search for site
    Then I select searched site
    Then I delete selected site
    Then I select searched site
    Then I navigate to who tab
    
  @smoke @Schedule @Schedule_WhoTab_TC004_TC007_TC008
  Scenario: Schedule_WhereTab_TC004_Search Select Delete Select Navigate to Who Tab
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I search for site
    Then I select searched site
    Then I navigate to who tab
    Then I search for user
    Then I select searched user
    Then I delete selected user
    Then I select searched user
    Then I navigate to when tab
    
    @smoke @Schedule @Schedule_WhenDailyTab_TC06_TC07_TC08_TC09_TC13_TC19_TC24_TC28_TC30
  Scenario: Schedule_WhenDailyTab_TC06_TC07_TC08_TC09_TC13_TC19_TC24_TC28_TC30
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I select searched site
    Then I navigate to who tab
    Then I select searched user
    Then I navigate to when tab
    Then I select frequency as daily
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with invalid date in start date
    Then I verify with current date in start date
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with current date in end date
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with future date in end date
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with valid timeframe
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with positive End after occurrences
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with valid timeframe
    Then I navigate to Settings tab


  @smoke @Schedule @Schedule_WhenWeeklyTab_TC105_TC106_TC107_TC112_TC115_TC118_TC119_TC120_TC121_TC122_TC123_TC124_TC125_TC137_TC145
  Scenario: Schedule_WhenWeeklyTab_TC105_TC106_TC107_TC112_TC115_TC118_TC119_TC120_TC121_TC122_TC123_TC124_TC125_TC137_TC145
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I select searched site
    Then I navigate to who tab
    Then I select searched user
    Then I navigate to when tab
    Then I select frequency as weekly
    Then I verify with selecting weekdays
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with invalid date in start date
    Then I verify with current date in start date
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with future date in end date
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with valid timeframe
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with positive End after occurrences
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with valid timeframe
    Then I navigate to Settings tab
    
    
     @smoke @Schedule @Schedule_WhenMonthlyTab_TC157_TC161_TC164_TC167_TC168_TC170_TC171_TC175_TC177_TC182_TC188
  Scenario: Schedule_WhenMonthlyTab_TC157_TC161_TC164_TC167_TC168_TC170_TC171_TC175_TC177_TC182_TC188
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I select searched site
    Then I navigate to who tab
    Then I select searched user
    Then I navigate to when tab
    Then I select frequency as monthly
    Then I select monthly last date
    Then I navigate to Settings tab
    Then I navigate back
    Then I verify with positive End after occurrences
    Then I select date of each month
    Then I verify with selecting weekdays
    Then I navigate to Settings tab
    
       @smoke @Schedule @Schedule_SettingsTab_TC05_TC07_TC11_TC12_TC13
  Scenario: Schedule_SettingsTab_TC05_TC07_TC11_TC12_TC13
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I select searched site
    Then I navigate to who tab
    Then I select searched user
    Then I navigate to when tab
    Then I navigate to Settings tab
    Then I verify notification settings Toggle status
    
         @smoke @Schedule @Schedule_EndToEnd_CreateSchedle_Weekly
  Scenario: Schedule_EndToEnd_CreateSchedle_Weekly
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I select searched site
    Then I navigate to who tab
    Then I select searched user
    Then I navigate to when tab
    Then I select frequency as weekly
    Then I set end date for weekly
    Then I set weekdays
    Then I navigate to Settings tab
    Then I click on Create
    Then I verify created schedule

       @smoke @Schedule @Schedule_TC14_TC18_TC26_TC34_TC90_EditSchedle_Weekly
  Scenario: Schedule_TC14_TC18_TC26_TC34_TC90_EditSchedle_Weekly
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I click on edit for weekly schedule
    Then I edit checklist and navigate to where tab
    Then I edit location and navigate to who tab
    Then I edit user and navigate to when tab
    Then I edit when tab and save
    Then I verify edited schedule
    
    
           @smoke @Schedule @Schedule_EndToEnd_CreateSchedle_Daily
  Scenario: Schedule_EndToEnd_CreateSchedle_Daily
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I select searched site
    Then I navigate to who tab
    Then I select searched user
    Then I navigate to when tab
    Then I select frequency as daily
    Then I set end date for daily
    Then I navigate to Settings tab
    Then I click on Create
    Then I verify created schedule

           @smoke @Schedule @Schedule_EndToEnd_CreateSchedle_Monthly
  Scenario: Schedule_EndToEnd_CreateSchedle_Monthly
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I select searched site
    Then I navigate to who tab
    Then I select searched user
    Then I navigate to when tab
    Then I select frequency as monthly
    Then I set end date for monthly
    Then I select selected days and set weekdays
    Then I navigate to Settings tab
    Then I click on Create
    Then I verify created schedule
    
             @smoke @Schedule @Schedule_EndToEnd_CreateSchedle_Yearly
  Scenario: Schedule_EndToEnd_CreateSchedle_Yearly
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I fill the form and navigate to what tab
    Then I navigate to where tab
    Then I select searched site
    Then I navigate to who tab
    Then I select searched user
    Then I navigate to when tab
    Then I select frequency as yearly
    Then I set end date for yearly
    Then I set date and month
    Then I navigate to Settings tab
    Then I click on Create
    Then I verify created schedule
    
  

      @smoke @Schedule @Schedule_Delete 
  Scenario: Schedule_Delete Schedule and verify
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I delete schedule and verify
    
       @smoke @Schedule @Schedule_GroupDelete 
  Scenario: Schedule_Delete Schedule group and verify
    Given I Logged in with valid credentials
    Then I navigated to "Schedule" menu
    Then I delete schedule group and verify
    
    
