package PageUIs.com.nopcommerce;

public class ShopingCartPageUI {
	public static final String PRODUCT_IN_SHOPING_CART = "xpath=//a[@class='product-name' and text()='%s']";
	public static final String DYNAMIC_HEADER_MENU = "XPATH=//div[@class='header-links']//a[@class='%s']";
	public static final String EDIT_BUTTON = "css=div.edit-item>a";
	
	public static final String PRODUCT_NAME_INFOR_IN_TABLE="XPATH=//td[@class='product']/a";
	public static final String PRODUCT_ATTRIBUTE_IN_TABLE="css=td.product div.attributes";
	public static final String PRODUCT_TOTAL_PRICE_IN_TABLE="CSS=td.subtotal";
	public static final String REMOVE_BUTTON="CSS=button.remove-btn";
	public static final String ORDER_CONTENT="XPATH=//div[@class='no-data' and contains(text(),'Your Shopping Cart is empty!')]";
	public static final String PRODUCT_NAME_IN_TABLE="XPATH=//td[@class='product']/a[text()='%s']";
	
	public static final String DYNAMIC_MENU_NAME = "XPATH=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_SUBMENU_NAME = "XPATH=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]//following-sibling::ul//a[contains(text(),'%s')]";
	public static final String QUANTITY_INPUT="CSS=input.qty-input";
	public static final String UPDATE_CART_BUTTON="CSS=button#updatecart";
	
	public static final String SELECT_WRAPING="css=select#checkout_attribute_1";
	public static final String AGREE_PERMISION_CHECKBOX="css=input#termsofservice";
	public static final String CHECKOUT_BUTTON="CSS=button#checkout";
	public static final String SAME_ADDRESS_CHECKBOX="CSS=input#ShipToSameAddress";
	public static final String DYNAMIC_BILLING_ADDRESS="xpath=//input[@id='%s']";
	public static final String DYNAMIC_BILLING_ADDRESS_SELECT="xpath=//select[@id='%s']";
	public static final String BILLING_CONTINUE_BUTTON="CSS=div#billing-buttons-container button[name='save']";
	public static final String DYNAMIC_CONTINUE_BUTTON="XPATH=//div[@id='%s']//button[text()='Continue']";
	public static final String SELECT_BILLING_ADDRESS="XPATH=//select[@id='billing-address-select']";
	public static final String SELECT_SHIPING_ADDRESS="XPATH=//select[@id='shipping-address-select']";
	public static final String CHOOSE_SHIPING_METHOD="XPATH=//label[text()='%s']/preceding-sibling::input";
	public static final String CHOOSE_PAYMENT_METHOD="XPATH=//label[text()='%s']/preceding-sibling::input";
	
	public static final String PAYMENT_INFOR="XPATH=//p[contains(text(),'Mail Personal')]";
	public static final String DYNAMIC_CONFIRM_ORDER_INFOR="XPATH=//div[@class='%s']//li[@class='%s']";
	public static final String DYNAMIC_INFOR_PRODUCT_IN_TABLE="XPATH=//td[@class='%s']//span[@class='%s']";
	public static final String PRODUCT_NAME_IN_CONFIRM_TABLE="XPATH=//td[@class='product']/a";
	public static final String GIFT_WRAPING="XPATH=//div[@class='cart-options']/div";
	public static final String PRICE_DETAIL="XPATH=//label[text()='%s']/parent::td/following-sibling::td/span";
	public static final String THANK_YOU_MESSAGE="XPATH=//div[@class='page-title']/h1[text()='Thank you']";
	public static final String SUCCESSFULL_ORDER_MESSAGE="XPATH=//div[@class='section order-completed']/div[@class='title']/strong[text()='Your order has been successfully processed!']";
	public static final String ORDER_NUMBER="XPATH=//div[@class='section order-completed']//div[@class='order-number']";
	public static final String CONFIRM_BUTTON="XPATH=//div[@id='confirm-order-buttons-container']//button[text()='Confirm']";
	
	public static final String SELECT_DYNAMIC_BY_ID="XPATH=//select[@id='%s']";
	public static final String DYNAMIC_VISA_INFOR="XPATH=//input[@id='%s']";

}
