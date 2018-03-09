package com.frmk.genericfunctions;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import com.frmk.testrunner.BaseTestRunner;

public class DriverSetup extends BaseTestRunner {

	private File targetFile;
	
	public void chromeDriverSetup() {
		String driverValue = "webdriver.chrome.driver";
		String chromeDriverPath = userDirectory + File.separator + "Drivers" + File.separator + "chromedriver.exe";
		targetFile = new File(chromeDriverPath);
		if (targetFile.exists()) {
			System.setProperty(driverValue, chromeDriverPath);
		} else {
			logs.logWarn("ChromeDriver not found. Enter a valid Path");
		}
		targetFile = null;
	}

	public void initiateChromeDriver() {
		HashMap<String, Object> optMap = new HashMap<String, Object>();
		optMap.put("profile.default_content_settings.popups", 0);
		optMap.put("download.prompt_for_download", false);

		ChromeOptions chOpt = new ChromeOptions();
		chOpt.addArguments("start-maximized");
		chOpt.setExperimentalOption("prefs", optMap);

		driver = new ChromeDriver(chOpt);

	}

	public void geckoDriverSetup() {
		String driverValue = "webdriver.gecko.driver";
		String geckoDriverPath = userDirectory + File.separator + "Drivers" + File.separator + "geckodriver.exe";
		targetFile = new File(geckoDriverPath);
		if (targetFile.exists()) {
			System.setProperty(driverValue, geckoDriverPath);
		} else {
			logs.logWarn("GeckoDriver not found. Enter a valid Path");
		}
		targetFile = null;
	}

	public void initiateFirefoxDriver() {

		FirefoxProfile ffPro = new FirefoxProfile();
		ffPro.setAcceptUntrustedCertificates(true);
		ffPro.setAssumeUntrustedCertificateIssuer(true);

		FirefoxOptions ffOpt = new FirefoxOptions();
		ffOpt.setProfile(ffPro);

		driver = new FirefoxDriver(ffOpt);
	}

}
