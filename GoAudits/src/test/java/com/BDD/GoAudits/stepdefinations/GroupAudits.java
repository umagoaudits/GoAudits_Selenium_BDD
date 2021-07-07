package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class GroupAudits extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add')]";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String saveonPopUp_xpath="(//span[contains(text(),'Save')])[2]";
	public static final String subTitle_xpath="//mat-label[text()='Sub Title']/../../../input";
	public static final String name_xpath="//mat-label[text()='Name']/../../../input";
	public static final String addlogo_xpath="//div[text()='Add logo']";
	public static final String checklist_xpath="//mat-select[@role ='listbox' and @aria-multiselectable='true' ]";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String total_xpath ="//div[contains(text(),'total')]";
	public static final String edit_xpath="//span[contains(text(),'Edit')]";
	public static final String delete_xpath="//span[contains(text(),'Delete/Archive')]";
	public static final String deletetab_xpath="//div[text()='Archived/Deleted']";
	public static final String audittab_xpath="//div[text()='Group Audit']";
	public static final String restore_xpath="//span[text()='Restore']";
	public static final String search_xpath="//input[@placeholder='Search']";
	public static final String nodata_xpath="//div[contains(text(),'No data to display')]";
	public static final String auditname_xpath="(//div[contains(@class,'datatable-body-cell-label')])[2]/span";
	

	@Then("^I add group audit with out name and verify error message$")
	public static void addAuditwithoutNameandVerify() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Group Audit", "Add");
			Thread.sleep(2000);
			seleniumUtils.Clear(Locator.XPATH, name_xpath);
			suppotLibrary.uploadDoc(Locator.XPATH, addlogo_xpath, System.getProperty("user.dir") + "\\logo.JPG", "Logo");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, saveonPopUp_xpath, "Group Audit - Logo", "Save");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, checklist_xpath, "Group Audit", "Check List Dropdown");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, "(//mat-option)[1]", "Group Audit", "Check List option 1");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Group Audit", "Save");
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Group Audit", "Save");
			verifyMessage("Please enter a group name");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I add group audit with out checklist and verify error message$")
	public static void addAuditwithoutchecklistandVerify() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Group Audit", "Add");
			Thread.sleep(2000);
			seleniumUtils.SendKeys(Locator.XPATH, name_xpath, "Auto", "Group Audit", "Name");
			suppotLibrary.uploadDoc(Locator.XPATH, addlogo_xpath, System.getProperty("user.dir") + "\\logo.JPG", "Logo");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, checklist_xpath, "Group Audit", "Check List Dropdown");
			Thread.sleep(500);
			if(seleniumUtils.GetAttribute(Locator.XPATH, "(//mat-option)[1]", "class").contains("selected")) {
				seleniumUtils.Click(Locator.XPATH, "(//mat-option)[1]", "Group Audit", "Check List option 1");
			}
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Group Audit", "Save");
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Group Audit", "Save");
			verifyMessage("Please select atleast one checklist");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I add group audit with invalid logo and verify error message$")
	public static void addAuditwithinvalidfileandVerify() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Group Audit", "Add");
			Thread.sleep(2000);
			suppotLibrary.uploadDoc(Locator.XPATH, addlogo_xpath, System.getProperty("user.dir") + "\\Log4j.properties", "Logo");
			Thread.sleep(500);
			verifyMessage("Failed to upload image the format is not supported");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I add group audit and verify$")
	public static void addGroupAudit() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath))
				writeConfig.storeData("Group Audit Count", seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			else
				writeConfig.storeData("Group Audit Count", "0");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Group Audit", "Add");
			Thread.sleep(2000);
			seleniumUtils.Clear(Locator.XPATH, name_xpath);
			String name = "Auto "+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, name_xpath, name, "Group Audit", "Name");
			suppotLibrary.uploadDoc(Locator.XPATH, addlogo_xpath, System.getProperty("user.dir") + "\\logo.JPG", "Logo");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, saveonPopUp_xpath, "Group Audit - Logo", "Save");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, checklist_xpath, "Group Audit", "Check List Dropdown");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, "(//mat-option)[1]", "Group Audit", "Check List option 1");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Group Audit", "Save");
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Group Audit", "Save");
			verifyMessage("The group audit has been successfully created");
			//vrifyCount("add");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I delete group audit and verify$")
	public static void deleteGroupAudit() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath))
				writeConfig.storeData("Group Audit Count", seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			else {
				addGroupAudit();
				writeConfig.storeData("Group Audit Count", "1");
			}
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Group Audit", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, delete_xpath, "Group Audit", "Delete");
			verifyMessage("Your group audit has been successfully archived");
			vrifyCount("delete");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I restore group audit and verify$")
	public static void restoreGroupAudit() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, deletetab_xpath);
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, deletetab_xpath, "Group Audit", "Deleted/Archived tab");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				
			}
			else {
				seleniumUtils.Click(Locator.XPATH, audittab_xpath, "Group Audit", "Audit Tab");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(6000);
				deleteGroupAudit();
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				seleniumUtils.Click(Locator.XPATH, deletetab_xpath, "Group Audit", "Deleted/Archived tab");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(6000);
			}
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Group Audit", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, restore_xpath, "Group Audit", "Restore");
			verifyMessage("Your group audit has been successfully restored");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I edit group audit and verify$")
	public static void editGroupAudit() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				
			}
			else {
				addGroupAudit();
			}
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Group Audit", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Group Audit", "Edit");
			Thread.sleep(3000);
			seleniumUtils.Clear(Locator.XPATH, name_xpath);
			String name = "Edit Auto "+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, name_xpath, name, "Group Audit", "Name");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Group Audit", "Save");
			verifyMessage("The group audit has been successfully updated");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I search group audit with invalid input and verify$")
	public static void invalidSearchGroupAudit() throws Exception {
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(7000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, search_xpath)) {
			seleniumUtils.SendKeys(Locator.XPATH, search_xpath, "sdfghjklhgfmnbvc", "Group Audit", "Search");
			
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, nodata_xpath);
			
			if(seleniumUtils.IsDisplayed(Locator.XPATH, nodata_xpath)) {
				LogFileControl.logPass("Group Audit - Search","Search result is displaying correctly for invalid search");
			}else {
				LogFileControl.logFail("Group Audit - Search","Search result is not displaying correctly for invalid search");
			}
			
		}else {
			LogFileControl.logFail("Group Audit","Search bar is not diaplaying");
			
		}
	}
	
	@Then("^I search group audit with valid input and verify$")
	public static void validSearchGroupAudit() throws Exception {
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(7000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, search_xpath)) {
			if(seleniumUtils.IsDisplayed(Locator.XPATH, auditname_xpath)) {
				String auditname = seleniumUtils.GetText(Locator.XPATH, auditname_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, search_xpath, auditname, "Group Audit", "Search");
			
			if(seleniumUtils.IsDisplayed(Locator.XPATH, auditname_xpath)) {
				LogFileControl.logPass("Template - Search","Search result is displaying correctly");
			}else {
				LogFileControl.logFail("Template - Search","Search result is not displaying correctly");
			}
			}else {
				LogFileControl.logInfo("Group Audit","No Group audit is available to search!!");
			}
			
		}else {
			LogFileControl.logFail("Group Audit","Search bar is not diaplaying");
			
		}
	}
	
	public static void vrifyCount(String operationType) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			int newc=0;
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				 newc=Integer.parseInt(seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			}
			int oldc= Integer.parseInt(dataProvider.getPropertyvalue("DataStore", "Group Audit Count"));
			if(operationType.trim().toLowerCase().contains("add")) {
				if(oldc+1==newc) {
					LogFileControl.logPass("Add Group Audit", "Group Audit added successfully");
				}else {
					LogFileControl.logFail("Add Group Audit", "Group Audit not added successfully");
				}
			}
			if(operationType.trim().toLowerCase().contains("delete")) {
				if(oldc-1==newc) {
					LogFileControl.logPass("Delete Group Audit", "Group Audit deleted successfully");
				}else {
					LogFileControl.logFail("Delete Group Audit", "Group Audit not deleted successfully");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	

}
