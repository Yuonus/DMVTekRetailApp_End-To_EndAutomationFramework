package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

	// Page Constructor
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}
	
	// Test Cases: Click MacBook pro
	
		// Locator: Do not create the dynamic locators in the class level
	// private By product = By.linkText("MacBook Pro");
	
	/*
	 Whenever By locators are dynamic, do not store them at the class level. Instead, define them inside the 
	 Page Class methods/functions because they are subject to change and are not static.
	 For example, consider the By locator used for a product in SearchResultsPage. 
	 A product like "MacBook Pro" can change at any moment, so its locator should be created dynamically 
	 within a method rather than stored as a class-level variable.
	 Only static or fixed By locators should be stored at the class level, such as: searchIcon, search, 
	 logoutLink, etc. These locators are stable and unlikely to change, which makes them suitable for 
	 class-level declarations
	 */
	
	// Methods
	public ProductInfoPage selectProducts(String productName) {
		By productNameLocator = By.linkText(productName);
		eleUtil.waitForVisibilityOfElement(productNameLocator, AppConstants.MEDIUM_DEFAULT_WAIT).click(); 
		// Note:
		// You can directly supply this method inside the method without storing it in a variable
		// eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.MEDIUM_DEFAULT_WAIT).click();
		// As clicking the product land us on a different page then the selectProducts() method is responsible to
		// return the object of the landing page.
		return new ProductInfoPage(driver);
	}
	
	
}
