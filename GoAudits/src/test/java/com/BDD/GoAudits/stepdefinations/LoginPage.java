package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.*;

public class LoginPage extends Base{
	
	@Given("^I Logged in with valid credentials$")
	public static void i_Logged_in_with_valid_credentials() throws Exception {
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

	public static void login(String userName,String password) throws Exception {
		seleniumUtils.SendKeys(Locator.XPATH, "//input[@formcontrolname='username']", userName, "Login", "User Name");
		seleniumUtils.SendKeys(Locator.XPATH, "//input[@formcontrolname='password']", password, "Login", "Password");
		seleniumUtils.Click(Locator.XPATH, "//span[text()='Login']", "Login", "Login");
	   
	}
	
	public static void login_Analytic(String userName,String password) throws Exception {
		seleniumUtils.SendKeys(Locator.NAME, "username", userName, "Login", "User Name");
		seleniumUtils.SendKeys(Locator.NAME, "password", password, "Login", "Password");
		seleniumUtils.Click(Locator.NAME, "login-submit", "Login", "Login");
	   
	}
	
	@Then("^I Logged out$")
	public static void logout() throws Exception {
		seleniumUtils.Click(Locator.XPATH, "//img[@alt='User']",  "Login", "User Logo");
		Thread.sleep(1000);
		seleniumUtils.Click(Locator.XPATH, "//a[text()='Logout ']", "Login", "Logout");
		Thread.sleep(3000);
	}

	

}
