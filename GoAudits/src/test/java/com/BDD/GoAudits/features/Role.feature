Feature: Role Smoke

  @smoke @Role @Role_009
  Scenario: Role_009_Verify user can able to create admin role with valid data
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I create role with permissions and verify
    
    @smoke @Role @Role_010
  Scenario: Role_010_Verify user can able to create Auditor role with valid data
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I create role with permissions and verify
    
      @smoke @Role @Role_013
  Scenario: Role_013_Verify user can able to delete existing role
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I delete role and verify
    
        @smoke @Role @Role_014
  Scenario: Role_014_Verify user can able to delete role which is already assigned to user
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I delete role which is already assigned to user and verify
    
        @smoke @Role @Role_017
  Scenario: Role_017_Verify User can able to edit to change the assigned permissions for a role
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I edit the assigned permissions and verify
    
          @smoke @Role @Role_024
  Scenario: Role_024_Verify user can able to rename the existing role name
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I edit the name of role and verify
    
            @smoke @Role @Role_025
  Scenario: Role_025_Verify user can able to delete the Permission which are assigned to role
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I edit the role and delete permissions and verify
    
         @smoke @Role @Role_028
  Scenario: Role_028_Verify Creating a admin role without Portal permission
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I create role with permissions and verify

           @smoke @Role @Role_015
  Scenario: Role_015_Verify user can able to inactive role From Add role page
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I inactive role from add role page and verify
    
             @smoke @Role @Role_029
  Scenario: Role_029_Verify creating a auditor role without App permission
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I create role with permissions and verify
    
               @smoke @Role @Role_030
  Scenario: Role_030_Verify creating a admin role with Analytics permission only
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I create role with permissions and verify
    
                @smoke @Role @Role_031
  Scenario: Role_031_Verify creating a auditor  role with custom permissions having Portal access and selective checklist
    Given I Logged in with valid credentials
    Then I navigated to "Advanced-Roles & Hierarchy" menu
    Then I create role with permissions and verify
    
    