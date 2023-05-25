package com.anz.stepdefinition;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.anz.utilities.Browser;
import com.anz.utilities.Parameterization;
import com.anz.utilities.SeleniumUtility;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BorrowingCalculator_StepDefinition {
	WebDriver driver= Browser.getDriver();
	Map<String,HashMap<String,String>> data= Parameterization.ExcelObjectReader("ANZ_Test");

	@Given("User is on Home loan borrowing power calculator page")
	public void User_is_on_Home_loan_borrowing_power_calculator_page() {
		try {
			String title=null;
			title= driver.getTitle();
			Assert.assertEquals("Home loan borrowing power calculator | ANZ", title);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

	@When("User select {string} as {string}")
	public void User_select_as(String element, String value) {
		try {
			WebElement element1= null;
			element1= SeleniumUtility.locateElement(data.get(element), driver);
			SeleniumUtility.clickOnWebelement(element1);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

	@And("User select {string} as {string} from dropdown list")
	public void User_select_as_from_dropdown_list(String element, String value) {
		try {
			WebElement element2= null;
			element2= SeleniumUtility.locateElement(data.get(element), driver);
			SeleniumUtility.selectByVisibleText(element2, value);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

	@And("User enter {string} as {string}")
	public void User_enter_as(String element, String value) {
		try {
			WebElement element3= null;	
			element3= SeleniumUtility.locateElement(data.get(element), driver);
			SeleniumUtility.inputData(element3, value);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

	@And("User click on {string} button")
	public void User_click_on_button(String element) {
		try {
			WebElement element4= null;
			element4= SeleniumUtility.locateElement(data.get(element), driver);
			//SeleniumUtility.expWait(driver, element4, "Clickable");
			SeleniumUtility.clickOnWebelement(element4);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

	@Then("User verify {string} of {string}")
	public void User_verify_of(String element, String value) {
		try {
			WebElement element5= null;
			String elementValue=null;
			element5= SeleniumUtility.locateElement(data.get(element),driver);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element5);
			Thread.sleep(1000);
			elementValue= SeleniumUtility.getText(element5);
			Assert.assertEquals(elementValue, value);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

	@And("User verify form is clear with all values as {string}")
	public void user_verify_form_is_clear_with_all_values_as(String string) {
		try {
			WebElement ele_annualIncome,ele_otherIncome,ele_monthlyExpense,ele_currentHomeLoan,ele_otherLoan,ele_monthlyCommitments,ele_creditLimit=null;
			String annualIncome,otherIncome,monthlyExpense,currentHomeLoan,otherLoan,monthlyCommitments,creditLimit=null;
			ele_annualIncome= SeleniumUtility.locateElement(data.get("Annual_Income"), driver); 
			ele_otherIncome= SeleniumUtility.locateElement(data.get("Other_Income"), driver); 
			ele_monthlyExpense= SeleniumUtility.locateElement(data.get("Living_Expenses"), driver); 
			ele_currentHomeLoan= SeleniumUtility.locateElement(data.get("Home_loan_repayment"), driver); 
			ele_otherLoan= SeleniumUtility.locateElement(data.get("Other_loan_repayment"), driver); 
			ele_monthlyCommitments= SeleniumUtility.locateElement(data.get("Other_Commitments"), driver); 
			ele_creditLimit= SeleniumUtility.locateElement(data.get("Credit_card_limit"), driver);
			annualIncome= ele_annualIncome.getAttribute("value");
			otherIncome= ele_otherIncome.getAttribute("value");
			monthlyExpense= ele_monthlyExpense.getAttribute("value");
			currentHomeLoan= ele_currentHomeLoan.getAttribute("value");
			otherLoan= ele_otherLoan.getAttribute("value");
			monthlyCommitments= ele_monthlyCommitments.getAttribute("value");
			creditLimit= ele_creditLimit.getAttribute("value");
			Assert.assertEquals(annualIncome,"0");
			Assert.assertEquals(otherIncome,"0");
			Assert.assertEquals(monthlyExpense,"0");
			Assert.assertEquals(currentHomeLoan,"0");
			Assert.assertEquals(otherLoan,"0");
			Assert.assertEquals(monthlyCommitments,"0");
			Assert.assertEquals(creditLimit,"0");
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}

	}


	@Then("User verify error message {string}")
	public void user_verify_error_message(String value) {
		try {
			WebElement errorMessage= null;
			String message=null;
			errorMessage= SeleniumUtility.locateElement(data.get("Error_Message"), driver);
			message= SeleniumUtility.getText(errorMessage);
			Assert.assertEquals(message, value);
		}catch(Exception e) {
			e.printStackTrace();
			boolean condition = false;
			Assert.assertTrue(condition,e.getMessage());
		}
	}

}