package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;

public class ProductResultsPageTest extends BaseTest{
	
	// Pre-condition: You will have to logged in
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void procutImagesTest () {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProducts("MacBook Pro");
		// productInfoPage.getProductImagesCount(); // TDD
		// Direct Assertion
		Assert.assertEquals(productInfoPage.getProductImagesCount(), 4);
	}
	
	@Test
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProducts("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();
		// System.out.println(productDetailsMap); // Better print it from Page Class
		
		// Hard Assertions: If the line 35 fails all the following assertion will fail and the test will stop/terminated
		// Assert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		// Assert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		// Assert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		// Assert.assertEquals(productDetailsMap.get("Availability"), "Out Of Stock");
		
		// Assert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		// Assert.assertEquals(productDetailsMap.get("External Tax Price"), "$2,000.00");
		
		// Soft Assertions: 
		// Since soft assertion is a class at TestNG, create a protected reference variable for it in the BaseTest class
		// then create its object inside the setup() method. softAssert
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "8001");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "Out Of Stock");
		
		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("External Tax Price"), "$2,000.00");
		
		// For viewing Assertions
		softAssert.assertAll(); //It will tell out of 6 assertions how many got fail
	}

}

/*		
 	Hard Assertion:
 		1: Assertion hardAssert = new Assertion();
 		2: Methods of hard assertions are static
 		3: Test execution will be aborted/canceled if the assert condition is not met
 		4: Hard assertions are used where there is need for one assertion only.
 		5: Test Output: No need to invoke any method to view test results.
 		6: By default, Assert in Selenium WebDriver are Hard Asserts.
 		
 	Soft Assertion:
 		1: Assertion softAssert = new SoftAssert();
 		2: Methods of soft assertions needs its object creation in order to call them.
 		3: Test execution will continue till the end of the test case even if the assert condition is not met.
 		4: Soft assertions are used where there is need for multiple assertions only.
		5: Tester needs to invoke assertAll(), to view assertions at the end of the test result.
		6: These are not included by default in the TestNG framework.
		
	Assignment
		1.	Capture all the footers
		2.	Write 8 soft assertion assertions 

*/
