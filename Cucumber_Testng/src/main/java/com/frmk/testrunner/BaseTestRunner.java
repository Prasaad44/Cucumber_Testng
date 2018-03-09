package com.frmk.testrunner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.cucumber.listener.ExtentProperties;
import com.frmk.genericfunctions.DriverSetup;
import com.frmk.genericfunctions.KillProcess;
import com.frmk.utilities.Logging;
import com.frmk.utilities.ReadProperties;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "./CucumberFeatures", glue = { "com.frmk.testcases" }, plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:" }, monochrome = true)

public class BaseTestRunner {

	public static WebDriver driver;
	public DriverSetup dvrstp;
	public static String userDirectory = System.getProperty("user.dir");
	public static String osName = "";
	public static Logging logs = new Logging();

	@BeforeSuite(alwaysRun = true)
	@Parameters({ "Browser", "Environment", "TestEnvironment", "OS" })
	public void initiateDriver(@Optional String Browser, @Optional String Environment, @Optional String TestEnvironment,
			@Optional String OS) {

		loadPropertiesFiles();
		loadReportingFile();

		dvrstp = new DriverSetup();

		if (Environment != null) {
			if (OS.equalsIgnoreCase("windows")) {
				osName = OS;
				if (Browser == null) {
					logs.logWarn("Please specify the Browser Name in Testng Xml");
				} else if (Browser.equalsIgnoreCase("chrome") || Browser.equalsIgnoreCase("googlechrome")) {
					dvrstp.chromeDriverSetup();
					dvrstp.initiateChromeDriver();
				} else if (Browser.equalsIgnoreCase("Firefox") || Browser.equalsIgnoreCase("FF")) {
					dvrstp.geckoDriverSetup();
					dvrstp.initiateFirefoxDriver();
				}
			}
			
			
		} else {
			logs.logWarn("Please specify the Environment in Testng Xml");
		}
	}

	@Test
	public void RunCukes() {
		new TestNGCucumberRunner(getClass()).runCukes();
	}

	@AfterSuite
	public void quitDrivers() {
		if (driver instanceof ChromeDriver) {
			KillProcess.killChromeDriver();

		} else if (driver instanceof FirefoxDriver) {
			KillProcess.killGeckoDriver();
		}
		driver.quit();
	}

	public static void loadPropertiesFiles() {
		ReadProperties.readPropertiesFile();
	}

	
	public static void loadReportingFile() {
		ExtentProperties exProp = ExtentProperties.INSTANCE;
		exProp.setReportPath(userDirectory+"/Reports/cucumber-reports/AutomationReport.html");
	}


}
