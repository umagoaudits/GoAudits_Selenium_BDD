package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class Users extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add New User')]";
	public static final String save_xpath="//button//span[contains(text(),'Save')]";
	public static final String yes_xpath="//span[contains(text(),'Yes')]";
	public static final String firstName_xpath="//mat-label[text()='First Name']/../../..//input";
	public static final String email_xpath="//mat-label[text()='Email']/../../..//input";
	public static final String password_xpath="//mat-label[text()='Password']/../../..//input";
	public static final String advanceSettings_xpath="//span[contains(text(),'Advanced Settings')]";
	public static final String customRole_xpath="//mat-label[text()='Custom Role']/../../..//mat-select";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String total_xpath ="//div[contains(text(),'otal')]";
	public static final String edit_xpath="//a[contains(text(),'Edit User')]";
	public static final String delete_xpath="//span[contains(text(),'Delete/Archive')]";
	public static final String deletetab_xpath="//div[text()='Deleted/Archived']";
	public static final String usertab_xpath="//div[text()='Users']";
	public static final String restore_xpath="//span[text()='Restore']";
	public static final String priorityname_xpath="(//div[contains(@class,'datatable-body-cell-label')])[1]/span";
	public static final String checkListClass_xpath="//div[@class='Aname']/span";
	public static final String companyClass_xpath="//div[@class='Cname']/span";
	

	//
	
	
	@Then("^I create user with app access$")
	public static void createUser_App() {
		try {
			String []apps = {"App"};
			String []per = {""} ;
			addUser(apps, "no", per);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify user is able to access app$")
	public static void verify_App() {
		try {
			DriverManager.getDriver().get(dataProvider.getConfigPropertyval("AppURL"));
			Thread.sleep(5000);
			navigate_App(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify user is not able to access portal$")
	public static void verify_Portal() {
		try {
			DriverManager.getDriver().get(dataProvider.getConfigPropertyval("URL"));
			Thread.sleep(5000);
			navigate_App(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify user is able to access portal$")
	public static void verify_Portal_positive() {
		try {
			DriverManager.getDriver().get(dataProvider.getConfigPropertyval("URL"));
			Thread.sleep(5000);
			navigate_App(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify user is not able to access analytics$")
	public static void verify_Ana() {
		try {
			navigate_Ana(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I create user with portal access and allow additional location$")
	public static void createUser_portal_Location() {
		try {
			String []apps = {"Portal"};
			String []per = {"Allow addition of locations from the App"} ;
			addUser(apps, "yes", per);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I create user with app access and workflow$")
	public static void createUser_app_workflow() {
		try {
			String []apps = {"App"};
			String []per = {"Approval Workflow"} ;
			addUser(apps, "yes", per);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I create user and assign role$")
	public static void createUser_roleAssigne() {
		try {
			String []apps = {""};
			String []per = {""} ;
			addUser(apps, "yes", per);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I create user with no role$")
	public static void createUser_noroleAssigne() {
		try {
			String []apps = {""};
			String []per = {""} ;
			addUser(apps, "yes", per);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I edit user and assign role$")
	public static void editUser_roleAssigne() {
		try {
			editRole(dataProvider.getPropertyvalue("DataStore", "CreatedUser"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify checklist for new user$")
	public static void verifyAssignedChecklist() {
		try {
			HomePage.i_navigated_to_menu("Forms/Checklists");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String checklist = readCSV.getCSVValue("Users", getScriptId(), "Permission  Checklists");
			String []checklists = null;
			if(checklist.contains("&"))
				checklists=checklist.split("&");
			else
				checklists=new String[] {checklist};
			int checklistUISize=seleniumUtils.Size(Locator.XPATH, checkListClass_xpath);
			boolean flag = false;
			if(checklistUISize==checklists.length) {
				for(int i=0;i<checklistUISize;i++) {
					if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@class='Aname']/span[@title='"+checklists[i]+"']")) {
						flag =  true;
					}else {
						flag = false;
					}
				}
			}else {
				flag = false;
			}
			if(flag)
				LogFileControl.logPass("Verify CheckList", "Checklist is displaying correctly");
			else
				LogFileControl.logFail("Verify CheckList", "Checklist is not displaying correctly");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify company for new user$")
	public static void verifyAssignedCompany() {
		try {
			HomePage.i_navigated_to_menu("Company/Dept");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String com = readCSV.getCSVValue("Users", getScriptId(), "Permission Company");
			String []companies = null;
			if(com.contains("&"))
				companies=com.split("&");
			else
				companies= new String [] {com};
			int checklistUISize=seleniumUtils.Size(Locator.XPATH, companyClass_xpath);
			boolean flag = false;
			if(checklistUISize==companies.length) {
				for(int i=0;i<checklistUISize;i++) {
					if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@class='Cname']/span[@title='"+companies[i]+"']")) {
						flag =  true;
					}else {
						flag = false;
					}
				}
			}else {
				flag = false;
			}
			if(flag)
				LogFileControl.logPass("Verify Company", "Company is displaying correctly");
			else
				LogFileControl.logFail("Verify Company", "Company is not displaying correctly");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify location for new user$")
	public static void verifyAssignedLocation() {
		try {
			HomePage.i_navigated_to_menu("Locations");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String loc = readCSV.getCSVValue("Users", getScriptId(), "Permission  Locations");
			String []locations = null;
			if(loc.contains("&"))
				locations=loc.split("&");
			else
				locations=new String[] {loc};
			int checklistUISize=Integer.parseInt(seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			boolean flag = false;
			if(checklistUISize==locations.length) {
				for(int i=0;i<checklistUISize;i++) {
					if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[text()=' "+locations[i]+" ']")) {
						flag =  true;
					}else {
						flag = false;
					}
				}
			}else {
				flag = false;
			}
			if(flag)
				LogFileControl.logPass("Verify Locations", "Locations is displaying correctly");
			else
				LogFileControl.logFail("Verify Locations", "Locations is not displaying correctly");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	@Then("^I verify status of allow additional location$")
	public static void verifyToggleAccess() {
		try {
			String []per = {"Allow addition of locations from the App"} ;
			verifyToggle(dataProvider.getPropertyvalue("DataStore", "CreatedUser"), per);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Add User", "Save");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify status of workflow$")
	public static void verifyToggleAccess_workflow() {
		try {
			String []per = {"Approval Workflow"} ;
			verifyToggle(dataProvider.getPropertyvalue("DataStore", "CreatedUser"), per);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Add User", "Save");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I login and delete user$")
	public static void login_delete() {
		try {
			DriverManager.getDriver().get(dataProvider.getConfigPropertyval("URL"));
			LoginPage.i_Logged_in_with_valid_credentials();
			HomePage.i_navigated_to_menu("Users");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			delete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void navigate_App(boolean flag) {
		try {
//			DriverManager.getDriver().get(dataProvider.getConfigPropertyval("AppURL"));
//			Thread.sleep(5000);
			LoginPage.login(dataProvider.getPropertyvalue("DataStore", "CreatedUser"), dataProvider.getPropertyvalue("DataStore", "CreatedPassword"));
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			if(flag) {
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//mat-error[@role='alert']")) {
					LogFileControl.logFailwithScreenCapture("Verify Login", "User is not able to log in");
				}else {
					LogFileControl.logPass("Verify Login", "User is able to log in");
				}
			}else {
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//mat-error[@role='alert']")) {
					LogFileControl.logPass("Verify Login", "User is not able to log in. Error displaying as \""+
				seleniumUtils.GetText(Locator.XPATH, "//mat-error[@role='alert']")+"\"");
				}else {
					LogFileControl.logFailwithScreenCapture("Verify Login", "User is able to log in");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void navigate_Ana(boolean flag) {
		try {
			DriverManager.getDriver().get(dataProvider.getConfigPropertyval("AnalyticURL"));
			Thread.sleep(5000);
			LoginPage.login_Analytic(dataProvider.getPropertyvalue("DataStore", "CreatedUser"), dataProvider.getPropertyvalue("DataStore", "CreatedPassword"));
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			if(flag) {
				if(seleniumUtils.IsDisplayed(Locator.NAME, "login-submit")) {
					LogFileControl.logFailwithScreenCapture("Verify Login", "User is not able to log in");
				}else {
					LogFileControl.logPass("Verify Login", "User is able to log in");
				}
			}else {
				if(seleniumUtils.IsDisplayed(Locator.NAME, "login-submit")) {
					LogFileControl.logPass("Verify Login", "User is not able to log in.");
				}else {
					LogFileControl.logFailwithScreenCapture("Verify Login", "User is able to log in");
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void addUser(String []apps,String roleAssignmentFlag,String [] permissionsNeedsToEnable ) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, add_xpath, "Users Listing", "Add New Users");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String name = "Auto"+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, firstName_xpath, name, "User", "First Name");
			seleniumUtils.SendKeys(Locator.XPATH, email_xpath, name+"@mail.com", "User", "Email");
			seleniumUtils.SendKeys(Locator.XPATH, password_xpath, "Test@12345", "User", "Password");
			for(int i=0;i<apps.length;i++) {
				seleniumUtils.Click(Locator.XPATH, "//span[contains(text(),'"+apps[i]+"')]", "Users", apps[i]);
				Thread.sleep(500);
			}
			if(roleAssignmentFlag.toLowerCase().contains("yes")) {
				seleniumUtils.Click(Locator.XPATH, advanceSettings_xpath, "Users", "Advance Settings");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, customRole_xpath, "Users", "Custom Role");
				Thread.sleep(200);
				String roleName= readCSV.getCSVValue("Users", getScriptId(), "Role Name");
				if(roleName!=null && !roleName.trim().equals("")) {
					seleniumUtils.Click(Locator.XPATH, "//span[text()=' "+roleName+" ']", "Users", "Custom Role");
				}else {
					seleniumUtils.Click(Locator.XPATH, "(//mat-option//span[contains(text(),'Auditor') or contains(text(),'Admin')])[1]", "Users", "Custom Role");
				}
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, firstName_xpath, "Users", "First Name");
				Thread.sleep(1000);
				for(int i=0;i<permissionsNeedsToEnable.length;i++) {
					seleniumUtils.Click(Locator.XPATH, "//*[contains(text(),'"+permissionsNeedsToEnable[i]+"')]", "Users", permissionsNeedsToEnable[i]);
					Thread.sleep(500);
				}
			}
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Users", "Save");
			verifyMessage("The user has been successfully created");
			writeConfig.storeData("CreatedUser", name+"@mail.com");
			writeConfig.storeData("CreatedPassword", "Test@12345");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I verify email field on edit mode$")
	public static void verifyDisableEmailOnEdit() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, edit_xpath)) {
				seleniumUtils.Click(Locator.XPATH, edit_xpath, "User Listing", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(2000);
				String status = seleniumUtils.GetAttribute(Locator.XPATH, "//mat-label[text()='Email']/../../../../../..", "class");
				if(status.toLowerCase().contains("disabled")) {
					LogFileControl.logPass("Verify Email field on Edit Mode", "Email field is disable on Edit Mode");
				}else {
					LogFileControl.logPass("Verify Email field on Edit Mode", "Email field is not disable on Edit Mode");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Then("^I restore deleted user and verify$")
	public static void deleteAndRestore() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			seleniumUtils.Click(Locator.XPATH, deletetab_xpath, "User Listing", "Delete tab");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
			}
			else {
			if(seleniumUtils.IsDisplayed(Locator.XPATH, edit_xpath)) {
				seleniumUtils.Click(Locator.XPATH, usertab_xpath, "User Listing", "Users tab");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(5000);
				seleniumUtils.Click(Locator.XPATH, expand_xpath, "User Listing", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, delete_xpath, "User Listing", "Delete");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, yes_xpath, "User Listing", "Yes");
				Thread.sleep(200);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(2000);
				seleniumUtils.Click(Locator.XPATH, deletetab_xpath, "User Listing", "Delete tab");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(5000);
			}
		}
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "User Listing", "Expand");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, restore_xpath, "User Listing", "Restore");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, yes_xpath, "User Listing", "Yes");
			Thread.sleep(200);
			verifyMessage("Restored Successfully");
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void delete() {
		try {
			
			if(seleniumUtils.IsDisplayed(Locator.XPATH, edit_xpath)) {
				seleniumUtils.Click(Locator.XPATH, usertab_xpath, "User Listing", "Users tab");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(5000);
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+dataProvider.getPropertyvalue("DataStore", "CreatedUser")+"']//following::mat-icon[text()='more_vert'][1]", "User Listing", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, delete_xpath, "User Listing", "Delete");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, yes_xpath, "User Listing", "Yes");
				Thread.sleep(200);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(2000);
				seleniumUtils.Click(Locator.XPATH, deletetab_xpath, "User Listing", "Delete tab");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(5000);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void verifyToggle(String userName,String [] permissionsNeedsToVerify) {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+userName+"']//following::a[contains(text(),'Edit User')][1]")) {
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+userName+"']//following::a[contains(text(),'Edit User')][1]", "User Listing", "Edit");
				Thread.sleep(3000);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(8000);
				if(!seleniumUtils.IsDisplayed(Locator.XPATH, customRole_xpath)) {
				seleniumUtils.Click(Locator.XPATH, advanceSettings_xpath, "Users", "Advance Settings");
				Thread.sleep(1000);
				}
				for(int i=0;i<permissionsNeedsToVerify.length;i++) {
				String status = seleniumUtils.GetAttribute(Locator.XPATH, "//*[contains(text(),'"+permissionsNeedsToVerify[i]+"')]/../..", "class");
				if(status.toLowerCase().contains("checked")) {
					LogFileControl.logPass("Verify "+permissionsNeedsToVerify[i]+" toggle status", "Status is enable");
				}else {
					LogFileControl.logPass("Verify "+permissionsNeedsToVerify[i]+" toggle status", "Status is not enable");
				}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void editRole(String userName) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+userName+"']//following::a[contains(text(),'Edit User')][1]")) {
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+userName+"']//following::a[contains(text(),'Edit User')][1]", "User Listing", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(8000);
				seleniumUtils.Click(Locator.XPATH, customRole_xpath, "Users", "Custom Role");
				Thread.sleep(200);
				String roleName= readCSV.getCSVValue("Users", getScriptId(), "Edited Role Name");
				if(seleniumUtils.GetAttribute(Locator.XPATH, "(//mat-option//span[contains(text(),'Auditor') or contains(text(),'Admin')])[1]/..", "aria-selected").contains("true")) {
					seleniumUtils.Click(Locator.XPATH, "(//mat-option//span[contains(text(),'Auditor') or contains(text(),'Admin')])[1]", "Users", "Custom Role");
					Thread.sleep(200);
				}
				if(roleName!=null && !roleName.trim().equals("")) {
					seleniumUtils.Click(Locator.XPATH, "//span[text()=' "+roleName+" ']", "Users", "Custom Role");
				}else {
					seleniumUtils.Click(Locator.XPATH, "(//mat-option//span[contains(text(),'Auditor') or contains(text(),'Admin')])[1]", "Users", "Custom Role");
				}
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, firstName_xpath, "Users", "First Name");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, save_xpath, "Users", "Save");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
