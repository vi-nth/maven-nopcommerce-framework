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

import PageObject.com.nopcommerce.AddressPageObject;
import PageObject.com.nopcommerce.LoginPageObject;
import PageObject.com.nopcommerce.MyAccountPageObject;
import PageObject.com.nopcommerce.RegisterPageObject;
import PageObject.com.nopcommerce.UserAccountPageObject;
import PageObject.com.nopcommerce.UserHomePageObject;
import PageObject.com.nopcommerce.UserPasswordPageObject;
import commons.BaseTest;
import commons.PageGeneratorManager;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class MyAccount extends BaseTest {
	WebDriver driver;
	private UserHomePageObject userHomePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private UserAccountPageObject userAccountPage;
	private AddressPageObject addressPage;
	private UserPasswordPageObject userPasswordPage;
	private String emailAddress;
	DataHelper dataFaker;

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
	public void MyAccount_01_Update_Customer_Infor(Method method) {
		ExtentTestManager.startTest(method.getName(), "Update customer infor successfully");
		ExtentTestManager.getTest().log(Status.INFO, "HomePage - Step 01: Click on 'My account' link at menu");
		userAccountPage = myAccountPage.clickToPageLink("ico-account");

		ExtentTestManager.getTest().log(Status.INFO,
				"MyAccount - Step 02: Verify 'My account - customer infor' is displayed");
		userAccountPage.isMyAccountPageDisplayed("My account - Customer info");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 03: Select gender is Female");
		userAccountPage.selectGenderAtRadioButton("Female");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 04: Edit firstName with value '"
				+ UserData.CusotmerInfor.FIRST_NAME + "' to 'Firstname' Textbox");
		userAccountPage.enterValueToTextoxByID(UserData.CusotmerInfor.FIRST_NAME, "FirstName");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 05: Edit lastName with value '"
				+ UserData.CusotmerInfor.LAST_NAME + "'to 'Lastname' Textbox  ");
		userAccountPage.enterValueToTextoxByID(UserData.CusotmerInfor.LAST_NAME, "LastName");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 06: Edit Date of birth with value '1/1/1999'");
		userAccountPage.selectDateInDropDown(UserData.CusotmerInfor.DATE, "DateOfBirthDay");
		userAccountPage.selectDateInDropDown(UserData.CusotmerInfor.MONTH, "DateOfBirthMonth");
		userAccountPage.selectDateInDropDown(UserData.CusotmerInfor.YEAR, "DateOfBirthYear");

		ExtentTestManager.getTest().log(Status.INFO,
				"MyAccount - Step 07: Edit Email with value '" + UserData.CusotmerInfor.EMAIL_ADDRESS + "'");
		userAccountPage.enterValueToTextoxByID(UserData.CusotmerInfor.EMAIL_ADDRESS, "Email");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 08: Edit Company with value ");
		userAccountPage.enterValueToTextoxByID(UserData.CusotmerInfor.COMPANY, "Company");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 09: Click on 'Save' Button ");
		userAccountPage.clickOnSaveButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"MyAccount - Step 10: Successfull message 'The customer info has been updated successfully.' is di ");
		Assert.assertEquals(userAccountPage.getNotificationMessage(),
				"The customer info has been updated successfully.");

	}

	@Test
	public void MyAccount_02_Add_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "Add address successfully");
		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 01: Click on 'My account' link at menu");
		addressPage = userAccountPage.clickOnDynamicPage("Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 02: Veify Address Body is blank");
		Assert.assertEquals(addressPage.getAddressBodyContent(), "No addresses");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 03: Click on 'Add new' Button");
		addressPage.clickOnAddNewButton();

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 05: Enter firstname with value '"
				+ UserData.Addresses.FIRST_NAME + "' to 'Firstname' Texbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.FIRST_NAME, "Address_FirstName");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 06: Enter lastname with value '"
				+ UserData.Addresses.LAST_NAME + "' to 'Lastname' Texbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.LAST_NAME, "Address_LastName");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 07: Enter email with value '"
				+ UserData.Addresses.EMAIL_ADDRESS + "'to 'Email' Textbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.EMAIL_ADDRESS, "Address_Email");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 08: Enter company with value '"
				+ UserData.Addresses.COUNTRY + "' to 'Company' Textbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.COUNTRY, "Address_Company");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 09: Select country is United States ");
		addressPage.selectValueByDynamicByID("United States", "Address_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 10: Select provice is Maryland");
		addressPage.selectValueByDynamicByID("Maryland", "Address_StateProvinceId");

		ExtentTestManager.getTest().log(Status.INFO,
				"MyAccount - Step 11: Enter city  with value '" + UserData.Addresses.COUNTRY + "'to 'City' Textbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.COUNTRY, "Address_City");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 12: Enter address1  with value '"
				+ UserData.Addresses.ADDRESS1 + "' to 'Address 1' Textbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.ADDRESS1, "Address_Address1");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 13: Enter address2  with value '"
				+ UserData.Addresses.ADDRESS2 + "' to 'Address 2' Textbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.ADDRESS2, "Address_Address2");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 14: Enter zip/postal code  with value '"
				+ UserData.Addresses.ZIP_POSTALCODE + "' to 'Zip/Postal code' Textbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.ZIP_POSTALCODE, "Address_ZipPostalCode");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 15: Enter phone number  with value '"
				+ UserData.Addresses.PHONE_NUMBER + "' to 'Phone number' Textbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.PHONE_NUMBER, "Address_PhoneNumber");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 16: Enter fax number  with value '"
				+ UserData.Addresses.FAX_NUMBER + "'to 'Fax number' Textbox");
		addressPage.enterValueByDynamicTextbox(UserData.Addresses.FAX_NUMBER, "Address_FaxNumber");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 17: Click on 'Save' Button");
		addressPage.clickOnSaveButton();

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 18: Successfull added message is displayed");
		Assert.assertEquals(addressPage.getNotificationMessage(), "The new address has been added successfully.");

	}

	@Test
	public void MyAccount_03_Change_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "Change Password successfully");
		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 01: Click on 'My account' link at menu");
		userPasswordPage = addressPage.clickOnDynamicPage("Change password");

		// Nhap newpass trung oldpass
		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 02: Enter old password with value '"
				+ UserData.Register.PASSWORD + "' to 'Old password' Textbox");
		userPasswordPage.enterToDynamicPasswordByID(UserData.Register.PASSWORD, "OldPassword");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 03: Enter new password with value '"
				+ UserData.Register.PASSWORD + "' to 'New password' Textbox");
		userPasswordPage.enterToDynamicPasswordByID(UserData.Register.PASSWORD, "NewPassword");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 04: Enter confirm password with value '"
				+ UserData.Register.PASSWORD + "' to 'Confirm password' Textbox");
		userPasswordPage.enterToDynamicPasswordByID(UserData.Register.PASSWORD, "ConfirmNewPassword");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 05: Click on 'Change password' Button");
		userPasswordPage.clickOnChangePasswordButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"MyAccount - Step 06: Verify error message contains 'the same as one of the last passwords you used'");
		Assert.assertEquals(userPasswordPage.getErrorMessageTextAtPassword(),
				"You entered the password that is the same as one of the last passwords you used. Please create a new password.");

		// Nhap newpass # oldpass
		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 07: Enter old password with value '"
				+ UserData.Register.PASSWORD + "' to 'Old password' Textbox");
		userPasswordPage.enterToDynamicPasswordByID(UserData.Register.PASSWORD, "OldPassword");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 08: Enter new password with value '"
				+ UserData.ChangePass.NEW_PASSWORD + "' to 'New password' Textbox");
		userPasswordPage.enterToDynamicPasswordByID(UserData.ChangePass.NEW_PASSWORD, "NewPassword");
		System.out.println("Newpass:" + UserData.ChangePass.NEW_PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 09: Enter confirm password with value '"
				+ UserData.ChangePass.NEW_PASSWORD + "' to 'Confirm password' Textbox");
		userPasswordPage.enterToDynamicPasswordByID(UserData.ChangePass.NEW_PASSWORD, "ConfirmNewPassword");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 10: Click on 'Change password' Button");
		userPasswordPage.clickOnChangePasswordButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"MyAccount - Step 11: Verify successfull updated message is displayed");
		Assert.assertEquals(userPasswordPage.getNotificationMessage(), "Password was changed");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 12: Close notification message");
		userPasswordPage.clickCloseMessage();

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 13: Log out account");
		userHomePage = userPasswordPage.clickToPageLink("ico-logout");

		ExtentTestManager.getTest().log(Status.INFO, "MyAccount - Step 14: Log in again");
		userHomePage.clickToPageLink("ico-login");

		// Nhap oldpass
		ExtentTestManager.getTest().log(Status.INFO,
				"Login - Step 14: Enter correct email '" + emailAddress + "'  to Email Textbox");
		loginPage.enterToTextboxByID(emailAddress, "Email");

		ExtentTestManager.getTest().log(Status.INFO,
				"Login - Step 15: Enter old password '" + UserData.Register.PASSWORD + "'  to Password Textbox");
		loginPage.enterToTextboxByID(UserData.Register.PASSWORD, "Password");

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 16: Click on Login Button");
		loginPage.clickOnLoginButton();

		ExtentTestManager.getTest().log(Status.INFO,
				"Login - Step 17: Verify error message contains'The credentials provided are incorrect' ");
		Assert.assertEquals(loginPage.getErrorMessageValidation(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

		// Nhap newPass
		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 18: Enter correct email '"
				+ UserData.CusotmerInfor.EMAIL_ADDRESS + "'  to Email Textbox");
		loginPage.enterToTextboxByID(UserData.CusotmerInfor.EMAIL_ADDRESS, "Email");

		ExtentTestManager.getTest().log(Status.INFO,
				"Login - Step 19: Enter new password '" + UserData.Register.PASSWORD + "'  to Password Textbox");
		loginPage.enterToTextboxByID(UserData.ChangePass.NEW_PASSWORD, "Password");
		System.out.println("Newpass:" + UserData.ChangePass.NEW_PASSWORD);

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 20: Click on Login Button");
		myAccountPage = loginPage.clickOnLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Login - Step 21: Verify direct to Homepage successfully");
		Assert.assertTrue(myAccountPage.isMyAccountPageDisplayed("ico-account"));

	}

	@Test
	public void MyAccount_04_My_Product_Reviews(Method method) {
		ExtentTestManager.getTest().log(Status.INFO, "ProductReview - Step 01: Click on 'Computer' on menu");
		myAccountPage.clickOnDynamicMenuByName("Computers");

		ExtentTestManager.getTest().log(Status.INFO, "ProductReview - Step 02: Click on 'Desktop' category");
		myAccountPage.clickOnDynamicCategory(" Desktops ");

		ExtentTestManager.getTest().log(Status.INFO,
				"ProductReview - Step 03: Click on 'Digital Storm VANQUISH 3 Custom Performance PC'");
		myAccountPage.clickOnProductName("Digital Storm VANQUISH 3 Custom Performance PC");

		ExtentTestManager.getTest().log(Status.INFO, "ProductReview - Step 04: Click on 'Add your review' link");
		myAccountPage.clickAddYourReview();

		ExtentTestManager.getTest().log(Status.INFO,
				"ProductReview - Step 05: Enter review title to 'Review title' textbox ");
		myAccountPage.enterReviewByClass("Price", "review-title");

		ExtentTestManager.getTest().log(Status.INFO,
				"ProductReview - Step 06: Enter review text to'Review text' Textarea ");
		myAccountPage.enterReviewText("Good price");

		ExtentTestManager.getTest().log(Status.INFO, "ProductReview - Step 07: Select rating point '4' ");
		myAccountPage.selectRatingByID("addproductrating_2");

		ExtentTestManager.getTest().log(Status.INFO, "ProductReview - Step 08: Click on 'Submit review' Button");
		myAccountPage.clickOnSubmitReview();

		ExtentTestManager.getTest().log(Status.INFO,
				"ProductReview - Step 09: Successfull message 'Product review is successfully added.' is displayed ");
		Assert.assertEquals(myAccountPage.getSuccessfullReviewedMessage(), "Product review is successfully added.");

		ExtentTestManager.getTest().log(Status.INFO, "ProductReview - Step 10: Verify review title is displayed");
		Assert.assertTrue(myAccountPage.isReviewTitleDisplayed("Price"));

	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}