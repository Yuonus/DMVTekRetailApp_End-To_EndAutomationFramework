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

	public static final String USER_REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
}
