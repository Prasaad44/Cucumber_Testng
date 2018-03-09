package com.frmk.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import com.frmk.testrunner.BaseTestRunner;

public class ReadProperties extends BaseTestRunner {

	static File dirFile = new File(BaseTestRunner.userDirectory + "/Configurations/");
	static FileInputStream fisFile;
	public static HashMap<String,String> configMap = new HashMap<String,String>();
	public static HashMap<String,String> locatorMap = new HashMap<String,String>();
	
	public static File[] returnAllFiles() {
		File[] fileLists = null;

		if (dirFile.exists()) {
			fileLists = dirFile.listFiles();
		} else {
			logs.logWarn(dirFile + " is not Found. Please enter a valid path");
		}
		return fileLists;
	}



	public static void readPropertiesFile() {
		
		File[] getFiles = returnAllFiles();
		Properties prop = new Properties();
		
		try {
			
			for(File f:getFiles)
			{
				String fileName = f.getName();
				fisFile = new FileInputStream(f);
				
				prop.load(fisFile);
					
					for(String key: prop.stringPropertyNames())
					{
						String value = prop.getProperty(key).toString();						
						if(fileName.equalsIgnoreCase("DataConfig.properties"))
						{
							configMap.put(key, value);
						} else if(fileName.equalsIgnoreCase("PageLocators.properties"))
						{
							locatorMap.put(key, value);
						}
						
					}
					
					fisFile.close();
					prop.clear();
			}
			
			
		} catch (Exception  e) {
			logs.logWarn("Properties File not Found");
		} 
	}

}
