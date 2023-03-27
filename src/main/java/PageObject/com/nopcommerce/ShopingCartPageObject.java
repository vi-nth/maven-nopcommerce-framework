package PageObject.com.nopcommerce;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageUIs.com.nopcommerce.MyAccountPageUI;
import PageUIs.com.nopcommerce.ShopingCartPageUI;
import PageUIs.com.nopcommerce.WishlistPageObject;
import commons.BasePage;
import commons.PageGeneratorManager;

public class ShopingCartPageObject extends BasePage {
	WebDriver driver;

	public ShopingCartPageObject(WebDriver driver) {

		this.driver = driver;
	}

	public boolean isProductAddToCartSuccessfully(String productName) {
		waitForElementVisible(driver, ShopingCartPageUI.PRODUCT_IN_SHOPING_CART, productName);
		return isElementDisplayed(driver, ShopingCartPageUI.PRODUCT_IN_SHOPING_CART, productName);
	}

	public WishlistPageObject clickOnDynamicHeadMenu(String className) {
		waitForElementClickable(driver, ShopingCartPageUI.DYNAMIC_HEADER_MENU, className);
		clickToElementByJS(driver, ShopingCartPageUI.DYNAMIC_HEADER_MENU, className);
		return PageGeneratorManager.getWishlistPage(driver);
	}

