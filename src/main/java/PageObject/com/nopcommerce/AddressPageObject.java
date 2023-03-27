package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.AddressPageUI;
import commons.BasePage;
import commons.PageGeneratorManager;

public class AddressPageObject extends BasePage{
	WebDriver driver;

	public AddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getAddressBodyContent() {
		waitForAllElementVisible(driver, AddressPageUI.BODY_CONTENT);
		return getElementText(driver, AddressPageUI.BODY_CONTENT);
	}

	public void clickOnAddNewButton() {
		waitForElementClickable(driver, AddressPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, AddressPageUI.ADD_NEW_BUTTON);
	}

	public void selectValueByDynamicByID(String selectedValue, String dropdownID) {
		waitForElementClickable(driver, AddressPageUI.DYNAMIC_DROPDOWN, dropdownID);
		selectItemInDefautlDropDown(driver, AddressPageUI.DYNAMIC_DROPDOWN, selectedValue, dropdownID);
		
	}

	public void enterValueByDynamicTextbox(String valueInput, String textboxID) {
		waitForElementVisible(driver, AddressPageUI.DYNAMIC_TEXTBOX, textboxID);
		sendkeyToElement(driver, AddressPageUI.DYNAMIC_TEXTBOX, valueInput, textboxID);
		
	}

	public void clickOnSaveButton() {
		waitForElementClickable(driver, AddressPageUI.SAVE_BUTTON);
		clickToElement(driver, AddressPageUI.SAVE_BUTTON);
		
	}

	public String getNotificationMessage() {
		waitForElementVisible(driver, AddressPageUI.NOTIFICATION_MESSAGE);
		return getElementText(driver, AddressPageUI.NOTIFICATION_MESSAGE);
	}

	public UserPasswordPageObject clickOnDynamicPage(String pageName) {
		waitForElementClickable(driver, AddressPageUI.DYNAMIC_PAGE_NAME, pageName);
		clickToElement(driver, AddressPageUI.DYNAMIC_PAGE_NAME, pageName);
		return PageGeneratorManager.getUserPasswordPage(driver);
	}

}
