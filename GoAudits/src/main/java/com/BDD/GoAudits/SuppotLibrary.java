package com.BDD.GoAudits;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.BDD.GoAudits.Locators.Locator;
import com.relevantcodes.extentreports.NetworkMode;

public class SuppotLibrary {

	static SuppotLibrary suppotLibrary = new SuppotLibrary();
	DataProvider dataProviderLogic = new DataProvider();
	public static String reportFolder=suppotLibrary.getReportFolder();
	
	public static void main(String args[]) {
		System.out.println(getDayCount("July 16, 2021", "August 30, 2022",7,"Jul"));
//		System.out.println(changeDateFormat("July 16, 2021"));
	}

	public String timestamp(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		String reportdate = simpleDateFormat.format(date);
		return reportdate;
	}
	
	public static int getDay(int day){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); 
		c.add(Calendar.DATE, day);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	public static String changeDateFormat(String date){
		String formattedDate = null;
		try {
			DateFormat originalFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
			DateFormat targetFormat = new SimpleDateFormat("MMM d, yyyy");
			Date date1 = originalFormat.parse(date);
			 formattedDate = targetFormat.format(date1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return formattedDate;
	}
	
	public static ArrayList<String> getDayCount(String startDate, String endDate , int dayOfWeek){
		ArrayList<String> dates = new ArrayList<String>();
		try {
			Date date1=new SimpleDateFormat("MMMM dd, yyyy").parse(startDate);
			Date date2=new SimpleDateFormat("MMMM dd, yyyy").parse(endDate);
			Calendar startCal = Calendar.getInstance();
		    startCal.setTime(date1);        

		    Calendar endCal = Calendar.getInstance();
		    endCal.setTime(date2);
		    //Return 0 if start and end are the same
		    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
		        return dates;
		    }

		    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
		        startCal.setTime(date2);
		        endCal.setTime(date1);
		    }
		    SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		    do {
		       //excluding start date
		    	
		        if (startCal.get(Calendar.DAY_OF_WEEK) ==dayOfWeek) {
		            //++workDays;
		            String output = sdf.format(startCal.getTime()); 
		            dates.add(output);
		            //System.out.println(output);
		        }
		        startCal.add(Calendar.DAY_OF_MONTH, 1);
		    } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()); //excluding end date

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    return dates;
	}
	
