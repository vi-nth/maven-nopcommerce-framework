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

import PageObject.com.nopcommerce.RegisterPageObject;
import PageObject.com.nopcommerce.UserHomePageObject;
import commons.BaseTest;
import commons.PageGeneratorManager;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Register extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private RegisterPageObject registerPage;
	private String emailAddress, invalidEmailAddress;
	private String browserName;

	DataHelper dataFaker;

	@Parameters({ "browserName", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {

		this.browserName = browserName;

		driver = getBrowserDriver(browserName, appUrl);

		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		dataFaker = DataHelper.getDataHelper();

		emailAddress = UserData.Register.EMAIL_ADDRESS + generateFakeNumber() + "@gmail.uk";
		invalidEmailAddress = "automation@@gmail.com";

	}

	@Test
	public void Register_01_Emty_Data(Method method) {

		ExtentTestManager.startTest(method.getName() + " - " + this.browserName.toUpperCase(), "Register unsuccessfully with empty data");
		ExtentTestManager.getTest().log(Status.INFO, "HomePage - Step 01: Click on 'Register'link at menu");
		registerPage = userHomePage.clickToPageLink("ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 2:  Click on 'Register' Button");
		registerPage.clickOnRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 3:  Verify error message is displayed at required fields");

		Assert.assertEquals(registerPage.getErrorMessageAtFieldByID("FirstName-error"), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtFieldByID("LastName-error"), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtFieldByID("Email-error"), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtFieldByID("Password-error"), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtFieldByID("ConfirmPassword-error"), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName()+ " - " + this.browserName.toUpperCase(), "Register unsuccessfully with invalid Email");
		ExtentTestManager.getTest().log(Status.INFO, "HomePage - Step 01: Click on 'Register'link at menu");
		registerPage = userHomePage.clickToPageLink("ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Select gender at 'Gender' Checkbox with value is 'Female'");
		registerPage.selectGenderAtRadioButton("Male");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to 'Firstname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.FIRST_NAME, "FirstName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to 'Lastname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.LAST_NAME, "LastName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Select Date of birth at Dropdown with value is 10/8/1998");
		registerPage.selectDateInDropDown(UserData.Register.DATE, "DateOfBirthDay");
		registerPage.selectDateInDropDown(UserData.Register.MONTH, "DateOfBirthMonth");
		registerPage.selectDateInDropDown(UserData.Register.YEAR, "DateOfBirthYear");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06:  Enter to 'Email Textbox' with invalid email is '" + invalidEmailAddress + "'  ");
		registerPage.enterValueToTextoxByID(invalidEmailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07:  Enter to 'Company Textbox' with value is '" + UserData.Register.COMPANY + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.COMPANY, "Company");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08:  Check to 'Newsletter' Checkbox");
		registerPage.checkNewsletterCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09:  Enter to 'Password Textbox' with value is '" + UserData.Register.PASSWORD + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "Password");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10:  Enter to 'Confirm Password Textbox' with value is '" + UserData.Register.PASSWORD + "'   ");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "ConfirmPassword");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11:  Click on 'Register' Button");
		registerPage.clickOnRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12:  Verify 'Wrong Email' is displayed at Email Textbox");
		Assert.assertEquals(registerPage.getErrorMessageAtFieldByID("Email-error"), "Wrong email");

	}

	@Test
	public void Register_03_Valid_Email_And_Password(Method method) {
		ExtentTestManager.startTest(method.getName()+ " - " + this.browserName.toUpperCase(), "Register successfully");
		ExtentTestManager.getTest().log(Status.INFO, "HomePage - Step 01: Click on 'Register'link at menu");
		registerPage = userHomePage.clickToPageLink("ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Select gender at 'Gender' Checkbox with value is 'Female'");
		registerPage.selectGenderAtRadioButton("Male");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to 'Firstname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.FIRST_NAME, "FirstName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to 'Lastname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.LAST_NAME, "LastName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Select Date of birth at Dropdown with value is 10/8/1998");
		registerPage.selectDateInDropDown(UserData.Register.DATE, "DateOfBirthDay");
		registerPage.selectDateInDropDown(UserData.Register.MONTH, "DateOfBirthMonth");
		registerPage.selectDateInDropDown(UserData.Register.YEAR, "DateOfBirthYear");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06:  Enter to 'Email Textbox' with invalid email is '" + emailAddress + "'  ");
		registerPage.enterValueToTextoxByID(emailAddress, "Email");
		System.out.println("Emailinput:" + emailAddress);

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07:  Enter to 'Company Textbox' with value is '" + UserData.Register.COMPANY + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.COMPANY, "Company");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08:  Check to 'Newsletter' Checkbox");
		registerPage.checkNewsletterCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09:  Enter to 'Password Textbox' with value is '" + UserData.Register.PASSWORD + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "Password");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10:  Enter to 'Confirm Password Textbox' with value is '" + UserData.Register.PASSWORD + "'   ");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "ConfirmPassword");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11:  Click on 'Register' Button");
		registerPage.clickOnRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12:  Verify 'Your registration completed' is displayed");
		Assert.assertEquals(registerPage.getSuccessfullRegisterMessage(), "Your registration completed");

	}

	@Test
	public void Register_04_Existing_Email(Method method) {
		ExtentTestManager.startTest(method.getName()+ " - " + this.browserName.toUpperCase(), "Register unsuccessfully with exsiting Email");
		ExtentTestManager.getTest().log(Status.INFO, "HomePage - Step 01: Click on 'Register'link at menu");
		registerPage = userHomePage.clickToPageLink("ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Select gender at 'Gender' Checkbox with value is 'Female'");
		registerPage.selectGenderAtRadioButton("Male");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to 'Firstname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.FIRST_NAME, "FirstName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to 'Lastname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.LAST_NAME, "LastName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Select Date of birth at Dropdown with value is 10/8/1998");
		registerPage.selectDateInDropDown(UserData.Register.DATE, "DateOfBirthDay");
		registerPage.selectDateInDropDown(UserData.Register.MONTH, "DateOfBirthMonth");
		registerPage.selectDateInDropDown(UserData.Register.YEAR, "DateOfBirthYear");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06:  Enter to 'Email Textbox' with existing email is '" + emailAddress + "'  ");
		registerPage.enterValueToTextoxByID(emailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07:  Enter to 'Company Textbox' with value is '" + UserData.Register.COMPANY + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.COMPANY, "Company");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08:  Check to 'Newsletter' Checkbox");
		registerPage.checkNewsletterCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09:  Enter to 'Password Textbox' with value is '" + UserData.Register.PASSWORD + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "Password");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10:  Enter to 'Confirm Password Textbox' with value is '" + UserData.Register.PASSWORD + "'   ");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "ConfirmPassword");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11:  Click on 'Register' Button");
		registerPage.clickOnRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12:  Verify error message 'The specified email already exists' is displayed");
		Assert.assertEquals(registerPage.getValidationEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Valid_Email_And_Password_Less_Than_6_Chars(Method method) {
		ExtentTestManager.startTest(method.getName()+ " - " + this.browserName.toUpperCase(), "Register unsuccessfully with password less than 6 chars");
		ExtentTestManager.getTest().log(Status.INFO, "HomePage - Step 01: Click on 'Register'link at menu");
		registerPage = userHomePage.clickToPageLink("ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Select gender at 'Gender' Checkbox with value is 'Female'");
		registerPage.selectGenderAtRadioButton("Male");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to 'Firstname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.FIRST_NAME, "FirstName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to 'Lastname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.LAST_NAME, "LastName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Select Date of birth at Dropdown with value is 10/8/1998");
		registerPage.selectDateInDropDown(UserData.Register.DATE, "DateOfBirthDay");
		registerPage.selectDateInDropDown(UserData.Register.MONTH, "DateOfBirthMonth");
		registerPage.selectDateInDropDown(UserData.Register.YEAR, "DateOfBirthYear");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06:  Enter to 'Email Textbox' with existent email is '" + emailAddress + "'  ");
		registerPage.enterValueToTextoxByID(emailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07:  Enter to 'Company Textbox' with value is '" + UserData.Register.COMPANY + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.COMPANY, "Company");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08:  Check to 'Newsletter' Checkbox");
		registerPage.checkNewsletterCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09:  Enter to 'Password Textbox' less  than 6 chars with value '123' ");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "123");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10:  Enter to 'Confirm Password Textbox'less  than 6 chars withvalue '123'");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "123");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11:  Click on 'Register' Button");
		registerPage.clickOnRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12:  Verify error message 'The specified email already exists' is displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFieldByID("Password-error"), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Register_With_Incorrect_Confirm_Password(Method method) {
		ExtentTestManager.startTest(method.getName()+ " - " + this.browserName.toUpperCase(), "Register unsuccessfully with invalid Confirm Password");
		ExtentTestManager.getTest().log(Status.INFO, "HomePage - Step 01: Click on 'Register'link at menu");
		registerPage = userHomePage.clickToPageLink("ico-register");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 02: Select gender at 'Gender' Checkbox with value is 'Female'");
		registerPage.selectGenderAtRadioButton("Male");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 03: Enter to 'Firstname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.FIRST_NAME, "FirstName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 04: Enter to 'Lastname Textbox' with value is '" + UserData.Register.FIRST_NAME + "' ");
		registerPage.enterValueToTextoxByID(UserData.Register.LAST_NAME, "LastName");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 05: Select Date of birth at Dropdown with value is 10/8/1998");
		registerPage.selectDateInDropDown(UserData.Register.DATE, "DateOfBirthDay");
		registerPage.selectDateInDropDown(UserData.Register.MONTH, "DateOfBirthMonth");
		registerPage.selectDateInDropDown(UserData.Register.YEAR, "DateOfBirthYear");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 06:  Enter to 'Email Textbox' with existent email is '" + emailAddress + "'  ");
		registerPage.enterValueToTextoxByID(emailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 07:  Enter to 'Company Textbox' with value is '" + UserData.Register.COMPANY + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.COMPANY, "Company");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 08:  Check to 'Newsletter' Checkbox");
		registerPage.checkNewsletterCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 09:  Enter to 'Password Textbox' with value is '" + UserData.Register.PASSWORD + "'  ");
		registerPage.enterValueToTextoxByID(UserData.Register.PASSWORD, "Password");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 10:  Enter to 'Confirm Password Textbox' with incorrect password  ");
		registerPage.enterValueToTextoxByID("123", "ConfirmPassword");

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 11:  Click on 'Register' Button");
		registerPage.clickOnRegisterButton();

		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 12:  Verify error message 'The specified email already exists' is displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFieldByID("ConfirmPassword-error"), "The password and confirmation password do not match.");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
