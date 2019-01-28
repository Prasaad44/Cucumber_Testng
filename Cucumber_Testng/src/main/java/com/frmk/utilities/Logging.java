package com.frmk.utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.cucumber.listener.Reporter;
import com.frmk.testrunner.BaseTestRunner;

public class Logging {

	public static Logger logs = Logger.getRootLogger();
	public static Logger logger = Logger.getLogger("appFramLog");

	static {
		
		System.setProperty("rootFile.log", BaseTestRunner.userDirectory+"/Reports/TraceLogs/AllAppLogs.log");
		System.setProperty("appFile.log", BaseTestRunner.userDirectory+"/Reports/TraceLogs/Customised.log");
		PropertyConfigurator.configure("./Log4j/log4j.properties");

	}

	public void logInfo(String message) {
		logger.info(message);
		Reporter.addStepLog("INFO : " + message);
	}

	public void logWarn(String message) {
		logger.warn(message);
		Reporter.addStepLog("INFO : " + message);
	}

	public void logTrace(String message) {
		logger.trace(message);
		Reporter.addStepLog("TRACE : " + message);
	}

	public void logDebug(String message) {
		logger.debug(message);
		Reporter.addStepLog("DEBUG : " + message);
	}

	public void logError(String message) {
		logger.error(message);
		Reporter.addStepLog("ERROR : " + message);
	}

	public void logFatal(String message) {
		logger.fatal(message);
		Reporter.addStepLog("FATAL : " + message);
	}

}
