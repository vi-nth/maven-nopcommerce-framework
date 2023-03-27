package PageUIs.com.nopcommerce;

public class MyOrdersPageUI {
	
	public static final String ORDERS_NUMBER="XPATH=//div[@class='order-list']//strong[contains(text(),'Order Number')]";
	public static final String ORDERS_DETAILS_BUTTON="XPATH=//div[@class='order-list']//button";
	public static final String ORDERS_NUMBER_DETAILS="XPATH=//div[@class='order-number']";
	public static final String ORDERS_STATUS="XPATH=//ul[@class='order-overview-content']//li[text()='Order Status: Pending']";
	public static final String ORDERS_TOTAL="XPATH=//ul[@class='order-overview-content']//li[@class='order-total']";
	
	public static final String DYNAMIC_CONFIRM_ORDER_INFOR="XPATH=//div[@class='%s']//li[@class='%s']";
	public static final String DYNAMIC_INFOR_PRODUCT_IN_TABLE="XPATH=//td[@class='%s']//span[@class='%s']";
	public static final String PRODUCT_NAME_IN_CONFIRM_TABLE="XPATH=//td[@class='product']//a";
	public static final String GIFT_WRAPING="XPATH=//div[@class='section options']/div";
	public static final String PRICE_DETAIL="XPATH=//label[text()='%s']/parent::td/following-sibling::td/span";

	public static final String REODER_BUTTON="xpath=//button[@class='button-1 re-order-button']";
}
