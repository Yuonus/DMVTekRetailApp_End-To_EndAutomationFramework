package com.qa.opencart.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	// driver initialization
	WebDriver driver; // right now it points to null

	public WebDriver initDriver(String browserName) {
		System.out.println("Browser name is: " + browserName);
		// We will implement the cross browser logic here
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println("Please pass the right browser name ..." + browserName);
			throw new FrameworkException("No Browser Found ...");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// This URL will be removed later and read from the configuration file.
		// For now, we will keep it hardcoded, and the same applies to the browser names above.
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		return driver; 
			// Why return the driver? To allow the calling class to use the same browser instance.
			// What happens if we don't return it? The calling class cannot receive the driver through this method, 
			// so it cannot use the returned reference to interact with the browser.
	}
	
	// Question?
	// NOW how exactly will the Login page get this driver? or how will you link this driver to the login page
	// We now that the driver in the LoginPage is still pointing to null.
	// Now we will start writing our testng classes.

}
