package com.frmk.genericfunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frmk.testrunner.BaseTestRunner;
import com.frmk.utilities.*;


public class WrapperMethods extends BaseTestRunner{
	
	Logging logs = new Logging();
	Actions act = new Actions(driver);
	public WebDriverWait wdWait;
	
	public void pageLoadTimeOut(Long TimeinSec)
	{
		driver.manage().timeouts().pageLoadTimeout(TimeinSec, TimeUnit.SECONDS);
	}
	
	public void clearCookies() {
		driver.manage().deleteAllCookies();
		logs.logInfo("Deleted all cookies");
	}
	
	public void refresh() {
		driver.navigate().refresh();
		logs.logInfo("Page Refreshed");
	}
	
	public void maximize() {
		driver.manage().window().maximize();
	}
	
	
	public void clickOnElement(String property) {		
		try {
			By locator = Locators.getBySelector(property);
			WebElement ele = driver.findElement(locator);
			ele.click();
			logs.logInfo("Clicked on element : "+ele);			
		} catch(Exception e) {
			logs.logError(e.getLocalizedMessage());
		}
		
	}
	
	public void enterText(String property,String text)
	{
		try {
			By locator = Locators.getBySelector(property);
			WebElement ele = driver.findElement(locator);
			ele.clear();
			ele.sendKeys(text);
			logs.logInfo(text + " has been entered");
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}
	}
	
	
	public void clearText(String property)
	{
		try {
			By locator = Locators.getBySelector(property);
			WebElement ele = driver.findElement(locator);
			ele.clear();
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}
	}
	
	
	public int checkElementPresent(String property) {
			By locator = Locators.getBySelector(property);
			List<WebElement> ele = driver.findElements(locator);			
			return ele.size();			 		
	}
	
	
	public void waitUntilElementDisplay(int lg,String property)
	{	
		By ele = Locators.getBySelector(property);
		wdWait = new WebDriverWait(driver,lg);
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(ele));		
	}
	
	
	public void selectDropDownByText(String property,String text) throws Exception
	{		
			By locator = Locators.getBySelector(property);
			WebElement ele = driver.findElement(locator);
			Select sel = new Select(ele);
			List<WebElement> selEle = sel.getOptions();
			if(!selEle.isEmpty())
			{
				for (WebElement ele1 : selEle) {
					if (ele1.getText().equals(text)) {
						ele1.click();
					}
				}
			}else
			{
				logs.logWarn("Dropdown is Empty");
			}
	}
	
	
	public void switchFrameByName(String text)
	{
		driver.switchTo().frame(text);		
	}
	
	public void switchFrameById(int n)
	{
		driver.switchTo().frame(n);
	}
	
	public void switchFrameByElement(String property)
	{
		By locator = Locators.getBySelector(property);
		WebElement ele = driver.findElement(locator);
		driver.switchTo().frame(ele);		
	}
	
	
	public void switchDefaultContent()
	{
		driver.switchTo().defaultContent();
	}
	
	
	
	public void moveToElement(String property)
	{
		By locator = Locators.getBySelector(property);
		WebElement ele = driver.findElement(locator);
		act.moveToElement(ele).build().perform();		
	}
	
	
	
	
	
	
	
	
	
	
	
}
