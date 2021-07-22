Feature: Users Smoke

  @smoke @Users @Users_009
  Scenario: Users_009_Verify User can able to create account with App access and check whether user can able to login into App,Portal,Analytics
    Given I Logged in with valid credentials
    Then I navigated to "Users" menu
    Then I create user with app access
    Then I Logged out
    Then I verify user is able to access app
    Then I verify user is not able to access portal
    Then I verify user is not able to access analytics
    Then I login and delete user

@smoke @Users @Users_019
  Scenario: Users_019_Verify user can able to pick up a role via custom role dropdown
    Given I Logged in with valid credentials
    Then I navigated to "Users" menu
    Then I create user and assign role
    Then I Logged out
    Then I verify user is able to access portal
    Then I verify company for new user
    Then I verify checklist for new user
    Then I verify location for new user
    Then I Logged out
    Then I login and delete user
    
    @smoke @Users @Users_031
  Scenario: Users_031_Verify updation of assigned roles and tags
    Given I Logged in with valid credentials
    Then I navigated to "Users" menu
    Then I create user with no role
    Then I edit user and assign role
    Then I Logged out
    Then I verify user is able to access portal
    Then I verify company for new user
    Then I verify checklist for new user
    Then I verify location for new user
    Then I Logged out
    Then I login and delete user
    
    @smoke @Users @Users_020
  Scenario: Users_020_Verify User can able to enable the toggle for audit workflow toggle
    Given I Logged in with valid credentials
    Then I navigated to "Users" menu
    Then I create user with app access and workflow
    Then I verify status of workflow

@smoke @Users @Users_022
  Scenario: Users_022_Verify user can able to enable the toggle for Allow additional location from the app Toggle
    Given I Logged in with valid credentials
    Then I navigated to "Users" menu
    Then I create user with portal access and allow additional location
    Then I verify status of allow additional location

@smoke @Users @Users_030
  Scenario: Users_030_Verify user can able to modify the user email address for existing accounts
    Given I Logged in with valid credentials
    Then I navigated to "Users" menu
    Then I verify email field on edit mode
    
    @smoke @Users @Users_037
  Scenario: Users_037_Verify user can able to restore the deleted users from delete tab
    Given I Logged in with valid credentials
    Then I navigated to "Users" menu
    Then I restore deleted user and verify
    
 
    
    
