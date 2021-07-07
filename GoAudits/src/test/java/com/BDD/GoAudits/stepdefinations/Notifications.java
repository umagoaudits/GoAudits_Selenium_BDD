package com.BDD.GoAudits.stepdefinations;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class Notifications extends Base{


	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add')]";
	public static final String startDate_xpath="//input[@aria-haspopup='dialog']";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String message_xpath="//div[@aria-live='assertive']";
	public static final String weeklyDate_xpath="//mat-select[@role='listbox'  and @aria-multiselectable='true']";
	public static final String monday_xpath="//span[contains(text(),'Monday')]";
	public static final String edit_xpath="//span[contains(text(),'Edit')]";
	public static final String delete_xpath="//span[contains(text(),'Delete/Archive')]";
	public static final String yes_xpath="//span[contains(text(),'Yes')]";
	public static final String company_xpath="//mat-label[text()='Company']/preceding::mat-select";



	@Then("^I add notification$")
	public static void i_add_notification() throws Exception {
	i_add_notification(1);	
	}
	
	@Then("^I add multiple notification and verify$")
	public static void i_add_multiple_notification() throws Exception {
		String count = readCSV.getCSVValue(getCSVFileName(), getScriptId(), "Add Notification Count");
		int c = 1;
		if(count!=null && !count.trim().equals("")) {
			c = Integer.parseInt(count);
		}
		for(int i=1;i<=c;i++) {
			i_add_notification(i);	
			verifyMessage("The notification has been successfully created");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		}
		
	}


	


	@Then("^I verify notification added successfully$")

	public static void i_verify_notification() throws Exception {
		verifyMessage("The notification has been successfully created");
	}

	/**
	 * @throws Exception
	 */
	@Then("^I update notification$")
	public static void i_update_notification() throws Exception {
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		String notifyType=readCSV.getCSVValue(getCSVFileName(), getScriptId(), "Notify Type");
		Thread.sleep(5000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[contains(text(),'"+notifyType+"')]")) {

		}else {
			i_add_notification();
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, message_xpath);
		}
		Thread.sleep(3000);
		seleniumUtils.Click(Locator.XPATH, "(//span[contains(text(),'"+notifyType+"')]//following::mat-icon[contains(text(),'more_vert')])[1]", "Notifications - Action Tasks", "Expand");
		Thread.sleep(1000);
		seleniumUtils.Click(Locator.XPATH, edit_xpath, "Notifications - Action Tasks", "Edit");
		Thread.sleep(2000);
		seleniumUtils.Click(Locator.XPATH, save_xpath, "Notifications - Action Tasks", "Save");

	}

	@Then("^I delete notification and verify$")
	public static void i_delete_notification() throws Exception {
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		String notifyType=readCSV.getCSVValue(getCSVFileName(), getScriptId(), "Notify Type");
		Thread.sleep(5000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[contains(text(),'"+notifyType+"')]")) {

		}else {
			i_add_notification();
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, message_xpath);
			Thread.sleep(3000);
		}
		int s= Integer.parseInt(seleniumUtils.GetText(Locator.XPATH, "//div[contains(text(),'total')]").trim().split(" ")[0]);
		seleniumUtils.Click(Locator.XPATH, "(//span[contains(text(),'"+notifyType+"')]//following::mat-icon[contains(text(),'more_vert')])[1]", "Notifications - Action Tasks", "Expand");
		Thread.sleep(1000);
		seleniumUtils.Click(Locator.XPATH, delete_xpath, "Notifications - Action Tasks", "Delete");
		Thread.sleep(2000);
		seleniumUtils.Click(Locator.XPATH, yes_xpath, "Notifications - Action Tasks", "Yes");
		verifyMessage("The notification has been successfully archived");
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		int news= Integer.parseInt(seleniumUtils.GetText(Locator.XPATH, "//div[contains(text(),'total')]").trim().split(" ")[0]);
		if(s-1==news) {
			LogFileControl.logPass("Delete Notification", "Notification deleted successfully");
		}else {
			LogFileControl.logFail("Delete Notification", "Notification not deleted successfully");
		}
	}

	@Then("^I verify notification updated successfully$")
	public static void i_verify_updated_notification() throws Exception {
		verifyMessage("The notification has been successfully updated");
	}


	public static void i_add_notification(int count) throws Exception {
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, add_xpath);
		Thread.sleep(3000);
		seleniumUtils.Click(Locator.XPATH, add_xpath, "Notifications - Action Tasks", "Add");
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[contains(text(),'Daily')]");
		Thread.sleep(1000);
		String company = readCSV.getCSVValue(getCSVFileName(), getScriptId(), "Company Index");
		int c=2;
		if(company!=null && !company.trim().equals("")) {
			c = Integer.parseInt(company)+1;
		}
		try {
			seleniumUtils.Click(Locator.XPATH, company_xpath, "Add Action Tasks", "Company");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//mat-option["+c+"]", "Add Action Tasks", "Company");
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String notifyType=readCSV.getCSVValue(getCSVFileName(), getScriptId(), "Notify Type");
		seleniumUtils.Click(Locator.XPATH, "//div[contains(text(),'"+notifyType+"')]", "Template", notifyType);
//		int count=1;
//		if(addNotificationCount!=null && !addNotificationCount.trim().equals("")) {
//			count = Integer.parseInt(addNotificationCount);
//		}
		//for(int i=1;i<=count;i++) {
			if(notifyType.equalsIgnoreCase("Daily"))
			{
				seleniumUtils.Clear(Locator.XPATH,startDate_xpath);
				seleniumUtils.SendKeys(Locator.XPATH,startDate_xpath, suppotLibrary.getDate(-1), "Add Action Tasks", "STart Date");
				String className = seleniumUtils.GetAttribute(Locator.XPATH,startDate_xpath, "class");
				if(className.contains("invalid")) {
					LogFileControl.logPass("Add Action Tasks", "Not able to select past date");
				}else {
					LogFileControl.logFail("Add Action Tasks", "Able to select past date");
				}
				seleniumUtils.Clear(Locator.XPATH,startDate_xpath);
				seleniumUtils.SendKeys(Locator.XPATH,startDate_xpath, suppotLibrary.getDate(count), "Add Action Tasks", "STart Date");
			}
			if(notifyType.equalsIgnoreCase("Weekly")) {
				seleniumUtils.Click(Locator.XPATH, weeklyDate_xpath, "Add Action Tasks", "Weekly Date");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//mat-option["+count+"]", "Add Action Tasks", "Day "+count);
				Thread.sleep(1000);
				try {
					seleniumUtils.Click(Locator.XPATH, "//div[contains(text(),'"+notifyType+"')]", "Template", notifyType);
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		//}
		seleniumUtils.Click(Locator.XPATH, save_xpath, "Add Action Tasks", "Save");

	}


	

}
