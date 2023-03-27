package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.UserData;

import PageObject.com.nopcommerce.LoginPageObject;
import PageObject.com.nopcommerce.MyAccountPageObject;
import PageObject.com.nopcommerce.MyOrdersPageObject;
import PageObject.com.nopcommerce.RegisterPageObject;
import PageObject.com.nopcommerce.ShopingCartPageObject;
import PageObject.com.nopcommerce.UserHomePageObject;
import commons.BaseTest;
import commons.PageGeneratorManager;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;

public class Order extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
	private ShopingCartPageObject myShopingCartPage;
	private MyOrdersPageObject myOrdersPage;

	private DataHelper dataFaker;
	private String emailAddress, billlingFristName, billingLastName, billingEmail, billingCompany, billingCity, billingAddress, billingPostalCode, billingPhone;
	private String newBilllingFristName, newBilllingLastName, newBilllingEmail, newBilllingCompany, newBilllingCity, newBilllingAddress, newBilllingPostalCode, newBilllingPhone;
	private String shipingFirstName, shipingLastName, shipingEmail, shipingCompany, shipingCity, shipingAddress, shipingPostalCode, shipingPhone;
	private String newShipingFirstName, newShipingLastName, newShipingEmail, newShipingCompany, newShipingCity, newShipingAddress, newShipingPostalCode, newShipingPhone;

	@Parameters({ "browserName", "url" })
	@BeforeClass
	public void beforeClass(String browseName, String appUrl) {

		driver = getBrowserDriver(browseName, appUrl);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		dataFaker = DataHelper.getDataHelper();

		emailAddress = UserData.Register.EMAIL_ADDRESS + generateFakeNumber() + "@gmail.uk";

		billlingFristName = dataFaker.getFirstName();
		billingLastName = dataFaker.getLastName();
		billingEmail = dataFaker.getEmailAddrress();
		billingCompany = dataFaker.getCompanyName();
		billingCity = dataFaker.getCity();
		billingAddress = dataFaker.getAddress();
		billingPostalCode = dataFaker.getPostalCode();
		billingPhone = dataFaker.getPhoneNumber();

		newBilllingFristName = dataFaker.getFirstName();
		newBilllingLastName = dataFaker.getLastName();
		newBilllingEmail = dataFaker.getEmailAddrress();
		newBilllingCompany = dataFaker.getCompanyName();
		newBilllingCity = dataFaker.getCity();
		newBilllingAddress = dataFaker.getAddress();
		newBilllingPostalCode = dataFaker.getPostalCode();
		newBilllingPhone = dataFaker.getPhoneNumber();

		shipingFirstName = dataFaker.getFirstName();
		shipingLastName = dataFaker.getLastName();
		shipingEmail = dataFaker.getEmailAddrress();
		shipingCompany = dataFaker.getCompanyName();
		shipingCity = dataFaker.getCity();
		shipingAddress = dataFaker.getAddress();
		shipingPostalCode = dataFaker.getPostalCode();
		shipingPhone = dataFaker.getPhoneNumber();

		newShipingFirstName = dataFaker.getFirstName();
		newShipingLastName = dataFaker.getLastName();
		newShipingEmail = dataFaker.getEmailAddrress();
		newShipingCompany = dataFaker.getCompanyName();
		newShipingCity = dataFaker.getCity();
		newShipingAddress = dataFaker.getAddress();
		newShipingPostalCode = dataFaker.getPostalCode();
		newShipingPhone = dataFaker.getPhoneNumber();

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
	public void Order_01_AddProductToCart(Method method) {

		ExtentTestManager.startTest(method.getName(), "Add product to cart successfully");
		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 01: Hover to Computers and click on Desktops");
		myAccountPage.clickAndHoverMenu("Computers", "Desktops");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 02: Click on product 'Build your own computer'");
		myAccountPage.clickOnProduct("Build your own computer");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 03: Select value '2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]' from 'Processor' Dropdown");
		myAccountPage.selectItemByDynamicID("2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]", "product_attribute_1");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 04: Select value '8GB [+$60.00]' form 'RAM' Dropdown");
		myAccountPage.selectItemByDynamicID("8GB [+$60.00]", "product_attribute_2");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 05: Choose value '400 GB [+$100.00]' form 'HDD' Radio Button");
		myAccountPage.checkItemByDynamicText("400 GB [+$100.00]");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 06: Select 'Vista Home [+$50.00]' from 'OS' Radio Button ");
		myAccountPage.checkItemByDynamicText("Vista Home [+$50.00]");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 07: Check 'Microsoft Office [+$50.00]' from 'Software' Checkbox list ");
		myAccountPage.checkItemByDynamicText("Microsoft Office [+$50.00]");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 08: Check 'Acrobat Reader [+$10.00]' from 'Software' Checkbox list ");
		myAccountPage.checkItemByDynamicText("Acrobat Reader [+$10.00]");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 09: Check 'Total Commander [+$5.00]' from 'Software' Checkbox list");
		myAccountPage.checkItemByDynamicText("Total Commander [+$5.00]");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 10: Click 'Add To Cart' Button");
		myAccountPage.clickAddToCartButton();

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 11: Verify notification message 'The product has been added to your shoping cart' is displayed");
		verifyEquals(myAccountPage.getMessageText(), "The product has been added to your shoping cart");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 12: Click close notification icon");
		myAccountPage.clickCloseMessageNotification();

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 13: Verify 'Shopping cart (1)' is displayed");
		verifyEquals(myAccountPage.getNumberProductOnHeader(), "Shopping cart (1)");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 14: Verify 'There are 1 item(s) in your cart.' is displayed");
		verifyEquals(myAccountPage.getNumberProductInYourCart(), "There are 1 item(s) in your cart.");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 15: Verify product name 'Build your own computer' is displayed ");
		verifyEquals(myAccountPage.getProductName(), "Build your own computer");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 16: Verify the details of the added product are displayed");
		verifyEquals(myAccountPage.getProductAttribute("attributes"),
				"Processor: 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]\nRAM: 8GB [+$60.00]\nHDD: 400 GB [+$100.00]\nOS: Vista Home [+$50.00]\nSoftware: Microsoft Office [+$50.00]\nSoftware: Acrobat Reader [+$10.00]\nSoftware: Total Commander [+$5.00]");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 17: Verify 'Unit price: $1,490.00' is displayed");
		verifyEquals(myAccountPage.getProductAttribute("price"), "Unit price: $1,490.00");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 18: Verify 'Quantity: 1' is displayed");
		verifyEquals(myAccountPage.getProductAttribute("quantity"), "Quantity: 1");

		ExtentTestManager.getTest().log(Status.INFO, " AddProductToCart - Step 19: Verify 'Sub-Total: $1,490.00' is displayed");
		verifyEquals(myAccountPage.getProductAttribute("totals"), "Sub-Total: $1,490.00");

	}

	@Test
	public void Order_02_EditProductInShopingCart(Method method) {

		ExtentTestManager.startTest(method.getName(), "Edit product in shoping cart successfully");
		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 01: Hover to Computers and click on Desktops");
		myAccountPage.clickAndHoverMenu("Computers", "Desktops");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 02: Click on 'Shoping Cart' at Menu bar");
		myAccountPage.clickOnDynamicHeadMenu("ico-cart");
		myShopingCartPage = PageGeneratorManager.getShopingCart(driver);

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 03: Click 'Edit' Button ");
		myAccountPage = myShopingCartPage.clickEditButton();

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 04: Select value '2.2 GHz Intel Pentium Dual-Core E2200' from 'Processor' Dropdown\"");
		myAccountPage.selectItemByDynamicID("2.2 GHz Intel Pentium Dual-Core E2200", "product_attribute_1");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 05: Select value '4GB [+$20.00]' form 'RAM' Dropdown");
		myAccountPage.selectItemByDynamicID("4GB [+$20.00]", "product_attribute_2");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 06: Choose value '400 GB [+$100.00]' form 'HDD' Radio Button");
		myAccountPage.checkItemByDynamicText("400 GB [+$100.00]");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 07: Select 'Vista Home [+$50.00]' from 'OS' Radio Button");
		myAccountPage.checkItemByDynamicText("Vista Home [+$50.00]");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 08: Check 'Microsoft Office [+$50.00]' from 'Software' Checkbox list");
		myAccountPage.checkItemByDynamicText("Microsoft Office [+$50.00]");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 09: Check 'Acrobat Reader [+$10.00]' from 'Software' Checkbox list ");
		myAccountPage.unCheckItemByDynamicText("Acrobat Reader [+$10.00]");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 10: Check 'Total Commander [+$5.00]' from 'Software' Checkbox list");
		myAccountPage.unCheckItemByDynamicText("Total Commander [+$5.00]");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 11: Verify the price after editing is '$1,420.00'");
		verifyEquals(myAccountPage.getMoneyText(), "$1,420.00");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 12: Enter number of products with value '2'");
		myAccountPage.enterNumberOfProducts("2");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 13: Click 'Update' Button");
		myAccountPage.clickToUpdateButton();

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 14: Verify notification message 'The product has been added to your shoping cart' is displayed");
		verifyEquals(myAccountPage.getMessageText(), "The product has been added to your shoping cart");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 15: Click on 'Shoping Cart' at Menu bar");
		myAccountPage.clickOnDynamicHeadMenu("ico-cart");
		myShopingCartPage = PageGeneratorManager.getShopingCart(driver);

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 16: Verify product name 'Build your own computer' is displayed in table");
		verifyEquals(myShopingCartPage.getProductNameInTable(), "Build your own computer");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 17: Verify the details of the added product are displayed in table");
		verifyEquals(myShopingCartPage.getProductAttributeInTable(),
				"Processor: 2.2 GHz Intel Pentium Dual-Core E2200\nRAM: 4GB [+$20.00]\nHDD: 400 GB [+$100.00]\nOS: Vista Home [+$50.00]\nSoftware: Microsoft Office [+$50.00]");

		ExtentTestManager.getTest().log(Status.INFO, " EditProductInCart - Step 18: Verify total price is '$2,840.00'");
		verifyEquals(myShopingCartPage.getTotalPriceInTable(), "$2,840.00");

	}

	@Test
	public void Order_03_RemoveFormCart(Method method) {

		ExtentTestManager.startTest(method.getName(), "Remove product from Shoping Cart successfully");
		ExtentTestManager.getTest().log(Status.INFO, " RemoveProductFromCart - Step 01: Click 'Remove' product in the table");
		myShopingCartPage.clickToRemoveProductButton();

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProductFromCart - Step 02: Verify Shoping Cart is empty");
		verifyTrue(myShopingCartPage.isShopingCartEmpty());

		ExtentTestManager.getTest().log(Status.INFO, " RemoveProductFromCart - Step 03: Verify 'Build your own computer' is not displayed");
		verifyTrue(myShopingCartPage.isProductsUndisplayed("Build your own computer"));

	}

	@Test
	public void Order_04_UpdateShopingCart(Method method) {

		ExtentTestManager.startTest(method.getName(), "Update Shoping Cart successfully");
		ExtentTestManager.getTest().log(Status.INFO, " UpdateShopingCart - Step 01: Hover to Computers and click on Desktops");
		myAccountPage = myShopingCartPage.clickAndHoverMenu("Computers", "Desktops");

		ExtentTestManager.getTest().log(Status.INFO, " UpdateShopingCart - Step 02: Add 'Lenovo IdeaCentre 600 All-in-One PC' to Cart");
		myAccountPage.clickAddToCartButton("Lenovo IdeaCentre 600 All-in-One PC");

		ExtentTestManager.getTest().log(Status.INFO, " UpdateShopingCart - Step 03: Click on 'Shoping Cart' at Menu bar");
		myAccountPage.clickOnDynamicHeadMenu("ico-cart");
		myShopingCartPage = PageGeneratorManager.getShopingCart(driver);

		ExtentTestManager.getTest().log(Status.INFO, " UpdateShopingCart - Step 04: Enter number of products with value is '5'");
		myShopingCartPage.enterNumberOfProducts("5");

		ExtentTestManager.getTest().log(Status.INFO, " UpdateShopingCart - Step 05: Click 'Update Shoping Cart' Button");
		myShopingCartPage.clickUpdateShopingCartButton();

		ExtentTestManager.getTest().log(Status.INFO, " UpdateShopingCart - Step 06: Verify total price is '$2,500.00'");
		verifyEquals(myShopingCartPage.getProductInfor(), "$2,500.00");

		ExtentTestManager.getTest().log(Status.INFO, " UpdateShopingCart - Step 07: Click on Remove all added products");
		myShopingCartPage.clickToRemoveProductButton();

	}

	@Test
	public void Order_05_CheckOutOrOderPaymentMethodByCheque(Method method) {

		ExtentTestManager.startTest(method.getName(), "Checkout and Payment method by cheque");
		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 01: Hover to Computers and click on Notebooks");
		myAccountPage.clickAndHoverMenu("Computers", "Notebooks");
		// ÏmyAccountPage = myShopingCartPage.clickAndHoverMenu("Computers",
		// "Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 02: Add 'Apple MacBook Pro 13-inch' to Cart");
		myAccountPage.clickAddToCartButton("Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 03: Click 'Add to Cart' Button");
		myAccountPage.clickAddToCartButton4();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 03.1: Click on 'Shoping Cart' at Menu bar");
		myAccountPage.clickOnDynamicHeadMenu("ico-cart");
		myShopingCartPage = PageGeneratorManager.getShopingCart(driver);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 04: Select 'Yes [+$10.00]' from 'Gift wrapping' Dropwdown");
		myShopingCartPage.selectWraping("Yes [+$10.00]");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 05: Check 'Agree permission' Checkbox");
		myShopingCartPage.checkToAgreePremisionCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 06: Click 'Check out' Button");
		myShopingCartPage.clickCheckoutButton();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 07: Uncheck 'The Same Address' Checkbox ");
		myShopingCartPage.UncheckToTheSameAddressCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 08: Input '" + billlingFristName + "' into 'FristName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billlingFristName, "BillingNewAddress_FirstName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 09: Input '" + billingLastName + "' into 'LastName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingLastName, "BillingNewAddress_LastName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 10: Input '" + billingEmail + "' into 'Email' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingEmail, "BillingNewAddress_Email");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 11: Input '" + billingCompany + "' into 'Company' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingCompany, "BillingNewAddress_Company");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 12: Select 'Viet Nam' from 'Country' Dropdown");
		myShopingCartPage.selectItemDyamic("Viet Nam", "BillingNewAddress_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 13: Select 'Other' from 'State/ Province' Dropdown");
		myShopingCartPage.selectItemDyamic("Other", "BillingNewAddress_StateProvinceId");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 14: Input '" + billingCity + "' into 'City' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingCity, "BillingNewAddress_City");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 15: Input'" + billingAddress + "' into 'Address 1' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingAddress, "BillingNewAddress_Address1");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 16: Input '" + billingPostalCode + "' into 'Zip/ Postal Code' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingPostalCode, "BillingNewAddress_ZipPostalCode");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 17: Input '" + billingPhone + "' into 'Phone number' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingPhone, "BillingNewAddress_PhoneNumber");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 18: Click on 'Continue' Button to type Shiping address");
		myShopingCartPage.clickContinueButton("billing-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 19: Select 'New Address' for Shiping Address");
		myShopingCartPage.selectShipingAddress("New Address");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 20: Input '" + shipingFirstName + "' into 'FristName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingFirstName, "ShippingNewAddress_FirstName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 21: Input '" + shipingLastName + "' into 'LastName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingLastName, "ShippingNewAddress_LastName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 22: Input '" + shipingEmail + "' into 'Email' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingEmail, "ShippingNewAddress_Email");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 23: Input '" + shipingCompany + "' into 'Company' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingCompany, "ShippingNewAddress_Company");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 24: Select 'Australia' from 'Country' Dropdown");
		myShopingCartPage.selectItemDyamic("Australia", "ShippingNewAddress_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 25: Select 'Other' from 'State/ Province' Dropdown");
		myShopingCartPage.selectItemDyamic("Other", "ShippingNewAddress_StateProvinceId");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 26: Input'" + shipingAddress + "' into 'Address 1' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingAddress, "ShippingNewAddress_Address1");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 27: Input '" + billingCity + "' into 'City' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingCity, "ShippingNewAddress_City");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 28: Input '" + shipingPostalCode + "' into 'Zip/ Postal Code' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingPostalCode, "ShippingNewAddress_ZipPostalCode");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 29: Input '" + billingPhone + "' into 'Phone number' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingPhone, "ShippingNewAddress_PhoneNumber");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 30: Click on 'Continue' Button to select Shiping method");
		myShopingCartPage.clickContinueButton("shipping-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 31: Select 'Ground ($0.00)' from 'Shiping method' Radio Button");
		myShopingCartPage.selectShipingMethod("Ground ($0.00)");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 32: Click on 'Continue' Button to select Payment method");
		myShopingCartPage.clickContinueButton("shipping-method-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 33: Select 'Check / Money Order' from 'Payment method' Radio Button");
		myShopingCartPage.selectPaymentMethod("Check / Money Order");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 34: Click on 'Continue' Button to view Payment Information");
		myShopingCartPage.clickContinueButton("payment-method-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 35: Verify the payment information displayed");
		verifyTrue(myShopingCartPage.isPaymentInforDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 36: Click on 'Continue' Button to view Confirm Order");
		myShopingCartPage.clickContinueButton("payment-info-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 37: Verify Billing name is '" + billlingFristName + " " + billingLastName + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "name"), billlingFristName + " " + billingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 38: Verify Billing email is '" + billingEmail + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "email")).contains(billingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 39: Verify Billing Phone number is '" + billingPhone + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "phone")).contains(billingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 40: Verify Billing fax is null");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 41: Verify Billing Address is '" + billingAddress + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "address1"), billingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 42: Verify Billing City-zip is '" + billingCity + "," + billingPostalCode + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "city-state-zip"), billingCity + "," + billingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 43: Verify Billing country is 'Viet Nam'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "country"), "Viet Nam");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 44: Verify Shiping name is '" + shipingFirstName + " " + shipingLastName + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "name"), shipingFirstName + " " + shipingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 45: Verify Shiping email is '" + shipingEmail + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "email")).contains(shipingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 45: Verify Shiping Phone number is '" + shipingPhone + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "phone")).contains(shipingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 46: Verify Shiping fax is null");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 47: Verify Shiping Address is '" + shipingAddress + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "address1"), shipingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 48: Verify Shiping City-zip is '" + shipingCity + "," + shipingPostalCode + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "city-state-zip"), shipingCity + "," + shipingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 49: Verify Billing country is 'Australia'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "country"), "Australia");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 50: Verify Payment Method: Check / Money Order");
		verifyEquals(myShopingCartPage.getInforOrderText("payment-method-info", "payment-method"), "Payment Method: Check / Money Order");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 51: Verify Shipping Method: Ground");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info-wrap", "shipping-method"), "Shipping Method: Ground");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 52: Verify SKU is 'AP_MBP_13'");
		verifyEquals(myShopingCartPage.getInforProductIntable("sku", "sku-number"), "AP_MBP_13");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 53: Verify Product Name is 'Apple MacBook Pro 13-inch'");
		verifyEquals(myShopingCartPage.getInforProductNameIntable(), "Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 54: Verify price is '$1,800.00'");
		verifyEquals(myShopingCartPage.getInforProductIntable("unit-price", "product-unit-price"), "$1,800.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 55: Verify QTY are 2");
		verifyEquals(myShopingCartPage.getInforProductIntable("quantity", "product-quantity"), "2");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 56: Verify Total price is '$3,600.00' ");
		verifyEquals(myShopingCartPage.getInforProductIntable("subtotal", "product-subtotal"), "$3,600.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 57: Verify Gift wrapping: Yes [+$10.00]");
		verifyTrue(((String) myShopingCartPage.getGiftWraping()).contains("Gift wrapping: Yes [+$10.00]"));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 58: Verify Sub-total is '$3,610.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Sub-Total:"), "$3,610.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 59: Verify Shipping is '$0.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Shipping:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 60: Verify Tax is '$0.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Tax:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 61: Verify Total is '$3,610.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Total:"), "$3,610.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 62: Verify You will earn: 361 points");
		verifyEquals(myShopingCartPage.getPriceDetail("You will earn:"), "361 points");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 63: Click 'Confirm' Button");
		myShopingCartPage.clickConfirmButton();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 64: Verify text 'Thank you' is displayed");
		verifyTrue(myShopingCartPage.isThankYouDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 65: Verify message 'Your order has been successfully proccessed' is displayed");
		verifyTrue(myShopingCartPage.isSuccessMessaageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 66: Verify Oder Number is displayed");
		verifyTrue(myShopingCartPage.isOrderNumberDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 67: Click on 'My Account' at Menu bar");
		myShopingCartPage.clickOnDynamicHeadMenu("ico-account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 68: Verify 'My Account' Page is displayed");
		verifyTrue(myAccountPage.isMyAccountPageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 69: Click 'Order' Page in the sidebar");
		myAccountPage.clickDynamicLeftMenuLink("Orders");
		myOrdersPage = PageGeneratorManager.getMyOrdersPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 70: Verify  'My Order' Page is displayed");
		verifyTrue(myOrdersPage.isOrderNumberDisplayed());

		String orderNumber = myOrdersPage.getOrderNumber().substring(14);
		System.out.println("-----------OrderNumber:" + orderNumber);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 71: Click on 'Detail' Button");
		myOrdersPage.clickDetailButton();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 72: Verify Order number is '" + orderNumber + "'");
		verifyTrue(((String) myOrdersPage.getOrderNumberInDetails()).contains(orderNumber));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 73: Verify 'Order status' is pending");
		verifyTrue(myOrdersPage.isOrderStatusDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 74: Verify Order Total: $3,610.00");
		verifyEquals(myOrdersPage.getOrderTotal(), "Order Total: $3,610.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 75: Verify Billing name is '" + billlingFristName + " " + billingLastName + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "name"), billlingFristName + " " + billingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 76: Verify Billing email is '" + billingEmail + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "email")).contains(billingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 77: Verify Billing Phone number is '" + billingPhone + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "phone")).contains(billingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 78: Verify Billing fax is null");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 79: Verify Billing Address is '" + billingAddress + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "address1"), billingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 80: HVerify Billing City-zip is '" + billingCity + "," + billingPostalCode + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "city-state-zip"), billingCity + "," + billingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 81: Verify Billing country is 'Viet Nam'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "country"), "Viet Nam");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 82: Verify Shiping name is '" + shipingFirstName + " " + shipingLastName + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "name"), shipingFirstName + " " + shipingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 83: Verify Shiping email is '" + shipingEmail + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "email")).contains(shipingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 84: Verify Shiping Phone number is '" + shipingPhone + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "phone")).contains(shipingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 85: Verify Shiping fax is null");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 86: Verify Shiping Address is '" + shipingAddress + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "address1"), shipingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 87: Verify Shiping City-zip is '" + shipingCity + "," + shipingPostalCode + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "city-state-zip"), shipingCity + "," + shipingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 88: Verify Billing country is 'Australia'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "country"), "Australia");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 89: Verify Payment Method: Check / Money Order");
		verifyEquals(myOrdersPage.getInforOrderText("payment-method-info", "payment-method"), "Payment Method: Check / Money Order");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 90: Verify Shipping Method: Ground");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info-wrap", "shipping-method"), "Shipping Method: Ground");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 91: Verify SKU is 'AP_MBP_13'");
		verifyEquals(myOrdersPage.getInforProductIntable("sku", "sku-number"), "AP_MBP_13");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 92: Verify Product Name is 'Apple MacBook Pro 13-inch'");
		verifyEquals(myOrdersPage.getInforProductNameIntable(), "Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 93: Verify price is '$1,800.00'");
		verifyEquals(myOrdersPage.getInforProductIntable("unit-price", "product-unit-price"), "$1,800.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 94: Verify QTY are 2");
		verifyEquals(myOrdersPage.getInforProductIntable("quantity", "product-quantity"), "2");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 95: Verify Total price is '$3,600.00' ");
		verifyEquals(myOrdersPage.getInforProductIntable("total", "product-subtotal"), "$3,600.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 96: Verify Gift wrapping: Yes [+$10.00]");
		verifyTrue(((String) myOrdersPage.getGiftWraping()).contains("Gift wrapping: Yes [+$10.00]"));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 97: Verify Sub-total is '$3,610.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Sub-Total:"), "$3,610.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 98: Verify Shipping is '$0.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Shipping:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 99: Verify Tax is '$0.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Tax:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCheque - Step 100: HoVerify Total is '$3,610.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Order Total:"), "$3,610.00");

	}

	@Test
	public void Order_06_CheckOutOrOrderPaymentMethodByCard(Method method) {

		ExtentTestManager.startTest(method.getName(), "Checkout and Payment method by Card");
		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 01: Hover to Computers and click on Notebooks");
		myAccountPage.clickAndHoverMenu("Computers", "Notebooks");
		// myAccountPage = myShopingCartPage.clickAndHoverMenu("Computers", "Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 02: Add 'Apple MacBook Pro 13-inch' to Cart");
		myAccountPage.clickAddToCartButton("Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 03: Click 'Add to Cart' Button");
		myAccountPage.clickAddToCartButton4();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 04: Click on 'Shoping Cart' at Menu bar");
		myAccountPage.clickOnDynamicHeadMenu("ico-cart");
		myShopingCartPage = PageGeneratorManager.getShopingCart(driver);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 05: Select 'Yes [+$10.00]' from 'Gift wrapping' Dropwdown");
		myShopingCartPage.selectWraping("Yes [+$10.00]");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 06: Check 'Agree permission' Checkbox");
		myShopingCartPage.checkToAgreePremisionCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 07: Click 'Check out' Button");
		myShopingCartPage.clickCheckoutButton();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 08: Uncheck 'The Same Address' Checkbox ");
		myShopingCartPage.UncheckToTheSameAddressCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 09: Input '" + billlingFristName + "' into 'FristName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billlingFristName, "BillingNewAddress_FirstName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 10: Input '" + billingLastName + "' into 'LastName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingLastName, "BillingNewAddress_LastName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 11: Input '" + billingEmail + "' into 'Email' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingEmail, "BillingNewAddress_Email");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 12: Input '" + billingCompany + "' into 'Company' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingCompany, "BillingNewAddress_Company");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 13: Select 'Viet Nam' from 'Country' Dropdown");
		myShopingCartPage.selectItemDyamic("Viet Nam", "BillingNewAddress_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 14: Select 'Other' from 'State/ Province' Dropdown");
		myShopingCartPage.selectItemDyamic("Other", "BillingNewAddress_StateProvinceId");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 15: Input '" + billingCity + "' into 'City' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingCity, "BillingNewAddress_City");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 16: Input'" + billingAddress + "' into 'Address 1' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingAddress, "BillingNewAddress_Address1");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 17: Input '" + billingPostalCode + "' into 'Zip/ Postal Code' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingPostalCode, "BillingNewAddress_ZipPostalCode");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 18: Input '" + billingPhone + "' into 'Phone number' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(billingPhone, "BillingNewAddress_PhoneNumber");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 19: Click on 'Continue' Button to type Shiping address");
		myShopingCartPage.clickContinueButton("billing-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 20: Select 'New Address' for Shiping Address");
		myShopingCartPage.selectShipingAddress("New Address");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 21: Input '" + shipingFirstName + "' into 'FristName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingFirstName, "ShippingNewAddress_FirstName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 22: Input '" + shipingLastName + "' into 'LastName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingLastName, "ShippingNewAddress_LastName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 23: Input '" + shipingEmail + "' into 'Email' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingEmail, "ShippingNewAddress_Email");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 24: Input '" + shipingCompany + "' into 'Company' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingCompany, "ShippingNewAddress_Company");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 25: Select 'Australia' from 'Country' Dropdown");
		myShopingCartPage.selectItemDyamic("Australia", "ShippingNewAddress_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 26: Select 'Other' from 'State/ Province' Dropdown");
		myShopingCartPage.selectItemDyamic("Other", "ShippingNewAddress_StateProvinceId");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 27: Input'" + shipingAddress + "' into 'Address 1' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingAddress, "ShippingNewAddress_Address1");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 28: Input '" + billingCity + "' into 'City' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingCity, "ShippingNewAddress_City");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 29: Input '" + shipingPostalCode + "' into 'Zip/ Postal Code' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingPostalCode, "ShippingNewAddress_ZipPostalCode");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 30: Input '" + billingPhone + "' into 'Phone number' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(shipingPhone, "ShippingNewAddress_PhoneNumber");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 31: Click on 'Continue' Button to select Shiping method");
		myShopingCartPage.clickContinueButton("shipping-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 32: Select 'Ground ($0.00)' from 'Shiping method' Radio Button");
		myShopingCartPage.selectShipingMethod("Ground ($0.00)");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 33: Click on 'Continue' Button to select Payment method");
		myShopingCartPage.clickContinueButton("shipping-method-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 34: Select 'Check / Money Order' from 'Payment method' Radio Button");
		myShopingCartPage.selectPaymentMethod("Credit Card");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 35: Click on 'Continue' Button to view Payment Information");
		myShopingCartPage.clickContinueButton("payment-method-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 36: Select 'Visa' frome Credit Cart Type");
		myShopingCartPage.selectValueInCreditCard("Visa", "CreditCardType");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 37: Input '" + dataFaker.getCardHolderName() + "'into 'Card holder Name' Textbox");
		myShopingCartPage.enterVisaInfo(dataFaker.getCardHolderName(), "CardholderName");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 38: Input '" + dataFaker.getCardNumber() + "'into 'Card holder Name' Textbox");
		myShopingCartPage.enterVisaInfo(dataFaker.getCardNumber(), "CardNumber");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 39: Select Expire month is '03'");
		myShopingCartPage.selectValueInCreditCard("03", "ExpireMonth");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 40: Select Expire year is '2006'");
		myShopingCartPage.selectValueInCreditCard("2026", "ExpireYear");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 41: Input '" + dataFaker.getCardCode() + "'into 'Card Code' Textbox");
		myShopingCartPage.enterVisaInfo(dataFaker.getCardCode(), "CardCode");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 42: Click on 'Continue' Button to view Confirm Order");
		myShopingCartPage.clickContinueButton("payment-info-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 43: Verify Billing name is '" + billlingFristName + " " + billingLastName + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "name"), billlingFristName + " " + billingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 44: Verify Billing email is '" + billingEmail + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "email")).contains(billingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 45: Verify Billing Phone number is '" + billingPhone + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "phone")).contains(billingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 46: Verify Billing fax is null");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 47: Verify Billing Address is '" + billingAddress + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "address1"), billingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 48: Verify Billing City-zip is '" + billingCity + "," + billingPostalCode + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "city-state-zip"), billingCity + "," + billingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 49: Verify Billing country is 'Viet Nam'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "country"), "Viet Nam");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 50: Verify Shiping name is '" + shipingFirstName + " " + shipingLastName + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "name"), shipingFirstName + " " + shipingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 51: Verify Shiping email is '" + shipingEmail + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "email")).contains(shipingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 52: Verify Shiping Phone number is '" + shipingPhone + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "phone")).contains(shipingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 53: Verify Shiping fax is null");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 54: Verify Shiping Address is '" + shipingAddress + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "address1"), shipingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 55: Verify Shiping City-zip is '" + shipingCity + "," + shipingPostalCode + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "city-state-zip"), shipingCity + "," + shipingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 56: Verify Billing country is 'Australia'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "country"), "Australia");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 57: Verify Payment Method: Credit Card");
		verifyEquals(myShopingCartPage.getInforOrderText("payment-method-info", "payment-method"), "Payment Method: Credit Card");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 58: Verify Shipping Method: Ground");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info-wrap", "shipping-method"), "Shipping Method: Ground");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 59: Verify SKU is 'AP_MBP_13'");
		verifyEquals(myShopingCartPage.getInforProductIntable("sku", "sku-number"), "AP_MBP_13");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 60: Verify Product Name is 'Apple MacBook Pro 13-inch'");
		verifyEquals(myShopingCartPage.getInforProductNameIntable(), "Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 61: Verify price is '$1,800.00'");
		verifyEquals(myShopingCartPage.getInforProductIntable("unit-price", "product-unit-price"), "$1,800.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 62: Verify QTY are 2");
		verifyEquals(myShopingCartPage.getInforProductIntable("quantity", "product-quantity"), "2");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 63: Verify Total price is '$3,600.00' ");
		verifyEquals(myShopingCartPage.getInforProductIntable("subtotal", "product-subtotal"), "$3,600.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 64: Verify Gift wrapping: Yes [+$10.00]");
		verifyTrue(((String) myShopingCartPage.getGiftWraping()).contains("Gift wrapping: Yes [+$10.00]"));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 65: Verify Sub-total is '$3,610.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Sub-Total:"), "$3,610.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 66: Verify Shipping is '$0.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Shipping:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 67: Verify Tax is '$0.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Tax:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 68: Verify Total is '$3,610.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Total:"), "$3,610.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 69: Verify You will earn: 361 points");
		verifyEquals(myShopingCartPage.getPriceDetail("You will earn:"), "361 points");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 70: Click 'Confirm' Button");
		myShopingCartPage.clickConfirmButton();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 71: Verify text 'Thank you' is displayed");
		verifyTrue(myShopingCartPage.isThankYouDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 72: Verify message 'Your order has been successfully proccessed' is displayed");
		verifyTrue(myShopingCartPage.isSuccessMessaageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 73: Verify Oder Number is displayed");
		verifyTrue(myShopingCartPage.isOrderNumberDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 74: Click on 'My Account' at Menu bar");
		myShopingCartPage.clickOnDynamicHeadMenu("ico-account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 75: Verify 'My Account' Page is displayed");
		verifyTrue(myAccountPage.isMyAccountPageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 76: Click 'Order' Page in the sidebar");
		myAccountPage.clickDynamicLeftMenuLink("Orders");
		myOrdersPage = PageGeneratorManager.getMyOrdersPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 77: Verify  'My Order' Page is displayed");
		verifyTrue(myOrdersPage.isOrderNumberDisplayed());

		String orderNumber = myOrdersPage.getOrderNumber().substring(14);
		System.out.println("-----------OrderNumber:" + orderNumber);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 78: Click on 'Detail' Button");
		myOrdersPage.clickDetailButton();

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 79: Verify Order number is '" + orderNumber + "'");
		verifyTrue(((String) myOrdersPage.getOrderNumberInDetails()).contains(orderNumber));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 80: Verify 'Order status' is pending");
		verifyTrue(myOrdersPage.isOrderStatusDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 81: Verify Order Total: $3,610.00");
		verifyEquals(myOrdersPage.getOrderTotal(), "Order Total: $3,610.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 82: Verify Billing name is '" + billlingFristName + " " + billingLastName + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "name"), billlingFristName + " " + billingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 83: Verify Billing email is '" + billingEmail + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "email")).contains(billingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 84: Verify Billing Phone number is '" + billingPhone + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "phone")).contains(billingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 85: Verify Billing fax is null");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 86: Verify Billing Address is '" + billingAddress + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "address1"), billingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 87: HVerify Billing City-zip is '" + billingCity + "," + billingPostalCode + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "city-state-zip"), billingCity + "," + billingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 88: Verify Billing country is 'Viet Nam'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "country"), "Viet Nam");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 89: Verify Shiping name is '" + shipingFirstName + " " + shipingLastName + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "name"), shipingFirstName + " " + shipingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 90: Verify Shiping email is '" + shipingEmail + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "email")).contains(shipingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 91: Verify Shiping Phone number is '" + shipingPhone + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "phone")).contains(shipingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 92: Verify Shiping fax is null");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 93: Verify Shiping Address is '" + shipingAddress + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "address1"), shipingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 94: Verify Shiping City-zip is '" + shipingCity + "," + shipingPostalCode + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "city-state-zip"), shipingCity + "," + shipingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 95: Verify Billing country is 'Australia'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "country"), "Australia");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 96: Verify Payment Method: Credit Card");
		verifyEquals(myOrdersPage.getInforOrderText("payment-method-info", "payment-method"), "Payment Method: Credit Card");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 97: Verify Shipping Method: Ground");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info-wrap", "shipping-method"), "Shipping Method: Ground");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 98: Verify SKU is 'AP_MBP_13'");
		verifyEquals(myOrdersPage.getInforProductIntable("sku", "sku-number"), "AP_MBP_13");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 99: Verify Product Name is 'Apple MacBook Pro 13-inch'");
		verifyEquals(myOrdersPage.getInforProductNameIntable(), "Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 100: Verify price is '$1,800.00'");
		verifyEquals(myOrdersPage.getInforProductIntable("unit-price", "product-unit-price"), "$1,800.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 101: Verify QTY are 2");
		verifyEquals(myOrdersPage.getInforProductIntable("quantity", "product-quantity"), "2");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 102: Verify Total price is '$3,600.00' ");
		verifyEquals(myOrdersPage.getInforProductIntable("total", "product-subtotal"), "$3,600.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 103: Verify Gift wrapping: Yes [+$10.00]");
		verifyTrue(((String) myOrdersPage.getGiftWraping()).contains("Gift wrapping: Yes [+$10.00]"));

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 104: Verify Sub-total is '$3,610.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Sub-Total:"), "$3,610.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 105: Verify Shipping is '$0.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Shipping:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 106: Verify Tax is '$0.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Tax:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " ChekoutByCard - Step 107: Verify Total is '$3,610.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Order Total:"), "$3,610.00");

	}

	@Test
	public void Order_07_Reorder(Method method) {

		ExtentTestManager.startTest(method.getName(), "Reorder successfully");
		ExtentTestManager.getTest().log(Status.INFO, "Reorder - Step 01: Click on 'Reorder' Button");
		myOrdersPage.clickReoderButton();
		myShopingCartPage = PageGeneratorManager.getShopingCart(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Reorder - Step 02: Input '10' into Qty Textbox");
		myShopingCartPage.enterNumberOfProducts("10");

		ExtentTestManager.getTest().log(Status.INFO, "Reorder - Step 03: Click 'Update shoping cart' Button");
		myShopingCartPage.clickUpdateShopingCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Reorder - Step 04: Verify the price of 10 items is '$18,000.00'");
		verifyEquals(myShopingCartPage.getProductInfor(), "$18,000.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 05: Select 'Yes [+$10.00]' from 'Gift wrapping' Dropwdown");
		myShopingCartPage.selectWraping("Yes [+$10.00]");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 06: Check 'Agree permission' Checkbox");
		myShopingCartPage.checkToAgreePremisionCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 07: Click 'Check out' Button");
		myShopingCartPage.clickCheckoutButton();

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 08: Uncheck 'The Same Address' Checkbox ");
		myShopingCartPage.UncheckToTheSameAddressCheckbox();

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 08.1: Select' New address' for Billing Address");
		myShopingCartPage.selectBilllingAddress("New Address");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 09: Input '" + newBilllingFristName + "' into 'FristName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newBilllingFristName, "BillingNewAddress_FirstName");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 10: Input '" + newBilllingLastName + "' into 'LastName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newBilllingLastName, "BillingNewAddress_LastName");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 11: Input '" + newBilllingEmail + "' into 'Email' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newBilllingEmail, "BillingNewAddress_Email");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 12: Input '" + newBilllingCompany + "' into 'Company' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newBilllingCompany, "BillingNewAddress_Company");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 13: Select 'Viet Nam' from 'Country' Dropdown");
		myShopingCartPage.selectItemDyamic("Viet Nam", "BillingNewAddress_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 14: Select 'Other' from 'State/ Province' Dropdown");
		myShopingCartPage.selectItemDyamic("Other", "BillingNewAddress_StateProvinceId");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 15: Input '" + newBilllingCity + "' into 'City' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newBilllingCity, "BillingNewAddress_City");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 16: Input'" + newBilllingAddress + "' into 'Address 1' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newBilllingAddress, "BillingNewAddress_Address1");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 17: Input '" + newBilllingPostalCode + "' into 'Zip/ Postal Code' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newBilllingPostalCode, "BillingNewAddress_ZipPostalCode");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 18: Input '" + newBilllingPhone + "' into 'Phone number' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newBilllingPhone, "BillingNewAddress_PhoneNumber");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 19: Click on 'Continue' Button to type Shiping address");
		myShopingCartPage.clickContinueButton("billing-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 20: Select 'New Address' for Shiping Address");
		myShopingCartPage.selectShipingAddress("New Address");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 21: Input '" + newShipingFirstName + "' into 'FristName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newShipingFirstName, "ShippingNewAddress_FirstName");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 22: Input '" + newShipingLastName + "' into 'LastName' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newShipingLastName, "ShippingNewAddress_LastName");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 23: Input '" + newShipingEmail + "' into 'Email' Textbox'");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newShipingEmail, "ShippingNewAddress_Email");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 24: Input '" + newShipingCompany + "' into 'Company' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newShipingCompany, "ShippingNewAddress_Company");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 25: Select 'Australia' from 'Country' Dropdown");
		myShopingCartPage.selectItemDyamic("Australia", "ShippingNewAddress_CountryId");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 26: Select 'Other' from 'State/ Province' Dropdown");
		myShopingCartPage.selectItemDyamic("Other", "ShippingNewAddress_StateProvinceId");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 27: Input'" + newShipingAddress + "' into 'Address 1' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newShipingAddress, "ShippingNewAddress_Address1");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 28: Input '" + newShipingCity + "' into 'City' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newShipingCity, "ShippingNewAddress_City");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 29: Input '" + newShipingPostalCode + "' into 'Zip/ Postal Code' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newShipingPostalCode, "ShippingNewAddress_ZipPostalCode");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 30: Input '" + newShipingPhone + "' into 'Phone number' Textbox");
		myShopingCartPage.enterToDynamicBillingAddressTextbox(newShipingPhone, "ShippingNewAddress_PhoneNumber");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 31: Click on 'Continue' Button to select Shiping method");
		myShopingCartPage.clickContinueButton("shipping-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 32: Select 'Next Day Air ($0.00)' from 'Shiping method' Radio Button");
		myShopingCartPage.selectShipingMethod("Next Day Air ($0.00)");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 33: Click on 'Continue' Button to select Payment method");
		myShopingCartPage.clickContinueButton("shipping-method-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 34: Select 'Check / Money Order' from 'Payment method' Radio Button");
		myShopingCartPage.selectPaymentMethod("Credit Card");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 35: Click on 'Continue' Button to view Payment Information");
		myShopingCartPage.clickContinueButton("payment-method-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 36: Select 'Visa' frome Credit Cart Type");
		myShopingCartPage.selectValueInCreditCard("Visa", "CreditCardType");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 37: Input '" + dataFaker.getCardHolderName() + "'into 'Card holder Name' Textbox");
		myShopingCartPage.enterVisaInfo(dataFaker.getCardHolderName(), "CardholderName");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 38: Input '" + dataFaker.getCardNumber() + "'into 'Card holder Name' Textbox");
		myShopingCartPage.enterVisaInfo(dataFaker.getCardNumber(), "CardNumber");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 39: Select Expire month is '03'");
		myShopingCartPage.selectValueInCreditCard("03", "ExpireMonth");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 40: Select Expire year is '2006'");
		myShopingCartPage.selectValueInCreditCard("2026", "ExpireYear");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 41: Input '" + dataFaker.getCardCode() + "'into 'Card Code' Textbox");
		myShopingCartPage.enterVisaInfo(dataFaker.getCardCode(), "CardCode");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 42: Click on 'Continue' Button to view Confirm Order");
		myShopingCartPage.clickContinueButton("payment-info-buttons-container");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 43: Verify Billing name is '" + newBilllingFristName + " " + newBilllingLastName + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "name"), newBilllingFristName + " " + newBilllingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 44: Verify Billing email is '" + newBilllingEmail + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "email")).contains(newBilllingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 45: Verify Billing Phone number is '" + newBilllingPhone + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "phone")).contains(newBilllingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 46: Verify Billing fax is null");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("billing-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 47: Verify Billing Address is '" + newBilllingAddress + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "address1"), newBilllingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 48: Verify Billing City-zip is '" + newBilllingCity + "," + newBilllingPostalCode + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "city-state-zip"), newBilllingCity + "," + newBilllingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 49: Verify Billing country is 'Viet Nam'");
		verifyEquals(myShopingCartPage.getInforOrderText("billing-info", "country"), "Viet Nam");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 50: Verify Shiping name is '" + newShipingFirstName + " " + newShipingLastName + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "name"), newShipingFirstName + " " + newShipingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 51: Verify Shiping email is '" + newShipingEmail + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "email")).contains(newShipingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 52: Verify Shiping Phone number is '" + newShipingPhone + "'");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "phone")).contains(newShipingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 53: Verify Shiping fax is null");
		verifyTrue(((String) myShopingCartPage.getInforOrderText("shipping-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 54: Verify Shiping Address is '" + newShipingAddress + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "address1"), newShipingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 55: Verify Shiping City-zip is '" + newShipingCity + "," + newShipingPostalCode + "'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "city-state-zip"), newShipingCity + "," + newShipingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 56: Verify Billing country is 'Australia'");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info", "country"), "Australia");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 57: Verify Payment Method: Credit Card");
		verifyEquals(myShopingCartPage.getInforOrderText("payment-method-info", "payment-method"), "Payment Method: Credit Card");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 58: Verify Shipping Method: Ground");
		verifyEquals(myShopingCartPage.getInforOrderText("shipping-info-wrap", "shipping-method"), "Shipping Method: Next Day Air");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 59: Verify SKU is 'AP_MBP_13'");
		verifyEquals(myShopingCartPage.getInforProductIntable("sku", "sku-number"), "AP_MBP_13");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 60: Verify Product Name is 'Apple MacBook Pro 13-inch'");
		verifyEquals(myShopingCartPage.getInforProductNameIntable(), "Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 61: Verify price is '$1,800.00'");
		verifyEquals(myShopingCartPage.getInforProductIntable("unit-price", "product-unit-price"), "$1,800.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 62: Verify QTY are 10");
		verifyEquals(myShopingCartPage.getInforProductIntable("quantity", "product-quantity"), "10");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 63: Verify Total price is '$18,000.00' ");
		verifyEquals(myShopingCartPage.getInforProductIntable("subtotal", "product-subtotal"), "$18,000.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 64: Verify Gift wrapping: Yes [+$10.00]");
		verifyTrue(((String) myShopingCartPage.getGiftWraping()).contains("Gift wrapping: Yes [+$10.00]"));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 65: Verify Sub-total is '$18,010.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Sub-Total:"), "$18,010.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 66: Verify Shipping is '$0.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Shipping:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 67: Verify Tax is '$0.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Tax:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 68: Verify Total is '$18,010.00'");
		verifyEquals(myShopingCartPage.getPriceDetail("Total:"), "$18,010.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 69: Verify You will earn: 1801 points");
		verifyEquals(myShopingCartPage.getPriceDetail("You will earn:"), "1801 points");
		sleepInSecond(5);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 70: Click 'Confirm' Button");
		myShopingCartPage.clickConfirmButtonReorder();
		sleepInSecond(5);

		myShopingCartPage.acceptAlertDisplayed();
		sleepInSecond(10);
		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 70.1: Click 'Confirm' Button again");
		myShopingCartPage.clickConfirmButton();

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 71: Verify text 'Thank you' is displayed");
		verifyTrue(myShopingCartPage.isThankYouDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 72: Verify message 'Your order has been successfully proccessed' is displayed");
		verifyTrue(myShopingCartPage.isSuccessMessaageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 73: Verify Oder Number is displayed");
		verifyTrue(myShopingCartPage.isOrderNumberDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 74: Click on 'My Account' at Menu bar");
		myShopingCartPage.clickOnDynamicHeadMenu("ico-account");
		myAccountPage = PageGeneratorManager.getMyAccountPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 75: Verify 'My Account' Page is displayed");
		verifyTrue(myAccountPage.isMyAccountPageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 76: Click 'Order' Page in the sidebar");
		myAccountPage.clickDynamicLeftMenuLink("Orders");
		myOrdersPage = PageGeneratorManager.getMyOrdersPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 77: Verify  'My Order' Page is displayed");
		verifyTrue(myOrdersPage.isOrderNumberDisplayed());

		String orderNumber = myOrdersPage.getOrderNumber().substring(14);
		System.out.println("-----------OrderNumber:" + orderNumber);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 78: Click on 'Detail' Button");
		myOrdersPage.clickDetailButton();

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 79: Verify Order number is '" + orderNumber + "'");
		verifyTrue(((String) myOrdersPage.getOrderNumberInDetails()).contains(orderNumber));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 80: Verify 'Order status' is pending");
		verifyTrue(myOrdersPage.isOrderStatusDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 81: Verify Order Total: $18,010.00");
		verifyEquals(myOrdersPage.getOrderTotal(), "Order Total: $18,010.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 82: Verify Billing name is '" + newBilllingFristName + " " + newBilllingLastName + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "name"), newBilllingFristName + " " + newBilllingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 83: Verify Billing email is '" + newBilllingEmail + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "email")).contains(newBilllingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 84: Verify Billing Phone number is '" + newBilllingPhone + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "phone")).contains(newBilllingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 85: Verify Billing fax is null");
		verifyTrue(((String) myOrdersPage.getInforOrderText("billing-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 86: Verify Billing Address is '" + newBilllingAddress + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "address1"), newBilllingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 87: HVerify Billing City-zip is '" + newBilllingCity + "," + newBilllingPostalCode + "'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "city-state-zip"), newBilllingCity + "," + newBilllingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 88: Verify Billing country is 'Viet Nam'");
		verifyEquals(myOrdersPage.getInforOrderText("billing-info", "country"), "Viet Nam");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 89: Verify Shiping name is '" + newShipingFirstName + " " + newShipingLastName + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "name"), newShipingFirstName + " " + newShipingLastName);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 90: Verify Shiping email is '" + newShipingEmail + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "email")).contains(newShipingEmail));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 91: Verify Shiping Phone number is '" + newShipingPhone + "'");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "phone")).contains(newShipingPhone));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 92: Verify Shiping fax is null");
		verifyTrue(((String) myOrdersPage.getInforOrderText("shipping-info", "fax")).contains(""));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 93: Verify Shiping Address is '" + newShipingAddress + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "address1"), newShipingAddress);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 94: Verify Shiping City-zip is '" + newShipingCity + "," + newShipingPostalCode + "'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "city-state-zip"), newShipingCity + "," + newShipingPostalCode);

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 95: Verify Billing country is 'Australia'");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info", "country"), "Australia");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 96: Verify Payment Method: Credit Card");
		verifyEquals(myOrdersPage.getInforOrderText("payment-method-info", "payment-method"), "Payment Method: Credit Card");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 97: Verify Payment Method Status: Pending");
		verifyEquals(myOrdersPage.getInforOrderText("payment-method-info", "payment-method-status"), "Payment Status: Pending");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 98: Verify Shipping Method: Next Day Air");
		verifyEquals(myOrdersPage.getInforOrderText("shipping-info-wrap", "shipping-method"), "Shipping Method: Next Day Air");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 99: Verify SKU is 'AP_MBP_13'");
		verifyEquals(myOrdersPage.getInforProductIntable("sku", "sku-number"), "AP_MBP_13");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 100: Verify Product Name is 'Apple MacBook Pro 13-inch'");
		verifyEquals(myOrdersPage.getInforProductNameIntable(), "Apple MacBook Pro 13-inch");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 101: Verify price is '$18,000.00'");
		verifyEquals(myOrdersPage.getInforProductIntable("unit-price", "product-unit-price"), "$18,000.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 102: Verify QTY are 10");
		verifyEquals(myOrdersPage.getInforProductIntable("quantity", "product-quantity"), "10");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 103: Verify Total price is '$18,000.00' ");
		verifyEquals(myOrdersPage.getInforProductIntable("total", "product-subtotal"), "$18,000.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 104: Verify Gift wrapping: Yes [+$10.00]");
		verifyTrue(((String) myOrdersPage.getGiftWraping()).contains("Gift wrapping: Yes [+$10.00]"));

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 105: Verify Sub-total is '$18,000.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Sub-Total:"), "$18,000.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 106: Verify Shipping is '$0.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Shipping:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 107: Verify Tax is '$0.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Tax:"), "$0.00");

		ExtentTestManager.getTest().log(Status.INFO, " Reorder - Step 108: Verify Total is '$18,000.00'");
		verifyEquals(myOrdersPage.getPriceDetail("Order Total:"), "$18,000.00");

	}
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
