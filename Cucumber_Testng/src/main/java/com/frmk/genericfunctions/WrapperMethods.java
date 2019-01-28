package com.frmk.genericfunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frmk.testrunner.BaseTestRunner;
import com.frmk.utilities.Locators;
import com.frmk.utilities.Logging;

public class WrapperMethods extends BaseTestRunner {

	Logging logs = new Logging();
	Actions act = new Actions(driver);
	public WebDriverWait wdWait;
	Alert alt;

	public void pageLoadTimeOut(Long TimeinSec) {
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
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}

	}

	public void enterText(String property, String text) {
		try {
			By locator = Locators.getBySelector(property);
			WebElement ele = driver.findElement(locator);
			ele.clear();
			ele.sendKeys(text);
		} catch (Exception e) {
			logs.logError(e.getLocalizedMessage());
		}
	}

	public void clearText(String property) {
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
		int reCheck = 0;
		if (ele.size() == 0) {
			do {
				checkElementPresent(property);
				reCheck++;
			} while (reCheck == 1);
		}
		return ele.size();
	}

	public void waitUntilElementDisplay(int lg, String property) {
		By ele = Locators.getBySelector(property);
		wdWait = new WebDriverWait(driver, lg);
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}

	public void selectDropDownByText(String property, String text) throws Exception {
		By locator = Locators.getBySelector(property);
		WebElement ele = driver.findElement(locator);
		Select sel = new Select(ele);
		List<WebElement> selEle = sel.getOptions();
		if (!selEle.isEmpty()) {
			for (WebElement ele1 : selEle) {
				if (ele1.getText().equals(text)) {
					ele1.click();
				}
			}
		} else {
			logs.logWarn("Dropdown is Empty");
		}
	}

	public void switchFrameByName(String text) {
		driver.switchTo().frame(text);
	}

	public void switchFrameById(int n) {
		driver.switchTo().frame(n);
	}

	public void switchFrameByElement(String property) {
		By locator = Locators.getBySelector(property);
		WebElement ele = driver.findElement(locator);
		driver.switchTo().frame(ele);
	}

	public void switchDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void moveToElement(String property) {
		By locator = Locators.getBySelector(property);
		WebElement ele = driver.findElement(locator);
		act.moveToElement(ele).build().perform();
	}

	public void switchAlert() {
		int i = 0;
		while (i++ < 5) {
			try {
				alt = driver.switchTo().alert();
				break;
			} catch (NoAlertPresentException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				continue;
			}
		}
	}

	public void alertOperation(String value) {
		switch (value) {
		case "accept":
			alt.accept();
			break;
		case "dismiss":
			alt.dismiss();
			break;
		}
	}

}
