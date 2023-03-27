package com.nopcommerce.user;


import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import PageObject.com.nopcommerce.UserHomePageObject;
import commons.BaseTest;
import commons.PageGeneratorManager;
import reportConfig.ExtentTestManager;

public class Sort_Display_Paging extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	

	@Parameters({ "browserName", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {

		driver = getBrowserDriver(browserName, appUrl);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}


	@Test
	public void Sort_01_AtoZ(Method method) {
		
		ExtentTestManager.startTest(method.getName(), "Sort A to Z");
		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 01: Hover to Computers and click on 'Notesbook'");
		userHomePage.hoverAndClickMenu("Computers", "Notebooks");
		
		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 02: Select sort 'Name: A to Z'");
		userHomePage.selectItemInSortDropdown("Name: A to Z");

		sleepInSecond(2);
		
		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 03: Verify sort A to Z successfully");
		verifyTrue(userHomePage.isSortProductNameByAscending());

	}

	@Test(dependsOnMethods = "Sort_01_AtoZ")
	public void Sort_02_ZtoA(Method method) {

		ExtentTestManager.startTest(method.getName(), "Sort Z to A");
		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 01: Select sort 'Name: Z to A'");
		userHomePage.selectItemInSortDropdown("Name: Z to A");
		
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 02: Verify sort Z to A successfully");
		verifyTrue(userHomePage.isSortProductNameByDecending());

	}

	@Test(dependsOnMethods = "Sort_02_ZtoA")
	public void Sort_03_LowToHigh(Method method) {

		ExtentTestManager.startTest(method.getName(), "Sort Low to High");
		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 01: Select sort 'Price: Low to High'");
		userHomePage.selectItemInSortDropdown("Price: Low to High");
		
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 02: Verify sort Low to High successfully");
		verifyTrue(userHomePage.isSortPriceByAscending());

	}

	@Test(dependsOnMethods = "Sort_03_LowToHigh")
	public void Sort_04_HighToLow(Method method) {

		ExtentTestManager.startTest(method.getName(), "Sort High to Low");
		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 01: Select sort 'Price: High to Low'");
		userHomePage.selectItemInSortDropdown("Price: High to Low");
		
		sleepInSecond(2);

		ExtentTestManager.getTest().log(Status.INFO, " Sort - Step 02: Verify sort High to Low successfully");
		verifyTrue(userHomePage.isSortPriceByDecending());

	}

	@Test(dependsOnMethods = "Sort_04_HighToLow")
	public void Display_05_3ItemsPerPage(Method method) {

		ExtentTestManager.startTest(method.getName(), "Select show 3 tiems per page");
		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 01: Select '3' per page");
		userHomePage.selectNumbersOfProductPerPage("3");

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 02: Verify the number of products less than or equal to 3 items");
		verifyTrue(userHomePage.isNumberOfProductsLessThanOrEqualTo(3));

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 03: Verify paging is displayed");
		verifyTrue(userHomePage.isPagingDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 04: Verify 'Next Button' is displayed");
		verifyTrue(userHomePage.isButtonPageDisplayed("next-page"));

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 05: Click to page '2'");
		userHomePage.clickToDynamicPage("2");

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 06: Verify 'Previous Button' is displayed");
		verifyTrue(userHomePage.isButtonPageDisplayed("previous-page"));

	}

	@Test(dependsOnMethods = "Display_05_3ItemsPerPage")
	public void Display_06_6ItemsPerPage(Method method) {

		ExtentTestManager.startTest(method.getName(), "Select show 6 tiems per page");
		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 01: Select '6' per page");
		userHomePage.selectNumbersOfProductPerPage("6");

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 02: Verify the number of products less than or equal to 6 items");
		verifyTrue(userHomePage.isNumberOfProductsLessThanOrEqualTo(6));

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 03: Verify paging is displayed");
		verifyFalse(userHomePage.isPagingDisplayed());
	}

	@Test(dependsOnMethods = "Display_06_6ItemsPerPage")
	public void Display_07_9ItemsPerPage(Method method) {

		ExtentTestManager.startTest(method.getName(), "Select show 9 tiems per page");
		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 01: Select '9' per page");
		userHomePage.selectNumbersOfProductPerPage("9");

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 02: Verify the number of products less than or equal to 9 items");
		verifyTrue(userHomePage.isNumberOfProductsLessThanOrEqualTo(9));

		ExtentTestManager.getTest().log(Status.INFO, " Display - Step 03: Verify paging is displayed");
		verifyFalse(userHomePage.isPagingDisplayed());

	}

}
