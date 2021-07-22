package com.BDD.GoAudits.stepdefinations;

import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.Locators.Locator;
import com.BDD.GoAudits.utility.Base;

import cucumber.api.java.en.Then;

public class HomePage extends Base{
	
	/**
	 * @param arg1
	 * @throws Exception
	 */
	public static final String spinning_xpath = "//div[contains(@class,'spinner')]";
	
	
	@Then("^I navigated to \"([^\"]*)\" menu$")
	public static void i_navigated_to_menu(String arg1) throws Exception {
		String a=arg1;
		if(seleniumUtils.Size(Locator.XPATH, "//a[contains(text(),'Skip to home')]")>0) {
			//seleniumUtils.Click(Locator.XPATH, "//a[contains(text(),'Skip to home')]", "Home", "Skip To Home");
			DriverManager.getDriver().get("https://admin.goaudits.com/#/managereports");
			seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
			Thread.sleep(2000);
		}
		String module1, module2 = arg1;
	    seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
	    Thread.sleep(5000);
		if(arg1.contains("-")) {
			 module1 =  arg1.split("-")[0];
			 module2 = arg1.split("-")[1];
			seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//*[contains(@title,'"+module1+"')]");
		    seleniumUtils.Click(Locator.XPATH, "//*[contains(@title,'"+module1+"')]", "Home", module1);
		    seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		}
			 seleniumUtils.waitforElementToBeClickable(Locator.XPATH, "//a[contains(@title,'"+module2+"')]");
			    seleniumUtils.Click(Locator.XPATH, "//a[contains(@title,'"+module2+"')]", "Home", module2);
			    seleniumUtils.waitforInvisibilityOfElement(Locator.XPATH, spinning_xpath);
		
	   
	}

}
