package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class ReportTag extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add')]";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String edit_xpath="//span[contains(text(),'Edit')]";
	public static final String checklistDropdown_xpath="//mat-label[text()='Select a Checklist']";
	public static final String enterTagCode_xpath="//mat-label[text()='Enter Tag Code']/../../../input";
	public static final String enterTagDescription_xpath="//mat-label[text()='Enter Tag description']/../../../input";
	public static final String enterpassingLevel_xpath="//mat-label[text()='Enter Passing Level']/../../../input";


	
	

	@Then("^I add report tag without filling mandatory fields and verify error message$")
	public static void addcustomFieldswithoutmandateFieldandVerify() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, add_xpath))
				seleniumUtils.Click(Locator.XPATH, add_xpath, "Report Tag", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, save_xpath);
			Thread.sleep(5000);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Report Tag", "Save");
			verifyMessage("Please select a checklist name from the list");
			seleniumUtils.Click(Locator.XPATH, checklistDropdown_xpath, "Report Tag", "Check List");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, "//mat-option[1]", "Report Tag", "Check List");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Report Tag", "Save");
			verifyMessage("Please enter a tag code");
			String name = suppotLibrary.getCurrentTime5digit();
			seleniumUtils.SendKeys(Locator.XPATH, enterTagCode_xpath, name, "Report Tag", "Tag Code");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Report Tag", "Save");
			verifyMessage("Please enter a tag description");
			seleniumUtils.SendKeys(Locator.XPATH, enterTagDescription_xpath, "Tag Description", "Report Tag", "Tag Description");
			Thread.sleep(200);
			seleniumUtils.Clear(Locator.XPATH, enterpassingLevel_xpath);
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Report Tag", "Save");
			verifyMessage("Please select passing level");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I add report tag and verify message$")
	public static void addReportTag() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			seleniumUtils.Click(Locator.XPATH, add_xpath, "Report Tag", "Add");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			seleniumUtils.Click(Locator.XPATH, checklistDropdown_xpath, "Report Tag", "Check List");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, "//mat-option[1]", "Report Tag", "Check List");
			Thread.sleep(8000);
			String name =suppotLibrary.getCurrentTime5digit();
			seleniumUtils.SendKeys(Locator.XPATH, enterTagCode_xpath, name, "Report Tag", "Tag Code");
			seleniumUtils.SendKeys(Locator.XPATH, enterTagDescription_xpath, "Tag Description", "Report Tag", "Tag Description");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Report Tag", "Save");
			verifyMessage("The report tag has been successfully created");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[@title='"+name+"']")) {
				LogFileControl.logPass("Verify added Report Tag", "Report Tag added successfully");
			}else {
				LogFileControl.logPass("Verify added Report Tag", "Report Tag didnt added successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Then("^I edit report tag and verify message$")
	public static void editReportTag() {
		try {

			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {

			}
			else {
				addReportTag();
			}
			seleniumUtils.Click(Locator.XPATH, expand_xpath, "Report Tag", "Expand");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Report Tag", "Edit");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			String name = suppotLibrary.getCurrentTime5digit();
			seleniumUtils.Clear(Locator.XPATH, enterTagCode_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, enterTagCode_xpath, name, "Report Tag", "Tag Code");
			seleniumUtils.Clear(Locator.XPATH, enterTagDescription_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, enterTagDescription_xpath, "Edit Description", "Report Tag", "Tag Description");
			seleniumUtils.Clear(Locator.XPATH, enterpassingLevel_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, enterpassingLevel_xpath, "70", "Report Tag", "Passing Level");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Report Tag", "Save");
			verifyMessage("The Report Tag has been successfully updated");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
