package PageUIs.com.nopcommerce;

public class CompareListPageUI {
	public static final String PRODUCT_NAME="XPATH=//a[text()='%s']";
	public static final String PRODUCT_PRICE="XPATH=//a[text()='%s']//ancestor::tr/following-sibling::tr[@class='product-price']//td[text()='%s']";
	public static final String PRODUCT_REMOVE="css=tbody tr.remove-product button";
	public static final String CLEAR_LIST_BUTTON="CSS=a.clear-list";
	public static final String NO_DATA_COMPARE="CSS=div.page-body div.no-data";

}
