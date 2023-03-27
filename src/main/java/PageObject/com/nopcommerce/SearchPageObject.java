package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.SearchPageUI;
import commons.BasePage;

public class SearchPageObject extends BasePage {

	WebDriver driver;

	public SearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickSearchButton() {
		waitForElementClickable(driver, SearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, SearchPageUI.SEARCH_BUTTON);

	}

	public String getErrorMessageText() {
		waitForElementVisible(driver, SearchPageUI.WARNING_MESSAGE);
		return getElementText(driver, SearchPageUI.WARNING_MESSAGE);
		
	}

	public void enterToSearchTextbox(String keySearch) {
	waitForElementVisible(driver, SearchPageUI.SEARCH_TEXTBOX, keySearch);
	sendkeyToElement(driver, SearchPageUI.SEARCH_TEXTBOX, keySearch);

	}

	public int getNumbersOfProduct() {
		waitForElementVisible(driver, SearchPageUI.NUMBER_PRODUCTS);
		return getElementSize(driver, SearchPageUI.NUMBER_PRODUCTS);
	}

	public String getNameTextOfProduct() {
		waitForElementVisible(driver, SearchPageUI.NUMBER_PRODUCTS);
		return getElementText(driver, SearchPageUI.NUMBER_PRODUCTS);
	}

	public void checkToAdvancedSearchCheckbox() {
		waitForElementClickable(driver, SearchPageUI.ADVANCED_CHECKBOX);
		checkToCheckboxOrRadio(driver, SearchPageUI.ADVANCED_CHECKBOX);

	}

	public void selectCategory(String valueSelected) {
		selectItemInDefautlDropDown(driver, SearchPageUI.CATEGORY_SELECT, valueSelected);

	}

	public void uncheckToAutomaticSearch() {
		waitForElementClickable(driver, SearchPageUI.AUTOMATIC_SEARCH_CHECKBOX);
		uncheckToCheckboxOrRadio(driver, SearchPageUI.AUTOMATIC_SEARCH_CHECKBOX);

	}

	public void checkToAutomaticSearch() {
		waitForElementClickable(driver, SearchPageUI.AUTOMATIC_SEARCH_CHECKBOX);
		checkToCheckboxOrRadio(driver, SearchPageUI.AUTOMATIC_SEARCH_CHECKBOX);
	}

	public void selectManufature(String valueSelected) {
		selectItemInDefautlDropDown(driver, SearchPageUI.MANUFATURER_SELECT, valueSelected);


	}

}
