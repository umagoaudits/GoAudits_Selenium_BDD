Feature: Email Template Smoke

@smoke @EmailTemplate @EmailTemplate_004
  Scenario: EmailTemplate_004_Verify validation messages in Email Template Screen
    Given I Logged in with valid credentials
    Then I navigated to "Email Template" menu
    Then I verify validation message
    
    @smoke @EmailTemplate @EmailTemplate_008
  Scenario: EmailTemplate_008_Verify multiple valid Email addresses
    Given I Logged in with valid credentials
    Then I navigated to "Email Template" menu
    Then I update multiple valid mail id and verify
    
    @smoke @EmailTemplate @EmailTemplate_009
  Scenario: EmailTemplate_009_Verify creation of Email Template with attributes in Subject
    Given I Logged in with valid credentials
    Then I navigated to "Email Template" menu
    Then I verify creation of email template with attributes in subject
    
    @smoke @EmailTemplate @EmailTemplate_010
  Scenario: EmailTemplate_010_Verify creation of Email Template with attributes in Message
    Given I Logged in with valid credentials
    Then I navigated to "Email Template" menu
    Then I verify creation of email template with attributes in message
    
    
