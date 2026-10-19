package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductResultsPageTest extends BaseTest{
	
	// Pre-condition: You will have to logged in
	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	/*	Day 30
		 In this class, we were searching for MacBook Pro, but there are other products that we also need to search for. 
		 To test with multiple products, we need a way to pass different product names into our test methods. 
		 This is where the @DataProvider comes into the picture, as it allows us to supply multiple sets of data to the test method.
		 
		 Data Provider Concept
			1: @DataProvider lets one test method run multiple times with different input data and most oftenly used in Data Driven Testing.
			2: In a Data Provider, we use the @DataProvider annotation. This annotation returns a two-dimensional Object array (Object[][]).
			Why use an Object array?
			Because an Object array can store any type of data, such as int, char, float, double, String, etc. This makes it flexible for passing different types of test data to your test methods.
	 */
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
		};
	}
	
	@Test(dataProvider = "getSearchData")
	public void procutImagesTest (String searchKey, String productName, int imageCount) {
		// searchResultsPage = accPage.doSearch("MacBook");
		searchResultsPage = accPage.doSearch(searchKey);
		// productInfoPage = searchResultsPage.selectProducts("MacBook Pro");
		productInfoPage = searchResultsPage.selectProducts(productName);
		// Assert.assertEquals(productInfoPage.getProductImagesCount(), 4);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
	}
 
//	@Test
//	public void procutImagesTest () {
//		searchResultsPage = accPage.doSearch("MacBook");
//		productInfoPage = searchResultsPage.selectProducts("MacBook Pro");
//		// productInfoPage.getProductImagesCount(); // TDD
//		// Direct Assertion
//		Assert.assertEquals(productInfoPage.getProductImagesCount(), 4);
//	}
	
//	Assignments:
//		Write the dataProvider for productInfoTest method

	@DataProvider
	public Object[][] getProductInfoData() {
	    return new Object[][] {
	        {
	            "MacBook",
	            "MacBook Pro",
	            "Apple",
	            "Product 18",
	            "800",
	            "Out Of Stock",
	            "$2,000.00",
	            "$2,000.00"
	        }
	    };
	}
	
	@Test(dataProvider = "getProductInfoData")
	public void productInfoTest(
	        String searchKey,
	        String productName,
	        String brand,
	        String productCode,
	        String rewardPoints,
	        String availability,
	        String price,
	        String externalTaxPrice) {

	    searchResultsPage = accPage.doSearch(searchKey);
	    productInfoPage = searchResultsPage.selectProducts(productName);

	    Map<String, String> productDetailsMap =
	            productInfoPage.getProductDetails();

	    softAssert.assertEquals(productDetailsMap.get("Brand"), brand);
	    softAssert.assertEquals(productDetailsMap.get("Product Code"), productCode);
	    softAssert.assertEquals(productDetailsMap.get("Reward Points"), rewardPoints);
	    softAssert.assertEquals(productDetailsMap.get("Availability"), availability);
	    softAssert.assertEquals(productDetailsMap.get("price"), price);
	    softAssert.assertEquals(
	            productDetailsMap.get("External Tax Price"),
	            externalTaxPrice
	    );

	    softAssert.assertAll();
	}
	
//	@Test
//	public void productInfoTest() {
//		searchResultsPage = accPage.doSearch("MacBook");
//		productInfoPage = searchResultsPage.selectProducts("MacBook Pro");
//		Map<String, String> productDetailsMap = productInfoPage.getProductDetails();
//		// System.out.println(productDetailsMap); // Better print it from Page Class
//		
//		// Hard Assertions: If the line 35 fails all the following assertion will fail and the test will stop/terminated
//		// Assert.assertEquals(productDetailsMap.get("Brand"), "Apple");
//		// Assert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
//		// Assert.assertEquals(productDetailsMap.get("Reward Points"), "800");
//		// Assert.assertEquals(productDetailsMap.get("Availability"), "Out Of Stock");
//		
//		// Assert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
//		// Assert.assertEquals(productDetailsMap.get("External Tax Price"), "$2,000.00");
//		
//		// Soft Assertions: 
//		// Since soft assertion is a class at TestNG, create a protected reference variable for it in the BaseTest class
//		// then create its object inside the setup() method. softAssert
//		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
//		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
//		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
//		softAssert.assertEquals(productDetailsMap.get("Availability"), "Out Of Stock");
//		
//		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
//		softAssert.assertEquals(productDetailsMap.get("External Tax Price"), "$2,000.00");
//		
//		// For viewing Assertions
//		softAssert.assertAll(); //It will tell out of 6 assertions how many got fail
//	}

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
