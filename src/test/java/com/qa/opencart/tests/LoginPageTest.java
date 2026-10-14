package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest {

	// Do not execute the priority in the beginning -- implement it once the test class is completed
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, "Account Login");
	}

	@Test(priority = 2)
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains("account/login"));
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
		Assert.assertTrue(loginPage.doLogin("aseer@gmail.com", "Test123"));
	}

	// Here is one issue in the code execution: As we know the tests in TestNG will run in alphabetical order by default and
	// now what if the loginTest() get executed first before all the methods and if it happens it means the user is already logged in and
	// rest of the test methods that are belongs to the login page will fail. So we will use the priority attribute to prioritize the test.
	
	// Notice: that we have not used any of the Selenium methods in the test class like: .click(), .sendKeys() ...
	// And this is what the Page Object Model suggests -- https://martinfowler.com/bliki/PageObject.html
	

}
