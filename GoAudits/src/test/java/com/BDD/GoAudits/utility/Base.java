/**
 * 
 */
package com.BDD.GoAudits.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.BDD.GoAudits.DataProvider;
import com.BDD.GoAudits.DriverManager;
import com.BDD.GoAudits.ExtentManager;
import com.BDD.GoAudits.ExtentTestManager;
import com.BDD.GoAudits.LogFileControl;
import com.BDD.GoAudits.ReadCSV;
import com.BDD.GoAudits.SeleniumUtils;
import com.BDD.GoAudits.SuppotLibrary;
import com.BDD.GoAudits.Utils;
import com.BDD.GoAudits.WriteConfig;
import com.BDD.GoAudits.Locators.Locator;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;



public class Base {
	private static String scenarioName;
	
	
	public static Utils seleniumUtils = new SeleniumUtils();
	public static DataProvider dataProvider = new DataProvider();
	public static DriverManager driverManager = new DriverManager();
	public static ReadCSV readCSV = new ReadCSV();
	public static SuppotLibrary suppotLibrary = new SuppotLibrary();
	public static WriteConfig writeConfig = new WriteConfig();
	public static ThreadLocal<String> threadscriptId=new ThreadLocal<String>() ;
	public static ThreadLocal<String> threadmethodName=new ThreadLocal<String>() ;
	//	private final char PKG_SEPARATOR = '.',DIR_SEPARATOR = '/';
	//	private final String CLASS_FILE_SUFFIX = ".class",BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";
//	private final char PKG_SEPARATOR = dataProvider.getPropertyval("PKG_SEPARATOR").charAt(0),DIR_SEPARATOR = dataProvider.getPropertyval("DIR_SEPARATOR").charAt(0);
//	private final String CLASS_FILE_SUFFIX = dataProvider.getPropertyval("CLASS_FILE_SUFFIX"),BAD_PACKAGE_ERROR = dataProvider.getPropertyval("BAD_PACKAGE_ERROR");
	static Base base=new Base();
	public static String url=null , browser = null, environment = null, group = null,parallelCount =null;
	//	public static ExtentReports extentReports;
	
	public static String getScriptId(){
		return (threadscriptId.get());
	}
	public static void setScriptId(String value) {
		threadscriptId.set(new String(value));
		LogFileControl.setScriptId_report(value);
	}
	private static final ThreadLocal<List<String>> myComponents = new ThreadLocal<List<String>>();

	public static List<String> getComponents() {
		return (myComponents.get());
	}

	
	public void setScenarioIteration(int value) {
		scenarioIteration.set(new Integer(value));
	}

	private static final ThreadLocal<Integer> scenarioIteration = new ThreadLocal<Integer>();

	public static int getScenarioIteration() {
		return (scenarioIteration.get());
	}
	
	public static void setComponents(List<String> value) {
		myComponents.set(new ArrayList<String>(value));
	}

	private static final ThreadLocal<String> excelFileName = new ThreadLocal<String>();

	public static String getExcelName() {
		return (excelFileName.get());
	}

	public void setExcelName(String value) {
		excelFileName.set(new String(value));
	}

	private static final ThreadLocal<String> csvFileName = new ThreadLocal<String>();

	public static String getCSVFileName() {
		return (csvFileName.get());
	}

	public static void setCSVFileName(String value) {
		csvFileName.set(new String(value));
	}

	private static final ThreadLocal<String> excelSheetName = new ThreadLocal<String>();

	public static String getexcelSheet() {
		return (excelSheetName.get());
	}

	public void setExcelSheet(String value) {
		excelSheetName.set(new String(value));
	}

	private static final ThreadLocal<String> mymethods = new ThreadLocal<String>();

	public static String getMethodName() {
		return (mymethods.get());
	}

	public static void setMethodName(String value) {
		mymethods.set(new String(value));
	}

	private static final ThreadLocal<Integer> iteration = new ThreadLocal<Integer>();

	public static int getIterarionCount() {
		return (iteration.get());
	}

	public static void setIterarionCount(int value) {
		iteration.set(new Integer(value));
	}
	
	private static final ThreadLocal<Integer> invocetion = new ThreadLocal<Integer>();

	public static int getInvocationCount() {
		return (invocetion.get());
	}

	public static void setInvocationCount(int value) {
		invocetion.set(new Integer(value));
	}
	
	private static final ThreadLocal<Integer> threadID = new ThreadLocal<Integer>();

	public static int getthreadID() {
		return (threadID.get());
	}

	public static void setthreadID(int value) {
		threadID.set(new Integer(value));
	}

	
	@BeforeSuite(alwaysRun=true)
	public void extentSetup(ITestContext context) {
		try {} catch (Exception e) {
			e.printStackTrace();
		}


	}
	public static final String message_xpath="//div[@aria-live='assertive']";
	public static void verifyMessage(String message) throws Exception {
		seleniumUtils.waitforVisibilityOfElement(Locator.XPATH, message_xpath);
		Thread.sleep(1000);
		String msg= seleniumUtils.GetText(Locator.XPATH, message_xpath);
		System.out.println(msg);
		if(msg.trim().equalsIgnoreCase(message.trim())) {
			LogFileControl.logPass(DriverManager.getDriver().getTitle(), "Popup Message is displaying properly as \""+msg+"\"");
		}else {
			LogFileControl.logFail(DriverManager.getDriver().getTitle(), "Popup Message is not displaying properly");
		}
	}

	
	
	
	
}
