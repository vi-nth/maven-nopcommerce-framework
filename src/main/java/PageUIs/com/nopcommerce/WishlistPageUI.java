package PageUIs.com.nopcommerce;

import java.util.concurrent.Flow.Publisher;

public class WishlistPageUI {
	public static final String PRODUCT_IN_WISHLIST="xpath=//a[@class='product-name' and text()='%s']";
	public static final String SHARE_LINK="CSS=a.share-link";
	public static final String MY_WISHLIST="CSS=div.page-title h1";
	public static final String CHECKBOX_ADD_TO_CART="XPATH=//a[text()='Apple MacBook Pro 13-inch']//ancestor::tr/td[@class='add-to-cart']/input";
	public static final String ADD_TO_CART_BUTTON="css=button[name='addtocartbutton']";
	public static final String REMOVE_PRODUCT="XPATH=//a[text()='%s']/parent::td/following-sibling::td//button[@class='remove-btn']";
	public static final String EMPTY_WISGLIST="css=div.page-body div.no-data";
	public static final String DYNAMIC_MENU_NAME="XPATH=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_SUBMENU_NAME="XPATH=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]//following-sibling::ul//a[contains(text(),'%s')]";
	public static final String DYNAMIC_HEADER_MENU = "XPATH=//div[@class='header-links']//a[@class='%s']";
}
