package com.BDD.GoAudits;

import java.util.List;


public interface GAUtils {

	/**
	 * @version 1.01
	 * @author Uma
	 * @param action (only pass "Accept" or "Dismiss")
	 * @description This method will allow to dismiss or accept the alert based on the input
	 */
	public abstract void actionAlert(String action);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param pageName
	 * @return the Alert message
	 * @description This method will get the alert message and accept the alert 
	 */
	public abstract String getAlertmessage(String pageName);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param day (After how many days you want the date e.g 7 days)
	 * @return (Date in string format)
	 */
	public abstract String getDate(String format, int day);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param day (After how many days you want the date e.g 7 days)
	 * @return (Date in string format)
	 */
	public abstract String getDate(int day);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param expectedDate 
	 * @param actualDate
	 * @param pageName
	 * @description to verify expected date is only displaying as actual date. (expectedDate and actualDate should be in same format)
	 */
	public abstract void verifyDate(String actualDate, String expectedDate,
			String pageName);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param expectedText 
	 * @param actualText
	 * @param pageName
	 * @description to verify expected Text is only displaying as actual Text. 
	 */
	public abstract void verifyText(String actualText, String expectedText,
			String pageName);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param actual
	 * @param expected
	 * @param pageName
	 * @description to verify expected list of Text is only displaying as actual list of Text.
	 */
	public abstract void verifyList(List<String> actual, List<String> expected,
			String pageName);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify the box is text box or not
	 */
	public abstract void isTextBox(String locatorType, String locatorValue,
			String labelName);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify the box is dropdown or not
	 */
	public abstract void isDropDown(String locatorType, String locatorValue,
			String labelName);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify webelement is disable or not
	 */
	public abstract boolean isDisabled(String locatorType, String locatorValue,
			String labelName);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify webelement is disable or not
	 */
	public abstract void verifyWebElement(boolean actualStatus,
			boolean expectedStatus, String labelName);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param locatorType
	 * @param locatorValue
	 * @param labelName
	 * @description to verify the element is disappeared or not
	 */
	public abstract void doHide(String locatorType, String locatorValue,
			String labelName);

	
	/**
	 * @version 1.01
	 * @author Uma
	 * @method name - GetDatetime()
	 * @Method Description - This method will return date and time in this 'yyMMddhhmm' format 
	 */
	public abstract String getDatetime();

	/**
	 * @version 1.01
	 * @author Uma
	 * @method name - getDatetime()
	 * @Method Description - This method will return date and time in this 'yyMMddhhmm' format 
	 */
	public abstract String getDatetimeinSec();

	
	/**
	 * @version 1.01
	 * @author Uma
	 * @param digitNo
	 * @return
	 */
	public abstract int randomGenerator(int digitNo);

	/**
	 * @version 1.01
	 * @author Uma
	 * @param low
	 * @param high
	 * @return
	 */
	public abstract int randomGenerator(int low, int high);

}