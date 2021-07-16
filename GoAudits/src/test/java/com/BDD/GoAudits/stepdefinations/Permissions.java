package com.BDD.GoAudits.stepdefinations;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class Permissions extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add')]";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String selectLocationMul_xpath="(//div[text()='Select Locations']/..//span[@class='mat-checkbox-label'])[1]";
	public static final String selectLocationSingle_xpath="(//div[text()='Select Locations']/..//span[@class='mat-checkbox-label'])[2]";
	public static final String selectCheckListMul_xpath="(//div[text()='Select Checklists']/..//span[@class='mat-checkbox-label'])[1]";
	public static final String selectCheckListSingle_xpath="(//div[text()='Select Checklists']/..//span[@class='mat-checkbox-label'])[2]";
	public static final String name_xpath="//mat-label[text()='Permission Name']/../../../input";
	public static final String group_xpath="//mat-label[text()='Permission Group']/../../../input";
	public static final String listFeature_xpath="//span[contains(text(),'Permission List-Features')]";
	public static final String groupDropdown_xpath="//span[contains(text(),'Permission Group')]";
	public static final String selectsingle_xpath="(//span[@class='mat-checkbox-label'])[1]";
	public static final String yes_xpath="//span[contains(text(),'Yes')]";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String edit_xpath="//button//span[contains(text(),'Edit')]";
	public static final String delete_xpath="//span[contains(text(),'Delete/Archive')]";
	public static final String restore_xpath="//span[text()='Restore']";
	public static final String total_xpath ="//div[contains(text(),'total')]";



	@Then("^I add permissions with out mandatory fields and verify error message$")
	public static void addPermissionwithoutmandatefieldandVerify() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Permissions", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Permissions", "Save");
			verifyMessage("Please provide a permission list name");
			seleniumUtils.SendKeys(Locator.XPATH, name_xpath, " ", "Permissions", "Permission Name");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Permissions", "Save");
			verifyMessage("Please provide a permission list name");
			seleniumUtils.SendKeys(Locator.XPATH, name_xpath, "fgghj", "Permissions", "Permission Name");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Permissions", "Save");
			verifyMessage("Please select at least one location from the list");
			seleniumUtils.Click(Locator.XPATH, selectLocationSingle_xpath, "Permissions", "Select Location");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Permissions", "Save");
			verifyMessage("Please select at least one checklist name from the list");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Then("^I add permissions List data and verify$")
	public static void addpermission_PermissionListData() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			String locationMultiple= readCSV.getCSVValue("Permissions", getScriptId(), "Location Multiple");
			String checklistMultiple= readCSV.getCSVValue("Permissions", getScriptId(), "Checklist Multiple");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Permissions", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, save_xpath);
			String name = "List"+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, name_xpath, name, "Permissions", "Permission Name");
			if(locationMultiple!=null && !locationMultiple.trim().equals("") && locationMultiple.toLowerCase().contains("yes")) {
				seleniumUtils.Click(Locator.XPATH, selectLocationMul_xpath, "Permissions", "Location");

			}
			else {
				seleniumUtils.Click(Locator.XPATH, selectLocationSingle_xpath, "Permissions", "Location");
			}
			if(checklistMultiple!=null && !checklistMultiple.trim().equals("") && checklistMultiple.toLowerCase().contains("yes")) {
				seleniumUtils.Click(Locator.XPATH, selectCheckListMul_xpath, "Permissions", "CheckList");

			}
			else {
				seleniumUtils.Click(Locator.XPATH, selectCheckListSingle_xpath, "Permissions", "CheckList");
			}

			seleniumUtils.Click(Locator.XPATH, save_xpath, "Permissions", "Save");
			verifyMessage("The permission name has been successfully created");

			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+name+"']")) {
				LogFileControl.logPass("Add Permissions", "Permissions added successfully");
			}else {
				LogFileControl.logFail("Add Permissions", "Permissions didn't added successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Then("^I edit permissions List data and verify$")
	public static void editpermission_PermissionListData() {
		editpermission("Data");
	}

	@Then("^I add permissions List Features and verify$")
	public static void addpermission_PermissionListFeature() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Permissions", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, save_xpath);
			seleniumUtils.Click(Locator.TAGNAME, "mat-select", "Permissions", "Permission Type");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, listFeature_xpath, "Permissions", "Permission Type");
			Thread.sleep(2000);
			String name = "Feature"+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, name_xpath, name, "Permissions", "Permission Name");
			seleniumUtils.Click(Locator.XPATH, selectsingle_xpath, "Permissions", "Select Feature");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Permissions", "Save");
			verifyMessage("Saved successfully");

			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+name+"']")) {
				LogFileControl.logPass("Add Permissions", "Permissions added successfully");
			}else {
				LogFileControl.logFail("Add Permissions", "Permissions didn't added successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Then("^I edit permissions List Features and verify$")
	public static void editpermission_PermissionListFeature() {
		editpermission("Feature");
	}
	
	@Then("^I add permissions Group and verify$")
	public static void addpermission_PermissionGroup() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Permissions", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, save_xpath);
			seleniumUtils.Click(Locator.TAGNAME, "mat-select", "Permissions", "Permission Type");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, groupDropdown_xpath, "Permissions", "Permission Type");
			Thread.sleep(2000);
			String name = "Group"+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, group_xpath, name, "Permissions", "Permission Name");
			seleniumUtils.Click(Locator.XPATH, selectsingle_xpath, "Permissions", "Select Feature");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Permissions", "Save");
			verifyMessage("The permission group has been successfully created");

			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+name+"']")) {
				LogFileControl.logPass("Add Permissions", "Permissions added successfully");
			}else {
				LogFileControl.logFail("Add Permissions", "Permissions didn't added successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I edit permissions Group and verify$")
	public static void editpermission_PermissionGroup() {
		editpermission("Group");
	}
	
	public static void editpermission(String permissionType) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			String xpath = null;
			if(permissionType.equals("Data")) {
				if(!seleniumUtils.IsDisplayed(Locator.XPATH, "(//span[contains(text(),'Data')]//following::mat-icon[text()='more_vert'])[1]"))
					addpermission_PermissionListData();
				xpath=name_xpath;
			}
			if(permissionType.equals("Feature")) {
				if(!seleniumUtils.IsDisplayed(Locator.XPATH, "(//span[contains(text(),'Feature')]//following::mat-icon[text()='more_vert'])[1]"))
					addpermission_PermissionListFeature();
				xpath=name_xpath;
			}
			if(permissionType.equals("Group")) {
				if(!seleniumUtils.IsDisplayed(Locator.XPATH, "(//span[contains(text(),'Group')]//following::mat-icon[text()='more_vert'])[1]"))
					addpermission_PermissionGroup();
				xpath=group_xpath;
			}
			seleniumUtils.Click(Locator.XPATH, "(//span[contains(text(),'"+permissionType+"')]//following::mat-icon[text()='more_vert'])[1]", "Permissions", "xpand");
			Thread.sleep(500);
			seleniumUtils.MoveToElement(Locator.XPATH, edit_xpath);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Permissions", "Edit");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, save_xpath);
			String name = "Edit"+suppotLibrary.getCurrentTime();
			seleniumUtils.Clear(Locator.XPATH, xpath);
			seleniumUtils.SendKeys(Locator.XPATH, xpath, name, "Permissions", "Permission Name");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Permissions", "Save");
			verifyMessage("The permission has been successfully updated");

			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+name+"']")) {
				LogFileControl.logPass("Edit Permissions", "Permissions edited successfully");
			}else {
				LogFileControl.logFail("Edit Permissions", "Permissions didn't edited successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I delete permissions and verify$")
	public static void deletePermission() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {}
			else {
				addpermission_PermissionGroup();
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
			}
			int oldc=Integer.parseInt(seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Permissions", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, delete_xpath, "Permissions", "Delete");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, yes_xpath, "Permissions", "Yes");
			verifyMessage("The permission has been successfully archived");
			DriverManager.getDriver().navigate().refresh();
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			int newc=0;
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				newc=Integer.parseInt(seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			}
			if(oldc-1==newc) {
				LogFileControl.logPass("Delete Permissions", "Permissions deleted successfully");
			}else {
				LogFileControl.logFail("Delete Permissions", "Permissions not deleted successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

	



}
