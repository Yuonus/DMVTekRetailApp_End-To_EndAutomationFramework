package com.qa.opencart.pages;

public class DemoPage {

	public String concatenateStrings(String schoolOwner, String bestSchoolName) {
		return schoolOwner + " " + bestSchoolName;
	}

	public static void main(String[] args) {
		DemoPage demo = new DemoPage();
		String result = demo.concatenateStrings("Sabawoon Yuonus", "S_Tech");
		System.out.println(result);
	}

}
