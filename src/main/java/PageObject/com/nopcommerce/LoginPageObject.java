package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.LoginPageUI;
import PageUIs.com.nopcommerce.RegisterPageUI;
import commons.BasePage;
import commons.PageGeneratorManager;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public MyAccountPageObject clickOnLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver,  LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

	public String getErrorMessageAtRequiredField() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE_AT_FIELD);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE_AT_FIELD);
	}

	public void enterToTextboxByID(String emailValue, String idTextbox) {
		waitForElementVisible(driver, LoginPageUI.DYNAMIC_TEXTBOX_BY_ID, idTextbox);
		sendkeyToElement(driver,  LoginPageUI.DYNAMIC_TEXTBOX_BY_ID, emailValue, idTextbox);
		
	}

	public String getErrorMessageValidation() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_VALIDATION_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_VALIDATION_MESSAGE);
	}

	



}
