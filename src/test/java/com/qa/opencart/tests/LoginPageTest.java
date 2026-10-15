package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	// Do not execute the priority in the beginning -- implement it once the test class is completed
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}

	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Test(priority = 4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}

	@Test(priority = 5)
	public void loginTest() {
		// loginPage.doLogin("aseer@gmail.com", "Test123");
		// Temporary assertion to complete the Test, as every test should have an assertion as per AAA rule
		// Assert.assertTrue(loginPage.doLogin("aseer@gmail.com", "Test123"));
		
		// Updated this assertion to verify the successful login
		// accPage = loginPage.doLogin("aseer@gmail.com", "Test123");
		// After fetching the values from properties file
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	// Here is one issue in the code execution: As we know the tests in TestNG will run in alphabetical order by default and
	// now what if the loginTest() get executed first before all the methods and if it happens it means the user is already logged in and
	// rest of the test methods that are belongs to the login page will fail. So we will use the priority attribute to prioritize the test.
	
	// Notice: that we have not used any of the Selenium methods in the test class like: .click(), .sendKeys() ...
	// And this is what the Page Object Model suggests -- https://martinfowler.com/bliki/PageObject.html
	

}
