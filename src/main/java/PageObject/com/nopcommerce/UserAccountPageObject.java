package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.LoginPageUI;
import PageUIs.com.nopcommerce.RegisterPageUI;
import PageUIs.com.nopcommerce.UserAccountPageUI;
import commons.BasePage;
import commons.PageGeneratorManager;

public class UserAccountPageObject extends BasePage {
	WebDriver driver;

	public UserAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyAccountPageDisplayed(String pageTitle) {
		waitForAllElementVisible(driver, UserAccountPageUI.PAGE_NAME_TITLE, pageTitle);
		return isElementDisplayed(driver, UserAccountPageUI.PAGE_NAME_TITLE, pageTitle);
	}

	public void selectGenderAtRadioButton(String genderValue) {
		waitForElementClickable(driver, UserAccountPageUI.DYNAMIC_RADIO_BUTTON, genderValue);
		clickToElement(driver, UserAccountPageUI.DYNAMIC_RADIO_BUTTON, genderValue);
		
	} 
	
	public void enterValueToTextoxByID(String valueInput, String textboxID) {
		waitForElementVisible(driver, UserAccountPageUI.DYNAMIC_TEXTBOX, textboxID);
		sendkeyToElement(driver, UserAccountPageUI.DYNAMIC_TEXTBOX, valueInput, textboxID);
		
	}

	public void selectDateInDropDown(String selectedValue, String dropdownID) {
		waitForElementVisible(driver, UserAccountPageUI.DYNAMIC_DROPDOWN, dropdownID);
		selectItemInDefautlDropDown(driver, UserAccountPageUI.DYNAMIC_DROPDOWN, selectedValue, dropdownID);
		
	}

	public void clickOnSaveButton() {
		waitForElementClickable(driver, UserAccountPageUI.SAVE_BUTTON);
		clickToElement(driver,UserAccountPageUI.SAVE_BUTTON);
		
	}

	public String getNotificationMessage() {
		waitForElementVisible(driver, UserAccountPageUI.NOTIFICATION_MESSAGE);
		return getElementText(driver, UserAccountPageUI.NOTIFICATION_MESSAGE);
	}

	public AddressPageObject clickOnDynamicPage(String pageName) {
		waitForElementClickable(driver, UserAccountPageUI.DYNAMIC_PAGE_NAME, pageName);
		clickToElement(driver, UserAccountPageUI.DYNAMIC_PAGE_NAME, pageName);
		return PageGeneratorManager.getAddressPage(driver);
	}

}
