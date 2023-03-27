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
import PageObject.com.nopcommerce.UserHomePageObject;
import commons.BaseTest;
import commons.PageGeneratorManager;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Login extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private String emailAddress, invalidEmailAddress, unexistingEmail;
	DataHelper dataFaker;

	@Parameters({ "browserName", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {

		driver = getBrowserDriver(browserName, appUrl);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		dataFaker = DataHelper.getDataHelper();

		emailAddress = UserData.Register.EMAIL_ADDRESS + generateFakeNumber() + "@gmail.uk";
		invalidEmailAddress = "automation@@gmail.com";
		unexistingEmail = "abc123@gmail.uk";

		
		registerPage = userHomePage.clickToPageLink("Register");
		
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

	}

	@Test
	public void Login_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login unsuccessfully with empty data");
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Click on 'Login' link at menu");
		loginPage = registerPage.clickToPageLink("Log in");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Click on Login Button");
		loginPage.clickOnLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Verify error message 'Please enter your email' is displayed at Email Textbox");
		Assert.assertEquals(loginPage.getErrorMessageAtRequiredField(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login unsuccessfully with Invalid Email");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Enter invalid email '" + invalidEmailAddress + "'  to Email Textbox");
		loginPage.enterToTextboxByID(invalidEmailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Verify error message 'Wrong email' is displayed at Email Textbox");
		Assert.assertEquals(loginPage.getErrorMessageAtRequiredField(), "Wrong email");

	}

	@Test
	public void Login_03_Unexisting_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login unsuccessfully with unexisting Email");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Enter unexisting email '" + unexistingEmail + "'  to Email Textbox");
		loginPage.enterToTextboxByID(unexistingEmail, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter password '" + UserData.Register.PASSWORD + "'  to Password Textbox");
		loginPage.enterToTextboxByID(UserData.Register.PASSWORD, "Password");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Click on Login Button");
		loginPage.clickOnLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Verify error message contains 'No customer account found' is displayed");
		Assert.assertEquals(loginPage.getErrorMessageValidation(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Correct_Email_And_Leave_Blank_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login unsuccessfully when leave blank password ");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Enter correct email '" + emailAddress + "'  to Email Textbox");
		loginPage.enterToTextboxByID(emailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Click on Login Button");
		loginPage.clickOnLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Verify error message contains 'The credentials provided are incorrect' is displayed");
		Assert.assertEquals(loginPage.getErrorMessageValidation(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Correct_Email_And_Incorrect_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login unsuccessfully with incorrect password");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Enter correct email '" + emailAddress + "'  to Email Textbox");
		loginPage.enterToTextboxByID(emailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter incorrect password '654321'  to Password Textbox");
		loginPage.enterToTextboxByID("654321", "Password");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Click on Login Button");
		loginPage.clickOnLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Verify error message contains 'The credentials provided are incorrect' is displayed");
		Assert.assertEquals(loginPage.getErrorMessageValidation(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Correct_Email_And_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Login unsuccessfully with correct email and password");
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Enter correct email '" + emailAddress + "'  to Email Textbox");
		loginPage.enterToTextboxByID(emailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Enter correct password '" + UserData.Register.PASSWORD + "'  to Password Textbox");
		loginPage.enterToTextboxByID(UserData.Register.PASSWORD, "Password");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Click on Login Button");
		myAccountPage = loginPage.clickOnLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Verify direct to Homepage successfully");
		Assert.assertTrue(myAccountPage.isMyAccountPageDisplayed("My account"));

	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
