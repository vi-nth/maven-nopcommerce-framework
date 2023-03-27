package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.MyOrdersPageUI;
import PageUIs.com.nopcommerce.ShopingCartPageUI;
import commons.BasePage;

public class MyOrdersPageObject extends BasePage {
	WebDriver driver;

	public MyOrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isOrderNumberDisplayed() {
		waitForElementVisible(driver,MyOrdersPageUI.ORDERS_NUMBER);
		return isElementDisplayed(driver, MyOrdersPageUI.ORDERS_NUMBER);
	}

	public void clickDetailButton() {
		waitForElementClickable(driver, MyOrdersPageUI.ORDERS_DETAILS_BUTTON);
		clickToElement(driver, MyOrdersPageUI.ORDERS_DETAILS_BUTTON);

	}

	public String getOrderNumber() {
		waitForElementVisible(driver,MyOrdersPageUI.ORDERS_NUMBER);
		return getElementText(driver, MyOrdersPageUI.ORDERS_NUMBER);
	}

	public String getOrderNumberInDetails() {
		waitForElementVisible(driver,MyOrdersPageUI.ORDERS_NUMBER_DETAILS);
		return getElementText(driver, MyOrdersPageUI.ORDERS_NUMBER_DETAILS);
	}

	public boolean isOrderStatusDisplayed() {
		waitForElementVisible(driver,MyOrdersPageUI.ORDERS_STATUS);
		return isElementDisplayed(driver, MyOrdersPageUI.ORDERS_STATUS);
	}
	
	public Object getOrderTotal() {
		waitForElementVisible(driver,MyOrdersPageUI.ORDERS_TOTAL);
		return getElementText(driver, MyOrdersPageUI.ORDERS_TOTAL);
	}

	public Object getInforOrderText(String dynamicClass, String dynamicName) {
		waitForElementVisible(driver, MyOrdersPageUI.DYNAMIC_CONFIRM_ORDER_INFOR, dynamicClass, dynamicName);
		return getElementText(driver, MyOrdersPageUI.DYNAMIC_CONFIRM_ORDER_INFOR, dynamicClass, dynamicName);
	}

	public Object getInforProductIntable(String columnName, String dynamicClass) {
		waitForElementVisible(driver, MyOrdersPageUI.DYNAMIC_INFOR_PRODUCT_IN_TABLE, columnName, dynamicClass);
		return getElementText(driver, MyOrdersPageUI.DYNAMIC_INFOR_PRODUCT_IN_TABLE, columnName, dynamicClass);
	}

	public Object getInforProductNameIntable() {
		waitForElementVisible(driver, MyOrdersPageUI.PRODUCT_NAME_IN_CONFIRM_TABLE);
		return getElementText(driver, MyOrdersPageUI.PRODUCT_NAME_IN_CONFIRM_TABLE);
	}

	public Object getPriceDetail(String dynamicText) {
		waitForElementVisible(driver, MyOrdersPageUI.PRICE_DETAIL, dynamicText);
		return getElementText(driver, MyOrdersPageUI.PRICE_DETAIL, dynamicText);
	}

	public Object getGiftWraping() {
		waitForElementVisible(driver, MyOrdersPageUI.GIFT_WRAPING);
		return getElementText(driver, MyOrdersPageUI.GIFT_WRAPING);
	}

	public void clickReoderButton() {
		waitForElementClickable(driver, MyOrdersPageUI.REODER_BUTTON);
		clickToElement(driver, MyOrdersPageUI.REODER_BUTTON);
		
	}

	

}
