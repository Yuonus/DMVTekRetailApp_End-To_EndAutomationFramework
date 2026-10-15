package com.qa.opencart.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {

	protected WebDriver driver;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage; // Do not initialize the accPage inside the BaseTest class setup(). we will initialize it in the AccountsPageTest class it self
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		driver = df.initDriver("chrome");
		loginPage = new LoginPage(driver);
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
