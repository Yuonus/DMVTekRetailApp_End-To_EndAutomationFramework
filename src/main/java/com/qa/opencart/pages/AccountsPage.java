package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

	// Locators
	private By logoutLink = By.linkText("Logout");
	private By search = By.xpath("//input[@name='search']");
	private By accHeaders = By.xpath("//h2"); // You can also cssSelector --> div#content h2
	private By searchIcon = By.cssSelector("div#search button");

	// Page Constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}

	// Page Actions

	public String getAccPageTitle() {
		String accPageTitle = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account Page Title: " + accPageTitle);
		return accPageTitle;
	}

	public String getAccPageURL() {
		String accPageURL = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Account Page URL: " + accPageURL);
		return accPageURL;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public void logout() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}

	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public List<String> getAccountsHeaders() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<>();
		for (WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver); // TDD
		
		/*	Clicking the search icon land us on a different page, so the "doSearch()" Method is responsible
		 	to return the object of the landing page so we can have the chaining concept applied.
		 	
		 	Test-Driven Development (TDD)
		 		Creating the "SearchResultsPage" class from this class is called Test-Driven Development.
		 		Test-Driven Development (TDD) is a software development approach where tests are written 
		 		before the actual code is written
		 		
		 */
	}

}
