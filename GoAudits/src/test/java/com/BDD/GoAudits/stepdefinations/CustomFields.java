package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class CustomFields extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add')]";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String total_xpath ="//div[contains(text(),'total')]";
	public static final String edit_xpath="//span[contains(text(),'Edit')]";
	public static final String delete_xpath="//span[contains(text(),'Delete/Archive')]";
	public static final String checklistDropdown_xpath="//mat-select[@formcontrolname='f_auditName']";
	public static final String fieldName_xpath="//input[@formcontrolname='f_field_name']";
	public static final String fieldLabel_xpath="//input[@formcontrolname='f_field_label']";
	public static final String fieldType_xpath="//mat-select[@formcontrolname='f_field_type']";
	public static final String company_xpath="//mat-select[@formcontrolname='f_company']";
	public static final String restore_xpath="//span[text()='Restore']";
	public static final String companydefault_xpath="//span[contains(text(),'Company:')]";


	
	

	@Then("^I add custom fields without filling mandatory fields and verify error message$")
	public static void addcustomFieldswithoutmandateFieldandVerify() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Custom Fields", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, save_xpath);
			Thread.sleep(5000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Custom Fields", "Save");
			verifyMessage("Please select a name from the list");
			seleniumUtils.Click(Locator.XPATH, checklistDropdown_xpath, "Custom Fields", "Check List");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, "//mat-option[1]", "Custom Fields", "Check List");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Custom Fields", "Save");
			verifyMessage("Please enter a field name");
			String name ="Auto Custom Field"+suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, fieldName_xpath, name, "Custom Fields", "Field Name");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Custom Fields", "Save");
			verifyMessage("Please enter a field label");
			seleniumUtils.SendKeys(Locator.XPATH, fieldLabel_xpath, "Field Label", "Custom Fields", "Field Label");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Custom Fields", "Save");
			verifyMessage("Please select a field type from the list");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I add custom fields and verify message$")
	public static void addCFdefaultCompany() {
		addCustomField();
	}
	@Then("^I add the custom fields with different companies and verify message$")
	public static void addCFdifferentCompany() {
		addCustomField();
	}

	public static void addCustomField() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, companydefault_xpath, "Action Plan", "Company");
			Thread.sleep(1000);
			String diffCompan="";
			diffCompan= readCSV.getCSVValue("CustomFields", getScriptId(), "Different Company");
			if(diffCompan!=null && !diffCompan.trim().equals("")) {
				if(diffCompan.toLowerCase().contains("yes")) {
					try {
						Thread.sleep(1000);
						if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@class='dropdown-menu show']/a[2]"))
							seleniumUtils.Click(Locator.XPATH, "//div[@class='dropdown-menu show']/a[2]", "Report Styles", "Company 2");
						else
							LogFileControl.logFail("Different Company", "This user has only one company Added!! Please verify precondition");
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else {
					seleniumUtils.Click(Locator.XPATH, "//div[@class='dropdown-menu show']/a[1]", "Report Styles", "Company 1");
				}

			}else {
				seleniumUtils.Click(Locator.XPATH, "//div[@class='dropdown-menu show']/a[1]", "Report Styles", "Company 1");
			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			seleniumUtils.Click(Locator.XPATH, add_xpath, "Custom Fields", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			seleniumUtils.Click(Locator.XPATH, checklistDropdown_xpath, "Custom Fields", "Check List");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, "//mat-option[1]", "Custom Fields", "Check List");
			Thread.sleep(8000);
			String name =suppotLibrary.getCurrentTime();
			seleniumUtils.SendKeys(Locator.XPATH, fieldName_xpath, name, "Custom Fields", "Field Name");
			seleniumUtils.SendKeys(Locator.XPATH, fieldLabel_xpath, "Field Label1", "Custom Fields", "Field Label");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, fieldType_xpath, "Custom Fields", "Field Type");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, "//mat-option[1]", "Custom Fields", "Check List");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Custom Fields", "Save");
			verifyMessage("The custom field has been successfully created");
			writeConfig.storeData("Added Custom Field", name);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+name+"']")) {
				LogFileControl.logPass("Verify added Custom Fields", "Custom field added successfully");
			}else {
				LogFileControl.logPass("Verify added Custom Fields", "Custom field didnt added successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Then("^I delete custom fields and verify message$")
	public static void deleteCustomField() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String fieldName = dataProvider.getPropertyvalue("DataStore","Added Custom Field");
			if(fieldName!=null && !fieldName.trim().equals("")) {
			}
			else {
				addCustomField();
				fieldName = dataProvider.getPropertyvalue("DataStore","Added Custom Field");
			}
			seleniumUtils.Click(Locator.XPATH, "//span[@title='"+fieldName+"']/following::mat-icon[text()='more_vert'][1]", "Custom Fields", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, delete_xpath, "Custom Fields", "Delete");
			verifyMessage("The customfield has been successfully archived");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, "//span[@title='"+fieldName+"']/following::mat-icon[text()='more_vert'][1]", "Custom Fields", "Expand");
			Thread.sleep(500);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, restore_xpath)) {
				LogFileControl.logPass("Verify deleted Custom Fields", "Custom field deleted successfully");
			}else {
				LogFileControl.logPass("Verify deleted Custom Fields", "Custom field didnt deleted successfully");
			}
			writeConfig.storeData("Deleted Custom Field", fieldName);
			writeConfig.storeData("Added Custom Field", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Then("^I restore custom fields and verify message$")
	public static void restoreCustomField() {
		try {seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(3000);
		String fieldName = dataProvider.getPropertyvalue("DataStore","Deleted Custom Field");
		if(fieldName!=null && !fieldName.trim().equals("")) {

		}
		else {
			deleteCustomField();
			fieldName = dataProvider.getPropertyvalue("DataStore","Deleted Custom Field");
		}
		seleniumUtils.Click(Locator.XPATH, "//span[@title='"+fieldName+"']/following::mat-icon[text()='more_vert'][1]", "Custom Fields", "Expand");
		Thread.sleep(500);
		seleniumUtils.Click(Locator.XPATH, restore_xpath, "Custom Fields", "Restore");
		verifyMessage("The customfield has been successfully restored");
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(2000);
		seleniumUtils.Click(Locator.XPATH, "//span[@title='"+fieldName+"']/following::mat-icon[text()='more_vert'][1]", "Custom Fields", "Expand");
		Thread.sleep(500);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, delete_xpath)) {
			LogFileControl.logPass("Verify restored Custom Fields", "Custom field restored successfully");
		}else {
			LogFileControl.logPass("Verify restored Custom Fields", "Custom field didnt restored successfully");
		}
		writeConfig.storeData("Deleted Custom Field", "");
		writeConfig.storeData("Added Custom Field", fieldName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Then("^I edit custom fields and verify message$")
	public static void editCustomField() {
		try {

			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			String fieldName = dataProvider.getPropertyvalue("DataStore","Added Custom Field");
			if(fieldName!=null && !fieldName.trim().equals("")) {

			}
			else {
				addCustomField();
				fieldName = dataProvider.getPropertyvalue("DataStore","Added Custom Field");
			}
			seleniumUtils.Click(Locator.XPATH, "//span[@title='"+fieldName+"']/following::mat-icon[text()='more_vert'][1]", "Custom Fields", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Custom Fields", "Edit");
			Thread.sleep(2000);
			seleniumUtils.SendKeys(Locator.XPATH, fieldLabel_xpath, "Field Label", "Custom Fields", "Field Label");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Custom Fields", "Save");
			verifyMessage("The custom field has been successfully updated");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
