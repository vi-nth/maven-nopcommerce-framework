package PageObject.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageUIs.com.nopcommerce.MyAccountPageUI;
import PageUIs.com.nopcommerce.UserHomePageUI;
import PageUIs.com.nopcommerce.WishlistPageObject;
import commons.BasePage;
import commons.PageGeneratorManager;

public class MyAccountPageObject extends BasePage {
	WebDriver driver;

	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isMyAccountPageDisplayed(String pageLinkName) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_PAGE_LINK, pageLinkName);
		return isElementDisplayed(driver, MyAccountPageUI.DYNAMIC_PAGE_LINK, pageLinkName);
	}

	public UserAccountPageObject clickToPageLink(String pageLinkName) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_PAGE_LINK, pageLinkName);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_PAGE_LINK, pageLinkName);
		return PageGeneratorManager.getUserAccountPage(driver);
	}

	public void clickOnDynamicMenuByName(String menuName) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_SUB_MENU, menuName);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_SUB_MENU, menuName);

	}

	public void clickOnDynamicCategory(String nameCategory) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_CATEGORY, nameCategory);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_CATEGORY, nameCategory);
	}

	public void clickOnProductName(String productName) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_PRODUCT_NAME, productName);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_PRODUCT_NAME, productName);

	}

	public void clickAddYourReview() {
		waitForElementClickable(driver, MyAccountPageUI.ADD_REVIEW);
		clickToElement(driver, MyAccountPageUI.ADD_REVIEW);
	}

	public void enterReviewByClass(String reviewInput, String reviewID) {
		waitForElementVisible(driver, MyAccountPageUI.REVIEW_TITLE_TEXTBOX, reviewID);
		clickToElement(driver, MyAccountPageUI.REVIEW_TITLE_TEXTBOX, reviewID);
		sendkeyToElement(driver, MyAccountPageUI.REVIEW_TITLE_TEXTBOX, reviewInput, reviewID);

	}

	public void selectRatingByID(String ratingPoint) {
		waitForElementClickable(driver, MyAccountPageUI.RATING, ratingPoint);
		clickToElement(driver, MyAccountPageUI.RATING, ratingPoint);

	}

	public void clickOnSubmitReview() {
		waitForElementClickable(driver, MyAccountPageUI.SUBMIT_REVIEW);
		clickToElement(driver, MyAccountPageUI.SUBMIT_REVIEW);

	}

	public String getSuccessfullReviewedMessage() {
		waitForElementVisible(driver, MyAccountPageUI.RESULT_REVIEW);
		return getElementText(driver, MyAccountPageUI.RESULT_REVIEW);
	}

	public boolean isReviewTitleDisplayed(String reviewTitle) {
		waitForElementInvisible(driver, MyAccountPageUI.REVIEW_TITLE, reviewTitle);
		return isElementDisplayed(driver, MyAccountPageUI.REVIEW_TITLE, reviewTitle);
	}

	public void enterReviewText(String textReview) {
		waitForElementVisible(driver, MyAccountPageUI.REVIEW_TEXT_TEXTBOX);
		sendkeyToElement(driver, MyAccountPageUI.REVIEW_TEXT_TEXTBOX, textReview);
	}

	public SearchPageObject clickToDynamicPage(String pageName) {
		waitForElementClickable(driver, MyAccountPageUI.PAGE_NAME, pageName);
		clickToElement(driver, MyAccountPageUI.PAGE_NAME, pageName);
		return PageGeneratorManager.getSearchPage(driver);
	}

	public void clickAndHoverMenu(String menuName, String subMenuName) {
		hoverMouseToElement(driver, MyAccountPageUI.DYNAMIC_MENU_NAME, menuName);
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_SUBMENU_NAME, menuName, subMenuName);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_SUBMENU_NAME, menuName, subMenuName);

	}

	public void clickOnProduct(String productName) {
		waitForElementClickable(driver, MyAccountPageUI.PRODUCT_NAME, productName);
		clickToElement(driver, MyAccountPageUI.PRODUCT_NAME, productName);

	}

	public void clickAddToWishlistButton() {
		waitForElementClickable(driver, MyAccountPageUI.ADD_WISHLIST_BUTTON);
		clickToElement(driver, MyAccountPageUI.ADD_WISHLIST_BUTTON);

	}

	public Object getMessageText() {
		waitForElementVisible(driver, MyAccountPageUI.NOTIFICATION_MESSAGE);
		return getElementText(driver, MyAccountPageUI.NOTIFICATION_MESSAGE);

	}

	public void clickOnDynamicHeadMenu(String menuName) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_HEADER_MENU, menuName);
		clickToElementByJS(driver, MyAccountPageUI.DYNAMIC_HEADER_MENU, menuName);

	}

	public void clickAddToCompareList(String productName) {
		waitForElementClickable(driver, MyAccountPageUI.ADD_T0_COMPARE_LIST_BUTTON, productName);
		clickToElement(driver, MyAccountPageUI.ADD_T0_COMPARE_LIST_BUTTON, productName);
	}

	public void clickOnDynamicUpperLink(String linkName) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_UPPER_MENU, linkName);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_UPPER_MENU, linkName);

	}

	public void selectItemByDynamicID(String itemSelected, String dynamicID) {
		selectItemInDefautlDropDown(driver, MyAccountPageUI.DYNAMIC_SELECT_BY_ID, itemSelected, dynamicID);

	}

	public void checkItemByDynamicText(String dynamicText) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_RADIO_BY_TEXT, dynamicText);
		checkToCheckboxOrRadio(driver, MyAccountPageUI.DYNAMIC_RADIO_BY_TEXT, dynamicText);

	}

	public void clickAddToCartButton() {
		waitForElementClickable(driver, MyAccountPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, MyAccountPageUI.ADD_TO_CART_BUTTON);

	}

	public Object getNumberProductOnHeader() {
		hoverMouseToElement(driver, MyAccountPageUI.NUMBER_PRODUCT_ON_HEADER);
		waitForElementVisible(driver, MyAccountPageUI.NUMBER_PRODUCT_ON_HEADER);
		return getElementText(driver, MyAccountPageUI.NUMBER_PRODUCT_ON_HEADER);
	}

	public Object getNumberProductInYourCart() {
		waitForElementVisible(driver, MyAccountPageUI.COUNT_PRODUCT);
		return getElementText(driver, MyAccountPageUI.COUNT_PRODUCT);
	}

	public Object getProductName() {
		waitForElementVisible(driver, MyAccountPageUI.PRODUCT_NAME_INFOR);
		return getElementText(driver, MyAccountPageUI.PRODUCT_NAME_INFOR);
	}

	public Object getProductAttribute(String attributeName) {
		waitForElementVisible(driver, MyAccountPageUI.PRODUCT_ATTRIBUTE, attributeName);
		return getElementText(driver, MyAccountPageUI.PRODUCT_ATTRIBUTE, attributeName);
	}

	public void unCheckItemByDynamicText(String dynamicText) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_RADIO_BY_TEXT, dynamicText);
		uncheckToCheckboxOrRadio(driver, MyAccountPageUI.DYNAMIC_RADIO_BY_TEXT, dynamicText);
	}

	public String getMoneyText() {
		waitForElementVisible(driver, MyAccountPageUI.PRODUCT_PRICE);
		return getElementText(driver, MyAccountPageUI.PRODUCT_PRICE);
	}

	public void enterNumberOfProducts(String valueInput) {
		waitForElementVisible(driver, MyAccountPageUI.INPUT_QUALITY);
		sendkeyToElement(driver, MyAccountPageUI.INPUT_QUALITY, valueInput);

	}

	public void clickToUpdateButton() {
		waitForElementClickable(driver, MyAccountPageUI.UPDATE_BUTTON);
		clickToElement(driver, MyAccountPageUI.UPDATE_BUTTON);

	}

	public void clickCloseMessageNotification() {
		waitForElementClickable(driver, MyAccountPageUI.CLOSE_BUTTON);
		clickToElement(driver, MyAccountPageUI.CLOSE_BUTTON);

	}

	public boolean isMessageDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void clickAddToCartButton(String productName) {
		waitForElementClickable(driver, MyAccountPageUI.ADD_TO_CART_BUTTON1, productName);
		clickToElement(driver, MyAccountPageUI.ADD_TO_CART_BUTTON1, productName);
		
	}

	public void clickAddToCartButton4() {
		waitForElementClickable(driver, MyAccountPageUI.ADD_TO_CART_BUTTON4);
		clickToElementByJS(driver, MyAccountPageUI.ADD_TO_CART_BUTTON4);
		
	}

	public boolean isMyAccountPageDisplayed() {
		waitForElementVisible(driver, MyAccountPageUI.MYACCOUNT_PAGE);
		return isElementDisplayed(driver, MyAccountPageUI.MYACCOUNT_PAGE);
	}

	public void clickDynamicLeftMenuLink(String dynamicText) {
		waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_LEFT_MENU, dynamicText);
		clickToElement(driver, MyAccountPageUI.DYNAMIC_LEFT_MENU, dynamicText);
		
	}

}
