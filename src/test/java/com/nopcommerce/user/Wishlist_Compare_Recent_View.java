package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.UserData;

import PageObject.com.nopcommerce.CompareListPageObject;
import PageObject.com.nopcommerce.LoginPageObject;
import PageObject.com.nopcommerce.MyAccountPageObject;
import PageObject.com.nopcommerce.RecentlyViewPageObject;
import PageObject.com.nopcommerce.RegisterPageObject;
import PageObject.com.nopcommerce.ShopingCartPageObject;
import PageObject.com.nopcommerce.UserHomePageObject;
import PageUIs.com.nopcommerce.WishlistPageObject;
import commons.BaseTest;
import commons.PageGeneratorManager;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Wishlist_Compare_Recent_View extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private WishlistPageObject myWishlistPage;
	private CompareListPageObject compareListPage;
	private ShopingCartPageObject myShopingCartPage;
	private RecentlyViewPageObject recentlyViewPage;

	private DataHelper dataFaker;
	private String emailAddress;

	@Parameters({ "browserName", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {

		driver = getBrowserDriver(browserName, appUrl);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		dataFaker = DataHelper.getDataHelper();

		emailAddress = UserData.Register.EMAIL_ADDRESS + generateFakeNumber() + "@gmail.uk";

		registerPage = userHomePage.clickToPageLink("ico-register");
		registerPage.selectGenderAtRadioButton("Male");
		registerPage.enterValueToTextoxByID(UserData.Register.FIRST_NAME, "FirstName");
		registerPage.enterValueToTextoxByID(UserData.Register.LAST_NAME, "LastName");

		registerPage.selectDateInDropDown(UserData.Register.DATE, "DateOfBirthDay");
		registerPage.selectDateInDropDown(UserData.Register.MONTH, "DateOfBirthMonth");
		registerPage.selectDateInDropDown(UserData.Register.YEAR, "DateOfBirthYear");

		registerPage.enterValueToTextoxByID(emailAddress, "Email");
		System.out.println("Emailinput:" + emailAddress);
		registerPage.enterValueToTextoxByID(UserData.Register.COMPANY, "Company");
		registerPage.checkNewsletterCheckbox();
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "Password");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "ConfirmPassword");
		registerPage.clickOnRegisterButton();

		loginPage = registerPage.clickToPageLink("Log in");
		loginPage.enterToTextboxByID(emailAddress, "Email");
		loginPage.enterToTextboxByID(UserData.Register.PASSWORD, "Password");
		myAccountPage = loginPage.clickOnLoginButton();

	}

	@Test
	public void TC_01_AddToWishList(Method method) {

		ExtentTestManager.startTest(method.getName(), "Add to Wishlist successfully");
		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 01: Hover to Computers and click on Notebooks");
		myAccountPage.clickAndHoverMenu("Computers", "Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 02: Click on 'Apple Macbook Pro 13-inch'");
		myAccountPage.clickOnProduct("Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 03: Click on 'Add To Wishlist' Button");
		myAccountPage.clickAddToWishlistButton();

		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 04: Verify message= 'The product has been added to your wishlist' is displayed");
		verifyEquals(myAccountPage.getMessageText(), "The product has been added to your wishlist");

		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 05: Click on 'Wishlist' at Menu");
		myAccountPage.clickOnDynamicHeadMenu("ico-wishlist");

		myWishlistPage = PageGeneratorManager.getWishlistPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 06: Verify 'Apple MacBook Pro 13-inch' added sucessfully in Wishlist");
		verifyTrue(myWishlistPage.isProductAddedSuccessfully("Apple MacBook Pro 13-inch"));

		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 07: Click on 'Your wishlist URL for sharing' link");
		myWishlistPage.clickOnWishlistUrlForSharingLink();

		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 08: Verify 'My Wishlist' is displayed");
		verifyTrue(myWishlistPage.isWishlistUserAccountDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " AddWishlist - Step 08: Verify 'Apple MacBook Pro 13-inch' is displayed in My Wishlist");
		verifyTrue(myWishlistPage.isProductsSharingDisplayed("Apple MacBook Pro 13-inch"));

	}

	@Test
	public void TC_02_AddProductToCartFromWishlistPage(Method method) {

		ExtentTestManager.startTest(method.getName(), "Add products to Cart from Wishlist Page successfully");
		ExtentTestManager.getTest().log(Status.INFO, " AddToCart - Step 01: Click back to Wishlist Page");
		myWishlistPage.clickBackPage();

		ExtentTestManager.getTest().log(Status.INFO, " AddToCart - Step 02: Check to 'Apple MacBook Pro 13-inch' checkbox ");
		myWishlistPage.checkToProductsAddToCart("Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " AddToCart - Step 03: Click 'Add To Cart' Button");
		myShopingCartPage = myWishlistPage.clickOnAddToCartButton();

		ExtentTestManager.getTest().log(Status.INFO, " AddToCart - Step 04: Verify add to Cart successfully");
		verifyTrue(myShopingCartPage.isProductAddToCartSuccessfully("Apple MacBook Pro 13-inch"));

		ExtentTestManager.getTest().log(Status.INFO, " AddToCart - Step 05: Click on 'Wishlist' link at menu");
		myWishlistPage = myShopingCartPage.clickOnDynamicHeadMenu("ico-wishlist");

		ExtentTestManager.getTest().log(Status.INFO, " AddToCart - Step 06: Verify Wishlist is empty");
		verifyTrue(myWishlistPage.isWhislistEmpty());

	}

	@Test
	public void TC_03_RemoveProductInWishlistPage(Method method) {

		ExtentTestManager.startTest(method.getName(), "Remove producy in Wishlist Page successfully");
		ExtentTestManager.getTest().log(Status.INFO, " RemoveProduct - Step 01: Hover to Computers and click on Notebooks");
		myAccountPage = myWishlistPage.clickAndHoverMenu("Computers", "Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProduct - Step 02: Click on 'Asus N551JK-XO076H Laptop'");
		myAccountPage.clickOnProduct("Asus N551JK-XO076H Laptop");

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProduct - Step 03: Click 'Add to Wishlist' Button");
		myAccountPage.clickAddToWishlistButton();

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProduct - Step 04: Verify message 'The product has been added to your wishlist' is displayed");
		verifyEquals(myAccountPage.getMessageText(), "The product has been added to your wishlist");

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProduct - Step 05: Click on' Wishlist' link at Menu");
		myAccountPage.clickOnDynamicHeadMenu("ico-wishlist");

		myWishlistPage = PageGeneratorManager.getWishlistPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProduct - Step 06: Verify 'Asus N551JK-XO076H Laptop' added successfully");
		verifyTrue(myWishlistPage.isProductAddedSuccessfully("Asus N551JK-XO076H Laptop"));

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProduct - Step 07: Click on 'Remove' Asus N551JK-XO076H Laptop from Wishlist");
		myWishlistPage.clickToRemoveProduct("Asus N551JK-XO076H Laptop");

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProduct - Step 08: Verify WishList is empty");
		verifyTrue(myWishlistPage.isWhislistEmpty());

	}

	@Test
	public void TC_04_AddProductToCompare(Method method) {

		ExtentTestManager.startTest(method.getName(), "Add products to Compare List successfully");
		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 01: Hover to Computers and click on Notebooks");
		myAccountPage = myWishlistPage.clickAndHoverMenu("Computers", "Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 02: Add 'HP Envy 6-1180ca 15.6-Inch Sleekbook' to Compare List");
		myAccountPage.clickAddToCompareList("HP Envy 6-1180ca 15.6-Inch Sleekbook");

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 03: Verify message 'The product has been added to your product comparison' is displayed ");
		verifyEquals(myAccountPage.getMessageText(), "The product has been added to your product comparison");
		sleepInSecond(1);

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 04: Add 'Lenovo Thinkpad X1 Carbon Laptop' to Compare List");
		myAccountPage.clickAddToCompareList("Lenovo Thinkpad X1 Carbon Laptop");

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 05: Verify message 'The product has been added to your product comparison' is displayed");
		verifyEquals(myAccountPage.getMessageText(), "The product has been added to your product comparison");
		sleepInSecond(1);

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 06: Click 'Compare Products List' on Footer Menu");
		myAccountPage.clickOnDynamicUpperLink("Compare products list");

		compareListPage = PageGeneratorManager.getCompareListPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 07: Verify icon Remove is displayed");
		verifyTrue(compareListPage.isRemoveIconDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 08: Verify product 'HP Envy 6-1180ca 15.6-Inch Sleekbook' is displayed");
		verifyTrue(compareListPage.isNameProductDisplayed("HP Envy 6-1180ca 15.6-Inch Sleekbook"));

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 09: Verify product 'Lenovo Thinkpad X1 Carbon Laptop' is displayed");
		verifyTrue(compareListPage.isNameProductDisplayed("Lenovo Thinkpad X1 Carbon Laptop"));

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 10: Verify price of 'HP Envy 6-1180ca 15.6-Inch Sleekbook' is displayed");
		verifyTrue(compareListPage.isPriceProductDisplayed("HP Envy 6-1180ca 15.6-Inch Sleekbook", "$1,460.00"));

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 11: Verify price of 'Lenovo Thinkpad X1 Carbon Laptop' is displayed");
		verifyTrue(compareListPage.isPriceProductDisplayed("Lenovo Thinkpad X1 Carbon Laptop", "$1,360.00"));

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 12: Click 'Clear list' Button");
		compareListPage.clickClearListButton();

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 13: Verify message 'You have no items to compare' is displayed");
		verifyEquals(compareListPage.getMessageText(), "You have no items to compare");

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 14: Verify 'HP Envy 6-1180ca 15.6-Inch SleekbooK' is undisplayed");
		verifyFalse(compareListPage.isNameProductUndisplayed("HP Envy 6-1180ca 15.6-Inch SleekbooK"));

		ExtentTestManager.getTest().log(Status.INFO, " Compare - Step 15: Verify 'Lenovo Thinkpad X1 Carbon Laptop' is undisplayed");
		verifyFalse(compareListPage.isNameProductUndisplayed("Lenovo Thinkpad X1 Carbon Laptop"));

	}

	@Test
	public void TC_05_RecentlyViewProducts(Method method) {

		ExtentTestManager.startTest(method.getName(), "Verify 3 recently viewed products");
		ExtentTestManager.getTest().log(Status.INFO, " RecentlyView - Step 01: Hover to Computers and click on Notebooks");
		myAccountPage.clickAndHoverMenu("Computers", "Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, " RecentlyView - Step 02: Click on 'Apple MacBook Pro 13-inch'");
		myAccountPage.clickOnProduct("Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " RecentlyView - Step 03: Click on 'Asus N551JK-XO076H Laptop'");
		myAccountPage.clickOnProduct("Asus N551JK-XO076H Laptop");

		ExtentTestManager.getTest().log(Status.INFO, " RecentlyView - Step 04: Click on 'HP Spectre XT Pro UltraBook'");
		myAccountPage.clickOnProduct("HP Spectre XT Pro UltraBook");

		ExtentTestManager.getTest().log(Status.INFO, " RecentlyView - Step 05: Click on 'Samsung Series 9 NP900X4C Premium Ultrabook'");
		myAccountPage.clickOnProduct("Samsung Series 9 NP900X4C Premium Ultrabook");

		ExtentTestManager.getTest().log(Status.INFO, " RecentlyView - Step 06: Click on 'HP Envy 6-1180ca 15.6-Inch Sleekbook'");
		myAccountPage.clickOnProduct("HP Envy 6-1180ca 15.6-Inch Sleekbook");

		ExtentTestManager.getTest().log(Status.INFO, " RecentlyView - Step 07: Click the 'Recently viewed products' Link in the Footer Menu");
		myAccountPage.clickOnDynamicUpperLink("Recently viewed products");

		recentlyViewPage = PageGeneratorManager.getRecentlyViewPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " RecentlyView - Step 08: Verify 3 recently viewed products displayed");
		verifyTrue(recentlyViewPage.isProductAtRecentlyViewDisplayed("HP Envy 6-1180ca 15.6-Inch Sleekbook"));
		verifyTrue(recentlyViewPage.isProductAtRecentlyViewDisplayed("Samsung Series 9 NP900X4C Premium Ultrabook"));
		verifyTrue(recentlyViewPage.isProductAtRecentlyViewDisplayed("HP Spectre XT Pro UltraBook"));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
