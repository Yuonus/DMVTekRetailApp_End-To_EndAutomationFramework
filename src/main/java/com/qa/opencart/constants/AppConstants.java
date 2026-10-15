package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {


	
	/*					Constants
	 	1: Values that do not change
	 	2: These values are static in nature
	 	3: They are not dynamic
	 	4: Constants are declared with public static final
	 		>> public: accessible from other classes
	 		>> static: Can be accessed without creating the object of the class
	 		>> final: Value cannot be change once assigned
	 	5: Naming Convention: Upper Cases with underscores
	 */

	// Login Page
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "account/login";
	
	// Handling Hard coded Timeouts
	public static final int SHORT_DEFAULT_WAIT = 5;
	public static final int MEDIUM_DEFAULT_WAIT = 10;
	public static final int LONG_DEFAULT_WAIT = 20;
	public static final int POLLING_DEFAULT_WAIT = 2;
	
	// Accounts Page
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACC_PAGE_URL_FRACTION = "route=account/account";
	public static final int ACC_PAGE_HEADERS_COUNT = 4;
	
	public static final List<String> AACOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");

	
	// Assignment: if your test cases are having any of the hard-coded values put it here
	
	/*				 Properties File
	 	1: PF provided by Java
	 	2: Usage: stores configuration setting + other information that a program needs
	 	3: Data is stored in Key-Value pairs
	 	4: Data access -- using the key
	 	5: Key is unique
	 	6: What can we store
	 		>> App URL
	 		>> Browser Types --> Chrome, FF, Edge
	 		>> Test data location
	 		>> Credentials
	 		>> API Keys + Secrets
	 	7: Why Properties file?
	 		:: To prevent hard coded values in the test script
	 		:: We can control the configuration data from one location
	 			like: Browser change -- URL Change -- Credential changes
	 
	 */
}
