package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.CompareListPageUI;
import commons.BasePage;

public class CompareListPageObject extends BasePage {
	WebDriver driver;

	public CompareListPageObject(WebDriver driver) {

		this.driver = driver;
	}

	public boolean isRemoveIconDisplayed() {
		waitForElementVisible(driver, CompareListPageUI.PRODUCT_REMOVE);
		return isElementDisplayed(driver, CompareListPageUI.PRODUCT_REMOVE);
	}

	public boolean isNameProductDisplayed(String productName) {
		waitForElementVisible(driver, CompareListPageUI.PRODUCT_NAME, productName);
		return isElementDisplayed(driver, CompareListPageUI.PRODUCT_NAME, productName);
	}

	public boolean isPriceProductDisplayed(String productName, String productPrice) {
		waitForElementVisible(driver, CompareListPageUI.PRODUCT_PRICE, productName, productPrice);
		return isElementDisplayed(driver, CompareListPageUI.PRODUCT_PRICE, productName, productPrice);
	}

	public void clickClearListButton() {
		waitForElementClickable(driver, CompareListPageUI.CLEAR_LIST_BUTTON);
		clickToElement(driver, CompareListPageUI.CLEAR_LIST_BUTTON);

	}

	public Object getMessageText() {
		waitForElementVisible(driver, CompareListPageUI.NO_DATA_COMPARE);
		return getElementText(driver, CompareListPageUI.NO_DATA_COMPARE);
	}

	public boolean isNameProductUndisplayed(String string) {
		waitForElementInvisible(driver, CompareListPageUI.PRODUCT_NAME);
		return isElementUndisplayed(driver, CompareListPageUI.PRODUCT_NAME);
	}

}