	public static ArrayList<String> getDayCount(String startDate, String endDate , int dayOfWeek, int weekNo){
		ArrayList<String> dates = new ArrayList<String>();
		try {
			Date date1=new SimpleDateFormat("MMMM dd, yyyy").parse(startDate);
			Date date2=new SimpleDateFormat("MMMM dd, yyyy").parse(endDate);
			Calendar startCal = Calendar.getInstance();
		    startCal.setTime(date1);        

		    Calendar endCal = Calendar.getInstance();
		    endCal.setTime(date2);
		    //Return 0 if start and end are the same
		    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
		        return dates;
		    }

		    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
		        startCal.setTime(date2);
		        endCal.setTime(date1);
		    }
		    SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		    do {
		       //excluding start date
		    	
		        if (startCal.get(Calendar.DAY_OF_WEEK) ==dayOfWeek && startCal.get(Calendar.DAY_OF_WEEK_IN_MONTH)==weekNo) 
		        {
		            //++workDays;
		            String output = sdf.format(startCal.getTime()); 
		            dates.add(output);
		            //System.out.println(output);
		        }
		        startCal.add(Calendar.DAY_OF_MONTH, 1);
		    } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()); //excluding end date

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    return dates;
	}
	
	public static ArrayList<String> getDayCount(String startDate, String endDate , int dateNo,String month){
		ArrayList<String> dates = new ArrayList<String>();
		try {
			SimpleDateFormat month_date = new SimpleDateFormat("MMM");
			int mIndex=0;
			for(int i=0;i<12;i++) {
				Calendar Cal = Calendar.getInstance();
				Cal.set(Calendar.MONTH,i);
			String month_name = month_date.format(Cal.getTime());
			if(month_name.contains(month)) {
				mIndex=i;
				break;
			}
			}
			Date date1=new SimpleDateFormat("MMMM dd, yyyy").parse(startDate);
			Date date2=new SimpleDateFormat("MMMM dd, yyyy").parse(endDate);
			Calendar startCal = Calendar.getInstance();
		    startCal.setTime(date1);        

		    Calendar endCal = Calendar.getInstance();
		    endCal.setTime(date2);
		    //Return 0 if start and end are the same
		    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
		        return dates;
		    }

		    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
		        startCal.setTime(date2);
		        endCal.setTime(date1);
		    }
		    SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
		    do {
		       //excluding start date
		    	
		        if (startCal.get(Calendar.DATE) ==dateNo && startCal.get(Calendar.MONTH)==mIndex) 
		        {
		            //++workDays;
		            String output = sdf.format(startCal.getTime()); 
		            dates.add(output);
		            //System.out.println(output);
		        }
		        startCal.add(Calendar.DAY_OF_MONTH, 1);
		    } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()); //excluding end date

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	    return dates;
	}
	
	public String getCurrentTime(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		String reportdate = simpleDateFormat.format(date);
		return reportdate;
	}
	public String getCurrentTime5digit(){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hmmss");
		String reportdate = simpleDateFormat.format(date);
		return reportdate;
	}
	
	public static String getDate(int day) {
		// TODO Auto-generated method stub
		String output = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); 
			c.add(Calendar.DATE, day); 
			output = sdf.format(c.getTime());
			System.out.println(output);

		}catch(Exception e){

		}
		return output;
	}
	
	public static ArrayList<String>getDates(int dayCount){
		ArrayList<String>dates = new ArrayList<String>();
		for(int i = 0;i<=dayCount;i++) {
			dates.add(suppotLibrary.getDate(i));
		}
		return dates;
	}
	
	public void createFile(String path)
	{

		File theDir = new File(path);  // Defining Directory/Folder Name  
		try{   
			if (!theDir.exists()){  // Checks that Directory/Folder Doesn't Exists!  
				theDir.mkdir();    
			}  
		}catch(Exception e){  
			//JOptionPane.showMessageDialog(null, e);  
		}  
	}

	public NetworkMode getnetWorkmode(){
		NetworkMode accessType;
		//if(dataProviderLogic.getPropertyval("NetworkMode").equalsIgnoreCase("offline")){
			accessType=NetworkMode.OFFLINE;
//		}else{
//			accessType=NetworkMode.ONLINE;
//		}
		return accessType;
	}

	public String getReportFolder(){
		String path=null;
		if(reportFolder==null){
		path=System.getProperty("user.dir") +"\\Report\\"+ timestamp()+"\\";
		createFile(path);
		}
		return path; 
	}

	public String getReportPath(){

		//String path=reportFolder+scriptId+"-"+timestamp()+".html";
		String path=reportFolder+"Automation Execution Report.html";
		return path; 
	}

	public String getScreenshotPath(String scriptId){
		String path=reportFolder+scriptId+"-"+timestamp()+".png";
		return path; 
	}

	public String screenCapture(WebDriver driver,String scriptId) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = reportFolder+scriptId+"-"+timestamp()+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);        

		return dest;
	}
	
	public void uploadDoc(Locator locatorType, String locatorValue, String filePathToBeUploaded,String elementName) throws IOException
	{
		try {
			SeleniumUtils su= new SeleniumUtils();
			su.MoveToElement(Locator.XPATH, locatorValue);
			su.Click(Locator.XPATH, locatorValue, DriverManager.getDriver().getTitle(), elementName);
			//String path = System.getProperty("user.dir") + "\\Log4j.properties";
			StringSelection data = new StringSelection
					(filePathToBeUploaded);
			Clipboard cb = Toolkit.getDefaultToolkit()
					.getSystemClipboard();
			cb.setContents(data, data);
			Thread.sleep(300);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);        // C
			r.keyPress(KeyEvent.VK_V);    // : (colon)
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);    // / (slash)
			Thread.sleep(1500);
			r.keyPress(KeyEvent.VK_ENTER);    // confirm by pressing Enter in the end
			r.keyRelease(KeyEvent.VK_ENTER);        

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public double getdecimalRounder(String decimalRound){
		int dr=Integer.valueOf(decimalRound);
		double rounder=1;
		for(int j=0;j<dr;j++){
			rounder=rounder*10.0;
		}
		return rounder;
	}
	
	public HashMap<String, String> getObjectDetails(String repositoryName,String elementName){
		if(elementName.contains(" ")){
			elementName = elementName.split(" ")[0];
		}
		String object = dataProviderLogic.getPropertyvalue(repositoryName,elementName);
		HashMap<String, String> objectDetails = new HashMap<String, String>();
		if(object.contains("=\"")){
			String objectSplit[] = object.split("=\"");
			objectDetails.put("LocatorType", objectSplit[0].toUpperCase());
			objectDetails.put("LocatorValue", objectSplit[1].replace("\"", ""));
		}
		return objectDetails;
		
	}
}
