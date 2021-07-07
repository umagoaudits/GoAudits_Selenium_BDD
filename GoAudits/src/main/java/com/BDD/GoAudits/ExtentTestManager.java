package com.BDD.GoAudits;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {
	 static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	 public static ExtentReports extent = ExtentManager.getInstance();
	   // public static ExtentReports extentReports;

	    public static synchronized ExtentTest getTest() {
	        return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	    }

	    public static synchronized void endTest() {
	        extent.endTest(extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	    }

	    public static synchronized ExtentTest startTest(String testName) {
	        return startTest(testName, "");
	    }

	    public static synchronized ExtentTest startTest(String testName, String desc) {
	        ExtentTest test = extent.startTest(testName, desc);
	        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

	        return test;
	    }
	    private static final ThreadLocal<ExtentTest>  logger = new ThreadLocal<ExtentTest>();
	    public static ExtentTest getlogger() {
	    	ExtentTest e = logger.get();
			return (logger.get());
		}

		public static void setlogger(ExtentTest extentTest) {
			logger.set(extentTest);
		}

}
