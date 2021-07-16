package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.SuppotLibrary;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class Schedule extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String createSchedule_xpath = "//span[contains(text(),'Create Schedule')]";
	public static final String scheduleTitleDropdown_xpath="//mat-select[@formcontrolname='selected_group_id']";
	public static final String scheduleCategory_xpath="//mat-select[@formcontrolname='selected_categoryid']";
	public static final String createNewOption_xpath="//mat-option//span//u[contains(text(),'Create New')]";
	public static final String existingOption_xpath="//mat-option[2]";
	public static final String scheduleTitleName_xpath="//input[@formcontrolname='schedule_group_title']";
	public static final String scheduleDescription_xpath="//textarea[@formcontrolname='schedule_description']";
	public static final String scheduleCategoryName_xpath="//input[@formcontrolname='new_category_name']";
	public static final String fieldLevelError_xpath="//mat-error[@role='alert']";
	public static final String next_xpath="//span[contains(text(),'Next')]";
	public static final String back_xpath="//span[contains(text(),'Back')]";
	public static final String whatTab_xpath="//div[@aria-selected='true']//div[text()='What']";
	public static final String frequency_xpath="//mat-label[text()='Frequency']/../../../mat-select";
	public static final String search_xpath="//input[contains(@placeholder,'Search')]";
	public static final String startDateCalendet_xpath="(//mat-label[contains(text(),'Start Date')]//following::button[@aria-label='Open calendar'])[1]";
	public static final String endDateCalendet_xpath="(//mat-label[contains(text(),'End date')]//following::button[@aria-label='Open calendar'])[1]";
	public static final String previousDate_xpath="//td[@aria-selected='true']//preceding::td[@aria-selected='false']";
	public static final String futureDate_xpath="//td[@aria-selected='true']//following::td[@aria-selected='false']";
	public static final String endAfterRadio_xpath="//div[contains(text(),'End after')]/preceding-sibling::div[contains(@class,'radio')]";
	public static final String occurance_xpath="//div[contains(text(),'End after')]//input";
	public static final String save_xpath="//span[contains(text(),'Save')]";
	public static final String ok_xpath="//span[contains(text(),'Ok')]";
	public static final String expand_xpath="(//td[@class='Title-col']//following::td[@class='Status-col']/span[contains(@class,'status-tag')]//following::mat-icon[text()='more_vert'])[1]";
	public static final String expand_fullGroup_xpath="(//td[@class='Title-col']//following::td[@class='Status-col']/span[contains(@class,'percentage')]//following::mat-icon[text()='more_vert'])[1]";
	public static final String total_xpath ="//div[contains(text(),'total')]";
	public static final String edit_xpath="//span[contains(text(),'Edit')]";
	public static final String create_xpath="//span[contains(text(),'Create')]";
	public static final String yes_xpath="//span[contains(text(),'Yes')]";
	public static final String delete_xpath="//span[contains(text(),'Delete')]";
	public static final String timeFrame_xpath="//mat-label[text()='Start at']/../../../input";
	public static final String timeFrame_completeBy_xpath="//mat-label[text()='Complete By']/../../../input";
	public static final String emailBeforeZeroHours_xpath="(//div[contains(text(),'Email before')]//following::label[contains(@for,'switch')])[1]";
	public static final String emailBeforeDay_xpath="(//div[contains(text(),'Email before')]//following::label[contains(@for,'switch')])[2]";
	public static final String allowUserToReassign_xpath="(//div[contains(text(),'Allow users to re-assign')]//following::label[contains(@for,'switch')])[1]";
	public static final String allowUserToChangeStatus_xpath="(//div[contains(text(),'Allow users to change status')]//following::label[contains(@for,'switch')])[1]";
	public static final String scheduleLockAfterTheDate_xpath="(//div[contains(text(),'Schedule lock after the date')]//following::label[contains(@for,'switch')])[1]";




	public static String scheduleTitle,auditName,siteName,userName,startDate,endDate;
	public static int weekDayNo;
	public static ArrayList<String> dates;









	@Then("^I create new title without name and new category with name and verify error message$")
	public static void fillScheduleFormwithoutmandateValue() {
		try {
			clickOnCreateSchedule();
			validateMandateField();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I fill the form and navigate to what tab$")
	public static void fillScheduleFormwithmandateValue() {
		try {
			clickOnCreateSchedule();
			fillMandateField_Schedule();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I navigate to where tab$")
	public static void naviagteToWhenTab() {
		try {
			auditName = seleniumUtils.GetText(Locator.XPATH, "(//mat-select)[2]/div/div/span/span");
			writeConfig.storeData("Schedule Audit Name", seleniumUtils.GetText(Locator.XPATH, "(//mat-select)[2]/div/div/span/span"));
			clickOnNext();
			verifyTab("Where", "positive");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I click on edit for weekly schedule$")
	public static void edit_weekly() {
		clickOnEdit("Weekly");
	}

	@Then("^I delete schedule and verify$")
	public static void subGroupDelete() {
		deleteAndVerify("Sub");
	}
	
	@Then("^I delete schedule group and verify$")
	public static void fullGroupDelete() {
		deleteAndVerify("full");
	}

	@Then("^I edit checklist and navigate to where tab$")
	public static void editWhatTab() {
		try {
			seleniumUtils.Click(Locator.XPATH, "(//mat-select)[2]", "What", "Audit Name");
			Thread.sleep(300);
			auditName = seleniumUtils.GetText(Locator.XPATH, "(//mat-option)[2]/span");
			seleniumUtils.Click(Locator.XPATH, "(//mat-option)[2]", "What", "Audit Name");
			auditName = seleniumUtils.GetText(Locator.XPATH, "(//mat-select)[2]/div/div/span/span");
			clickOnNext();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I edit location and navigate to who tab$")
	public static void editWhereTab() {
		try {
			deleteSelected();
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[2]")) {
				siteName = seleniumUtils.GetText(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[2]");
				seleniumUtils.Click(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[2]", "Search Result", "Select");
				Thread.sleep(1000);
			}else {
				seleniumUtils.Click(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[1]", "Search Result", "Select");
			}
			clickOnNext();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I edit user and navigate to when tab$")
	public static void editWhoTab() {
		try {
			deleteSelected();
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[2]")) {
				userName = seleniumUtils.GetText(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[2]");
				seleniumUtils.Click(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[2]", "Search Result", "Select");
				Thread.sleep(1000);
			}else {
				seleniumUtils.Click(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[1]", "Search Result", "Select");
			}
			clickOnNext();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I edit when tab and save$")
	public static void editWhenTab() {
		try {
			int []day1= {1};
			selectweekDate(day1);
			Thread.sleep(300);
			clickOnNext();
			seleniumUtils.Click(Locator.XPATH, save_xpath, "Schedule", "Save");
			//Updated Successfully
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I search for site$")
	public static void searchSite() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			search("My Site");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I search for user$")
	public static void searchUser() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			search("uma");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select searched site$")
	public static void selectSite() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			selectSearchResult();
			siteName = seleniumUtils.GetText(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//span//a/..");
			writeConfig.storeData("Schedule Site Name", seleniumUtils.GetText(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//span//a/.."));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select searched user$")
	public static void selectUser() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			selectSearchResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		userName = seleniumUtils.GetText(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//span//a/..");
		writeConfig.storeData("Schedule User Name", seleniumUtils.GetText(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//span//a/.."));
	}
	@Then("^I delete selected user$")
	public static void deleteUser() {
		deleteSite();
	}

	@Then("^I delete selected site$")
	public static void deleteSite() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			deleteSelected();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I navigate to who tab$")
	public static void navigateToWho() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			clickOnNext();
			verifyTab("Who", "positive");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I navigate to when tab$")
	public static void navigateToWhen() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			clickOnNext();
			verifyTab("When", "positive");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I navigate to Settings tab$")
	public static void navigateToSettings() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			clickOnNext();
			verifyTab("Settings", "positive");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select frequency as daily$")
	public static void setFrequenct() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			selectFrequency("Daily");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select frequency as monthly$")
	public static void setFrequenct_monthly() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			selectFrequency("Monthly");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select frequency as yearly$")
	public static void setFrequenct_yearly() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			selectFrequency("Yearly");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select frequency as weekly$")
	public static void setFrequenct_week() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			selectFrequency("Weekly");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify with invalid date in start date$")
	public static void invalidStartDate() {
		try {
			datePickerValidate_startDate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify with current date in start date$")
	public static void validStartDate() {
		try {
			datePicker_startDate(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify with current date in end date$")
	public static void validendDate() {
		try {
			datePicker_endDate(0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Then("^I verify with future date in end date$")
	public static void validFutureEndDate() {
		try {
			datePicker_endDate(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I set end date for weekly$")
	public static void setEndDate() {
		try {
			datePicker_endDate(35);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I set end date for monthly$")
	public static void setEndDateMon() {
		try {
			datePicker_endDate(35);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I set end date for yearly$")
	public static void setEndDateyear() {
		try {
			datePicker_endDate(375);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I set end date for daily$")
	public static void setEndDate_daily() {
		try {
			datePicker_endDate(3);
			dates = suppotLibrary.getDates(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



	@Then("^I verify with positive End after occurrences$")
	public static void validEndAfter() {
		try {
			endAfterValidate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify with selecting weekdays$")
	public static void selectWeekDays() {
		try {
			int[] days= {1,2,3,4,5};
			selectweekDate(days);
			Thread.sleep(1000);
			int []day1= {0};
			selectweekDate(day1);
			Thread.sleep(1000);
			int []day2= {-1};
			selectweekDate(day2);
			Thread.sleep(1000);
			int []day3= {0,1,2,3,4,5,6,7,8};
			selectweekDate(day3);
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I set weekdays$")
	public static void setWeekDays() {
		try {
			int []day1= {0};
			selectweekDate(day1);
			Thread.sleep(1000);
			String start = suppotLibrary.getDate(0);
			//			String end = dataProvider.getPropertyvalue("DataStore", "Schedule End Date")
			//			int day= Integer.parseInt(dataProvider.getPropertyvalue("DataStore", "Weekly Selected date no"));
			dates=suppotLibrary.getDayCount(start, endDate, weekDayNo);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select selected days and set weekdays$")
	public static void setWeekDays_Monthly() {
		try {
			onSelecteddateOfEachMonthMonthlySchedule();
			Thread.sleep(500);
			selectMonthlySchedule_2nd();
			int []day1= {0};
			selectweekDate(day1);
			Thread.sleep(1000);
			String start = suppotLibrary.getDate(0);
			dates=suppotLibrary.getDayCount(start, endDate, weekDayNo,2);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I set date and month$")
	public static void setDateMonth_Yearly() {
		try {
			selectDateMonth_yearly(12, "Apr");
			Thread.sleep(1000);
			String start = suppotLibrary.getDate(0);
			dates=suppotLibrary.getDayCount(start, endDate, 12,"Apr");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Then("^I verify created schedule$")
	public static void verify_Schedule() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			//			verifyMessage("Created Successfully");
			boolean flag=false;
			for(int i=1;i<=dates.size();i++) {
				String title = seleniumUtils.GetText(Locator.XPATH, "(//td[@class='Title-col' and contains(text(),'"+scheduleTitle+"')]/../../../../div/div/span/table/tbody/tr/td[contains(@class,'Title')])["+i+"]");
				String where = seleniumUtils.GetText(Locator.XPATH, "(//td[@class='Title-col' and contains(text(),'"+scheduleTitle+"')]/../../../../div/div/span/table/tbody/tr/td[contains(@class,'Where')])["+i+"]");
				String who = seleniumUtils.GetText(Locator.XPATH, "(//td[@class='Title-col' and contains(text(),'"+scheduleTitle+"')]/../../../../div/div/span/table/tbody/tr/td[contains(@class,'Who')])["+i+"]");
				String when = seleniumUtils.GetText(Locator.XPATH, "(//td[@class='Title-col' and contains(text(),'"+scheduleTitle+"')]/../../../../div/div/span/table/tbody/tr/td[contains(@class,'When')])["+i+"]");
				if(auditName.contains(title) && siteName.contains(where)
						&& userName.contains(who) && when.contains(suppotLibrary.changeDateFormat(dates.get(i-1)))) {
					flag=true;
				}else
					flag=false;
			}
			if(flag)
				LogFileControl.logPass("Verify Created schedule", "Schedule created successfully");
			else 
				LogFileControl.logFail("Verify Created schedule", "Schedule not created successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify edited schedule$")
	public static void verifyEditedSchedule() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			verifyMessage("Updated Successfully");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//td[contains(text(),'"+auditName+"')]/..//td[contains(text(),'"+siteName+"')]/following-sibling::td/span[contains(text(),'"+userName+"')]")) 
				LogFileControl.logPass("Verify Edited schedule", "Schedule edited successfully");
			else 
				LogFileControl.logFail("Verify Edited schedule", "Schedule not edited successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify with valid timeframe$")
	public static void validtimeFrame() {
		try {
			selectTimeFrame();
			Thread.sleep(3000);
			selectTimeFrame_completeBy();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select monthly last date$")
	public static void monthlyLastDate() {
		try {
			selectMonthlySchedule_Last();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I select date of each month$")
	public static void dateOfEachMonth() {
		try {
			onSelecteddateOfEachMonthMonthlySchedule();
			selectMonthlySchedule_2nd();
			selectMonthlySchedule_Any();
			selectMonthlySchedule_Last();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I navigate back$")
	public static void navigateBack() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			clickOnBack();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I verify notification settings Toggle status$")
	public static void verifyToggleStatus() {
		try {
			verifyToggleStatus(emailBeforeZeroHours_xpath, "grey", "Email before 0 hour(s) whenever schedule is due");
			verifyToggleStatus(emailBeforeDay_xpath, "green", "Email before 1 day(s) whenever schedule is due");
			verifyToggleStatus(scheduleLockAfterTheDate_xpath, "green", "Schedule lock after the date");
			verifyToggleStatus(allowUserToReassign_xpath, "grey", "Allow users to re-assign/swap");
			verifyToggleStatus(allowUserToChangeStatus_xpath, "green", "Allow users to change status");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	@Then("^I click on Create$")
	public static void clickOnCreate() {
		try {
			seleniumUtils.Click(Locator.XPATH, create_xpath, "Schedule", "Create");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/********************************************************* Schedule ******************************************************/
	public static void clickOnCreateSchedule() {
		try {
			if(!driverManager.getDriver().getCurrentUrl().contains("https://admin.goaudits.com/#/schedulenew")) {
				driverManager.getDriver().get("https://admin.goaudits.com/#/schedulenew");
			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, createSchedule_xpath);
			seleniumUtils.Click(Locator.XPATH, createSchedule_xpath, "Schedule", "Create Schedule");
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void clickOnNext() {
		try {
			seleniumUtils.Click(Locator.XPATH, next_xpath, "Schedule", "Next");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void clickOnBack() {
		try {
			seleniumUtils.Click(Locator.XPATH, back_xpath, "Schedule", "Back");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void verifyTab(String tabName, String scenarioType) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(1000);
			if( scenarioType.toLowerCase().contains("negative")) { 
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@aria-selected='true']//div[text()='"+tabName+"']")) {
					LogFileControl.logFail("Tab Verification", "Navigated to "+tabName+" Tab");
				}else {
					LogFileControl.logPass("Tab Verification", "Not Navigated to "+tabName+" Tab");
				}
			}
			if( scenarioType.toLowerCase().contains("positive")) { 
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[@aria-selected='true']//div[text()='"+tabName+"']")) {
					LogFileControl.logPass("Tab Verification", "Navigated to "+tabName+" Tab");
				}else {
					LogFileControl.logFail("Tab Verification", "Not Navigated to "+tabName+" Tab");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void validateMandateField() {
		try {
			seleniumUtils.Click(Locator.XPATH, scheduleTitleDropdown_xpath, "Schedule", "Schedule Title");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, createNewOption_xpath, "Schedule", "Schedule Title");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, scheduleCategory_xpath, "Schedule", "Create new Category Title");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, createNewOption_xpath, "Schedule", "Category Title");
			Thread.sleep(1000);
			seleniumUtils.SendKeys(Locator.XPATH, scheduleCategoryName_xpath, "test", "Schedule", "Category Title");
			clickOnNext();
			if(seleniumUtils.GetText(Locator.XPATH, fieldLevelError_xpath).trim().equalsIgnoreCase("Please enter schedule title.")) {
				LogFileControl.logPass("Field Error Verification", "Error message is displaying as \"Please enter schedule title.\"");
			}else {
				LogFileControl.logFail("Field Error Verification", "Error message is not displaying properly");
			}

			verifyTab("What", "negative");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void clickOnEdit(String scheduleType) {
		try {
			if(!driverManager.getDriver().getCurrentUrl().contains("https://admin.goaudits.com/#/schedulenew")) {
				driverManager.getDriver().get("https://admin.goaudits.com/#/schedulenew");
			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			seleniumUtils.Click(Locator.XPATH, "(//td[@class='Title-col' and contains(text(),'"+scheduleType+"')]//following::td[@class='Status-col']/span[contains(@class,'status-tag')]//following::mat-icon[text()='more_vert'])[1]", "Schedule", "Expand");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, edit_xpath, "Schedule", "Edit");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void deleteAndVerify(String groupType) {
		try {
			if(!driverManager.getDriver().getCurrentUrl().contains("https://admin.goaudits.com/#/schedulenew")) {
				driverManager.getDriver().get("https://admin.goaudits.com/#/schedulenew");
			}
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			String xpath,xpath1,text,text1 = null;
			int oldc=1;
			if(groupType.toLowerCase().contains("sub")) {
				xpath = expand_xpath;
				xpath1="(//span[contains(@class,'status-tag')])[1]/../preceding-sibling::td[contains(@class,'Title-col')]";
				text = seleniumUtils.GetText(Locator.XPATH, xpath1);
				xpath1="(//span[contains(@class,'percentage')])[1]/../preceding-sibling::td[contains(@class,'Title-col')]";
				text1 = seleniumUtils.GetText(Locator.XPATH, xpath1);
				oldc = seleniumUtils.Size(Locator.XPATH, "//td[contains(text(),'"+text+"')]");
			}else {
					xpath = expand_fullGroup_xpath;
					xpath1="(//span[contains(@class,'percentage')])[1]/../preceding-sibling::td[contains(@class,'Title-col')]";
					text = seleniumUtils.GetText(Locator.XPATH, xpath1);
					oldc = seleniumUtils.Size(Locator.XPATH, "//td[contains(text(),'"+text+"')]");
			}
			
			seleniumUtils.Click(Locator.XPATH, xpath, "Schedule", "Expand");
			Thread.sleep(1000);
			seleniumUtils.Click(Locator.XPATH, delete_xpath, "Schedule", "Delete");
			Thread.sleep(500);
			seleniumUtils.Click(Locator.XPATH, yes_xpath, "Schedule", "Delete");
			if(groupType.toLowerCase().contains("sub")) 
			verifyMessage("Schedule deleted successfully.");
			else
				verifyMessage("Schedule group deleted successfully.");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(8000);
			int newC = seleniumUtils.Size(Locator.XPATH, "//td[contains(text(),'"+text+"')]");
			if(groupType.toLowerCase().contains("sub")) {
			if(oldc-1==newC) {
				LogFileControl.logPass("Delete Schedule", "Schedule deleted successfully");
			}else {
				if(oldc==1 && !seleniumUtils.IsDisplayed(Locator.XPATH, "//td[contains(text(),'"+text1+"')]")) {
					LogFileControl.logPass("Delete Schedule", "Schedule deleted successfully");
				}else
				LogFileControl.logFail("Delete Schedule", "Schedule not deleted successfully");
			}
			}else {
				if(oldc-1==newC) {
					LogFileControl.logPass("Delete Schedule", "Schedule deleted successfully");
				}else {
					if(oldc-2==newC && !seleniumUtils.IsDisplayed(Locator.XPATH, "//td[contains(text(),'"+text1+"')]")) {
						LogFileControl.logPass("Delete Schedule", "Schedule deleted successfully");
					}else
					LogFileControl.logFail("Delete Schedule", "Schedule not deleted successfully");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void fillMandateField_Schedule() {
		try {
			String  scenarioName= getScriptId();
			String title;
			if(scenarioName.toLowerCase().contains("daily")) {
				title = "Daily"+suppotLibrary.getCurrentTime();
			}else {
				if(scenarioName.toLowerCase().contains("monthly")) {
					title = "Monthly"+suppotLibrary.getCurrentTime();
				}else {
					if(scenarioName.toLowerCase().contains("weekly")) {
						title = "Weekly"+suppotLibrary.getCurrentTime();
					}else {
						if(scenarioName.toLowerCase().contains("yearly")) {
							title = "Yearly"+suppotLibrary.getCurrentTime();
						}else {
							title = "Auto"+suppotLibrary.getCurrentTime();
						}
					}
				}
			}
			seleniumUtils.Click(Locator.XPATH, scheduleTitleDropdown_xpath, "Schedule", "Schedule Title");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, createNewOption_xpath, "Schedule", "Schedule Title");
			Thread.sleep(200);
			seleniumUtils.SendKeys(Locator.XPATH, scheduleTitleName_xpath, title, "Schedule", "Schedule Title");
			seleniumUtils.Click(Locator.XPATH, scheduleCategory_xpath, "Schedule", "Create new Category Title");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, createNewOption_xpath, "Schedule", "Category Title");
			Thread.sleep(200);
			seleniumUtils.SendKeys(Locator.XPATH, scheduleCategoryName_xpath, title, "Schedule", "Category Title");
			seleniumUtils.SendKeys(Locator.XPATH, scheduleDescription_xpath, "Auto Description", "Schedule", "Description");
			clickOnNext();
			writeConfig.storeData("Schedule Title", title);
			scheduleTitle=title;
			if(seleniumUtils.IsDisplayed(Locator.XPATH, whatTab_xpath)) {
				LogFileControl.logPass("Tab Verification", "Navigated to What Tab");
			}else {
				LogFileControl.logFail("Tab Verification", "Not Navigated to What Tab");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void fillMandateFieldWithExisting_Schedule() {
		try {
			clickOnCreateSchedule();
			seleniumUtils.Click(Locator.XPATH, scheduleTitleDropdown_xpath, "Schedule", "Schedule Title");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, existingOption_xpath, "Schedule", "Schedule Title");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, scheduleCategory_xpath, "Schedule", "Create new Category Title");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, "//mat-option[3]", "Schedule", "Category Title");
			Thread.sleep(200);
			seleniumUtils.SendKeys(Locator.XPATH, scheduleDescription_xpath, "Auto Description", "Schedule", "Description");
			clickOnNext();
			if(seleniumUtils.IsDisplayed(Locator.XPATH, whatTab_xpath)) {
				LogFileControl.logPass("Tab Verification", "Navigated to What Tab");
			}else {
				LogFileControl.logFail("Tab Verification", "Not Navigated to What Tab");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	/********************************************************* What Tab ******************************************************/

	public static void fillwithOutMandate_What() {

	}


	/********************************************************* Where Tab ******************************************************/

	public static void search(String searchString) {
		try {
			seleniumUtils.SendKeys(Locator.XPATH, search_xpath, searchString, "Where", "Search");
			DriverManager.getDriver().findElement(By.xpath(search_xpath)).sendKeys(Keys.chord(Keys.TAB));
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			int s = seleniumUtils.Size(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[1]");
			boolean flag =false;
			for(int i=1;i<=s;i++) {
				if(seleniumUtils.GetText(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)["+i+"]").toLowerCase().contains(searchString.toLowerCase())) {
					flag = true;
				}else
					flag=false;
			}
			if(flag) {
				LogFileControl.logPass("Search", "Search result displayed correctly");
			}else {
				LogFileControl.logFail("Search", "Search result not displayed correctly");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void selectSearchResult() {
		try {
			int oldC = seleniumUtils.Size(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//a//i");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[1]")) {
				seleniumUtils.Click(Locator.XPATH, "(//div[contains(text(),'Search Result')]//..//div//a)[1]", "Search Result", "Select");
				Thread.sleep(1000);
			}else {
				LogFileControl.logFail("Select Element", "No element is available to select");
			}
			int newC = seleniumUtils.Size(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//a//i");
			if(oldC+1 == newC) {
				LogFileControl.logPass("Verify selection", "Selected Successfully");
			}else {
				LogFileControl.logFail("Verify selection", "Not Selected Successfully");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void deleteSelected() {
		try {
			int oldC = seleniumUtils.Size(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//a//i");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//a//i")) {
				seleniumUtils.Click(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//a//i", "Selected Element", "Delete");
				Thread.sleep(1000);
			}else {
				LogFileControl.logFail("Select Element", "No element is available to delete");
			}
			int newC = seleniumUtils.Size(Locator.XPATH, "//div[contains(text(),'Selected')]//..//div//a//i");
			if(oldC-1 == newC) {
				LogFileControl.logPass("Verify deletion", "Deleted Successfully");
			}else {
				LogFileControl.logFail("Verify deletion", "Not Deleted Successfully");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/********************************************************* When Tab ******************************************************/

	public static void selectFrequency(String frequencyType) {
		try {
			seleniumUtils.Click(Locator.XPATH, frequency_xpath, "When", "Frequency");
			Thread.sleep(200);

			seleniumUtils.Click(Locator.XPATH, "//mat-option//span[contains(text(),'"+frequencyType+"')]", "When", "Frequency");
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void datePickerValidate_startDate() {
		try {
			seleniumUtils.Click(Locator.XPATH, startDateCalendet_xpath, "When", "Start Date");
			Thread.sleep(200);
			if(seleniumUtils.GetAttribute(Locator.XPATH, previousDate_xpath, "aria-disabled").contains("true")) {
				LogFileControl.logPass("Verify Date Picker", "Past date not able to select");
			}else {
				LogFileControl.logFail("Verify Date Picker", "Past date able to select");
			}
			seleniumUtils.Click(Locator.XPATH, startDateCalendet_xpath, "When", "Start Date");
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void datePicker_startDate(int day) {
		try {
			seleniumUtils.Click(Locator.XPATH, startDateCalendet_xpath, "When", "Start Date");
			Thread.sleep(200);
			String date = suppotLibrary.getDate(day);
			seleniumUtils.Click(Locator.XPATH, "//td[@aria-label='"+date+"']", "When", "Start Date");
			Thread.sleep(500);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void datePicker_endDate(int day) {
		try {
			seleniumUtils.Click(Locator.XPATH, endDateCalendet_xpath, "When", "End Date");
			Thread.sleep(200);
			String date = suppotLibrary.getDate(day);
			endDate = date;
			writeConfig.storeData("Schedule End Date", date);
			if(day<0) {
				if(seleniumUtils.GetAttribute(Locator.XPATH, previousDate_xpath, "aria-disabled").contains("true")) {
					LogFileControl.logPass("Verify Date Picker", "Past date not able to select");
				}else {
					LogFileControl.logFail("Verify Date Picker", "Past date able to select");
				}
				seleniumUtils.Click(Locator.XPATH, endDateCalendet_xpath, "When", "End Date");
				Thread.sleep(200);
			}else {
				if(seleniumUtils.IsDisplayed(Locator.XPATH, "//td[@aria-label='"+date+"']"))
					seleniumUtils.Click(Locator.XPATH, "//td[@aria-label='"+date+"']", "When", "End Date");
				else {
					int count = (day/30)+2;
					for(int i=1;i<=count;i++) {
						seleniumUtils.Click(Locator.XPATH, "//button[@aria-label='Next month']", "When", "End Date - Next Month");
						Thread.sleep(2000);
						if(seleniumUtils.IsDisplayed(Locator.XPATH, "//td[@aria-label='"+date+"']")) {
							seleniumUtils.Click(Locator.XPATH, "//td[@aria-label='"+date+"']", "When", "End Date");
							break;
						}
					}
				}
				Thread.sleep(500);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void datePickerValidate_startDate_weekly() {
		try {
			seleniumUtils.Click(Locator.XPATH, startDateCalendet_xpath, "When", "Start Date");
			Thread.sleep(200);
			if(seleniumUtils.GetAttribute(Locator.XPATH, previousDate_xpath, "aria-disabled").contains("true")) {
				LogFileControl.logPass("Verify Date Picker", "Past date not able to select");
			}else {
				LogFileControl.logFail("Verify Date Picker", "Past date able to select");
			}
			int avFusize=seleniumUtils.Size(Locator.XPATH, "//td[@aria-selected='true']//following::td[@aria-selected='false' and not(@aria-disabled='true')]");
			int fuSize = seleniumUtils.Size(Locator.XPATH, "//td[@aria-selected='true']//following::td[@aria-selected='false']");
			if(fuSize>6) {
				if(avFusize==6) {
					LogFileControl.logPass("Verify Date Picker", "Upto next 6days Future date available");
				}else {
					LogFileControl.logFail("Verify Date Picker", "Upto next 6days Future date is available");
				}
			}else {
				if(avFusize==fuSize) {
					LogFileControl.logPass("Verify Date Picker", "Upto next 6days Future date available");
				}else {
					LogFileControl.logFail("Verify Date Picker", "Upto next 6days Future date is available");
				}
			}

			seleniumUtils.Click(Locator.XPATH, startDateCalendet_xpath, "When", "Start Date");
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void datePickerValidate_EndtDate() {
		try {
			seleniumUtils.Click(Locator.XPATH, endDateCalendet_xpath, "When", "End Date");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, futureDate_xpath, "When", "End Date - Future Date");
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void endAfterValidate() {
		try {
			seleniumUtils.Click(Locator.XPATH, endAfterRadio_xpath, "When", "End After");
			Thread.sleep(200);
			seleniumUtils.SendKeys(Locator.XPATH, occurance_xpath, "3", "When", "Occurance");
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void selectTimeFrame() {
		try {
			seleniumUtils.Click(Locator.XPATH, timeFrame_xpath, "When", "Time");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, ok_xpath, "When", "OK");
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void selectTimeFrame_completeBy() {
		try {
			seleniumUtils.Click(Locator.XPATH, timeFrame_completeBy_xpath, "When", "Time");
			Thread.sleep(200);
			seleniumUtils.Click(Locator.XPATH, ok_xpath, "When", "OK");
			Thread.sleep(200);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void selectweekDate(int [] days) {
		try {
			for(int i=1;i<=8;i++) {
				if(seleniumUtils.GetAttribute(Locator.XPATH, "//div[contains(@class,'weekly')]//a["+i+"]", "class").toLowerCase().contains("active")) {
					seleniumUtils.Click(Locator.XPATH, "//div[contains(@class,'weekly')]//a["+i+"]", "When", "Week Days");
					Thread.sleep(200);
				}
			}
			for(int i=0;i<days.length;i++) {
				if(days[i]==8)
					seleniumUtils.Click(Locator.XPATH, "//div[contains(@class,'weekly')]//a[8]", "When", "Week Days");
				int d = suppotLibrary.getDay(days[i]);
				writeConfig.storeData("Weekly Selected date no", String.valueOf(d));
				weekDayNo = d;
				if(!seleniumUtils.GetAttribute(Locator.XPATH, "//div[contains(@class,'weekly')]//a["+d+"]", "class").toLowerCase().contains("active")) 
					seleniumUtils.Click(Locator.XPATH, "//div[contains(@class,'weekly')]//a["+d+"]", "When", "Week Days");
				Thread.sleep(200);
			}
			Thread.sleep(200);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void selectDateMonth_yearly(int date, String month) {
		try {
			if(!seleniumUtils.GetAttribute(Locator.XPATH, "//a[text()='"+date+"']", "class").toLowerCase().contains("active")) 
				seleniumUtils.Click(Locator.XPATH, "//a[text()='"+date+"']", "When", "Date");
			Thread.sleep(200);
			if(!seleniumUtils.GetAttribute(Locator.XPATH, "//a[text()='"+month+"']", "class").toLowerCase().contains("active")) 
				seleniumUtils.Click(Locator.XPATH, "//a[text()='"+month+"']", "When", "Month");
			Thread.sleep(200);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void selectMonthlySchedule_Any() {
		try {
			seleniumUtils.Click(Locator.XPATH, "//a[text()='Any']", "When", "Any");
			Thread.sleep(200);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void selectMonthlySchedule_Last() {
		try {
			seleniumUtils.Click(Locator.XPATH, "//a[text()='Last']", "When", "Last");
			Thread.sleep(200);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void selectMonthlySchedule_2nd() {
		try {
			seleniumUtils.Click(Locator.XPATH, "//a[text()='2nd']", "When", "Last");
			Thread.sleep(200);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void onSelecteddateOfEachMonthMonthlySchedule() {
		try {
			seleniumUtils.Click(Locator.XPATH, "//a[text()='selected days']", "When", "Last");
			Thread.sleep(200);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	/********************************************************** Settings *************************************************************************/


	public static void verifyToggleStatus(String fieldXpath, String expectedColour, String elementName) {
		try {
			String colour = seleniumUtils.GetCssValue(Locator.XPATH, fieldXpath, "background-color");
			String toggleStatus="";
			if(colour.equals("rgba(109, 212, 0, 1)")) {
				colour="green";
				toggleStatus = "Enable";
			}
			if(colour.equals("rgba(128, 128, 128, 1)")) {
				colour="grey";
				toggleStatus = "Disable";
			}
			if(colour.toLowerCase().contains(expectedColour.toLowerCase())) {
				LogFileControl.logPass("Verifying field status", elementName+" toggle status is displaying correctly as "+toggleStatus);
			}else {
				LogFileControl.logFail("Verifying field status", elementName+" toggle status is not displaying correctly");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}





}
