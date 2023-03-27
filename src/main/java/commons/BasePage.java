package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

// Common class
public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	// Nhiem vu mow 1 Url bat ky ra
	// Common funtion
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	// getTitle
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	// getCurrentUrl/ pageSource
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSoureCode(WebDriver driver) {
		return driver.getPageSource();
	}

	// Back to page
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	// forward to page
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	// refresh
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(5);
	}

	// Wait for alert presence
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	// accepAlert
	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	// cancelAlert
	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	// getAlertText
	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	// sendKeyToAlert
	protected void senKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	// switchToWindowByID
	protected void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	// switchToWindowByTitle
	protected void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				break;
			}

		}
	}

	// closeAllWindowWithoutParent
	protected void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowDs = driver.getWindowHandles();

		for (String id : allWindowDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	// Ham tuong tac voi element
	// private By getByXpath(String xpathLocator) {
	// return By.xpath(xpathLocator);
	// }

	/**
	 * locatorType: id=/ css=/ xpath=/ name=/ class= locatorType: ID=/ CSS=/ XPATH=/
	 * NAME=/ CLASS= locatorType: Id=/ Css=/ Xpath=/ Name=/ Class=
	 * 
	 * @param locatorType
	 * @return
	 */
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=")
				|| locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=")
				|| locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=")
				|| locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator Type is not supported");
		}

		return by;

	}

	/**
	 * Truyen vao locator = xpath --> work Truyen vào locator # xpath--> sai
	 * 
	 * @param locatorType
	 * @param values
	 * @return
	 */
	private String getDynamicXpath(String locatorType, String... values) {
		System.out.println("Locator Type Before=" + locatorType);
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")
				|| locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) values);
		}
		for (String value : values) {
			System.out.println("Values ap to locator=" + value);
		}
		System.out.println("Locator Type After =" + locatorType);

		return locatorType;
	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	//Sort
	public List<WebElement> getListWebElementSort(WebDriver driver, String locatorType) {
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    try {
	        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorType)));
	    } catch (StaleElementReferenceException e) {
	        return getListWebElementSort(driver, locatorType);
	    }
	}

	protected void clickToElement(WebDriver driver, String locatorType) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locatorType);
			sleepInSecond(3);
		} else {
			getWebElement(driver, locatorType).click();
		}

	}

	protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locatorType, dynamicValues);
			sleepInSecond(3);
		} else {
			getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
		}
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void clearValueInElementByDeleteKey(WebDriver driver, String locatorType) {
		WebElement element = this.getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}
	
	public void clearValueInElementByDeleteKey(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = this.getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected void selectItemInDefautlDropDown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	protected void selectItemInDefautlDropDown(WebDriver driver, String locatorType, String textItem,
			String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	protected void getFirstSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.getFirstSelectedOption();
	}

	protected void getFirstSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.getFirstSelectedOption();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
			String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollInToView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}

		}

	}

	public void selectItemInEdiableDropdown(WebDriver driver, String parentXpath, String childXpath,
			String expectedItemText) {
		getWebElement(driver, parentXpath).clear();
		getWebElement(driver, parentXpath).sendKeys(expectedItemText);
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
		System.out.println("All item = " + allItems.size());

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItemText)) {
				if (!item.isDisplayed()) {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
					item.click();
				} else {
					item.click();
					break;
				}
			}
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected String getElementAttribute(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getAttribute(locatorType);
	}

	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName,
			String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	protected String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	protected String getHexaColorFromRgba(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	protected void checkToCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

//	protected void checkToCheckboxOrRadioBỵJs(WebDriver driver, String locatorType, String... dynamicValues) {
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
//		if (!element.isSelected()) {
//			jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
//		}
//	}

	protected void checkToCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToCheckboxOrRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToCheckboxOrRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			System.out.println("Case3: Element is not in DOM");
			return true;

		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Case2: Element present in DOM but not invisble/ displayed");
			return true;

		} else {
			System.out.println("Case1: Element is in DOM and displayed");
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			System.out.println("Case3: Element is not in DOM");
			return true;

		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Case2: Element present in DOM but not invisble/ displayed");
			return true;

		} else {
			System.out.println("Case1: Element is in DOM and displayed");
			return false;
		}
	}

	public void overrideImplicitTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	protected boolean isElementEnable(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	protected void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}
	
	protected void doubleClickToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locatorType)).perform();
	}
	
	protected void doubleClickToElement(WebDriver driver, String locatorType,String... dynamicValues) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	protected void scrollToBottomPage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void hightlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);

		String originalStyle = element.getAttribute("style");
		// B: có viền bao quanh
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void scrollToElementOnTop(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	protected void scrollToElementOnDown(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locatorType));
	}

	protected String getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath=", "");
		return (String) jsExecutor.executeScript("return $document.evaluate(\"" + xpathLocator
				+ "\", document, null,XPathResult.FIRST_ORDERED_NODE_TYPE,null).singleNodeValue).val()");
	}

	protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locatorType));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}

		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locatorType));
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		}
		return false;
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return status;
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	protected void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	/*
	 * Wait for Element undisplayed in DOM or Not in DOM and override implicit
	 * timepout
	 */
	protected void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	protected void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementInviisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(
				ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		// duong dan upload file
		// 1 file Java.png
		// Nhieu file --: String[] fileNames = {"CSharp.png","Java.png", "Python.png"};
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		// duong dan cua tat ca cac file
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		// getWebElement(driver, BasePageJQueryUI.UPLOAD_FILE).sendKeys(fullFileName);

	}

	public void uploadMultipleFilesMap4D(WebDriver driver, String fileName) {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		fileName = filePath + fileName;
		fileName = fileName.trim();
		//getWebElement(driver, UserManagementPageUI.UPLOAD_FILE).sendKeys(fileName);

	}

	public void uploadFile(WebDriver driver, String locatorType, String fileName, String... dynamicValues) {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		fileName = filePath + fileName;
		fileName = fileName.trim();
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).sendKeys(fileName);

	}

	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void pressEscKeys(WebDriver driver) throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
