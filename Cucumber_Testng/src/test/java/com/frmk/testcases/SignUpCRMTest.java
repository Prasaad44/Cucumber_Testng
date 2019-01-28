package com.frmk.testcases;

import java.util.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.cucumber.listener.Reporter;
import com.frmk.pageobjects.SignUpPage;
import com.frmk.testrunner.BaseTestRunner;
import cucumber.api.DataTable;
import cucumber.api.java.en.*;

public class SignUpCRMTest extends BaseTestRunner {

	SignUpPage signPage = new SignUpPage();

	@Given("^Navigates to CRM Application$")
	public void navigates_to_CRM_Application() {
		if (signPage.checkLogo()) {
			Reporter.addStepLog("CRM HomePage has been launched");
		}

	}

	@When("^I Click Signup Link and Choose Free Edition$")
	public void i_Click_Signup_Link_and_Choose_Free_Edition() {
		boolean signup = false;
		boolean edition = false;
		SoftAssert ast = new SoftAssert();

		signup = signPage.clickSignup();
		ast.assertTrue(signup, "Failed to Click on SignUp Link");
		Reporter.addStepLog("Signup Button has been clicked");

		edition = signPage.selectEdition();
		ast.assertTrue(edition, "Failed to Select Edition");
		Reporter.addStepLog("Free Edition has been Selected");

		ast.assertAll();

	}

	@Then("^I enter Personal details and Submit$")
	public void i_enter_Personal_details(DataTable details) {
		boolean finalFlag = false;
		SoftAssert ast = new SoftAssert();

		List<List<String>> datas = details.raw();
		if (signPage.enterDetails(datas))
			Reporter.addStepLog("Mandatory Details has been entered");
		else
			Reporter.addStepLog("Failed to enter details");

		finalFlag = signPage.submitForm();
		ast.assertTrue(finalFlag, "Failed to Signup");
		if (finalFlag)
			Reporter.addStepLog("Signup Completed Successfully");

		ast.assertAll();
	}

	@And("^I should get Alert Popup$")
	public void i_should_get_alert_popup() {
		if (signPage.checkAlert()) {
			signPage.doOnAlert("accept");
			Reporter.addStepLog("Alert has been displayed");
		}
	}

}