	public MyAccountPageObject clickEditButton() {
		waitForElementClickable(driver, ShopingCartPageUI.EDIT_BUTTON);
		clickToElementByJS(driver, ShopingCartPageUI.EDIT_BUTTON);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

	public String getProductNameInTable() {
		waitForElementVisible(driver, ShopingCartPageUI.PRODUCT_NAME_INFOR_IN_TABLE);
		return getElementText(driver, ShopingCartPageUI.PRODUCT_NAME_INFOR_IN_TABLE);
	}

	public String getProductAttributeInTable() {
		waitForElementVisible(driver, ShopingCartPageUI.PRODUCT_ATTRIBUTE_IN_TABLE);
		return getElementText(driver, ShopingCartPageUI.PRODUCT_ATTRIBUTE_IN_TABLE);
	}

	public String getTotalPriceInTable() {
		waitForElementVisible(driver, ShopingCartPageUI.PRODUCT_TOTAL_PRICE_IN_TABLE);
		return getElementText(driver, ShopingCartPageUI.PRODUCT_TOTAL_PRICE_IN_TABLE);
	}

	public void clickToRemoveProductButton() {
		waitForElementClickable(driver, ShopingCartPageUI.REMOVE_BUTTON);
		clickToElement(driver, ShopingCartPageUI.REMOVE_BUTTON);

	}

	public boolean isShopingCartEmpty() {
		waitForElementVisible(driver, ShopingCartPageUI.ORDER_CONTENT);
		return isElementDisplayed(driver, ShopingCartPageUI.ORDER_CONTENT);
	}

	public boolean isProductsUndisplayed(String productName) {
		waitForElementInvisible(driver, ShopingCartPageUI.PRODUCT_NAME_IN_TABLE, productName);
		return isElementUndisplayed(driver, ShopingCartPageUI.PRODUCT_NAME_IN_TABLE, productName);
	}

	public MyAccountPageObject clickAndHoverMenu(String menuName, String subMenuName) {
		hoverMouseToElement(driver, ShopingCartPageUI.DYNAMIC_MENU_NAME, menuName);
		waitForElementClickable(driver, ShopingCartPageUI.DYNAMIC_SUBMENU_NAME, menuName, subMenuName);
		clickToElement(driver, ShopingCartPageUI.DYNAMIC_SUBMENU_NAME, menuName, subMenuName);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

	public void enterNumberOfProducts(String valueInput) {
		waitForElementVisible(driver, ShopingCartPageUI.QUANTITY_INPUT);
		sendkeyToElement(driver, ShopingCartPageUI.QUANTITY_INPUT, valueInput);
	}

	public void clickUpdateShopingCartButton() {
		waitForElementClickable(driver, ShopingCartPageUI.UPDATE_CART_BUTTON);
		clickToElement(driver, ShopingCartPageUI.UPDATE_CART_BUTTON);

	}

	public String getProductInfor() {
		waitForElementVisible(driver, ShopingCartPageUI.PRODUCT_TOTAL_PRICE_IN_TABLE);
		return getElementText(driver, ShopingCartPageUI.PRODUCT_TOTAL_PRICE_IN_TABLE);
	}

	public void selectWraping(String selectedValue) {
		selectItemInDefautlDropDown(driver, ShopingCartPageUI.SELECT_WRAPING, selectedValue);

	}

	public void checkToAgreePremisionCheckbox() {
		waitForElementClickable(driver, ShopingCartPageUI.AGREE_PERMISION_CHECKBOX);
		checkToCheckboxOrRadio(driver, ShopingCartPageUI.AGREE_PERMISION_CHECKBOX);

	}

	public void clickCheckoutButton() {
		waitForElementClickable(driver, ShopingCartPageUI.CHECKOUT_BUTTON);
		checkToCheckboxOrRadio(driver, ShopingCartPageUI.CHECKOUT_BUTTON);
	}

	public void UncheckToTheSameAddressCheckbox() {
		waitForElementClickable(driver, ShopingCartPageUI.SAME_ADDRESS_CHECKBOX);
		uncheckToCheckboxOrRadio(driver, ShopingCartPageUI.SAME_ADDRESS_CHECKBOX);

	}

	public void enterToDynamicBillingAddressTextbox(String valueInput, String dynamicID) {
		waitForElementVisible(driver, ShopingCartPageUI.DYNAMIC_BILLING_ADDRESS, dynamicID);
		sendkeyToElement(driver, ShopingCartPageUI.DYNAMIC_BILLING_ADDRESS, valueInput, dynamicID);

	}

	public void selectItemDyamic(String selectedValue, String dynamicID) {
		try{
			selectItemInDefautlDropDown(driver, ShopingCartPageUI.DYNAMIC_BILLING_ADDRESS_SELECT, selectedValue, dynamicID);
		}catch (StaleElementReferenceException e) {
			selectItemInDefautlDropDown(driver, ShopingCartPageUI.DYNAMIC_BILLING_ADDRESS_SELECT, selectedValue, dynamicID);
		}

	}

	public void clickContinueButton(String dynamicID) {
		waitForElementClickable(driver, ShopingCartPageUI.DYNAMIC_CONTINUE_BUTTON, dynamicID);
		checkToCheckboxOrRadio(driver, ShopingCartPageUI.DYNAMIC_CONTINUE_BUTTON, dynamicID);

	}

	public void selectShipingAddress(String selectedValue) {
		selectItemInDefautlDropDown(driver, ShopingCartPageUI.SELECT_SHIPING_ADDRESS, selectedValue);

	}

	public void selectShipingMethod(String slectedOption) {
		waitForElementClickable(driver, ShopingCartPageUI.CHOOSE_SHIPING_METHOD, slectedOption);
		checkToCheckboxOrRadio(driver, ShopingCartPageUI.CHOOSE_SHIPING_METHOD, slectedOption);

	}

	public void selectPaymentMethod(String slectedOption) {
		waitForElementClickable(driver, ShopingCartPageUI.CHOOSE_PAYMENT_METHOD, slectedOption);
		checkToCheckboxOrRadio(driver, ShopingCartPageUI.CHOOSE_PAYMENT_METHOD, slectedOption);

	}

	public boolean isPaymentInforDisplayed() {
		waitForElementVisible(driver, ShopingCartPageUI.PAYMENT_INFOR);
		return isElementDisplayed(driver, ShopingCartPageUI.PAYMENT_INFOR);
	}

	public Object getInforOrderText(String dynamicClass, String dynamicName) {
		waitForElementVisible(driver, ShopingCartPageUI.DYNAMIC_CONFIRM_ORDER_INFOR, dynamicClass, dynamicName);
		return getElementText(driver, ShopingCartPageUI.DYNAMIC_CONFIRM_ORDER_INFOR, dynamicClass, dynamicName);
	}

	public Object getInforProductIntable(String columnName, String dynamicClass) {
		waitForElementVisible(driver, ShopingCartPageUI.DYNAMIC_INFOR_PRODUCT_IN_TABLE, columnName, dynamicClass);
		return getElementText(driver, ShopingCartPageUI.DYNAMIC_INFOR_PRODUCT_IN_TABLE, columnName, dynamicClass);
	}

	public Object getInforProductNameIntable() {
		waitForElementVisible(driver, ShopingCartPageUI.PRODUCT_NAME_IN_CONFIRM_TABLE);
		return getElementText(driver, ShopingCartPageUI.PRODUCT_NAME_IN_CONFIRM_TABLE);
	}

	public Object getPriceDetail(String dynamicText) {
		waitForElementVisible(driver, ShopingCartPageUI.PRICE_DETAIL, dynamicText);
		return getElementText(driver, ShopingCartPageUI.PRICE_DETAIL, dynamicText);
	}

	public boolean isThankYouDisplayed() {
		waitForElementVisible(driver, ShopingCartPageUI.THANK_YOU_MESSAGE);
		return isElementDisplayed(driver, ShopingCartPageUI.THANK_YOU_MESSAGE);
	}

	public boolean isSuccessMessaageDisplayed() {
		waitForElementVisible(driver, ShopingCartPageUI.SUCCESSFULL_ORDER_MESSAGE);
		return isElementDisplayed(driver, ShopingCartPageUI.SUCCESSFULL_ORDER_MESSAGE);
	}

	public boolean isOrderNumberDisplayed() {
		waitForElementVisible(driver, ShopingCartPageUI.ORDER_NUMBER);
		return isElementDisplayed(driver, ShopingCartPageUI.ORDER_NUMBER);
	}

	public Object getGiftWraping() {
		waitForElementVisible(driver, ShopingCartPageUI.GIFT_WRAPING);
		return getElementText(driver, ShopingCartPageUI.GIFT_WRAPING);
	}

	public void clickConfirmButton() {
		waitForElementClickable(driver, ShopingCartPageUI.CONFIRM_BUTTON);
		clickToElement(driver, ShopingCartPageUI.CONFIRM_BUTTON);
	}
	public void clickConfirmButtonReorder() {
		waitForElementClickable(driver, ShopingCartPageUI.CONFIRM_BUTTON);
		clickToElementByJS(driver, ShopingCartPageUI.CONFIRM_BUTTON);
	}

	
//	public void clickConfirmButton() {
//		try {
//			waitForElementClickable(driver, ShopingCartPageUI.CONFIRM_BUTTON);
//			clickToElement(driver, ShopingCartPageUI.CONFIRM_BUTTON);
//		} catch (UnhandledAlertException f) {
//				acceptAlert(driver);
//			
//		}
//
//	}

	public void selectValueInCreditCard(String selectedValue, String dynamicID) {
		selectItemInDefautlDropDown(driver, ShopingCartPageUI.SELECT_DYNAMIC_BY_ID, selectedValue, dynamicID);

	}

	public void enterVisaInfo(String inputValue, String dynamicID) {
		waitForElementVisible(driver, ShopingCartPageUI.DYNAMIC_VISA_INFOR, dynamicID);
		sendkeyToElement(driver, ShopingCartPageUI.DYNAMIC_VISA_INFOR, inputValue, dynamicID);

	}

	public void selectBilllingAddress(String selectedValue) {
		selectItemInDefautlDropDown(driver, ShopingCartPageUI.SELECT_BILLING_ADDRESS, selectedValue);

	}

	public void acceptAlertDisplayed() {
		acceptAlert(driver);
		
	}

}
