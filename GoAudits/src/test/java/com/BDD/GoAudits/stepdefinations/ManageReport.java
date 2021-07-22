package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.LogFileControl;

import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ManageReport extends Base{

	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	public static final String addFilter_xpath = "//button[contains(text(),'Add Filter')]";
	public static final String yes_xpath="//span[contains(text(),'Yes')]";
	public static final String expand_xpath="(//mat-icon[text()='more_vert'])";
	public static final String delete_xpath="//button//span[contains(text(),'Delete')]";
	public static final String deletedTab_xpath="//div[contains(text(),'Deleted')]";
	public static final String download_xpath="//button//span[contains(text(),'Download Report')]";
	public static final String regenerate_xpath="//button//span[contains(text(),'Regenerate Report')]";
	public static final String restore_xpath="//button//span[contains(text(),'Restore')]";
	public static final String nextPage_xpath="//a[@title='Next page']";
	public static final String close_xpath="//a[@class='close-icon']";
	public static final String edit_xpath="//button//span[contains(text(),'Edit')]";
	public static final String save_xpath="//button//span[contains(text(),'Save')]";
	public static final String complete_xpath="//button//span[contains(text(),'Complete')]";
	public static final String reject_xpath="//button//span[contains(text(),'Reject')]";
	public static final String confirm_xpath="//button//span[contains(text(),'Confirm')]";
	public static final String inprogress_xpath="//mat-option//span[contains(text(),'In Progress')]";
	public static final String rejectOption_xpath="//mat-option//span[contains(text(),'Rejected')]";
	

	//


	@Given("^I Logged in with valid credentials for Manage Report$")
	public static void login() {
		try {
			LoginPage.login(dataProvider.getConfigPropertyval("User Name_ManageReport"), dataProvider.getConfigPropertyval("Password_ManageReport"));
			Thread.sleep(8000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I search for submit for approval audit$")
	public static void searchsubmitForApproval() {
		try {
			search("Status", "Submit for Approval");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I search for rejected audit$")
	public static void searchReject() {
		try {
			search("Status", "Rejected");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I search for completed audit$")
	public static void searchComplete() {
		try {
			search("Status", "Completed");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I search for inprogress audit$")
	public static void searchInProgress() {
		try {
			search("Status", "In Progress");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I search and verify for completed audit$")
	public static void search_verifyComplete() {
		try {
			String searchValue=search("Status", "Completed");
			verifySearchResult("Status", searchValue);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I search and verify for audit with report name$")
	public static void search_verifyLocation() {
		try {
			String searchValue=search("Report", "");
			verifySearchResult("Report Name", searchValue);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	public static String search(String searchType, String searchValue ) {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			boolean flag = seleniumUtils.IsDisplayed(Locator.XPATH, close_xpath);
			while(flag) {
				seleniumUtils.Click(Locator.XPATH, close_xpath, "Report", "Close filter");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(3000);
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				flag = seleniumUtils.IsDisplayed(Locator.XPATH, close_xpath);
			}
			seleniumUtils.Click(Locator.XPATH, addFilter_xpath, "Report", "Add Filter");
			Thread.sleep(500);
		
			seleniumUtils.Click(Locator.XPATH, "//div[@class='dropdown-menu show']//span[contains(text(),'"+searchType+"')]", "Report", searchType);
			Thread.sleep(1000);
				seleniumUtils.Click(Locator.XPATH, "//button//span[contains(text(),'"+searchType+"')]", "Report", searchType);
				Thread.sleep(500);
				if(searchValue.trim().contentEquals("")) {
					searchValue = seleniumUtils.GetText(Locator.XPATH, "(//span[text()='"+searchType+":']//following::label[contains(@class,'checkbox')])[2]//span");
					seleniumUtils.Click(Locator.XPATH, "(//span[text()='"+searchType+":']//following::label[contains(@class,'checkbox')])[2]//span", "Report", searchType);
				}
				else {
					seleniumUtils.Click(Locator.XPATH, "//span[text()='"+searchType+":']//following::label[contains(@class,'checkbox')]//span[contains(text(),'"+searchValue+"')]", "Report", searchType);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchValue;
	}

	public static void verifySearchResult(String searchType, String searchValue ) {
		try {
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			int s = seleniumUtils.Size(Locator.XPATH, "//thead[@class='p-datatable-thead']/tr/th");
			int index = 0;
			for(int i=1;i<=s;i++) {
				String text  = seleniumUtils.GetText(Locator.XPATH, "//thead[@class='p-datatable-thead']/tr/th["+i+"]").trim();
				if(text.contains(searchType)) {
					index = i;
					break;
				}
			}
			boolean flag = true,flag1 = false;
			String cellValue= "";
			while(flag) {
				int rowSize = seleniumUtils.Size(Locator.XPATH, "//thead[@class='p-datatable-thead']/../tbody//tr");
				for(int i=1;i<=rowSize;i++) {
					String text = seleniumUtils.GetText(Locator.XPATH, "//thead[@class='p-datatable-thead']/../tbody//tr["+i+"]//td["+index+"]//span").trim();
					if(text.contains(searchValue))
						flag1=true;
					else {
						flag1=false;
						System.out.println("Cell Value  "+text);
						System.out.println("Expected Value  "+searchValue);
					}
						
				}
				
				if(seleniumUtils.IsDisplayed(Locator.XPATH, nextPage_xpath)) {
					seleniumUtils.Click(Locator.XPATH, nextPage_xpath, "Audit Workflow", "Next Page");
					seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
					Thread.sleep(3000);
				}else {
					flag=false;
					rowSize = seleniumUtils.Size(Locator.XPATH, "//thead[@class='p-datatable-thead']/../tbody//tr");
					for(int i=1;i<=rowSize;i++) {
						String text = seleniumUtils.GetText(Locator.XPATH, "//thead[@class='p-datatable-thead']/../tbody//tr["+i+"]//td["+index+"]//span").trim();
						if(text.contains(searchValue))
							flag1=true;
						else{
							flag1=false;
							System.out.println("Cell Value  "+text);
							System.out.println("Expected Value  "+searchValue);
						}
					}
				}
			}
			if(flag1)
				LogFileControl.logPass("Verify search result", "Search result is displaying based on search condition");
			else
				LogFileControl.logFail("Verify search result", "Search result is not displaying based on search condition");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}



	@Then("^I delete report$")
	public static void deleteReport() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				seleniumUtils.Click(Locator.XPATH, expand_xpath, "Report", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, delete_xpath, "Report", "Delete");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, yes_xpath, "Report", "Yes");
				verifyMessage_containingText("Please wait while audit is being archived");
				Thread.sleep(1000);
				verifyMessage_containingText("Audit archived successfully");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I download report$")
	public static void downloadReport() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				seleniumUtils.Click(Locator.XPATH, expand_xpath, "Report", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, download_xpath, "Report", "Download");
				verifyMessage_containingText("Please wait while the audit report is downloading");
				Thread.sleep(1000);
				verifyMessage_containingText("Your report has been successfully downloaded");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I regenerate report$")
	public static void regenerateReport() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				seleniumUtils.Click(Locator.XPATH, expand_xpath, "Report", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, regenerate_xpath, "Report", "Regenerate Report");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, yes_xpath, "Report", "Yes");
				verifyMessage("Please wait. Report is generating...");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I restore report$")
	public static void restoreReport() {
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			seleniumUtils.Click(Locator.XPATH, deletedTab_xpath, "Report", "Archived/Deleted");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				seleniumUtils.Click(Locator.XPATH, expand_xpath, "Report", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, restore_xpath, "Report", "Restore");
				verifyMessage_containingText("Please wait while audit is being restored");
				Thread.sleep(1500);
				verifyMessage_containingText("Audit restored successfully");

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^I change the status to approve and verify$")
	public static void submitforApproval_CompleteAndVerify() {
		try {
			String refid= editReport_submitForApproval("Complete");
			verifyReportByStatusandRefId(refid, "Completed");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I change the status to reject and verify$")
	public static void submitforApproval_RejectAndVerify() {
		try {
			String refid= editReport_submitForApproval("Reject");
			verifyReportByStatusandRefId(refid, "Rejected");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I change the status from complete to reject and verify$")
	public static void complete_RejectAndVerify() {
		try {
			String refid= editReport_complete("Reject");
			verifyReportByStatusandRefId(refid, "Rejected");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Then("^I change the status from complete to in progress and verify$")
	public static void complete_inProgressAndVerify() {
		try {
			String refid= editReport_complete("in progress");
			verifyReportByStatusandRefId(refid, "In Progress");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static String editReport_complete(String tobeConvertedStatus) {
		String refId="";
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			refId = seleniumUtils.GetText(Locator.XPATH, "//tr[1]//td[1]");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				seleniumUtils.Click(Locator.XPATH, expand_xpath, "Report", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, edit_xpath, "Report", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(5000);
				seleniumUtils.Click(Locator.NAME, "selected_status", "Report", "Status");
				Thread.sleep(1000);
				if(tobeConvertedStatus.toLowerCase().contains("reject")) {
					seleniumUtils.Click(Locator.XPATH, rejectOption_xpath, "Report", tobeConvertedStatus);
					Thread.sleep(2000);
					seleniumUtils.Click(Locator.XPATH, save_xpath, "Report", "Save");
					Thread.sleep(1000);
					seleniumUtils.SendKeys(Locator.NAME, "comments", "Test Reject", "Report", "Comments for rejecting");
					Thread.sleep(1000);
					seleniumUtils.Click(Locator.XPATH, "//button//span[contains(text(),'Confirm')]", "Report", "Confirm");
					verifyMessage_containingText("Please wait");
					Thread.sleep(2000);
					verifyMessage_containingText("Audit is been rejected");
				}else {
					seleniumUtils.Click(Locator.XPATH, inprogress_xpath, "Report", tobeConvertedStatus);
					Thread.sleep(1000);
					seleniumUtils.Click(Locator.XPATH, save_xpath, "Report", "Save");
					verifyMessage_containingText("Saving Audit Details. Please wait...");
					Thread.sleep(1500);
					verifyMessage_containingText("Audit has been saved.");
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return refId;
	}
	
	public static String editReport_submitForApproval(String tobeConvertedStatus) {
		String refId="";
		try {
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(5000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			refId = seleniumUtils.GetText(Locator.XPATH, "//tr[1]//td[1]");
			if(seleniumUtils.IsDisplayed(Locator.XPATH, expand_xpath)) {
				seleniumUtils.Click(Locator.XPATH, expand_xpath, "Report", "Expand");
				Thread.sleep(200);
				seleniumUtils.Click(Locator.XPATH, edit_xpath, "Report", "Edit");
				seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
				Thread.sleep(5000);
				if(tobeConvertedStatus.toLowerCase().contains("complete")) {
				seleniumUtils.Click(Locator.XPATH, complete_xpath ,"Report", tobeConvertedStatus);
				verifyMessage_containingText("Approving audit. Please wait...");
				Thread.sleep(2000);
				verifyMessage_containingText("Audit is been approved.");
				}else {
					seleniumUtils.Click(Locator.XPATH, reject_xpath, "Report", tobeConvertedStatus);
					Thread.sleep(2000);
					seleniumUtils.SendKeys(Locator.NAME, "comments", "Test Reject", "Report", "Comments for rejecting");
					seleniumUtils.Click(Locator.XPATH, confirm_xpath, "Report", "Confirm");
					verifyMessage_containingText("Please wait");
					Thread.sleep(2000);
					verifyMessage_containingText("Audit is been rejected");
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return refId;
	}
	
	public static void verifyReportByStatusandRefId(String refID, String expectedStatus) {
		try {
			search("Status", expectedStatus);
			Thread.sleep(3000);
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(3000);
			boolean flag = true,flag1 = false;
			while(flag) {
				int rowSize = seleniumUtils.Size(Locator.XPATH, "//thead[@class='p-datatable-thead']/../tbody//tr");
				for(int i=1;i<=rowSize;i++) {
					String text = seleniumUtils.GetText(Locator.XPATH, "//thead[@class='p-datatable-thead']/../tbody//tr["+i+"]//td[1]").trim();
					if(text.contains(refID)) {
						flag1=true;
						break;
					}
					
				}
				if(flag1==false ) {
					if(seleniumUtils.IsDisplayed(Locator.XPATH, nextPage_xpath)) {
					seleniumUtils.Click(Locator.XPATH, nextPage_xpath, "Audit Workflow", "Next Page");
					seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
					Thread.sleep(3000);
				}else {
					flag=false;
					rowSize = seleniumUtils.Size(Locator.XPATH, "//thead[@class='p-datatable-thead']/../tbody//tr");
					for(int i=1;i<=rowSize;i++) {
						String text = seleniumUtils.GetText(Locator.XPATH, "//thead[@class='p-datatable-thead']/../tbody//tr["+i+"]//td[1]").trim();
						if(text.contains(refID)) {
							flag1=true;
							break;
						}
						
					}
				}
			}else {
				break;
			}
			}
			if(flag1)
				LogFileControl.logPass("Verify Report after status change", "Status changed successfully");
			else
				LogFileControl.logPass("Verify Report after status change", "Status didn't changed successfully");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
