package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.Locators.Selector;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class EmailTemplate extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String tomail_xpath="//mat-label[text()='To Email']/../../..//input[@type='email']";
	public static final String ccmail_xpath="//mat-label[text()='Cc Email']/../../..//input[@type='email']";
	public static final String tomailerror_xpath="//mat-label[text()='To Email']/../../..//mat-error";
	public static final String ccmailerror_xpath="//mat-label[text()='Cc Email']/../../..//mat-error";
	public static final String subject_xpath="//mat-label[text()='Subject']/../../..//input";
	public static final String mailBody_xpath="//div[contains(@aria-label,'Rich Text Editor')]/p";
	public static final String saveButtonattribute_xpath="//span[contains(text(),'Save')]/..";
	public static final String mailBodyPlus_xpath="//mat-label[contains(text(),'Message')]/../preceding-sibling::button/i[contains(@class,'plus')]";
	public static final String subjectPlus_xpath="//mat-label[text()='Subject']//preceding::i[contains(@class,'plus')]";
	public static final String subjecterror_xpath="//mat-error[contains(text(),'Subject is required')]";
	public static final String messageerror_xpath="//mat-error[contains(text(),'Message is required')]";
	public static final String subjectPlusList_xpath="//div[contains(@class,'menu-content')]/button";


	@Then("^I verify validation message$")
	public static void addTagCategoryLocationandVerify() {
		verifyValidationEmail();
	}
	
	@Then("^I update multiple valid mail id and verify$")
	public static void multipleValidMail() {
		verifyMultipleValidEmail();
	}
	
	@Then("^I verify creation of email template with attributes in subject$")
	public static void mailwithAttributeSubject() {
		createMailTemplateWithAttribute("subject");
	}
	
	@Then("^I verify creation of email template with attributes in message$")
	public static void mailwithAttributeMessage() {
		createMailTemplateWithAttribute("message");
	}




	public static void verifyValidationEmail() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, tomail_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, tomail_xpath, "xfcghjk", "Email Template", "To Mail");
			DriverManager.getDriver().findElement(By.xpath(tomail_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, tomailerror_xpath)) {
				LogFileControl.logPass("Verify error message on sending invaild value in To Mail", "Error message is displayed as \""+seleniumUtils.GetText(Locator.XPATH, tomailerror_xpath)+"\"");
			}else {
				LogFileControl.logFail("Verify error message on sending invaild value in To Mail", "Error message is not displaying");
			}
			if(seleniumUtils.GetAttribute(Locator.XPATH, saveButtonattribute_xpath,"disabled").contains("true")) {
				LogFileControl.logPass("Verify save button on sending invaild value in To Mail", "Save Button is disable");
			}else {
				LogFileControl.logFail("Verify save button on sending invaild value in To Mail", "Save Button is not disable");
			}
			
			seleniumUtils.SendKeys(Locator.XPATH, ccmail_xpath, "xfcghjk", "Email Template", "Cc Mail");
			DriverManager.getDriver().findElement(By.xpath(ccmail_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, ccmailerror_xpath)) {
				LogFileControl.logPass("Verify error message on sending invaild value in Cc Mail", "Error message is displayed as \""+seleniumUtils.GetText(Locator.XPATH, ccmailerror_xpath)+"\"");
			}else {
				LogFileControl.logFail("Verify error message on sending invaild value in Cc Mail", "Error message is not displaying");
			}
			if(seleniumUtils.GetAttribute(Locator.XPATH, saveButtonattribute_xpath,"disabled").contains("true")) {
				LogFileControl.logPass("Verify save button on sending invaild value in Cc Mail", "Save Button is disable");
			}else {
				LogFileControl.logFail("Verify save button on sending invaild value in Cc Mail", "Save Button is not disable");
			}
			
			seleniumUtils.Clear(Locator.XPATH, subject_xpath);
			DriverManager.getDriver().findElement(By.xpath(subject_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			DriverManager.getDriver().findElement(By.xpath(subject_xpath)).sendKeys("A");
			DriverManager.getDriver().findElement(By.xpath(subject_xpath)).sendKeys(Keys.chord(Keys.BACK_SPACE));
			DriverManager.getDriver().findElement(By.xpath(subject_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, subjecterror_xpath)) {
				LogFileControl.logPass("Verify error message on clear value in Subject", "Error message is displayed as \""+seleniumUtils.GetText(Locator.XPATH, subjecterror_xpath)+"\"");
			}else {
				LogFileControl.logFail("Verify error message on clear value in Subject", "Error message is not displaying");
			}
			if(seleniumUtils.GetAttribute(Locator.XPATH, saveButtonattribute_xpath,"disabled").contains("true")) {
				LogFileControl.logPass("Verify save button on clear value in Subject", "Save Button is disable");
			}else {
				LogFileControl.logFail("Verify save button on clear value in Subject", "Save Button is not disable");
			}
			
			seleniumUtils.Clear(Locator.XPATH, mailBody_xpath);
			DriverManager.getDriver().findElement(By.xpath(mailBody_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			DriverManager.getDriver().findElement(By.xpath(mailBody_xpath)).sendKeys("A");
			DriverManager.getDriver().findElement(By.xpath(mailBody_xpath)).sendKeys(Keys.chord(Keys.BACK_SPACE));
			DriverManager.getDriver().findElement(By.xpath(mailBody_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, messageerror_xpath)) {
				LogFileControl.logPass("Verify error message on clear value in Message", "Error message is displayed as \""+seleniumUtils.GetText(Locator.XPATH, messageerror_xpath)+"\"");
			}else {
				LogFileControl.logFail("Verify error message on clear value in Message", "Error message is not displaying");
			}
			if(seleniumUtils.GetAttribute(Locator.XPATH, saveButtonattribute_xpath,"disabled").contains("true")) {
				LogFileControl.logPass("Verify save button on clear value in Message", "Save Button is disable");
			}else {
				LogFileControl.logFail("Verify save button on clear value in Message", "Save Button is not disable");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void verifyMultipleValidEmail() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, tomail_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, tomail_xpath, "Auto@mail.com", "Email Template", "To Mail");
			DriverManager.getDriver().findElement(By.xpath(tomail_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			seleniumUtils.SendKeys(Locator.XPATH, tomail_xpath, "Auto1@mail.com", "Email Template", "To Mail");
			DriverManager.getDriver().findElement(By.xpath(tomail_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			seleniumUtils.SendKeys(Locator.XPATH, ccmail_xpath, "Autocc@mail.com", "Email Template", "Cc Mail");
			DriverManager.getDriver().findElement(By.xpath(ccmail_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			seleniumUtils.SendKeys(Locator.XPATH, ccmail_xpath, "Autocc1@mail.com", "Email Template", "Cc Mail");
			DriverManager.getDriver().findElement(By.xpath(ccmail_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Email Template", "Save");
			verifyMessage("Email address updated successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void createMailTemplateWithAttribute(String fieldName) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, tomail_xpath);
			seleniumUtils.Clear(Locator.XPATH, tomail_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, tomail_xpath, "Auto@mail.com", "Email Template", "To Mail");
			DriverManager.getDriver().findElement(By.xpath(tomail_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			seleniumUtils.Clear(Locator.XPATH, ccmail_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, ccmail_xpath, "Autocc@mail.com", "Email Template", "Cc Mail");
			DriverManager.getDriver().findElement(By.xpath(ccmail_xpath)).sendKeys(Keys.chord(Keys.TAB));
			Thread.sleep(200);
			String xpath ="";
			if(fieldName.contains("subject")) {
				seleniumUtils.Clear(Locator.XPATH, subject_xpath);
				seleniumUtils.Click(Locator.XPATH, subjectPlus_xpath, "Email Template", "+ icon - Subject");
				xpath=subjectPlus_xpath;
			}
			if(fieldName.contains("message")) {
				seleniumUtils.Clear(Locator.XPATH, mailBody_xpath);
				seleniumUtils.Click(Locator.XPATH, mailBodyPlus_xpath, "Email Template", "+ icon - Message");
				xpath=mailBodyPlus_xpath;
			}
			Thread.sleep(200);
			int s = seleniumUtils.Size(Locator.XPATH, subjectPlusList_xpath);
			ArrayList<String> list = new ArrayList<String>();
			for(int i = 1;i<=s;i++) {
				String listValue = seleniumUtils.GetText(Locator.XPATH, "(//div[contains(@class,'menu-content')]/button)["+i+"]");
				seleniumUtils.Click(Locator.XPATH, "(//div[contains(@class,'menu-content')]/button)["+i+"]", "Email Template", listValue);
				Thread.sleep(200);
				list.add(listValue);
				seleniumUtils.Click(Locator.XPATH, xpath, "Email Template", "+ icon - Subject");
				Thread.sleep(200);
			}
			String sub="";
			if(fieldName.contains("subject")) 
				 sub=seleniumUtils.GetAttribute(Locator.XPATH, subject_xpath,"value");
			if(fieldName.contains("message"))
				sub=seleniumUtils.GetText(Locator.XPATH, mailBody_xpath);
				boolean flag=false;
				for(int i = 0;i<s;i++) {
					if(sub.contains(list.get(i).replace(" ","").toLowerCase())) {
						flag=true;
					}else {
						flag=false;
					}
				}
				if(flag) {
					LogFileControl.logPass("Verify Attribute in "+fieldName, "All Attribute added in "+fieldName);
				}else {
					LogFileControl.logFail("Verify Attribute in "+fieldName, "All Attribute did not added in "+fieldName);
				}
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Email Template", "Save");
			Thread.sleep(200);
			try {
				seleniumUtils.Click(Locator.XPATH, save_xpath, "Email Template", "Save");
			} catch (Exception e) {
			}
			verifyMessage("Email address updated successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
}
