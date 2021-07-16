package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class AuditWorkflow extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Create')]";
	public static final String actionPlanTab_xpath = "//div[text()='Action Plan']";
	public static final String company_xpath="//mat-select[@formcontrolname='f_company']";
	public static final String companyName_xpath="//mat-select[@formcontrolname='f_company']//following::span[2]";
	public static final String auditName_xpath="//mat-select[@formcontrolname='f_auditName']";
	public static final String location_xpath="//mat-select[@formcontrolname='f_location']";
	public static final String assignees_xpath="//mat-select[@formcontrolname='f_assignees']";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String edit_xpath="//span[contains(text(),'Edit')]";
	public static final String delete_xpath="//span[contains(text(),'Delete')]";
	public static final String yes_xpath="//span[contains(text(),'Yes')]";
	public static final String dropdownMenusingle_xpath="(//mat-option)[2]";
	public static final String dropdownMenuall_xpath="(//mat-option)[1]";
	public static final String pagenext_xpath="//a[@aria-label='go to next page']";
	public static final String pagenextStatus_xpath="//a[@aria-label='go to next page']/..";
	public static final String total_xpath ="//div[contains(text(),'total')]";





	@Then("^I create Audit Workflow without filling mandatory fields and verify error message$")
	public static void addauditWorkFlowwithoutmandateFieldandVerify() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String tabName= readCSV.getCSVValue("AuditWorkflow", getScriptId(), "Tab Name");
			if(tabName!=null && !tabName.trim().equals("")) {
				if(tabName.toLowerCase().contains("action plan")) {
					seleniumUtils.Click(Locator.XPATH, actionPlanTab_xpath, "Audit Workflow", "Action Plan");
					seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
					Thread.sleep(2000);
				}
			}

			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Audit Workflow", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, save_xpath);
			Thread.sleep(5000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Audit Workflow", "Save");
			verifyMessage("Please select a checklist name from the list");
			seleniumUtils.Click(Locator.XPATH, auditName_xpath, "Audit Workflow", "Audit Name");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, dropdownMenusingle_xpath, "Audit Workflow", "Audit Name");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, company_xpath, "Audit Workflow", "Company");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Audit Workflow", "Save");
			verifyMessage("Please select a location name from the list");
			seleniumUtils.Click(Locator.XPATH, location_xpath, "Audit Workflow", "Location");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, dropdownMenusingle_xpath, "Audit Workflow", "Location");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, company_xpath, "Audit Workflow", "Company");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Audit Workflow", "Save");
			verifyMessage("Please select a assignee name from the list");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify creation of Audit Workflow with multiple checklists one location and one Assignee for Audit$")
	public static void addAuditWorkFlowmulchecklisOneLocationOneAssigneeActionPlan() {
		addauditWorkFlow();
	}
	
	@Then("^I verify creation of Audit Workflow with multiple checklists one location and one Assignee for Action Plan$")
	public static void addAuditWorkFlowmulchecklisOneLocationOneAssignee() {
		addauditWorkFlow();
	}
	
	@Then("^I verify creation of Audit Workflow with multiple checklists multiple locations and with multiple Assignee for Action Plan$")
	public static void addAuditWorkFlowmulchecklismulLocationmulAssignee() {
		addauditWorkFlow();
	}
	
	@Then("^I create Audit Workflow and verify$")
	public static void addAuditWorkFlow() {
		addauditWorkFlow();
	}
	
	
	
	@Then("^I edit Audit Workflow and verify$")
	public static void editauditWorkFlow() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String tabName= readCSV.getCSVValue("AuditWorkflow", getScriptId(), "Tab Name");
			if(tabName!=null && !tabName.trim().equals("")) {
				if(tabName.toLowerCase().contains("action plan")) {
					seleniumUtils.Click(Locator.XPATH, actionPlanTab_xpath, "Audit Workflow", "Action Plan");
					seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
					Thread.sleep(2000);
				}
			}

			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
			}else {
				addauditWorkFlow();
			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Audit Workflow", "Expand");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Audit Workflow", "Edit");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Audit Workflow", "Save");
			verifyMessage("The workflow has been successfully updated");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I delete Audit Workflow and verify$")
	public static void deleteauditWorkFlow() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String tabName= readCSV.getCSVValue("AuditWorkflow", getScriptId(), "Tab Name");
			if(tabName!=null && !tabName.trim().equals("")) {
				if(tabName.toLowerCase().contains("action plan")) {
					seleniumUtils.Click(Locator.XPATH, actionPlanTab_xpath, "Audit Workflow", "Action Plan");
					seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
					Thread.sleep(2000);
				}
			}

			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
			}else {
				addauditWorkFlow();
			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			int oldCount = Integer.parseInt(seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Audit Workflow", "Expand");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, delete_xpath, "Audit Workflow", "Delete");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, yes_xpath, "Audit Workflow", "Yes");
			verifyMessage("The workflow has been successfully archived");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			int newCount = 0;
			newCount = Integer.parseInt(seleniumUtils.GetText(Locator.XPATH, total_xpath).trim().split(" ")[0]);
			if(oldCount-1==newCount) {
				LogFileControl.logPass("Delete Audit WorkFlow", "Audit WorkFlow deleted successfully");
			}else {
				LogFileControl.logFail("Delete Audit WorkFlow", "Audit WorkFlow not deleted successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	
	public static void addauditWorkFlow() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			String tabName= readCSV.getCSVValue("AuditWorkflow", getScriptId(), "Tab Name");
			String auditMultiple= readCSV.getCSVValue("AuditWorkflow", getScriptId(), "Audit Multiple");
			String locationMultiple= readCSV.getCSVValue("AuditWorkflow", getScriptId(), "Location Multiple");
			String assigneeMultiple= readCSV.getCSVValue("AuditWorkflow", getScriptId(), "Assignee Multiple");
			if(tabName!=null && !tabName.trim().equals("")) {
				if(tabName.toLowerCase().contains("action plan")) {
					seleniumUtils.Click(Locator.XPATH, actionPlanTab_xpath, "Audit Workflow", "Action Plan");
					seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
					Thread.sleep(2000);
				}
			}

			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Audit Workflow", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, save_xpath);
			Thread.sleep(5000);
			String companyName = seleniumUtils.GetText(Locator.XPATH, companyName_xpath);
			seleniumUtils.Click(Locator.XPATH, auditName_xpath, "Audit Workflow", "Audit Name");
			Thread.sleep(200);
			ArrayList<String> auditname = new ArrayList<String>(),location= new ArrayList<String>(),assignee= new ArrayList<String>();
			if(auditMultiple!=null && !auditMultiple.trim().equals("") && auditMultiple.toLowerCase().contains("yes")) {
				seleniumUtils.Click(Locator.XPATH, dropdownMenuall_xpath, "Audit Workflow", "Audit Name");
				int s = seleniumUtils.Size(Locator.XPATH, "//mat-option");
				for(int i=2;i<=s;i++) {
					auditname.add(seleniumUtils.GetText(Locator.XPATH, "(//mat-option["+i+"]//following::span)[1]"));
				}
			}
			else {
				seleniumUtils.Click(Locator.XPATH, dropdownMenusingle_xpath, "Audit Workflow", "Audit Name");
				auditname.add(seleniumUtils.GetText(Locator.XPATH, "(//mat-option[2]//following::span)[1]"));
			}
			seleniumUtils.Click(Locator.XPATH, company_xpath, "Audit Workflow", "Company");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, location_xpath, "Audit Workflow", "Location");
			Thread.sleep(200);
			if(locationMultiple!=null && !locationMultiple.trim().equals("") && locationMultiple.toLowerCase().contains("yes")) {
				seleniumUtils.Click(Locator.XPATH, dropdownMenuall_xpath, "Audit Workflow", "Location");
				int s = seleniumUtils.Size(Locator.XPATH, "//mat-option");
				for(int i=2;i<=s;i++) {
					location.add(seleniumUtils.GetText(Locator.XPATH, "(//mat-option["+i+"]//following::span)[1]"));
				}
			}
			else {
				seleniumUtils.Click(Locator.XPATH, dropdownMenusingle_xpath, "Audit Workflow", "Location");
				location.add(seleniumUtils.GetText(Locator.XPATH, "(//mat-option[2]//following::span)[1]"));
			}
			seleniumUtils.Click(Locator.XPATH, company_xpath, "Audit Workflow", "Company");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, assignees_xpath, "Audit Workflow", "Assignee");
			Thread.sleep(200);
			if(assigneeMultiple!=null && !assigneeMultiple.trim().equals("") && assigneeMultiple.toLowerCase().contains("yes")) {
				seleniumUtils.Click(Locator.XPATH, dropdownMenuall_xpath, "Audit Workflow", "Location");
				int s = seleniumUtils.Size(Locator.XPATH, "//mat-option");
				for(int i=2;i<=s;i++) {
					assignee.add(seleniumUtils.GetText(Locator.XPATH, "(//mat-option["+i+"]//following::span)[1]"));
				}
			}
			else {
				seleniumUtils.Click(Locator.XPATH, dropdownMenusingle_xpath, "Audit Workflow", "Assignee");
				assignee.add(seleniumUtils.GetText(Locator.XPATH, "(//mat-option[2]//following::span)[1]"));
			}
			seleniumUtils.Click(Locator.XPATH, company_xpath, "Audit Workflow", "Company");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Audit Workflow", "Save");
			verifyMessage("The workflow has been successfully created");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			ArrayList<ArrayList<String>> combinations = getCombinations(companyName, auditname, location, assignee);
			ArrayList<ArrayList<String>> combinationsfromUi = new ArrayList<ArrayList<String>>();
			boolean flag = true;
			while(flag) {
				int s = seleniumUtils.Size(Locator.XPATH, "(//div[contains(@class,'datatable-row-center')])");
				for(int i=2;i<=s;i++) {
					ArrayList<String> arr = new ArrayList<String>();
					arr.add(seleniumUtils.GetText(Locator.XPATH, "((//div[contains(@class,'datatable-row-center')])["+i+"]//following::span)[1]"));
					arr.add(seleniumUtils.GetText(Locator.XPATH, "((//div[contains(@class,'datatable-row-center')])["+i+"]//following::span)[2]"));
					arr.add(seleniumUtils.GetText(Locator.XPATH, "((//div[contains(@class,'datatable-row-center')])["+i+"]//following::span)[3]"));
					arr.add(seleniumUtils.GetText(Locator.XPATH, "((//div[contains(@class,'datatable-row-center')])["+i+"]//following::span)[4]"));
					combinationsfromUi.add(arr);
				}
				seleniumUtils.Click(Locator.XPATH, pagenext_xpath, "Audit Workflow", "Next Page");
				if(seleniumUtils.GetAttribute(Locator.XPATH, pagenextStatus_xpath, "class").trim().toLowerCase().contains("disabled")) {
					flag=false;
					 s = seleniumUtils.Size(Locator.XPATH, "(//div[contains(@class,'datatable-row-center')])");
					for(int i=2;i<=s;i++) {
						ArrayList<String> arr = new ArrayList<String>();
						arr.add(seleniumUtils.GetText(Locator.XPATH, "((//div[contains(@class,'datatable-row-center')])["+i+"]//following::span)[1]"));
						arr.add(seleniumUtils.GetText(Locator.XPATH, "((//div[contains(@class,'datatable-row-center')])["+i+"]//following::span)[2]"));
						arr.add(seleniumUtils.GetText(Locator.XPATH, "((//div[contains(@class,'datatable-row-center')])["+i+"]//following::span)[3]"));
						arr.add(seleniumUtils.GetText(Locator.XPATH, "((//div[contains(@class,'datatable-row-center')])["+i+"]//following::span)[4]"));
						combinationsfromUi.add(arr);
					}
				}
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
			}
			combinations.removeAll(combinationsfromUi);
			if(combinations.size()==0) {
				LogFileControl.logPass("Add Audit WorkFlow", "Work flow added successfully");
			}else {
				LogFileControl.logFail("Add Audit WorkFlow", "Work flow didn't added successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}



	public static ArrayList<ArrayList<String>> getCombinations(String a,ArrayList<String>arr,ArrayList<String>arr1,ArrayList<String>arr2) {

		ArrayList<ArrayList<String>>newarr = new ArrayList<ArrayList<String>>();
		for(int i=0;i<arr.size();i++) {
			for(int j=0;j<arr1.size();j++) {
				for(int k=0;k<arr2.size();k++) {
					ArrayList<String>arr3 = new ArrayList<String>();
					arr3.add(a);
					arr3.add(arr.get(i));
					arr3.add(arr1.get(j));
					arr3.add(arr2.get(k));
					newarr.add(arr3);
				}
			}
		}
		return newarr;

	}

}
