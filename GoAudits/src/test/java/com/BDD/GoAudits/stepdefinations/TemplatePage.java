package com.BDD.GoAudits.stepdefinations;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.Locators.Selector;
import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class TemplatePage extends Base{

	@Then("^I add pre-defined template$")
	public static void i_add_pre_defined_template() throws Exception {
		Thread.sleep(5000);
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(2000);
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
		Thread.sleep(3000);
		seleniumUtils.Click(Locator.XPATH, "//div[text()='Create New']", "Template", "Create New");
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[@class='template-list']/div[1]");
		Thread.sleep(5000);
		seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, "(//div[@class='template-list']/div[1]//following::div[@class='Tname'])[1]");
		String templateName = seleniumUtils.GetText(Locator.XPATH, "(//div[@class='template-list']/div[1]//following::div[@class='Tname'])[1]");
		writeConfig.storeData("Template Name", templateName);
		seleniumUtils.Click(Locator.XPATH, "//div[@class='template-list']/div[1]", "Template", templateName);
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//span[contains(text(),'Use this template')]");
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@aria-selected='true']/div[text()='Preview']")) {
			LogFileControl.logPass("Preview of Template", "System is displaying preview of selected template.");
		}else {
			LogFileControl.logFail("Preview of Template", "System is not displaying preview of selected template.");
		}
		seleniumUtils.MoveToElement(Locator.XPATH, "//span[contains(text(),'Use this template')]");
		seleniumUtils.Click(Locator.XPATH, "//span[contains(text(),'Use this template')]/..", "Template", "Use this template");
		Thread.sleep(2000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[contains(text(),'Use this template')]/.."))
		{
			DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'Use this template')]/..")).sendKeys(Keys.chord(Keys.ENTER));
		}
	}

	@Then("^I add duplicate audit and verify error message$")
	public static void i_add_duplicate_audit() throws Exception {
		i_add_pre_defined_template();
		Thread.sleep(1000);
		//seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//span[contains(text(),'This template name is already in use')]");
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[contains(text(),'This template name is already in use')]")) {
			LogFileControl.logPass("Verify Error Message", "Error Message is displaying correctly");
		}else {
			HomePage.i_navigated_to_menu("Forms/Checklists");
			i_add_pre_defined_template();
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[contains(text(),'This template name is already in use')]")) {
				LogFileControl.logPass("Verify Error Message", "Error Message is displaying correctly");
			}else {
				LogFileControl.logFail("Verify Error Message", "Error Message is not displaying correctly");
			}
		}
	}

	@Then("^I verify pre-defined template added successfully$")
	public void i_verify_pre_defined_template_added_successfully() throws Exception {
		HomePage.i_navigated_to_menu("Forms/Checklists");
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
		Thread.sleep(3000);
		String cname= dataProvider.getPropertyvalue("DataStore", "Template Name");
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//span[text()='"+cname+"']//following::mat-icon[text()='more_vert'])[1]")) {
			LogFileControl.logPass("Template Add", "Template added successfully");
			renameTemplateName(cname);
		}
	}

	@Then("^I add multiple template and verify$")
	public void i_add_multiple_Template() throws Exception {
		addTemplate(0);
		Thread.sleep(3000);
		HomePage.i_navigated_to_menu("Forms/Checklists");
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
		Thread.sleep(3000);
		addTemplate(1);
		Thread.sleep(3000);
		HomePage.i_navigated_to_menu("Forms/Checklists");
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
		Thread.sleep(3000);
		String cname0= dataProvider.getPropertyvalue("DataStore", "Template Name0");
		String cname1= dataProvider.getPropertyvalue("DataStore", "Template Name1");
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//span[text()='"+cname0+"']//following::mat-icon[text()='more_vert'])[1]")) {
			LogFileControl.logPass("Template Add", "Template added successfully");
			renameTemplateName(cname0);
		}else {
			LogFileControl.logFail("Template Add", "Template not added successfully");
		}
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
		Thread.sleep(3000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//span[text()='"+cname1+"']//following::mat-icon[text()='more_vert'])[1]")) {
			LogFileControl.logPass("Template Add", "Template added successfully");
			renameTemplateName(cname1);
		}else {
			LogFileControl.logFail("Template Add", "Template not added successfully");
		}
	}

	@Then("^I edit template with invalid format and verify error message$")
	public static void i_edit_template() throws Exception {
		Thread.sleep(5000);
		renameFirstTemplate("");
		String errmsg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
		if(errmsg!=null && errmsg.trim()!="") {
			LogFileControl.logPass("Template Edit with invalid input: Blank Space", "Error message is displaying properly as "+errmsg);
		}else {
			LogFileControl.logFail("Template Edit with invalid input: Blank Space", "Error message is not displaying properly");
		}
		renameFirstTemplate("?");
		errmsg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
		if(errmsg!=null && errmsg.trim()!="") {
			LogFileControl.logPass("Template Edit with invalid input: ?", "Error message is displaying properly as "+errmsg);
		}else {
			LogFileControl.logFail("Template Edit with invalid input: ?", "Error message is not displaying properly");
		}
	}
	
	@Then("^I delete score ranges and verify$")
	public static void i_delete_score_range() throws Exception {
		deleteScoreRange();
	}

	@Then("^I edit template with adding multiple signature and verify message$")
	public static void i_add_multipleSign() throws Exception {
		Thread.sleep(5000);
		AddSignatureFirstTemplate();
		String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
		if(msg!=null && msg.trim()!="") {
			LogFileControl.logPass("Add Signature", "Successful message is displaying properly as "+msg);
		}else {
			LogFileControl.logFail("Add Signature", "Successful message is not displaying properly");
		}
		deleteSign();
	}

	@Then("^I add action assignee and verify message$")
	public static void i_add_actionAssinee() throws Exception {
		Thread.sleep(5000);
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(2000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
			seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
			Thread.sleep(5000);
		}
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]");
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[text()='Add Assignee']")) {

		}else {
			seleniumUtils.Click(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]", "Template - Assignee", "Manage Action Plan Assignees");
			Thread.sleep(2000);
		}
		int s=0;
		try {
			s= seleniumUtils.Size(Locator.XPATH, "//i[contains(@class,'ellipsis')]");
		} catch (Exception e) {
			// TODO: handle exception
		}
		createAssignee();
		Thread.sleep(5000);
		int newS = seleniumUtils.Size(Locator.XPATH, "//i[contains(@class,'ellipsis')]");
		if(newS==(s+1)) {
			LogFileControl.logPass("Add Multiple Assignee", "Added successfully");
		}else {
			LogFileControl.logFail("Add Multiple Assignee", "Not Added successfully");
		}
		seleniumUtils.Click(Locator.XPATH, "(//span[text()='Save'])[1]", "Template - Assignee", "Save");
		seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
		String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
		if(msg!=null && msg.trim()!="") {
			LogFileControl.logPass("Add Assignee", "Successful message is displaying properly as "+msg);
		}else {
			LogFileControl.logFail("Add Assignee", "Successful message is not displaying properly");
		}
