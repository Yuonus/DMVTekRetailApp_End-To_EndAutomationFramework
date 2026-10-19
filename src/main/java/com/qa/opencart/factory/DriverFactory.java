package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	// driver initialization
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is: " + browserName);
		
		optionsManager = new OptionsManager(prop);
		
		// We will implement the cross browser logic here
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "edge":
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
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
		// For now, we will keep it hard-coded, and the same applies to the browser names above.
		driver.get(prop.getProperty("url"));
		return driver;

	}

	public Properties initProp() {
		// Initializing Properties Class -- creating its object
		prop = new Properties();
		// Establishing connection with config.properties file
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			// Loading the data from config.prperties
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ./ means start from the root of the project and reach to the
		// config.properties file. -- You can show this using the tree command
		return prop;
	}
	
	// Where should we call this method ? We will call this method from BaseTest class
	// Why BaseTest? Because we have to start fetching values from config.properties file and the 
	// the first value we want to fetch is the chrome. so we already have the object and reference of the DriverFactory class inside the BaseTest
	// class all we have to do it to call the initProp() using the DriverFactory class reference variable
	

}
