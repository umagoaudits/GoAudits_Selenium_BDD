package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.*;

public class LoginPage extends Base{
	
	@Given("^I Logged in with valid credentials$")
	public void i_Logged_in_with_valid_credentials() throws Exception {
		seleniumUtils.SendKeys(Locator.XPATH, "//input[@formcontrolname='username']", dataProvider.getConfigPropertyval("User Name"), "Login", "User Name");
		seleniumUtils.SendKeys(Locator.XPATH, "//input[@formcontrolname='password']", dataProvider.getConfigPropertyval("Password"), "Login", "Password");
		seleniumUtils.Click(Locator.XPATH, "//span[text()='Login']", "Login", "Login");
	   
	}
	
//	@Given("^I Logged in with valid credentials$")
//	public void i_Logged_in_() throws Exception {
//		seleniumUtils.SendKeys(Locator.XPATH, "//input[@formcontrolname='username']", readCSV.getCSVValue(getCSVFileName(), getScriptId(), "User Name", 1), "Login", "User Name");
//		seleniumUtils.SendKeys(Locator.XPATH, "//input[@formcontrolname='password']", "Dinesh_452", "Login", "Password");
//		seleniumUtils.Click(Locator.XPATH, "//span[text()='Login']", "Login", "Login");
//	   
//	}

	

	

}
