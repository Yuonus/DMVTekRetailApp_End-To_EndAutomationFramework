package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtils;

public class RegisterPageTest extends BaseTest {
	// We don't need to log in to register, since registration does not require authentication/logging in.
	// However, we still need a reference to the Login page to click the Register link.

	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	public String getRandomEmailID(String userFirstName) {
		return userFirstName + "S_Tech" + System.currentTimeMillis() + "@gmail.com";
		// Or we can also use this given approach
		// return "testAutomation" + UUID.randomUUID() + "@gmail.com";
	}

//	@Test
//	public void userRegTest() {
//		boolean isRegDone = registerPage.userRegistration("Dave", "David", "davDavid@gmail.com", "2589631472",
//				"Test123", "No");
//		Assert.assertTrue(isRegDone);
//	}
	
	// First you will have to work without Data provider:
//	@DataProvider
//	public Object[][] getUserRegData() {
//		return new Object[][] {
//			{"Dave", "David", getRandomEmailID("Dave"), "2589631472", "Test123", "No"},
//			{"Smith", "McTernan", getRandomEmailID("Smith"), "2580001472", "Test123", "No"},
//			{"Brishna", "Nazari", getRandomEmailID("brishna"), "2581191472", "Test123", "Yes"},
//		};
//	}
	
//	@Test (dataProvider = "getUserRegData")
//	public void userRegTest(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
//		boolean isRegDone = registerPage.userRegistration(firstName, lastName, getRandomEmailID(firstName), telephone,
//				password, subscribe);
//		Assert.assertTrue(isRegDone);
//	}
	
//	@Test
//	public void userRegTest() {
//		boolean isRegDone = registerPage.userRegistration("Dave", "David", getRandomEmailID("Dave"), "2589631472",
//				"Test123", "No");
//		Assert.assertTrue(isRegDone);
//	}
	
	
	
	/* 					Reading Excel Data
	 	To Read the Excel Data Selenium, TestNG and Java cannot directly read it  we should download the third party library Apache POI 
		1: POI-OOXML--> Maven Repository > Search POI-OOXML > Click on "Apache POI API Based on OPC and OOXML Schemas" > copy the latest version and paste it in your POM.XML file.
		2: Update the Maven Project
		3: Write the code to read the excel file --- utils > ExcelUtils class
	 */
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		// ExcelUtils.getTestData("register"); better to use the sheet name form AppConstants
		Object[][] regData = ExcelUtils.getTestData(AppConstants.REGISTRATION_DATA_SHEET_NAME);
		return regData;
	}
	
	
	@Test (dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		boolean isRegDone = registerPage.userRegistration(firstName, lastName, getRandomEmailID(firstName), telephone, password, subscribe);
		Assert.assertTrue(isRegDone);
	}
	

}
