package commons;

import org.openqa.selenium.WebDriver;

import PageObject.com.nopcommerce.AddressPageObject;
import PageObject.com.nopcommerce.CompareListPageObject;
import PageObject.com.nopcommerce.LoginPageObject;
import PageObject.com.nopcommerce.MyAccountPageObject;
import PageObject.com.nopcommerce.MyOrdersPageObject;
import PageObject.com.nopcommerce.RecentlyViewPageObject;
import PageObject.com.nopcommerce.RegisterPageObject;
import PageObject.com.nopcommerce.SearchPageObject;
import PageObject.com.nopcommerce.ShopingCartPageObject;
import PageObject.com.nopcommerce.UserAccountPageObject;
import PageObject.com.nopcommerce.UserHomePageObject;
import PageObject.com.nopcommerce.UserPasswordPageObject;
import PageUIs.com.nopcommerce.WishlistPageObject;

public class PageGeneratorManager {
	
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);

	}
	
	public static RegisterPageObject getRegisterPage(WebDriver drvier) {
		return new RegisterPageObject(drvier);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
		
	}
	
	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new MyAccountPageObject(driver);
		
	}
	
	public static UserAccountPageObject getUserAccountPage(WebDriver driver) {
		return new UserAccountPageObject(driver);
		
	}
	
	public static AddressPageObject getAddressPage(WebDriver driver) {
		return new AddressPageObject(driver);
		
	}
	
	public static UserPasswordPageObject getUserPasswordPage(WebDriver driver) {
		return new UserPasswordPageObject(driver);
		
	}
	
	public static SearchPageObject getSearchPage(WebDriver driver) {
		return new SearchPageObject(driver);
	}
	
	public static WishlistPageObject getWishlistPage(WebDriver driver) {
		return new WishlistPageObject(driver);
	}
	
	public static ShopingCartPageObject getShopingCart(WebDriver driver) {
		return new ShopingCartPageObject(driver);
	}
	
	public static CompareListPageObject getCompareListPage (WebDriver driver) {
		return new CompareListPageObject(driver);
	}
	
	public static RecentlyViewPageObject getRecentlyViewPage(WebDriver driver) {
		return new RecentlyViewPageObject(driver);
	}
	
	public static MyOrdersPageObject getMyOrdersPage(WebDriver driver) {
		return new MyOrdersPageObject(driver);
	}
}
