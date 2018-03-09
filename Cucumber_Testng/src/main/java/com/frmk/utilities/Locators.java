package com.frmk.utilities;

import org.openqa.selenium.By;

public class Locators extends Logging {

	public static By getBySelector(String property) {

		By returnElement = null;
		String[] splits = property.split("=");

		String locatorType = splits[0].toLowerCase();
		String expression = "";
		if(splits.length>2)
		{			
			for (int i = 1; i < splits.length; i++) {
				if(i==1)
				{
					expression = expression + splits[i];
				}
				if(i>=2) {
				expression = expression +"="+ splits[i];
				}
			}
		}
		switch (locatorType) {

		case "xpath":
			returnElement = By.xpath(expression);
			break;

		case "css":
			returnElement = By.cssSelector(expression);
			break;

		case "id":
			returnElement = By.id(expression);
			break;

		case "name":
			returnElement = By.name(expression);
			break;

		case "tagname":
			returnElement = By.tagName(expression);
			break;

		case "linktext":
			returnElement = By.linkText(expression);
			break;

		case "partiallink":
			returnElement = By.partialLinkText(expression);
			break;

		case "classname":
			returnElement = By.className(expression);
			break;

		case "class":
			returnElement = By.className(expression);
			break;

		default:
			logs.error("Invalid locatorType : " + locatorType);
			break;
		}

		return returnElement;
	}

}