//		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		
		
	}

	@Then("^I delete action assignee and verify message$")
	public static void i_delete_actionAssinee() throws Exception {
		Thread.sleep(5000);
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(2000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
			seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
			Thread.sleep(5000);
		}
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]");
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[text()='Add Assignee']")) {

		}else {
			seleniumUtils.Click(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]", "Template - Assignee", "Manage Action Plan Assignees");
			Thread.sleep(2000);
		}
		int s=0;
		try {
			s= seleniumUtils.Size(Locator.XPATH, "//i[contains(@class,'ellipsis')]");
		} catch (Exception e) {
			// TODO: handle exception
		}
		deleteAssignee();
		int newS = seleniumUtils.Size(Locator.XPATH, "//i[contains(@class,'ellipsis')]");
		if(newS==(s-1)) {
			LogFileControl.logPass("Delete Multiple Assignee", "Deleted successfully");
		}else {
			LogFileControl.logFail("Delete Multiple Assignee", "Not deleted successfully");
		}
		seleniumUtils.Click(Locator.XPATH, "(//span[text()='Save'])[1]", "Template - Assignee", "Save");
		seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
		String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
		if(msg!=null && msg.trim()!="") {
			LogFileControl.logPass("Delete Assignee", "Successful message is displaying properly as "+msg);
		}else {
			LogFileControl.logFail("Delete Assignee", "Successful message is not displaying properly");
		}
	}

	@Then("^I delete template and verify message$")
	public static void i_delete_template() throws Exception {
		Thread.sleep(5000);
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(2000);
		int s=0;
		try {
			s= seleniumUtils.Size(Locator.XPATH, "(//mat-icon[text()='more_vert'])");
		} catch (Exception e) {
			// TODO: handle exception
		}
		deleteTemplate();
		Thread.sleep(5000);
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(2000);
		int newS = seleniumUtils.Size(Locator.XPATH, "(//mat-icon[text()='more_vert'])");
		if(newS==(s-1)) {
			LogFileControl.logPass("Delete Template", "Deleted successfully");
		}else {
			LogFileControl.logFail("Delete Template", "Not deleted successfully");
		}

	}

	@Then("^I add \"([^\"]*)\" score ranges and verify$")
	public static void i_addMultipleScoreRange(String arg1) throws Exception {
		int count = Integer.parseInt(arg1);
		addScoreRange(count);

	}

	@Then("^I restore template and verify message$")
	public static void i_restore_template() throws Exception {
		Thread.sleep(3000);
		restoreDeletedTemplate();

	}

	@Then("^I search template with valid input and verify$")
	public static void i_serach_template_valid() throws Exception {
		Thread.sleep(3000);
		validSearchTemplate();

	}
	
	@Then("^I search template with invalid input and verify$")
	public static void i_serach_template_invalid() throws Exception {
		Thread.sleep(3000);
		invalidSearchTemplate();

	}

	@Then("^I update action assignee and verify message$")
	public static void i_update_actionAssinee() throws Exception {
		editAssignee();
		seleniumUtils.Click(Locator.XPATH, "(//span[text()='Save'])[1]", "Template - Assignee", "Save");
		seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
		String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
		if(msg!=null && msg.trim()!="") {
			LogFileControl.logPass("Edit Assignee", "Successful message is displaying properly as "+msg);
		}else {
			LogFileControl.logFail("Edit Assignee", "Successful message is not displaying properly");
		}
	}

	@Then("^I add multiple action assignee and verify message$")
	public static void i_add_multiple_actionAssinee() throws Exception {
		Thread.sleep(5000);
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		Thread.sleep(2000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
			seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
			Thread.sleep(5000);
		}
		seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]");
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[text()='Add Assignee']")) {

		}else {
			seleniumUtils.Click(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]", "Template - Assignee", "Manage Action Plan Assignees");
			Thread.sleep(2000);
		}
		int s=0;
		try {
			s= seleniumUtils.Size(Locator.XPATH, "//i[contains(@class,'ellipsis')]");
		} catch (Exception e) {
			// TODO: handle exception
		}

		int count = 2;
		for(int i=1;i<=count;i++) {
			createAssignee();
			Thread.sleep(3000);
		}

		int newS = seleniumUtils.Size(Locator.XPATH, "//i[contains(@class,'ellipsis')]");
		if(newS==(s+count)) {
			LogFileControl.logPass("Add Multiple Assignee", "Added successfully");
		}else {
			LogFileControl.logFail("Add Multiple Assignee", "Not Added successfully");
		}
		seleniumUtils.Click(Locator.XPATH, "(//span[text()='Save'])[1]", "Template - Assignee", "Save");
		seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
		String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
		if(msg!=null && msg.trim()!="") {
			LogFileControl.logPass("Add Multiple Assignee", "Successful message is displaying properly as "+msg);
		}else {
			LogFileControl.logFail("Add Multiple Assignee", "Successful message is not displaying properly");
		}
	}
	@Then("^I upload invalid logo and verify error message$")
	public static void i_upload_invalid_logo() throws Exception {
		Thread.sleep(5000);
		seleniumUtils.Click(Locator.XPATH, "//input[@id='file-input']/../div", "Template", "Logo");
		String path = System.getProperty("user.dir") + "\\Log4j.properties";
		StringSelection data = new StringSelection
				(path);
		Clipboard cb = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		cb.setContents(data, data);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);        // C
		r.keyPress(KeyEvent.VK_V);    // : (colon)
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);    // / (slash)
		Thread.sleep(1500);
		r.keyPress(KeyEvent.VK_ENTER);    // confirm by pressing Enter in the end
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(500);
		String errmsg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
		if(errmsg!=null && errmsg.trim()!="") {
			LogFileControl.logPass("Template Edit with invalid input: Blank Space", "Error message is displaying properly as "+errmsg);
		}else {
			LogFileControl.logFail("Template Edit with invalid input: Blank Space", "Error message is not displaying properly");
		}
	}

	public List<String> getUsesAuditTemplateName() {
		List<String> aditName = new ArrayList<String>();
		try {
			int s = seleniumUtils.Size(Locator.XPATH, "//div[@class='Aname']");
			for(int i =1;i<=s;i++) {
				aditName.add(seleniumUtils.GetText(Locator.XPATH, "(//div[@class='Aname'])["+i+"]"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return aditName;

	}

	public static void renameTemplateName(String tName) {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			if(findRecord(tName)) {
			seleniumUtils.Click(Locator.XPATH, "(//span[text()='"+tName+"']//following::mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
			Thread.sleep(5000);
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, "//input[@formcontrolname='auditname']");
			seleniumUtils.Clear(Locator.XPATH, "//input[@formcontrolname='auditname']");
			seleniumUtils.SendKeys(Locator.XPATH, "//input[@formcontrolname='auditname']", "Auto"+suppotLibrary.timestamp(), "Edit checklist", "Name");
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Save']", "Template", "Save");
			seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
			String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
			if(msg!=null && msg.trim()!="") {
				LogFileControl.logPass("Rename Template", "Successful message is displaying properly as "+msg);
			}else {
				LogFileControl.logFail("Renamea	 Template", "Successful message is not displaying properly");
			}
		}
			Thread.sleep(5000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
		} catch (Exception e) {

		}
	}
	//div[contains(text(),'Please select audit name')]
	//div[contains(text(),'Checklist first letter')]
	public static void renameFirstTemplate(String newName) {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
				seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
				Thread.sleep(5000);
			}
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, "//input[@formcontrolname='auditname']");
			seleniumUtils.Clear(Locator.XPATH, "//input[@formcontrolname='auditname']");
			seleniumUtils.SendKeys(Locator.XPATH, "//input[@formcontrolname='auditname']", newName, "Edit checklist", "Name");
			if(newName.equals(""))
				DriverManager.getDriver().findElement(By.xpath("//a[text()='Signature in App']")).click();
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Save']", "Template", "Save");
			Thread.sleep(1000);
		} catch (Exception e) {

		}
	}

	public static void AddSignatureFirstTemplate() {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
				seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
				Thread.sleep(5000);
			}
			if(seleniumUtils.IsDisplayed(Locator.LINKTEXT, "Add")) {
				seleniumUtils.Click(Locator.LINKTEXT, "Add", "Template", "Add");
				Thread.sleep(1000);
				int s = seleniumUtils.Size(Locator.XPATH, "//*[contains(text(),'Signature')]/../../../input");
				seleniumUtils.SendKeys(Locator.XPATH, "(//*[contains(text(),'Signature')]/../../../input)["+s+"]", "Auto Sign", "Template", "Signature Name");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "(//span[contains(text(),'Show On Device')])["+s+"]", "Template", "Show On Device");
				Thread.sleep(5000);
			}else {
				seleniumUtils.Click(Locator.LINKTEXT, "Signature in App", "Template", "Signature in App");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.LINKTEXT, "Add", "Template", "Add");
				Thread.sleep(1000);
				int s = seleniumUtils.Size(Locator.XPATH, "//*[contains(text(),'Signature')]/../../../input");
				seleniumUtils.SendKeys(Locator.XPATH, "(//*[contains(text(),'Signature')]/../../../input)["+s+"]", "Auto Sign", "Template", "Signature Name");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "(//span[contains(text(),'Show On Device')])["+s+"]", "Template", "Show On Device");
				Thread.sleep(5000);
			}
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Save']", "Template", "Save");
			Thread.sleep(2000);
		} catch (Exception e) {

		}
	}

	public static void deleteSign() {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
				seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
				Thread.sleep(5000);
			}
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//i[contains(@class,'trash')]")) {
				seleniumUtils.Click(Locator.XPATH, "//i[contains(@class,'trash')]", "Template - sign", "delete");
				Thread.sleep(1000);

			}else {
				seleniumUtils.Click(Locator.LINKTEXT, "Signature in App", "Template", "Signature in App");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//i[contains(@class,'trash')]", "Template - sign", "delete");
				Thread.sleep(1000);
			}
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Save']", "Template", "Save");
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void createAssignee() {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
				seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
				Thread.sleep(5000);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
			}
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[text()='Add Assignee']")) {

			}else {
				seleniumUtils.Click(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]", "Template - Assignee", "Manage Action Plan Assignees");
				Thread.sleep(2000);
			}
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Add Assignee']", "Template - Assignee", "Add Assignee");
			Thread.sleep(2000);
			seleniumUtils.SendKeys(Locator.XPATH, "//*[text()='Full Name']/../../../input", "Auto", "Template - Assignee", "Name");
			seleniumUtils.SendKeys(Locator.XPATH, "//*[text()='Email Address']/../../../input", "Auto"+suppotLibrary.getCurrentTime()+"@gmail.com", "Template - Assignee", "Email");
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Approval Required']", "Template - Assignee", "Approval Required");
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, "(//span[text()='Save'])[2]", "Template - Assignee", "Save");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void editAssignee() {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
				seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
				Thread.sleep(5000);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
			}
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[text()='Add Assignee']")) {

			}else {
				seleniumUtils.Click(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]", "Template - Assignee", "Manage Action Plan Assignees");
				Thread.sleep(4000);
			}
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//i[contains(@class,'ellipsis')]")) {

			}else {
				createAssignee();
			}
			seleniumUtils.Click(Locator.XPATH, "//i[contains(@class,'ellipsis')]", "Template - Assignee", "Expand");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//button[text()='Edit']", "Template - Assignee", "Edit");
			Thread.sleep(2000);
			seleniumUtils.Clear(Locator.XPATH, "//*[text()='Full Name']/../../../input");
			seleniumUtils.SendKeys(Locator.XPATH, "//*[text()='Full Name']/../../../input", "Edit Auto", "Template - Assignee", "Name");
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, "(//span[text()='Save'])[2]", "Template - Assignee", "Save");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void deleteAssignee() {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			Thread.sleep(5000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
				seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
				Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
				Thread.sleep(5000);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
			}
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[text()='Add Assignee']")) {

			}else {
				seleniumUtils.Click(Locator.XPATH, "//a[contains(text(),'Manage Action Plan Assignees')]", "Template - Assignee", "Manage Action Plan Assignees");
				Thread.sleep(5000);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(2000);
			}
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//i[contains(@class,'ellipsis')]")) {

			}else {
				createAssignee();
			}
			seleniumUtils.Click(Locator.XPATH, "//i[contains(@class,'ellipsis')]", "Template - Assignee", "Expand");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//button[text()='Delete']", "Template - Assignee", "Delete");
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void deleteTemplate() {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
			Thread.sleep(3000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {

			}else {
				i_add_pre_defined_template();
			}
			seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "Expand");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Delete/Archive']", "Template", "Delete");
			seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
			String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
			if(msg!=null && msg.trim()!="") {
				LogFileControl.logPass("Delete Template", "Successful message is displaying properly as "+msg);
			}else {
				LogFileControl.logFail("Delete Template", "Successful message is not displaying properly");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void restoreDeletedTemplate() {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, "//div[text()='Deleted/Archived']", "Template", "Deleted/Archived tab");
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {

			}else {
				seleniumUtils.Click(Locator.XPATH, "//div[text()='Audits']", "Template", "Audits");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
				seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
				Thread.sleep(3000);
				deleteTemplate();
				Thread.sleep(2000);
				seleniumUtils.Click(Locator.XPATH, "//div[text()='Deleted/Archived']", "Template", "Deleted/Archived tab");
				Thread.sleep(6000);
			}


			seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "Expand");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Restore']", "Template", "Restore");
			seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
			String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
			if(msg!=null && msg.trim()!="") {
				LogFileControl.logPass("Restore Template", "Successful message is displaying properly as "+msg);
			}else {
				LogFileControl.logFail("Restore Template", "Successful message is not displaying properly");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void addTemplate(int tamplateIndex) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[text()='Create New']");
			Thread.sleep(3000);
			seleniumUtils.Click(Locator.XPATH, "//div[text()='Create New']", "Template", "Create New");
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//div[@class='template-list']/div[1]");
			Thread.sleep(8000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
			try {
				seleniumUtils.Select(Locator.XPATH, "//select[contains(@id,'exampleFormControlSelect')]", Selector.SELECTBYINDEX, String.valueOf(tamplateIndex));
				Thread.sleep(3000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, "(//div[@class='template-list']/div[1]//following::div[@class='Tname'])[1]");
			String templateName = seleniumUtils.GetText(Locator.XPATH, "(//div[@class='template-list']/div[1]//following::div[@class='Tname'])[1]");
			writeConfig.storeData("Template Name"+tamplateIndex, templateName);
			seleniumUtils.Click(Locator.XPATH, "//div[@class='template-list']/div[1]", "Template", templateName);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//span[contains(text(),'Use this template')]");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@aria-selected='true']/div[text()='Preview']")) {
				LogFileControl.logPass("Preview of Template", "System is displaying preview of selected template.");
			}else {
				LogFileControl.logFail("Preview of Template", "System is not displaying preview of selected template.");
			}
			seleniumUtils.MoveToElement(Locator.XPATH, "//span[contains(text(),'Use this template')]");
			seleniumUtils.Click(Locator.XPATH, "//span[contains(text(),'Use this template')]/..", "Template", "Use this template");
			Thread.sleep(2000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//span[contains(text(),'Use this template')]/.."))
			{
				DriverManager.getDriver().findElement(By.xpath("//span[contains(text(),'Use this template')]/..")).sendKeys(Keys.chord(Keys.ENTER));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void addScoreRange(int rowCountToBeInserted) {
		try {
			double min = 1,max= 5.9;
			if(editFirstTemplate()) {
				seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//span[contains(text(),'Add/Edit Score Range')]");
				seleniumUtils.Click(Locator.XPATH, "//span[contains(text(),'Add/Edit Score Range')]", "Edit - Template", "Add/Edit Score Range");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
				seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "(//span[text()='Save'])[2]");
				Thread.sleep(1000);
				int s=0;
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//i[contains(@class,'trash')]")) {
					s= seleniumUtils.Size(Locator.XPATH, "//i[contains(@class,'trash')]");
					for(int j=1;j<=s;j++) {
						seleniumUtils.Click(Locator.XPATH, "(//i[contains(@class,'trash')])["+j+"]", "Add/Edit Score Range", "Delete");
						Thread.sleep(1000);
					}

				}
				for(int i = 1;i<=rowCountToBeInserted;i++) {

					try {
						seleniumUtils.Click(Locator.XPATH, "//span[contains(text(),'Add Rows')]", "Add/Edit Score Range", "Add Row");
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					seleniumUtils.Select(Locator.XPATH, "(//input[contains(@name,'min_value')]/../../td/select)["+i+"]", Selector.SELECTBYVALUE, "P");
					seleniumUtils.SendKeys(Locator.XPATH, "(//input[contains(@name,'min_value')])["+i+"]", String.valueOf(min), "Add/Edit Score Range", "Min Value");
					seleniumUtils.SendKeys(Locator.XPATH, "(//input[contains(@name,'min_value')]//following::input[1])["+i+"]", String.valueOf(max), "Add/Edit Score Range", "Max Value");
					min = max+.1; max= min+4.9;
				}

				seleniumUtils.Click(Locator.XPATH, "(//span[text()='Save'])[2]", "Add/Edit Score Range", "Save");
				seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
				String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
				if(msg!=null && msg.trim()!="") {
					LogFileControl.logPass("Add/Edit Score Range", "Successful message is displaying properly as "+msg);
				}else {
					LogFileControl.logFail("Add/Edit Score Range", "Successful message is not displaying properly");
				}
			}else {
				LogFileControl.logInfo("Template", "No Audit Template is available to edit");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void deleteScoreRange() {
		try {
			if(editFirstTemplate()) {
				seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//span[contains(text(),'Add/Edit Score Range')]");
				seleniumUtils.Click(Locator.XPATH, "//span[contains(text(),'Add/Edit Score Range')]", "Edit - Template", "Add/Edit Score Range");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
				seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "(//span[text()='Save'])[2]");
				Thread.sleep(1000);
				int s=0;
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//i[contains(@class,'trash')]")) {
					
						seleniumUtils.Click(Locator.XPATH, "(//i[contains(@class,'trash')])", "Add/Edit Score Range", "Delete");
						Thread.sleep(1000);
					}else {
						addScoreRange(1);
						seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
						seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//span[contains(text(),'Add/Edit Score Range')]");
						seleniumUtils.Click(Locator.XPATH, "//span[contains(text(),'Add/Edit Score Range')]", "Edit - Template", "Add/Edit Score Range");
						seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
						seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "(//span[text()='Save'])[2]");
						Thread.sleep(1000);
						seleniumUtils.Click(Locator.XPATH, "(//i[contains(@class,'trash')])", "Add/Edit Score Range", "Delete");
						Thread.sleep(1000);
				}
				seleniumUtils.Click(Locator.XPATH, "(//span[text()='Save'])[2]", "Add/Edit Score Range", "Save");
				seleniumUtils.WaitForElementToBeAvailable(Locator.XPATH, "//div[@aria-live='assertive']");
				String msg= seleniumUtils.GetText(Locator.XPATH, "//div[@aria-live='assertive']");
				if(msg!=null && msg.trim()!="") {
					LogFileControl.logPass("Delete Score Range", "Successful message is displaying properly as "+msg);
				}else {
					LogFileControl.logFail("Delete Score Range", "Successful message is not displaying properly");
				}
			}else {
				LogFileControl.logInfo("Template", "No Audit Template is available to edit");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static boolean editFirstTemplate() throws Exception {
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
		Thread.sleep(7000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]")) {
			seleniumUtils.Click(Locator.XPATH, "(//mat-icon[text()='more_vert'])[1]", "Template", "More OpTion");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, "//span[text()='Edit']", "Template", "Edit");
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
			return true;
		}else {
			LogFileControl.logInfo("Edit template","No Template to edit");
			return false;
		}
	}
	
	public static void validSearchTemplate() throws Exception {
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
		Thread.sleep(7000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//input[@placeholder='Search']")) {
			seleniumUtils.SendKeys(Locator.XPATH, "//input[@placeholder='Search']", "Auto", DriverManager.getDriver().getTitle(), "Search");
			
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, "(//div[@class='Aname']/span)");
			int s = seleniumUtils.Size(Locator.XPATH, "(//div[@class='Aname']/span)");
			boolean flag = false;
			for(int i = 1;i<=s;i++) {
				if(seleniumUtils.GetText(Locator.XPATH, "(//div[@class='Aname']/span)["+i+"]").contains("Auto")) {
					flag = true;
				}else {
					flag = false;
					break;
				}
			}
			if(flag) {
				LogFileControl.logPass("Template - Search","Search result is displaying correctly");
			}else {
				LogFileControl.logFail("Template - Search","Search result is not displaying correctly");
			}
			
		}else {
			LogFileControl.logFail("Template","Search bar is not diaplaying");
			
		}
	}

	public static void invalidSearchTemplate() throws Exception {
		seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, "//div[contains(@class,'spinner')]");
		Thread.sleep(7000);
		if(seleniumUtils.IsDisplayed(Locator.XPATH, "//input[@placeholder='Search']")) {
			seleniumUtils.SendKeys(Locator.XPATH, "//input[@placeholder='Search']", "sdfghjklhgfmnbvc", "Template", "Search");
			
			seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, "//p[contains(text(),'We have a huge library')]");
			
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//p[contains(text(),'We have a huge library')]")) {
				LogFileControl.logPass("Template - Search","Search result is displaying correctly for invalid search");
			}else {
				LogFileControl.logFail("Template - Search","Search result is not displaying correctly for invalid search");
			}
			
		}else {
			LogFileControl.logFail("Template","Search bar is not diaplaying");
			
		}
	}

	
}
