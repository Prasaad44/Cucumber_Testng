package com.frmk.pageobjects;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;
import com.frmk.genericfunctions.WrapperMethods;
import com.frmk.testrunner.BaseTestRunner;
import com.frmk.utilities.ReadProperties;

public class SignUpPage extends BaseTestRunner {

	public String SignupLink = ReadProperties.locatorMap.get("SignUpLink");
	public String selDropVal = ReadProperties.locatorMap.get("SelectEdition");
	public String ChatText = ReadProperties.locatorMap.get("ChatText");
	public String ChatClose = ReadProperties.locatorMap.get("ChatClose");
	public String FrameElement = ReadProperties.locatorMap.get("FrameElement");
	public String chatFrameName = "intercom-borderless-frame";
	public String FreeCRMLogo = ReadProperties.locatorMap.get("FreeCRMLogo");
	public String enterFirstName = ReadProperties.locatorMap.get("FirstName");
	public String enterLastName = ReadProperties.locatorMap.get("LastName");
	public String enterEmail = ReadProperties.locatorMap.get("Email");
	public String enterConfirmEmail = ReadProperties.locatorMap.get("ConfirmEmail");
	public String enterUsername = ReadProperties.locatorMap.get("Username");
	public String enterPassword = ReadProperties.locatorMap.get("Password");
	public String enterConfirmPassword = ReadProperties.locatorMap.get("ConfirmPassword");
	public String inputCheck = ReadProperties.locatorMap.get("InputCheckbox");
	public String submitForm = ReadProperties.locatorMap.get("Submit");

	WrapperMethods element = new WrapperMethods();

	public boolean checkLogo() {
		element.switchDefaultContent();
		boolean flag = false;
		try {
			Thread.sleep(2000);
			if (element.checkElementPresent(FreeCRMLogo) > 0)
				flag = true;
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}
		return flag;
	}

	public boolean clickSignup() {
		boolean flag = false;
		try {
			element.waitUntilElementDisplay(10, SignupLink);
			if (element.checkElementPresent(SignupLink) > 0) {
				element.clickOnElement(SignupLink);
				flag = true;
			}
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}
		return flag;
	}

	public boolean selectEdition() {
		boolean flag = false;
		try {
			element.waitUntilElementDisplay(30, selDropVal);
			element.selectDropDownByText(selDropVal, "Free Edition");
			flag = true;
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}
		return flag;
	}

	public boolean enterDetails(List<List<String>> detail) {
		boolean flag = false;
		try {
			element.waitUntilElementDisplay(30, submitForm);
			element.enterText(enterFirstName, detail.get(0).get(0));
			element.enterText(enterLastName, detail.get(0).get(1));
			element.enterText(enterEmail, detail.get(0).get(2));
			element.enterText(enterConfirmEmail, detail.get(0).get(2));
			element.enterText(enterUsername, detail.get(0).get(3));
			element.enterText(enterPassword, detail.get(0).get(4));
			element.enterText(enterConfirmPassword, detail.get(0).get(4));
			flag = true;
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}
		return flag;
	}

	public boolean submitForm() {
		boolean flag = false;
		try {
			element.clickOnElement(inputCheck);
			element.clickOnElement(submitForm);
			flag = true;
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}

		return flag;
	}

	public boolean checkAlert() {
		boolean flag = false;
		try {
			element.switchAlert();
			System.out.println("Alert Switched");
			flag = true;
		} catch (NoAlertPresentException e) {
			logs.logError(e.getLocalizedMessage());
		}
		return flag;
	}

	public void doOnAlert(String operation) {
		element.alertOperation(operation);
	}

}
