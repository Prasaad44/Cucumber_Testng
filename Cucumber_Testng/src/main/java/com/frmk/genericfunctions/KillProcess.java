package com.frmk.genericfunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.frmk.testrunner.BaseTestRunner;
import com.frmk.utilities.Logging;

public class KillProcess {

	static Logging logs = new Logging();
	
	
	public static void killChromeDriver() {
		String processName = "chromedriver.exe";
		if (checkRun(processName)) {
			killCurrentProcess(processName);
		}
	}

	public static void killGeckoDriver() {
		String processName = "geckodriver.exe";
		if (checkRun(processName)) {
			killCurrentProcess(processName);
		}
	}

	private static boolean checkRun(String processName) {
		boolean flag = false;
		if (BaseTestRunner.osName.equalsIgnoreCase(getOSName())) {
			try {

				Process p = Runtime.getRuntime().exec("tasklist");
				BufferedReader read = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String currentProcess;
				while ((currentProcess = read.readLine()) != null) {
					if (currentProcess.contains(processName)) {
						logs.logInfo(processName + " is Running");
						flag = true;
					}
				}
			} catch (Exception e) {
				flag = false;
			}
		} else {
			logs.logWarn("Can't Kill Process. Exeuction OS is not Windows");
		}
		return flag;
	}

	private static boolean killCurrentProcess(String processName) {
		boolean flag = false;
		
		logs.logInfo("Killing Process : " + processName);
		
		String KILL = "taskkill /F /T /IM ";
		try
		{
			Runtime.getRuntime().exec(KILL+processName);
			flag = true;
		}
		catch(IOException e)
		{
			flag = false;
		}
		
		return flag;
	}

	public static String getOSName() {
		String osName = System.getProperty("os.name");
		osName = osName.replaceAll("[^a-zA-Z]", " ");
		return osName.toLowerCase().trim();
	}

}
