package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class Role extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add')]";
	public static final String save_xpath="//button//span[contains(text(),'Save')]";
	public static final String popupsave_xpath="(//button//span[contains(text(),'Save')])[2]";
	public static final String yes_xpath="//span[contains(text(),'Yes')]";
	public static final String roleName_xpath="//input[@formcontrolname='role_name']";
	public static final String roleType_xpath="//mat-select[@formcontrolname='role_type']";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String edit_xpath="//button//span[contains(text(),'Edit')]";
	public static final String delete_xpath="//span[contains(text(),'Delete/Archive')]";
	public static final String assignedRoleName_xpath="//i[contains(@class,'trash')]/../../../div[1]";
	public static final String assignedRoleDelete_xpath="//i[contains(@class,'trash')]";
	public static final String active_xpath="//span[contains(text(),'Active')]";
	public static final String archived_xpath="//span[contains(text(),'Archived')]";


	//


	@Then("^I create role with permissions and verify$")
	public static void createUser_App() {
		try {
			String role = readCSV.getCSVValue("Role", getScriptId(), "Role Type");
			String permissions = readCSV.getCSVValue("Role", getScriptId(), "Permissions");
			addRole(role, permissions);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I delete role and verify$")
	public static void deleteRole() {
		try {
			String data = dataProvider.getPropertyvalue("DataStore", "CreatedRole");
			if(data!=null && !data.equals(""))
				deleteRole(data, "The role has been successfully archived");
			else {
				addRole("Admin", "App");
				deleteRole();
			}
			writeConfig.storeData("CreatedRole", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I edit the assigned permissions and verify$")
	public static void editPermission_role() {
		try {
			String data = dataProvider.getPropertyvalue("DataStore", "CreatedRole");
			if(data!=null && !data.equals(""))
				editPermission_Role(data, readCSV.getCSVValue("Role", getScriptId(), "Permissions"));
			else {
				addRole("Admin", "App");
				editPermission_role();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I edit the name of role and verify$")
	public static void editName_role() {
		try {
			String data = dataProvider.getPropertyvalue("DataStore", "CreatedRole");
			if(data!=null && !data.equals(""))
				editName_Role(data);
			else {
				addRole("Admin", "App");
				editName_role();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I edit the role and delete permissions and verify$")
	public static void deletePermission_role() {
		try {
			String data = dataProvider.getPropertyvalue("DataStore", "CreatedRole");
			if(data!=null && !data.equals(""))
				deletePermission_Role(data);
			else {
				addRole("Admin", "App");
				deletePermission_role();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I inactive role from add role page and verify$")
	public static void inactiveRole_fromEditPage() {
		try {
			String data = dataProvider.getPropertyvalue("DataStore", "CreatedRole");
			if(data!=null && !data.equals(""))
				edit_deactive_Role(data);
			else {
				addRole("Admin", "App");
				inactiveRole_fromEditPage();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I delete role which is already assigned to user and verify$")
	public static void deleteAssignedRole() {
		try {
			deleteRole(readCSV.getCSVValue("Role", getScriptId(), "Role Name"), "This role is already assign to a user, so you cannot delete it now");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void addRole(String roleType, String permissions ) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, add_xpath, "Users Listing", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String name = roleType+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, roleName_xpath, name, "Role", "Role Name");
			seleniumUtils.Click(Locator.XPATH, roleType_xpath, "Role", "Role Type");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, "//mat-option//span[contains(text(),'"+roleType+"')]", "Role", "Role Type");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, add_xpath, "Role", "Add role permission");
			Thread.sleep(5000);
			String []permissionsList = null;
			if(permissions.contains("&")) {
				permissionsList=permissions.split("&");
			}else {
				permissionsList = new String [] {permissions};
			}
				
			for(int i=0;i<permissionsList.length;i++) {
				seleniumUtils.Click(Locator.XPATH, "//span[@class='mat-checkbox-label'  and contains(text(),'"+permissionsList[i]+"')]", "Role", permissionsList[i]);
				Thread.sleep(500);
			}
			seleniumUtils.Click(Locator.XPATH, popupsave_xpath, "Role", "Save");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Role", "Save");
			verifyMessage("The role has been successfully created");
			writeConfig.storeData("CreatedRole", name);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			verifyRecordNameDisplaying(name, "Add Role");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void deleteRole(String roleName, String expextedMessage) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(findRecord(roleName)) {
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+roleName+"']//following::mat-icon[text()='more_vert'][1]", "Role", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, delete_xpath, "Role", "Delete");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, yes_xpath, "Role", "Yes");
				Thread.sleep(200);
				verifyMessage(expextedMessage);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void verifyToggle(String userName,String [] permissionsNeedsToVerify) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(findRecord(userName)) {
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+userName+"']//following::a[contains(text(),'Edit User')][1]", "User Listing", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(8000);
				//				seleniumUtils.Click(Locator.XPATH, advanceSettings_xpath, "Users", "Advance Settings");
				//				Thread.sleep(1000);
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

	public static void editName_Role(String roleName) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(findRecord(roleName)) {
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+roleName+"']//following::mat-icon[text()='more_vert'][1]", "Role", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, edit_xpath, "Role", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				String name = "Edited"+suppotLibrary.getCurrentTime();
				seleniumUtils.Clear(Locator.XPATH, roleName_xpath);
				seleniumUtils.SendKeys(Locator.XPATH, roleName_xpath, name, "Role", "Role Name");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, save_xpath, "Role", "Save");
//				verifyMessage("The role has been successfully updated");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				verifyRecordNameDisplaying(name, "Edit Role");
				writeConfig.storeData("CreatedRole", name);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void editPermission_Role(String roleName,String permissions) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(findRecord(roleName)) {
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+roleName+"']//following::mat-icon[text()='more_vert'][1]", "Role", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, edit_xpath, "Role", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Role", "Add permission");
				Thread.sleep(3000);
				String [] permissionsList = null;
				if(permissions.contains("&")) {
					permissionsList=permissions.split("&");
				}else
					permissionsList=new String[] {permissions};
				for(int i=0;i<permissionsList.length;i++) {
					if(seleniumUtils.GetAttribute(Locator.XPATH, "//span[@class='mat-checkbox-label'  and contains(text(),'"+permissionsList[i]+"')]//preceding-sibling::div/input", "aria-checked").contains("false"))
						seleniumUtils.Click(Locator.XPATH, "//span[@class='mat-checkbox-label'  and contains(text(),'"+permissionsList[i]+"')]", "Role", permissionsList[i]);
					Thread.sleep(500);
				}
				seleniumUtils.Click(Locator.XPATH, popupsave_xpath, "Role", "Save");
				Thread.sleep(500);
				seleniumUtils.Click(Locator.XPATH, save_xpath, "Role", "Save");
				verifyMessage("The role has been successfully updated");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				findRecord(roleName);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+roleName+"']//following::button[contains(text(),'View Permissions')][1]", "Role", "View Permission");
				Thread.sleep(2000);
				boolean flag = true;
				for(int i=0;i<permissionsList.length;i++) {
					if(seleniumUtils.IsDisplayed(Locator.XPATH, "//p[contains(text(),'"+permissionsList[i]+"')]"))
						flag=true;
					else
						flag=false;
				}
				if(flag)
					LogFileControl.logPass("Edit Role", "Newly updated permissions displaying");
				else 
					LogFileControl.logFail("Edit Role", "Newly updated permissions is not displaying");


			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void deletePermission_Role(String roleName) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(findRecord(roleName)) {
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+roleName+"']//following::mat-icon[text()='more_vert'][1]", "Role", "Expand");
				Thread.sleep(500);
				seleniumUtils.Click(Locator.XPATH, edit_xpath, "Role", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				String permission = seleniumUtils.GetText(Locator.XPATH, assignedRoleName_xpath);
				seleniumUtils.Click(Locator.XPATH, assignedRoleDelete_xpath, "Role", "Delete Role");
				verifyMessage("The roles permission list has been successfully deleted");
				Thread.sleep(500);
				seleniumUtils.Click(Locator.XPATH, save_xpath, "Role", "Save");
				verifyMessage("The role has been successfully updated");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				findRecord(roleName);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+roleName+"']//following::button[contains(text(),'View Permissions')][1]", "Role", "View Permission");
				Thread.sleep(2000);
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//p[contains(text(),'"+permission+"')]"))
					LogFileControl.logFail("Edit Role", "Deleted permissions displaying");
				else 
					LogFileControl.logPass("Edit Role", "Deleted permissions is not displaying");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	public static void edit_deactive_Role(String roleName) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(findRecord(roleName)) {
				seleniumUtils.Click(Locator.XPATH, "//span[@title='"+roleName+"']//following::mat-icon[text()='more_vert'][1]", "Role", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, edit_xpath, "Role", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				seleniumUtils.Click(Locator.XPATH, active_xpath, "Role", "Active");
				Thread.sleep(1500);
				if(seleniumUtils.IsDisplayed(Locator.XPATH, archived_xpath))
					LogFileControl.logPass("Verify Active button", "Active button changed to \"Archived\"");
				else 
					LogFileControl.logFail("Verify Active button", "Active button is not changed to \"Archived\"");

				seleniumUtils.Click(Locator.XPATH, save_xpath, "Role", "Save");
				verifyMessage("The role has been successfully updated");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
//				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+roleName+"']"))
//					LogFileControl.logFail("Edit Role", "Archived role displaying");
//				else 
//					LogFileControl.logPass("Edit Role", "Archived role is not displaying");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
