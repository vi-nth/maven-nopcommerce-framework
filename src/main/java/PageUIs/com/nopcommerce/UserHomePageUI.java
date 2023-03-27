package PageUIs.com.nopcommerce;

public class UserHomePageUI {
	public static final String DYNAMIC_PAGE_LINK="XPATH=//div[@class='header-links']//a[@class='%s']";
	public static final String DYNAMIC_MENU_NAME="XPATH=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_SUBMENU_NAME="XPATH=//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]//following-sibling::ul//a[contains(text(),'%s')]";
	public static final String SORT_DROPDOWN="CSS=select#products-orderby";
	public static final String PAGING_NUMBER="CSS=select#products-pagesize";
	public static final String NUMBERS_OF_ITEMS="CSS=div.details h2";
	public static final String PAGING_DISPLAYED="CSS=div.pager";
	public static final String DYNAMIC_PAGING_BUTTON="xpath=//li[@class='%s']/a";
	public static final String DYNAMIC_PAGING_NUMBER="XPATH=//div[@class='pager']//a[text()='%s']";
	public static final String PRODUCT_NAME="XPATH=//div[@class='details']/h2/a";
	public static final String PRODUCT_PRICE="XPATH=//div[@class='prices']/span";

}
