package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class ActionPlan extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add')]";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String priority_xpath="//input[@formcontrolname='priority_name']";
	public static final String companyselected_xpath="//mat-select[@formcontrolname='selected_clientid']";
	public static final String checklistselected_xpath="//mat-select[@formcontrolname='selected_auditTypeid']";
	public static final String dueDate_xpath="//input[@formcontrolname='default_due_days']";
	public static final String companydefault_xpath="//span[contains(text(),'Company:')]";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String total_xpath ="//div[contains(text(),'total')]";
	public static final String edit_xpath="//span[contains(text(),'Edit')]";
	public static final String delete_xpath="//span[contains(text(),'Delete/Archive')]";
	public static final String deletetab_xpath="//div[text()='Archived/Deleted']";
	public static final String actionPlanSettigtab_xpath="//div[text()='Action Plan Settings']";
	public static final String restore_xpath="//span[text()='Restore']";
	public static final String priorityname_xpath="(//div[contains(@class,'datatable-body-cell-label')])[1]/span";
	

	@Then("^I add action plan with out priority and verify error message$")
	public static void addActionPlanandVerify() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Action Plan", "Add");
			Thread.sleep(2000);
			seleniumUtils.Clear(Locator.XPATH, priority_xpath);
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Action Plan", "Save");
			verifyMessage("Please select a priority from the list");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

	@Then("^I add action plan and verify$")
	public static void addActionPlan() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath))
				writeConfig.storeData("Action Plan Count", seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			else
				writeConfig.storeData("Action Plan Count", "0");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Action Plan", "Add");
			Thread.sleep(2000);
			seleniumUtils.Clear(Locator.XPATH, priority_xpath);
			String name = "Priority "+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, priority_xpath, name, "Action Plan", "Priority");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Action Plan", "Save");
			verifyMessage("The action plan has been successfully created");
			vrifyCount("add");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I add similar priority for multiple company and verify$")
	public static void addSameNameActionPlanDifferentCompany() {
		try {
			int actionPlanCount = 1;
			String count= readCSV.getCSVValue(getCSVFileName(), getScriptId(), "Action Plan Count");
			if(count!=null && !count.trim().equals("")) {
				actionPlanCount = Integer.parseInt(count);
			}
			String name = "Priority "+suppotLibrary.getCurrentTime();
			for(int i=1;i<=actionPlanCount;i++) {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath))
				writeConfig.storeData("Action Plan Count", seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			else
				writeConfig.storeData("Action Plan Count", "0");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Action Plan", "Add");
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, companyselected_xpath, "", "Action Plan - Add");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//mat-option["+i+"]", "Action Plan - Add", "Company");
			Thread.sleep(3000);
			seleniumUtils.Clear(Locator.XPATH, priority_xpath);
			
			seleniumUtils.SendKeys(Locator.XPATH, priority_xpath, name, "Action Plan", "Priority");
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Action Plan", "Save");
			verifyMessage("The action plan has been successfully created");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, companydefault_xpath, "Action Plan", "Company");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//div[@class='dropdown-menu show']/a["+i+"]", "Action Plan", "Company Name");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			verifyRecordNameDisplaying(name,"Action Plan");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I delete action plan and verify$")
	public static void deleteGroupAudit() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath))
				writeConfig.storeData("Action Plan Count", seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			else {
				addActionPlan();
				writeConfig.storeData("Action Plan Count", "1");
			}
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Action Plan", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, delete_xpath, "Action Plan", "Delete");
			verifyMessage("Your action plan has been successfully archived");
			vrifyCount("delete");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I restore action plan and verify$")
	public static void restoreGroupAudit() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, deletetab_xpath);
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, deletetab_xpath, "Action Plan", "Deleted/Archived tab");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				
			}
			else {
				seleniumUtils.Click(Locator.XPATH, actionPlanSettigtab_xpath, "Action Plan", "Action Plan Settings Tab");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(6000);
				deleteGroupAudit();
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				seleniumUtils.Click(Locator.XPATH, deletetab_xpath, "Action Plan", "Deleted/Archived tab");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(6000);
			}
			String pName = seleniumUtils.GetText(Locator.XPATH, priorityname_xpath).trim();
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Action Plan", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, restore_xpath, "Action Plan", "Restore");
			verifyMessage("Your action plan has been successfully restored");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, actionPlanSettigtab_xpath, "Action Plan", "Action Plan Settings Tab");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[contains(text(),'"+pName+"')]")) {
				LogFileControl.logPass("Verify Restored Priority", "Priority restored and displaying under Action Plan Settings List");
			}else {
				LogFileControl.logFail("Verify Restored Priority", "Priority not restored and not displaying under Action Plan Settings List");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I edit action plan and verify$")
	public static void editGroupAudit() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				
			}
			else {
				addActionPlan();
			}
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Action Plan", "Expand");
			Thread.sleep(500);
			seleniumUtils.MoveToElement(Locator.XPATH, edit_xpath);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Action Plan", "Edit");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(7000);
			String type = seleniumUtils.GetAttribute(Locator.XPATH, companyselected_xpath, "aria-disabled");
			if(seleniumUtils.GetAttribute(Locator.XPATH, companyselected_xpath, "aria-disabled").contains("true")) {
				LogFileControl.logPass("Verify Company Dropdown", "Company Dropdown is disabled on edit mode");
			}else {
				LogFileControl.logFail("Verify Company Dropdown", "Company Dropdown is not disabled on edit mode");
			}
			if(seleniumUtils.GetAttribute(Locator.XPATH, checklistselected_xpath, "aria-disabled").contains("true")) {
				LogFileControl.logPass("Verify CheckList Dropdown", "CheckList Dropdown is disabled on edit mode");
			}else {
				LogFileControl.logFail("Verify CheckList Dropdown", "CheckList Dropdown is not disabled on edit mode");
			}
			seleniumUtils.Clear(Locator.XPATH, priority_xpath);
			String name = "Edit Priority "+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, priority_xpath, name, "Action Plan", "Priority");
			seleniumUtils.Clear(Locator.XPATH, dueDate_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, dueDate_xpath, "1", "Action Plan", "Due Date");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Action Plan", "Save");
			verifyMessage("The Action Plan has been successfully updated");
		} catch (Exception e) {
			// TODO: handle exception
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
			int oldc= Integer.parseInt(dataProvider.getPropertyvalue("DataStore", "Action Plan Count"));
			if(operationType.trim().toLowerCase().contains("add")) {
				if(oldc+1==newc) {
					LogFileControl.logPass("Add Action Plan", "Action Plan added successfully");
				}else {
					LogFileControl.logFail("Add Action Plan", "Action Plan not added successfully");
				}
			}
			if(operationType.trim().toLowerCase().contains("delete")) {
				if(oldc-1==newc) {
					LogFileControl.logPass("Delete Action Plan", "Action Plan deleted successfully");
				}else {
					LogFileControl.logFail("Delete Action Plan", "Action Plan not deleted successfully");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
