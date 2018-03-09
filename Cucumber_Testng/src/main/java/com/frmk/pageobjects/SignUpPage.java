package com.frmk.pageobjects;

import org.openqa.selenium.NoSuchElementException;

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

	WrapperMethods element = new WrapperMethods();

	public boolean clickSignup() {
		boolean flag = false;
		try {
			if (element.checkElementPresent(SignupLink) > 0)
				element.clickOnElement(SignupLink);
			flag = true;
		} catch (NoSuchElementException e) {	
			element.switchFrameByName(chatFrameName);
			if(element.checkElementPresent(ChatText)>0)
			{
				element.moveToElement(ChatText);
				element.waitUntilElementDisplay(10, ChatClose);
				element.clickOnElement(ChatClose);
				element.switchDefaultContent();
				clickSignup();
			} else {			
			logs.logError(e.getLocalizedMessage());
			}
		}
		return flag;
	}

	
	public boolean selectEdition()
	{
		boolean flag = false;
		try {
			element.waitUntilElementDisplay(30, selDropVal);			
			element.selectDropDownByText(selDropVal, "Free Edition");	
			flag = true;
		}catch(Exception e)
		{
			logs.logError(e.getLocalizedMessage());
		}
		return flag;
	}
	
	
	
}
