package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;

import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class ReportStyles extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String edit_xpath="(//i[contains(@class,'pencil-alt')])[1]";
	public static final String updatedReportStyle_xpath="(//img[contains(@src,'assets/reports_img/list')])[5]";
	public static final String desriptionText_xpath="//mat-label[contains(text(),'Description')]/../../../textarea";
	public static final String confidentialityText_xpath="//mat-label[contains(text(),'Confidentiality')]/../../../textarea";
	public static final String desription_linkText="Enter Description & Usage";
	public static final String confidentiality_linkText="Enter Confidentiality";
	public static final String company_xpath="//span[contains(text(),'Company')]";
	public static final String differentCompany_xpath="//a[contains(@class,'dropdown-item')][2]";







	@Then("^I verify updation of selecting another report template$")
	public static void changeReportStyleForDefaultCompany() {
		changeReportStyle();
	}
	@Then("^I verify updation of selecting another report template for different Company$")
	public static void changeReportStyleForDifferentCompany() {
		changeReportStyle();
	}

	public static void changeReportStyle() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, edit_xpath);
			String diffCompan="";
			diffCompan= readCSV.getCSVValue("ReportStyles", getScriptId(), "Different Company");
			if(diffCompan!=null && !diffCompan.trim().equals("")) {
				if(diffCompan.toLowerCase().contains("yes")) {
					try {
						seleniumUtils.Click(Locator.XPATH, company_xpath, "Report Styles", "Company");
						Thread.sleep(1000);
						if(seleniumUtils.IsDisplayed(Locator.XPATH, differentCompany_xpath))
							seleniumUtils.Click(Locator.XPATH, differentCompany_xpath, "Report Styles", "Company 2");
						else
							LogFileControl.logFail("Different Company", "This user has only one company Added!! Please verify precondition");
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}

			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, edit_xpath);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Report Styles", "Edit");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, updatedReportStyle_xpath);
			seleniumUtils.Click(Locator.XPATH, updatedReportStyle_xpath, "Report Styles", "Report 5");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Report Styles", "Save");
			verifyMessage("Report config updated successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify updation of report style with description and confidentiality$")
	public static void updateDescriptionReportStyle() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, edit_xpath);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Report Styles", "Edit");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, updatedReportStyle_xpath);
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, desriptionText_xpath)) {
			}else {
				seleniumUtils.Click(Locator.LINKTEXT, desription_linkText, "Report Styles", "Enter Description & Usage");
				Thread.sleep(500);
			}
			seleniumUtils.Clear(Locator.XPATH, desriptionText_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, desriptionText_xpath, "Add Description", "Report Styles", "Description & Usage");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, confidentialityText_xpath)) {
			}else {
				seleniumUtils.Click(Locator.LINKTEXT, confidentiality_linkText, "Report Styles", "Enter Confidentiality");
				Thread.sleep(500);
			}
			seleniumUtils.Clear(Locator.XPATH, confidentialityText_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, confidentialityText_xpath, "Add Confidentiality", "Report Styles", "Confidentiality");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Report Styles", "Save");
			verifyMessage("Report config updated successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



}
