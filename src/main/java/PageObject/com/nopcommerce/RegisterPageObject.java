package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.RegisterPageUI;
import commons.BasePage;
import commons.PageGeneratorManager;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectGenderAtRadioButton(String genderValue) {
		waitForElementClickable(driver, RegisterPageUI.DYNAMIC_RADIO_BUTTON, genderValue);
		clickToElement(driver, RegisterPageUI.DYNAMIC_RADIO_BUTTON, genderValue);
	}

	public void enterValueToTextoxByID(String textboxValue, String textboxID) {
		waitForElementVisible(driver, RegisterPageUI.DYNAMIC_TEXTBOX, textboxID);
		sendkeyToElement(driver, RegisterPageUI.DYNAMIC_TEXTBOX, textboxValue, textboxID);
	}

	public void selectDateInDropDown(String dateValue, String dateByName) {
		waitForElementVisible(driver, RegisterPageUI.DYNAMIC_DROPDOWN, dateByName);
		selectItemInDefautlDropDown(driver, RegisterPageUI.DYNAMIC_DROPDOWN, dateValue, dateByName);
	}

	public void checkNewsletterCheckbox() {
		waitForElementClickable(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);
		clickToElement(driver, RegisterPageUI.NEWSLETTER_CHECKBOX);

	}

	public void clickOnRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);

	}

	public String getErrorMessageAtFieldByID(String errorByID) {
		waitForElementVisible(driver, RegisterPageUI.ERROR_MESSAGE, errorByID);
		return getElementText(driver, RegisterPageUI.ERROR_MESSAGE, errorByID);
		
	}

	public String getSuccessfullRegisterMessage() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESSFULL_MESSAGE);
		return getElementText(driver, RegisterPageUI.SUCCESSFULL_MESSAGE);
	}

	public String getValidationEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.VALIDATION_EMAIL_MESSAGE);
		return getElementText(driver, RegisterPageUI.VALIDATION_EMAIL_MESSAGE);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, RegisterPageUI.CONFRIM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFRIM_PASSWORD_ERROR_MESSAGE);
	}

	public LoginPageObject clickToPageLink(String pageLinkName) {
		waitForElementClickable(driver, RegisterPageUI.DYNAMIC_PAGE_LINK, pageLinkName);
		clickToElement(driver, RegisterPageUI.DYNAMIC_PAGE_LINK, pageLinkName);
		return PageGeneratorManager.getLoginPage(driver);
	}

}
