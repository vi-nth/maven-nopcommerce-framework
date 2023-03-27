package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.UserData;

import PageObject.com.nopcommerce.LoginPageObject;
import PageObject.com.nopcommerce.MyAccountPageObject;
import PageObject.com.nopcommerce.RegisterPageObject;
import PageObject.com.nopcommerce.SearchPageObject;
import PageObject.com.nopcommerce.UserHomePageObject;
import commons.BaseTest;
import commons.PageGeneratorManager;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Search_And_Adcanced_Search extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private SearchPageObject searchPage;
	DataHelper dataFaker;
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
	public void Search_01_SearchWithEmptyData(Method method) {
		
		ExtentTestManager.startTest(method.getName(), "Search failed if empty ");
		ExtentTestManager.getTest().log(Status.INFO, " - Step 01: Click on 'Search'link on down menu");
		searchPage = myAccountPage.clickToDynamicPage("Search");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 02: Click on 'Search' Button");
		searchPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Verrify message 'Search term minimum length is 3 characters' is displayed");
		verifyEquals(searchPage.getErrorMessageText(), "Search term minimum length is 3 characters");
	}

	@Test
	public void Search_02_SearchWithDataNotExist(Method method) {

		ExtentTestManager.startTest(method.getName(), "Search failed if data is unexist ");
		ExtentTestManager.getTest().log(Status.INFO, " - Step 01: Enter to Searchbox with value 'Macbook Pro 2025'");
		searchPage.enterToSearchTextbox("Macbook Pro 2025");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 02: Click on 'Search' Button");
		searchPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Verrify message 'No products were found that matched your criteria.' is displayed");
		verifyEquals(searchPage.getErrorMessageText(), "No products were found that matched your criteria.");

	}

	@Test
	public void Search_03_SearchWithProductNameTuongDoi(Method method) {

		ExtentTestManager.startTest(method.getName(), "Search successfully if a relative product name ");
		ExtentTestManager.getTest().log(Status.INFO, " - Step 01: Enter to Searchbox with value 'Lenovo'");
		searchPage.enterToSearchTextbox("Lenovo");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 02: Click on 'Search' Button");
		searchPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Verify the resutls has 2 items");
		verifyEquals(searchPage.getNumbersOfProduct(), 2);

		ExtentTestManager.getTest().log(Status.INFO, " - Step 04: Verify the resutls contains 'Lenovo IdeaCentre 600 All-in-One PC'");
		verifyEquals(searchPage.getNameTextOfProduct(), "Lenovo IdeaCentre 600 All-in-One PC");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 05: Verify the resutls contains 'Lenovo Thinkpad X1 Carbon Laptop'");
		verifyEquals(searchPage.getNameTextOfProduct(), "Lenovo Thinkpad X1 Carbon Laptop");

	}

	@Test
	public void Search_04_SearchWithProductNameTuyetDoi(Method method) {
		
		ExtentTestManager.startTest(method.getName(), "Search successfully if an absolute product name ");
		ExtentTestManager.getTest().log(Status.INFO, " - Step 01: Enter to Searchbox with value 'ThinkPad X1 Carbon'");
		searchPage.enterToSearchTextbox("ThinkPad X1 Carbon");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 02: Click on 'Search' Button");
		searchPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Verify the resutls has 1 item");
		verifyEquals(searchPage.getNumbersOfProduct(), 1);

		ExtentTestManager.getTest().log(Status.INFO, " - Step 04: Verify the resutls contains 'Lenovo Thinkpad X1 Carbon Laptop'");
		verifyEquals(searchPage.getNameTextOfProduct(), "Lenovo Thinkpad X1 Carbon Laptop");
	}

	@Test
	public void Search_05_AdvancedSearchWithParentCategories(Method method) {

		ExtentTestManager.startTest(method.getName(), "Advanced search successfully if Parent Categories ");
		ExtentTestManager.getTest().log(Status.INFO, " - Step 01: Enter to Searchbox with value 'Apple Macbook Pro'");
		searchPage.enterToSearchTextbox("Apple Macbook Pro");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 02: Check to 'Advanced search' checkbox");
		searchPage.checkToAdvancedSearchCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Select category with value 'Computers'");
		searchPage.selectCategory("Computers");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 04: Uncheck 'Automatically search sub categories' checkbox");
		searchPage.uncheckToAutomaticSearch();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 05: Click on 'Search' Button");
		searchPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 06: Verrify message 'No products were found that matched your criteria.' is displayed");
		verifyEquals(searchPage.getErrorMessageText(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_06_AdvancedSearchWithSubCategories(Method method) {

		ExtentTestManager.startTest(method.getName(), "Advanced search successfully if sub Category ");
		ExtentTestManager.getTest().log(Status.INFO, " - Step 01: Enter to Searchbox with value 'Apple Macbook Pro'");
		searchPage.enterToSearchTextbox("Apple Macbook Pro");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 02: Check to 'Advanced search' checkbox");
		searchPage.checkToAdvancedSearchCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Select category with value 'Computers'");
		searchPage.selectCategory("Computers");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 04: Check 'Automatically search sub categories' checkbox");
		searchPage.checkToAutomaticSearch();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 05: Select Manufacture with value 'Apple'");
		searchPage.selectManufature("Apple");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 05: Click on 'Search' Button");
		searchPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 06: Verify the resutls has 1 item");
		verifyEquals(searchPage.getNumbersOfProduct(), 1);

		ExtentTestManager.getTest().log(Status.INFO, " - Step 07: Verify the resutls contains 'Apple MacBook Pro 13-inch'");
		verifyEquals(searchPage.getNameTextOfProduct(), "Apple MacBook Pro 13-inch");
	}

	@Test
	public void Search_07_AdvancedSearchWithIncorrectManufacture(Method method) {

		ExtentTestManager.startTest(method.getName(), "Advanced search has no results if incorrect manufacture ");
		ExtentTestManager.getTest().log(Status.INFO, " - Step 01: Enter to Searchbox with value 'Apple Macbook Pro'");
		searchPage.enterToSearchTextbox("Apple Macbook Pro");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 02: Check to 'Advanced search' checkbox");
		searchPage.checkToAdvancedSearchCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Select category with value 'Computers'");
		searchPage.selectCategory("Computers");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 04: Check 'Automatically search sub categories' checkbox");
		searchPage.checkToAutomaticSearch();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 05: Select Manufacture with value 'HP'");
		searchPage.selectManufature("HP");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 06: Click on 'Search' Button");
		searchPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 06: Verrify message 'No products were found that matched your criteria.' is displayed");
		verifyEquals(searchPage.getErrorMessageText(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_08_AdvancedSearchWithCorrectManufacture(Method method) {

		ExtentTestManager.startTest(method.getName(), "Advanced search has 1 result if incorrect manufacture ");
		ExtentTestManager.getTest().log(Status.INFO, " - Step 01: Enter to Searchbox with value 'Apple Macbook Pro'");
		searchPage.enterToSearchTextbox("Apple Macbook Pro");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 02: Check to 'Advanced search' checkbox");
		searchPage.checkToAdvancedSearchCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 03: Select category with value 'Computers'");
		searchPage.selectCategory("Computers");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 04: Check 'Automatically search sub categories' checkbox");
		searchPage.checkToAutomaticSearch();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 05: Select Manufacture with value 'Apple'");
		searchPage.selectManufature("Apple");

		ExtentTestManager.getTest().log(Status.INFO, " - Step 06: Click on 'Search' Button");
		searchPage.clickSearchButton();

		ExtentTestManager.getTest().log(Status.INFO, " - Step 07: Verify the resutls contains 'Apple MacBook Pro 13-inch'");
		verifyEquals(searchPage.getErrorMessageText(), "Apple MacBook Pro 13-inch");

	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
