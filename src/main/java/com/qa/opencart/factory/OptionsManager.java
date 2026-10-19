package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/*
 
	 1.	Create a Properties class reference to read the properties from the file.
		private Properties prop;
	2.	Create the constructor of the OptionsManager class. And initialize the prop un-initialized reference variable
			public OptionsManager(Properties prop) {
				this.prop = prop;
			}
	3.	Create methods[getChromeOptions(), getFirefoxOptions(), getEdgeOptions()] to provide browser options for different browsers, such as:
		•	ChromeOptions
		•	FirefoxOptions
		•	EdgeOptions
	4.	Once these methods are implemented, the OptionsManager class is fully ready.
		Next, in the DriverFactory class, we follow these steps:
	5.	Maintain a reference of the OptionsManager class in DriverFactory class.
		OptionsManager optionsManager;
	6.	Initialize the optionsManager reference inside the initDriver() method and pass the Properties object (prop) as an argument to provide the required browser options. 
	7.	Inside the switch statements call the browser relevant methods
	8.	Run the RegisterPageTest class under the following configurations in the config.properties file:
			>>	Set incognito=false.
			>>	Set incognito=true and headless=false.
			>>	Set both incognito=false and headless=false.
			>>	If you want to run the test on different browser you will have update the value from config.properties file. browser = chrome --> To this browser = firefox
			>>	To run the test on a different browser, update the browser property in the config.properties file.
			    For example, change browser=chrome to browser=firefox to execute the test on Firefox instead of Chrome.

 
 */

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			co.addArguments("--headless");
		}
		// co.addArguments("--headless"); Based on which condition or from where you will read the headless. We will read the headless from the Cofig.properties file
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			co.addArguments("--incognito");
		}
		return co;
	}
	
	// Handling Firefox options: If you have one one line in if condition you can drop the {} brances
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) fo.addArguments("-private");
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) eo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) eo.addArguments("--inprivate");
		return eo;
	}
	
	// Safari Does not support incognito and headless

}
