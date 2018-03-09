package com.frmk.testcases;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.cucumber.listener.Reporter;
import com.frmk.pageobjects.SignUpPage;
import com.frmk.testrunner.BaseTestRunner;
import com.frmk.utilities.Logging;
import com.frmk.utilities.ReadProperties;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class SignUpCRMTest extends BaseTestRunner {

	SignUpPage signPage = new SignUpPage();
	
	@Before
	public void initializeReport() {
		Reporter.loadXMLConfig(BaseTestRunner.userDirectory + "/Reports/Extent-Config.xml");

	}

	@Given("^Navigates to CRM Application$")
	public void navigates_to_CRM_Application() {
		driver.get(ReadProperties.configMap.get("URL"));
		logs.logInfo("Application has been launched Successfully");
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Free CRM"), "Title not matches, validation failed");

	}

	@When("^I Click Signup Link and Choose Free Edition$")
	public void i_Click_Signup_Link_and_Choose_Free_Edition() {
		boolean signup = false;
		boolean edition = false;
		
		SoftAssert ast = new SoftAssert();
		
		signup = signPage.clickSignup();
		ast.assertTrue(signup, "Failed to Click on SignUp Link");
		
		edition = signPage.selectEdition();
		ast.assertTrue(edition, "Failed to Select Edition");
		
		ast.assertAll();

	}

	@Then("^I enter Mandatory details$")
	public void i_enter_Mandatory_details(DataTable arg1) {

		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		Assert.assertTrue(true);
		System.out.println("Hello, am in I enter Mandatory details");

	}

	@And("^I should navigate to Home Screen$")
	public void i_should_navigate_to_Home_Screen() {

		// Write code here that turns the phrase above into concrete actions

		Assert.assertTrue(true);
		logs.logInfo("Hello,I should navigate to Home Screen ");
	}

	
	
	
	
	
	
	
	
	
	@Given("^Check the Condition$")
	public void check_the_condition() {

		Assert.assertTrue(true);
		logs.logInfo("Hello dude cool in scenario 2");
		System.out.println("Hello dude cool in scenario 2");
	}

}
