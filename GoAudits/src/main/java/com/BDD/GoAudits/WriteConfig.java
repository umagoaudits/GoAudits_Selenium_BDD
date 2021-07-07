package com.BDD.GoAudits;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteConfig {

	DataProvider dataProvider = new DataProvider();

	public void storeData(String key, String value){
		writeData(key,value,"DataStore.properties");
	}

	public void storeCalculationData(String key, String value){
		writeData(key,value,"Calculation.properties");
	}

	public void storeSetUpData(String key, String value){
		writeData(key,value,"DMSSetUpData.properties");
	}

	//	public void storePromotionData(String key, String value){
	//		writeData(key,value,"PromotionData.properties");
	//	}

	public void updateUser(String file,String key, String value){
		writeData(key,value,file+".properties");
		System.out.println("Credentials updated as "+value);
	}

	private void writeData(String key, String value, String fileName){

		try {
			FileInputStream in = new FileInputStream(fileName);
			Properties props = new Properties();
			props.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream(fileName);
			props.setProperty(key, value);
			props.store(out, null);
			out.close();
		} catch (FileNotFoundException ex) {
			// file does not exist
		} catch (IOException ex) {
			// I/O error
		}
	}

	public String storeObjectValue(String key, String value){
		try {
			String data = dataProvider.getPropertyvalue("ObjectRepository", key);
			if(data==null){
				if(value == null){
					System.out.println("Object Value of "+key+" is blank. Please provide object value");
					key = "Blank Object";
				}else{
				writeData(key,value,"ObjectRepository.properties");
				}
			}else{
					if(value != null && !data.contentEquals(value)){
					key = key+1;
					storeObjectValue(key, value);
				}
			}
		} catch (Exception e) {
			writeData(key,value,"ObjectRepository.properties");
		}
		return key;
		
	}

	

}
