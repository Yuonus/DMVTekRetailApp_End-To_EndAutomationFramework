package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;

public class ElementUtils {

	private WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
		;
	}

	/**
	 * @author Sabawoon Yuonus
	 * @param locatorType
	 * @param locatorValue
	 * @return By class --> This method is used accept the String locator
	 */
	public By getBy(String locatorType, String locatorValue) {
		By by = null;
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "css":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;
		default:
			System.out.println("Wrong locator type is passed ... " + locatorType);
			throw new FrameworkException("WRONG LOCATOR TYPE");
		}
		return by;
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(getBy(locatorType, locatorValue)).click();
	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doElementGetText(String locatorType, String locatorValue) {
		return getElement(getBy(locatorType, locatorValue)).getText();
	}

	/**
	 * This method handles creation of multiple matching elements
	 * 
	 * @param locator
	 * @author Sabawoon Yuonus
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * This method handles the count of multiple web elements
	 * 
	 * @param locator
	 * @author Sabawoon Yuonus
	 */
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<String> getElementsText(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	public List<String> getElementsAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);

		List<String> eleAttributeList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			eleAttributeList.add(attrValue);
		}
		return eleAttributeList;
	}

	public void search(By searchFieldLocator, By suggestionLocator, String searchPhrase, String suggNameForClick) {
		getElement(searchFieldLocator).sendKeys(searchPhrase);
		;
		try {
			Thread.sleep(2000);
		} catch (Exception e) {

		}

		List<WebElement> suggList = getElements(suggestionLocator);
		System.out.println(suggList.size());

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggNameForClick)) {
				e.click();
				break;
			}
		}
	}

	public boolean checkSingleElementPresent(By locator) {
		return getElements(locator).size() == 1 ? true : false;
	}

	public boolean checkMultipleElementPresent(By locator) {
		return getElements(locator).size() >= 1 ? true : false;
	}

	public boolean checkMultipleElementPresent(By locator, int totalElements) {
		return getElements(locator).size() == totalElements ? true : false;
	}

	// ******************* Select Drop Downs Utilities **********************

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownByContainsVisibleText(By locator, String subString) {
		Select select = new Select(getElement(locator));
		select.selectByContainsVisibleText(subString);
	}

	public void selectDropDownOption(By locator, String dropDownValue) {
		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();
		System.out.println("Total Number of elements in the dropdown are: " + optionsList.size());
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(dropDownValue)) {
				e.click();
				break;
			}
		}
	}

	public List<String> getDropDownOption(By locator) {
		Select select = new Select(getElement(locator));

		List<WebElement> optionsList = select.getOptions();
		List<String> optionsTextList = new ArrayList<>();
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;
	}

	public int getDropDownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();
	}

	public void selectDropDownValue(By locator, String valueForSelection) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			String text = e.getText();
			if (text.equals(valueForSelection)) {
				e.click();
				break;
			}
		}
	}

	public boolean isDropDownMultiple(By locator) {
		Select select = new Select(getElement(locator));
		return select.isMultiple() ? true : false;
	}

	public void selectDropdownMultipleValues(By locator, String... values) {
		Select select = new Select(getElement(locator));
		if (isDropDownMultiple(locator)) {
			for (String value : values) {
				select.selectByVisibleText(value);
			}
		}
	}

	public void fourLevelMenuHandle(By parentMenuLocator, By firstChildMenuLocator, By secondChildMenuLocator,
			By thirdMenuChildLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		doClick(parentMenuLocator);
		Thread.sleep(1000); // Waiting for sub menus to appear
		act.moveToElement(getElement(firstChildMenuLocator)).perform();
		Thread.sleep(1000);
		act.moveToElement(getElement(secondChildMenuLocator)).perform();
		Thread.sleep(1000);
		doClick(thirdMenuChildLocator);
	}

	public void doRightClick(By locator, By optionsLocator, String menuToSelect) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).perform();
		List<WebElement> contextMenuOptionsList = getElements(optionsLocator);
		System.out.println("Total options in the context menue: " + contextMenuOptionsList.size());
		for (WebElement e : contextMenuOptionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(menuToSelect)) {
				e.click();
				break;
			}
		}
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator)).perform();
	}

	public void doActionsSendKeysWithPause(By locator, String value) {
		Actions act = new Actions(driver);
		char val[] = value.toCharArray();
		for (char c : val) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(500).perform();
		}
	}

	public void doScrollToSpecificElement(By locator) {
		Actions act = new Actions(driver);
		act.scrollToElement(getElement(locator)).perform();
	}

	public void doUploadFile(By locator, String filePath) {
		getElement(locator).sendKeys(filePath);
	}

	// **************** Wait Utils *********//
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void doClickWithWait(By locator, int timeOut) {
		waitForVisibilityOfElement(locator, timeOut).click();
	}

	public void doSendKeysWithWait(By locator, String valueToSend, int timeOut) {
		waitForVisibilityOfElement(locator, timeOut).sendKeys(valueToSend);
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForPresenceOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public String waitForTitleContains(String titlePartialValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleContains(titlePartialValue))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println("Page loading takes time and the title is not loaded yet");
			e.printStackTrace();
		}
		return titlePartialValue;

	}

	public String waitForTitleIs(String titleCompleteValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleIs(titleCompleteValue))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println("Page loading takes time and the title is not loaded yet");
			e.printStackTrace();
		}
		return titleCompleteValue;
	}

	public String waitForURLContains(String urlPartialValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlContains(urlPartialValue))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println(urlPartialValue + "Page loading took too long, and the expected URL was not found.");
			e.printStackTrace();
		}
		return null;
	}

	public String waitForURLFullValue(String urlFullValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlToBe(urlFullValue))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println(urlFullValue + "Page loading took too long, and the expected URL was not found.");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method waits for a JavaScript alert to be present. It supports all
	 * standard JavaScript dialog types: alert, confirm, and prompt.
	 * 
	 * @author Sabawoon Yuonus
	 * @param timeOut
	 * @return
	 */
	public Alert waitForJSAlerts(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptJSAlert(int timeOut) {
		waitForJSAlerts(timeOut).accept();
	}

	public void dismissJSAlert(int timeOut) {
		waitForJSAlerts(timeOut).dismiss();
	}

	public String getJSAlertText(int timeOut) {
		return waitForJSAlerts(timeOut).getText();
	}

	public void enterValueOnJSPrompt(int timeOut, String valueToSend) {
		waitForJSAlerts(timeOut).sendKeys(valueToSend);
	}

	public void waitForFrameByLocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameByIndex(By frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameByIdOrName(String IDOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}

	public void waitForFrameByWebElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public boolean checkNewWindowExist(int timeOut, int expectedNumberOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
				return true;
			}
		} catch (TimeoutException e) {
			System.out.println("The expected number of browser windows was not reached within the specified timeout.");
		}
		return false;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible. The method waits
	 * for the specified timeout and checks the condition at the specified interval
	 * time until the element is found or the timeout is reached.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @param intervalTime
	 * @return
	 */
	public WebElement waitForPresenceOfElement(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0. The method waits for the
	 * specified timeout and checks the condition at the specified interval time
	 * until the element becomes visible or the timeout is reached.
	 * 
	 * @author Sabawoon Yuonus
	 * @param locator
	 * @param timeOut
	 * @param intervalTime
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime)).withMessage("Time is done -- Element is not Found")
				.ignoring(TimeoutException.class).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForFrameWithFluentWait(String frameIDOrName, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.withMessage("Frame was not visible within the specified timeout").ignoring(NoSuchFrameException.class);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDOrName));
	}

	public Alert waitForAlertsWithFluentWait(int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.withMessage("Alert was not visible within the specified timeout")
				.ignoring(NoAlertPresentException.class);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public WebElement retryingElement(By locator, int timeOut) {
		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("Element is found ... " + locator + " in attempt " + attempts);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("Element is not found ... " + locator + " in attempt " + attempts);
				try {
					Thread.sleep(500); // Default Polling time after each attempt
				} catch (InterruptedException e1) {
					e1.printStackTrace(); // to show where the exception come from
				}
			}
			attempts++;
		}
		if (element == null) {
			System.out.println("Element is not found ... tried for " + timeOut + " times " + " with the interval of "
					+ 500 + " milli seconds ");
			throw new FrameworkException("No Such Element");
		}
		return element;
	}

	public WebElement retryingElement(By locator, int timeOut, int intervalTime) {
		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("Element is found ... " + locator + " in attempt " + attempts);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("Element is not found ... " + locator + " in attempt " + attempts);
				try {
					Thread.sleep(intervalTime); // Custom Polling time after each attempts
				} catch (InterruptedException e1) {
					e1.printStackTrace();// to show where the exception come from
				}
			}
			attempts++;
		}

		if (element == null) {
			System.out.println("Element is not found ... tried for " + timeOut + " times " + " with the interval of "
					+ intervalTime + " milli seconds ");
			throw new FrameworkException("No Such Element");
		}
		return element;
	}
	
	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"))
				.toString();
		return Boolean.parseBoolean(flag);
	}
	
	public void setPageLoadTimeout(int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
	}

}