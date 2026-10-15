package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	/*
	 * First condition -- User should login to interact with account page 
	 * The question is: where should we perform the login? 
	 * which class should be perform the login? 
	 * As we know that we already have the doLogin() method inside the LoginPage class.
	 * now the question is which class should we call this method?
	 * we should call this method from AccountsPageTest class as it is the precondition
	 * for this class To call the doLogin() we will wrap it inside the @BeforeClass
	 * 
	 * Why did you not initialize the accPage on the BaseTest class? Because we
	 * don't the Accounts page object for all the classes we just need it for
	 * AccountsPageTest class So I created it here. And I only keep its reference in
	 * the BaseTest class and if any other class needs it I can use it there.
	 * 
	 */

	@BeforeClass
	public void accsetup() {
		// accPage = new AccountsPage(driver);
		// accPage = loginPage.doLogin("aseer@gmail.com", "Test123");
		// After fetching the values from config.properties file
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
		// We can directly verify
		// Assert.assertEquals(accPage.getAccPageTitle(), "My Account");
	}

	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}

	@Test
	public void accPageHeadersCountTest() {
		Assert.assertEquals(accPage.getAccountsHeaders().size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
	}

	@Test
	public void accPageHeadersTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList, AppConstants.AACOUNTS_PAGE_HEADERS_LIST);
	}
}
