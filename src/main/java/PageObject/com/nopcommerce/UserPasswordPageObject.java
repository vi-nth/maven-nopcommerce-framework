package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.UserPasswordPageUI;
import commons.BasePage;
import commons.PageGeneratorManager;

public class UserPasswordPageObject extends BasePage {
	WebDriver driver;

	public UserPasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToDynamicPasswordByID(String passwordInput, String passwordID) {
		waitForElementVisible(driver, UserPasswordPageUI.DYNAMIC_PASSWORD, passwordID);
		sendkeyToElement(driver, UserPasswordPageUI.DYNAMIC_PASSWORD, passwordInput, passwordID);

	}

	public void clickOnChangePasswordButton() {
		waitForElementClickable(driver, UserPasswordPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, UserPasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}

	public String getErrorMessageTextAtPassword() {
		waitForElementVisible(driver, UserPasswordPageUI.ERROR_MESSAGE_PASSWORD);
		return getElementText(driver, UserPasswordPageUI.ERROR_MESSAGE_PASSWORD);
	}


	public String getNotificationMessage() {
		waitForElementVisible(driver, UserPasswordPageUI.NOTIFICATION_MESSAGE);
		return getElementText(driver, UserPasswordPageUI.NOTIFICATION_MESSAGE);
	
	}

	public void clickCloseMessage() {
		waitForElementClickable(driver, UserPasswordPageUI.CLOSE_NOTIFICATION_MESSAGE);
		clickToElement(driver, UserPasswordPageUI.CLOSE_NOTIFICATION_MESSAGE);
		
	}

	public UserHomePageObject clickToPageLink(String linkPageName) {
		waitForElementClickable(driver, UserPasswordPageUI.DYNAMIC_PAGE_LINK, linkPageName);
		clickToElement(driver, UserPasswordPageUI.DYNAMIC_PAGE_LINK, linkPageName);
		return PageGeneratorManager.getUserHomePage(driver);
	}

}
