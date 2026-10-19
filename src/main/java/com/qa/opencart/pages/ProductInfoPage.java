package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtils;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

	// Page Constructor
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}
	
	//Locators
	By productHeader = By.cssSelector("div#content h1");
	By productImages = By.cssSelector("ul.thumbnails img");
	// For Retail --> By productImages = div.image.magnific-popup img
	// The same locator will work with other products, try it with Samsung -- iMac
	
	By productMetaData = By.xpath("(//div[@class='col-sm-4']//descendant::ul[@class='list-unstyled'])[1]//child::li");
	// For Retail Produc MD: (//div[@class='col-sm']//child::ul[@class='list-unstyled'])[1]//child::li
	
	By productPriceData = By.xpath("(//div[@class='col-sm-4']//descendant::ul[@class='list-unstyled'])[2]//child::li");
	
	// TCM01: Fetching product header
	public String getProductHeaderName() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("The product headers is: " + productHeaderVal);
		return productHeaderVal;
	}

	// TCM02: Product Images Count
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product: " + getProductHeaderName() + " images count: " + imagesCount);
		return imagesCount;
	}
	
	// TCM03: Product Meta Data Test
	// private Map<String, String> productMap = new HashMap<>(); // Unordered
	// First use the HashMap then LinkedHashMap -- this should happen after all the steps are done and when it is time to printing
	// private Map<String, String> productMap = new LinkedHashMap<>(); // Ordered
	// For alphabetical order for keys not for values use TreeMap
	private Map<String, String> productMap = new TreeMap<>(); 
	
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock
	//public void getProductMetaData() { // private comes after getProductDetails()
	private void getProductMetaData() {
		// To get the meta data we will use the HashMap
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData, AppConstants.MEDIUM_DEFAULT_WAIT);
		for(WebElement e : metaDataList) {
			String metaData = e.getText();
			// We have to break the value "Brand: Apple" the value into two parts -- break bases on colon
			String metaKey = metaData.split(":")[0].trim(); // gives you key -- Brand
			String metaValue = metaData.split(":")[1].trim(); // gives you value -- Apple
			
			// Start filling the HashMap "productMap"
			productMap.put(metaKey, metaValue);
		}
	}
	
	// TCM04: Product Price Data Test
//	$2,000.00
//	Ex Tax: $2,000.00
	// public void getProductPriceData() {
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.waitForVisibilityOfElements(productPriceData, AppConstants.MEDIUM_DEFAULT_WAIT);
		// Here we don'0t want to split because "$2,000.00" does not have any key
		String productPrice = metaPriceList.get(0).getText(); // will give you $2,000.00
		// We will split the Ex Tax Price and remove the Ex Tax by not using its is index in the array which is zero
		String productExTaxPrice = metaPriceList.get(1).getText().split(":")[1].trim(); // $2,000.00
		
		// Storing the values in HashMap: as we have created the HashMap "productMap" in class level, We can also use it here
		// As we dont have key for 2000 we can give our custom key
		productMap.put("price", productPrice);
		productMap.put("External Tax Price", productExTaxPrice);
	}
	
	// We are not returning anything from those two above methods:
	// But we will return the value from another method 
	public Map<String, String> getProductDetails() {
		// If you also want to return the product name -- store in the the HashMap
		productMap.put("Product name: ", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap); // This will print the product meta-data in an unordered mode -- to print in order use linkedHashMap
		return productMap;
	}
	 // You can make the above two methods private to not give access to the user

}

/*  Is it mandatory to create a Test class for each and every page class? No
 	We will handle the SearchResultsPage class from AccountsPageTest class
 */
