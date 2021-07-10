package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;

import java.util.ArrayList;

import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.Locators.Selector;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class Tags extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String add_xpath = "//span[contains(text(),'Add')]";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String tagName_xpath="//mat-label[text()='Tag Category Name']/../../../input";
	public static final String subtagName_xpath="//mat-label[text()='Tag Name']/../../../input";
	public static final String showIn_name="category_type_id";
	public static final String active_xpath = "//span[text()='Active']";
	public static final String activeswitch_xpath="//span[text()='Active']/..//input";
	public static final String yes_xpath="//span[text()='Yes']";
	public static final String addTagCategory_xpath="//span[contains(text(),'Add Tag Category')]";
	public static final String addTag_xpath="(//span[contains(text(),'Add Tag') and not(contains(text(),'Category'))])[1]";
	public static final String edit_xpath="(//div[contains(@class,'headdiv')]/following::a)[1]";
	public static final String delete_xpath="(//div[contains(@class,'headdiv')]/following::a)[2]";
	public static final String editTag_xpath="(//div[contains(@class,'T-each')]//following::a)[1]";
	public static final String deleteTag_xpath="(//div[contains(@class,'T-each')]//following::a)[2]";
	public static final String tagTitle_xpath="//div[contains(@class,'headdiv')]//following::div[contains(@class,'T-list')][1]/div/div/div[@title]";
	public static final String tagCategoryTitle_xpath="//div[contains(@class,'headdiv')]";
	
	


	@Then("^I create of Tag Category with selection for Location and verify$")
	public static void addTagCategoryLocationandVerify() {
		addTagCategory();
	}
	@Then("^I create inactive Tag Category with selection for Location and verify$")
	public static void addInactiveTagCategoryLocationandVerify() {
		addTagCategory();
	}
	@Then("^I create of Tag Category with selection for User and verify$")
	public static void addTagCategoryUserandVerify() {
		addTagCategory();
	}
	@Then("^I update of Tag Category with selection for Location and verify$")
	public static void updateTagCategoryLocationandVerify() {
		updateTagCategory();
	}
	@Then("^I update of Tag Category with selection for User and verify$")
	public static void updateTagCategoryUserandVerify() {
		updateTagCategory();
	}

	@Then("^I delete of Tag Category with selection for Location and verify$")
	public static void deletionTagCategoryLocationandVerify() {
		deleteTagCategory();
	}
	@Then("^I delete of Tag Category with selection for User and verify$")
	public static void deletionTagCategoryUserandVerify() {
		deleteTagCategory();
	}
	@Then("^I add Tag and verify$")
	public static void addTagandVerify() {
		addTag();
	}
	@Then("^I update Tag and verify$")
	public static void updateTagandVerify() {
		updateTag();
	}
	@Then("^I delete Tag and verify$")
	public static void deleteTagandVerify() {
		deleteTag();
	}
	@Then("^I add multiple Tag and verify$")
	public static void addMulTagandVerify() {
		addTag();
	}
	@Then("^I delete Tag Category and verify tag category deleted with all tags$")
	public static void deleteTagCatandVerify() {
		deleteTagCategoryandVerifyAllTagsdeleted();
	}



	public static void addTagCategory() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, addTagCategory_xpath);
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, addTagCategory_xpath, "Tag List", "Add Tag Category");
			Thread.sleep(2000);
			String showIn= readCSV.getCSVValue("Tags", getScriptId(), "Show In");
			String status= readCSV.getCSVValue("Tags", getScriptId(), "Status");
			String name="";
			if(showIn!=null && !showIn.trim().equals("")) {
				name = showIn+" Auto "+suppotLibrary.getCurrentTime();
				seleniumUtils.SendKeys(Locator.XPATH, tagName_xpath, name, "Add Tag Group", "Tag Name");
				seleniumUtils.Select(Locator.NAME, showIn_name, Selector.SELECTBYVISIBLTTEXT, showIn);
			}else {
				name = "Auto "+suppotLibrary.getCurrentTime();
				seleniumUtils.SendKeys(Locator.XPATH, tagName_xpath, name, "Add Tag Group", "Tag Name");
			}
			Thread.sleep(500);
			if(status!=null && !status.trim().equals("")) {

				if(status.equalsIgnoreCase("inactive")) {
					String ariaCheck = seleniumUtils.GetAttribute(Locator.XPATH, activeswitch_xpath, "aria-checked");
					if(ariaCheck.equalsIgnoreCase("true"))
						seleniumUtils.Click(Locator.XPATH, active_xpath, "Add Tag Group", "In Active");
				}
			}	
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Add Tag Group", "Save");
			verifyMessage("The tag category has been successfully created");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@title='"+name+"']")) {
				LogFileControl.logPass("Verify added Tag Group", "Tag Group added and displaying under Tag List");
			}else {
				LogFileControl.logFail("Verify added Tag Group", "Tag Group is not added and displaying under Tag List");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void updateTagCategory() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, addTagCategory_xpath);
			Thread.sleep(2000);
			String showIn= readCSV.getCSVValue("Tags", getScriptId(), "Show In");
			String name="";
			if(showIn!=null && !showIn.trim().equals("")) {
				name = showIn+" Edit "+suppotLibrary.getCurrentTime();
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[contains(@title,'"+showIn+"')]")) {
					seleniumUtils.Click(Locator.XPATH, "(//div[contains(@title,'"+showIn+"')]/following::a)[1]", "Tags List", "Edit");
				}else {
					addTagCategory();
					Thread.sleep(2000);
					seleniumUtils.Click(Locator.XPATH, "(//div[contains(@title,'"+showIn+"')]/following::a)[1]", "Tags List", "Edit");
				}
			}else {
				name = "Edit "+suppotLibrary.getCurrentTime();
				if(seleniumUtils.IsDisplayed(Locator.XPATH, edit_xpath)) {
					seleniumUtils.Click(Locator.XPATH, edit_xpath, "Tags List", "Edit");
				}else {
					addTagCategory();
					Thread.sleep(2000);
					seleniumUtils.Click(Locator.XPATH, edit_xpath, "Tags List", "Edit");
				}
			}
			Thread.sleep(2000);
			seleniumUtils.Clear(Locator.XPATH, tagName_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, tagName_xpath, name, "Add Tag Group", "Tag Name");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Add Tag Group", "Save");
			try {
				seleniumUtils.Click(Locator.XPATH, save_xpath, "Add Tag Group", "Save");
			} catch (Exception e) {
				// TODO: handle exception
			}
			verifyMessage("The tag category has been successfully updated");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@title='"+name+"']")) {
				LogFileControl.logPass("Verify updated Tag Group", "Tag Group updated and displaying under Tag List");
			}else {
				LogFileControl.logFail("Verify updated Tag Group", "Tag Group is not updated and displaying under Tag List");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void deleteTagCategory() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, addTagCategory_xpath);
			Thread.sleep(2000);
			String showIn= readCSV.getCSVValue("Tags", getScriptId(), "Show In");
			String name ="";
			if(showIn!=null && !showIn.trim().equals("")) {
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[contains(@title,'"+showIn+"')]")) {
					seleniumUtils.Click(Locator.XPATH, "(//div[contains(@title,'"+showIn+"')]/following::a)[2]", "Tags List", "Delete");
					name = seleniumUtils.GetAttribute(Locator.XPATH, "//div[contains(@title,'"+showIn+"')]","title");
				}else {
					addTagCategory();
					Thread.sleep(2000);
					name = seleniumUtils.GetAttribute(Locator.XPATH, "//div[contains(@title,'"+showIn+"')]","title");
					seleniumUtils.Click(Locator.XPATH, "(//div[contains(@title,'"+showIn+"')]/following::a)[2]", "Tags List", "Delete");
				}
			}else {
				if(seleniumUtils.IsDisplayed(Locator.XPATH, delete_xpath)) {
					name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");
					seleniumUtils.Click(Locator.XPATH, delete_xpath, "Tags List", "Delete");
				}else {
					addTagCategory();
					Thread.sleep(2000);
					name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");
					seleniumUtils.Click(Locator.XPATH, delete_xpath, "Tags List", "Delete");
				}
			}
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, yes_xpath, "Tag List", "Yes");
			verifyMessage("The tag category has been successfully deleted");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@title='"+name+"']")) {
				LogFileControl.logFail("Verify deleted Tag Group", "Tag Group is not deleted and displaying under Tag List");
			}else {
				LogFileControl.logPass("Verify deleted Tag Group", "Tag Group deleted and not displaying under Tag List");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void deleteTagCategoryandVerifyAllTagsdeleted() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, addTagCategory_xpath);
			Thread.sleep(6000);
			String name ="";
			
				if(seleniumUtils.IsDisplayed(Locator.XPATH, delete_xpath)) {
					name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");
					
				}else {
					addTagCategory();
					Thread.sleep(2000);
					name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");
				}
			Thread.sleep(2000);
			int s = seleniumUtils.Size(Locator.XPATH, tagTitle_xpath);
			if(s<=0) {
				addTag();
			}
			s = seleniumUtils.Size(Locator.XPATH, tagTitle_xpath);
			ArrayList<String>tagList = new ArrayList<String>();
			for(int i=1;i<=s;i++) {
				tagList.add(seleniumUtils.GetTagName(Locator.XPATH, tagTitle_xpath).trim());
			}
			seleniumUtils.Click(Locator.XPATH, delete_xpath, "Tags List", "Delete");
			seleniumUtils.Click(Locator.XPATH, yes_xpath, "Tag List", "Yes");
			verifyMessage("The tag category has been successfully deleted");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@title='"+name+"']")) {
				LogFileControl.logFail("Verify deleted Tag Group", "Tag Group is not deleted and displaying under Tag List");
			}else {
				boolean flag=false;
				for(int i=1;i<=s;i++) {
					if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@title='"+name+"']//following::div[@class='T-list'][1]/div/div/div[@title='"+tagList.get(i-1)+"']")) {
						flag = false;
						break;
					}else {
						flag=true;
					}
				}
				if(flag)
				LogFileControl.logPass("Verify deleted Tag Group", "Tag Group deleted successfully with all Tag");
				else
					LogFileControl.logFail("Verify deleted Tag Group", "Tag Group not deleted successfully with all Tag");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static void addTag() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, addTagCategory_xpath);
			Thread.sleep(2000);
			String name ="";
			if(seleniumUtils.IsDisplayed(Locator.XPATH, delete_xpath)) {
				name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");

			}else {
				addTagCategory();
				Thread.sleep(2000);
				name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");
			}
			int count = 1;
			String tagCount = readCSV.getCSVValue("Tags", getScriptId(), "Tag Count");
			if(tagCount!=null && !tagCount.trim().equals("")) {
				count = Integer.parseInt(tagCount);
			}
			for(int i=1;i<=count;i++) {
				String subName = "Auto Tag "+suppotLibrary.getCurrentTime();
				Thread.sleep(500);
				seleniumUtils.Click(Locator.XPATH, addTag_xpath, "Tags List", "Add Tag");
				seleniumUtils.SendKeys(Locator.XPATH, subtagName_xpath, subName, "Add Tag", "Tag Name");
				seleniumUtils.Click(Locator.XPATH, save_xpath, "Add Tag", "Save");
				verifyMessage("The tag has been successfully created");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(6000);
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@title='"+name+"']//following::div[@class='T-list'][1]/div/div/div[@title='"+subName+"']")) {
					LogFileControl.logPass("Verify added Tag", "Tag added and displaying under corresponding Tag Category");
				}else {
					LogFileControl.logFail("Verify added Tag", "Tag is not added");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void updateTag() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, addTagCategory_xpath);
			Thread.sleep(2000);
			String name ="";
			String subName= "Edit Tag "+suppotLibrary.getCurrentTime();
			if(seleniumUtils.IsDisplayed(Locator.XPATH, delete_xpath)) {
				name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");

			}else {
				addTagCategory();
				Thread.sleep(2000);
				name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");
			}
			if(seleniumUtils.IsDisplayed(Locator.XPATH, editTag_xpath)) {
			}else {
				addTag();
				Thread.sleep(2000);
			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, addTagCategory_xpath);
			Thread.sleep(2000);
			seleniumUtils.Click(Locator.XPATH, editTag_xpath, "Tags List", "Edit Tag");
			seleniumUtils.Clear(Locator.XPATH, subtagName_xpath);
			seleniumUtils.SendKeys(Locator.XPATH, subtagName_xpath, subName, "Edit Tag", "Tag Name");
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Edit Tag", "Save");
			verifyMessage("The tag has been successfully updated");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@title='"+name+"']//following::div[@class='T-list'][1]/div/div/div[@title='"+subName+"']")) {
				LogFileControl.logPass("Verify updated Tag", "Tag updated and displaying under corresponding Tag Category");
			}else {
				LogFileControl.logFail("Verify updated Tag", "Tag is not updated");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void deleteTag() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, addTagCategory_xpath);
			Thread.sleep(2000);
			String name ="";
			String subName= "Edit Tag "+suppotLibrary.getCurrentTime();
			if(seleniumUtils.IsDisplayed(Locator.XPATH, delete_xpath)) {
				name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");

			}else {
				addTagCategory();
				Thread.sleep(2000);
				name = seleniumUtils.GetAttribute(Locator.XPATH, tagCategoryTitle_xpath,"title");
			}
			if(seleniumUtils.IsDisplayed(Locator.XPATH, editTag_xpath)) {
			}else {
				addTag();
				Thread.sleep(2000);
			}
			seleniumUtils.Click(Locator.XPATH, deleteTag_xpath, "Tags List", "Delete Tag");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, yes_xpath, "Tags List", "Delete Tag");
			verifyMessage("The tag has been successfully deleted");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(6000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@title='"+name+"']//following::div[@class='T-list'][1]/div/div/div[@title='"+subName+"']")) {
				LogFileControl.logFail("Verify deleted Tag", "Tag is not deleted");
			}else {
				LogFileControl.logPass("Verify updated Tag", "Tag deleted successfully");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
