package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

//public class LoginPage {
//
//	// 1: Private WebDriver driver reference
//	private WebDriver driver;
//
//	// If we make the driver reference variable public, everyone can access it.
//	// And the current value of "driver" is null because it has not been initialized.
//	// If anyone tries to use it, it will point to null and will result in a NullPointerException.
//	
//	// 2: Locators (Object Repository / Locator Repository / Page Object Repository)
//	// Locators should be private and accessed through public page methods. Encapsulation concept
//
//	private By userName = By.id("input-email");
//	private By password = By.id("input-password");
//	private By loginBtn = By.xpath("//input[@type='submit']");
//	private By forgotPwdLink = By.linkText("Forgotten Password");
//	private By logo = By.cssSelector("img[title='naveenopencart']");
//
//	//3: Page Constructor
//	// The page constructor should be public; otherwise, other classes will not be able to create
//	// an object of the LoginPage class. If no object is created, the constructor will not be called,
//	// the driver will not be initialized, and attempting to use the null driver reference will result in a NullPointerException.
//	public LoginPage(WebDriver driver) {
//		this.driver = driver;
//	}
//
//	// 4: Page actions/methods
//	//a. Methods should generally be non-static because they use the instance-level WebDriver.
//	//b. Do not use assertions here -- use them at test classes
//
//	public String getLoginPageTitle() {
//		String title = driver.getTitle();
//		System.out.println("Login page title is: " + title);
//		return title;
//	}
//
//	public String getLoginPageURL() {
//		String url = driver.getCurrentUrl();
//		System.out.println("Login page url is: " + url);
//		return url;
//	}
//
//	public boolean isForgotPwdLinkExist() {
//		return driver.findElement(forgotPwdLink).isDisplayed();
//	}
//
//	public boolean isLogoExist() {
//		return driver.findElement(logo).isDisplayed();
//	}
//
//	public boolean doLogin(String username, String pwd) {
//		driver.findElement(userName).sendKeys(username);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
//		System.out.println("User is logged in");
//		return true;
//	    // We are using "return true" just to verify whether the user is logged in or not.
//	    // This is temporary, and we will change it later.
//	    // But what if the user is not logged in? It will still return true, which is a bug in our code.
//	    // We will handle this part tomorrow when we learn the page chaining concept.
//	}
//	
//	// Now that every thing is setup and finished, how will you get the driver? 
//	// Or how will you initialize the browser: driver = new ChromeDriver();
//	// Now we will create the DriverFactory.java to generate/initialize the browser for us
//
//}

// Calling the ElementUtils() Class inside the LoginPage.java
public class LoginPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	// Day 30
	private By registerLink = By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}
	
	// CTRL + O = To see the methods in ElementUtils class

	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page title is: " + title);
		return title;
	}

	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page url is: " + url);
		return url;
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForVisibilityOfElement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		// I don't want to wait for password and login button as I have already waited for email and the other two will be already there
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);

		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver); // TDD
	}

}
