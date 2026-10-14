package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

	// 1: Maintain private driver reference
	private WebDriver driver;
	private JavascriptExecutor js;

	// 2: We have to supply the driver to the JavaScriptUtil class
	// To supply the driver we will create the constructor of the class

	public JavaScriptUtils(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) this.driver;
	}

	public String getTitleByJs() {
		String pageTitle = js.executeScript("return document.title").toString();
		System.out.println("Page title: " + pageTitle);
		return pageTitle;
	}

	public String getURLByJs() {
		String pageURL = js.executeScript("return document.URL").toString();
		System.out.println("Page URL: " + pageURL);
		return pageURL;
	}

	/*
	 * Notice that the line "JavascriptExecutor js = (JavascriptExecutor) driver;"
	 * is repeated in every JavaScript-related method.
	 * 
	 * As we add more generic JavaScript methods in the future, we would have to
	 * repeat the same line in each method.
	 * 
	 * Solution: Create a class-level instance variable:
	 * "private JavascriptExecutor js;"
	 * 
	 * Initialize it inside the constructor, and then use the same js reference
	 * variable across all the required methods.
	 */

	public void generateJSAlert(String alertMessage) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + alertMessage + "')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public void generateJSConfirm(String confirmMessage) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("confirm('" + confirmMessage + "')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public void generateJSPrompt(String promptMessage, String valueToSend) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("prompt('" + promptMessage + "')");
		Alert alrt = driver.switchTo().alert();
		alrt.sendKeys(valueToSend);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		alrt.accept();
	}

	public void goBackWithJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1)");
	}

	public void goForwardWithJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1)");
	}

	public void pageRefreshWithJS() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1)");
	}

	public String getPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText").toString();
	}

	public void doSendKeysWithJS(String locator, String valueToSend) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + locator + "').value='" + valueToSend + "';");
	}

	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}

	public void scrollPageDownToTheMiddle() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
	}

	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public void scrollIntoViewWithJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", element);
	}
	
	public void zoomChromeEdgeSafari(String zoomPercentage) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='" + zoomPercentage + "%'");
	}

	public void zoomChromeFirefox(String zoomPercentage) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.MozTransform='scale(" + zoomPercentage + "%)'");
	}
	
	public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border = '2px solid red'", element);
	}

	/**
	 * This method can perform the following actions: 
	 * 1. Scroll to the specified element. 
	 * 2. Draw a border around the element with a custom pixel size. 
	 * 3. Set a custom color for the border.
	 *
	 *@author Sabawoon Yuonus
	 * @param element     The WebElement to scroll to and/or highlight.
	 * @param pixelSize   The border thickness in pixels.
	 * @param borderColor The color of the border.
	 * @param scrollFirst If true, scrolls to the element before drawing the border.
	 */
	public void drawBorder(WebElement element, String pixelSize, String borderColor, boolean scrollFirst) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		if (scrollFirst) {
			js.executeScript("arguments[0].scrollIntoView(false);", element);
		}

		js.executeScript("arguments[0].style.border = '" + pixelSize + "px solid " + borderColor + "'", element);
	}
	
	public void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 4; i++) {
			changeColor("rgb(0,200,0)", element);
			changeColor(bgColor, element);
		}
	}

	// The First flash() method has hard coded the flashing color
	// Lets make the method to let the user select the color
	public void flash(WebElement element, String flashColor) {

		String bgColor = element.getCssValue("backgroundColor");

		for (int i = 0; i < 6; i++) {
			changeColor(flashColor, element);
			changeColor(bgColor, element);
		}
	}

	public void changeColor(String color, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "';", element);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void sendKeysWithJS(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value = arguments[1];", element, value);
	}
	
}
