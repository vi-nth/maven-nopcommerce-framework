package PageUIs.com.nopcommerce;

import org.openqa.selenium.WebDriver;

import PageObject.com.nopcommerce.MyAccountPageObject;
import PageObject.com.nopcommerce.ShopingCartPageObject;
import commons.BasePage;
import commons.PageGeneratorManager;

public class WishlistPageObject extends BasePage {
	WebDriver diver;

	public WishlistPageObject(WebDriver diver) {
		this.diver = diver;
	}

	public boolean isProductAddedSuccessfully(String productName) {
		waitForElementVisible(diver, WishlistPageUI.PRODUCT_IN_WISHLIST,productName);
		return isElementDisplayed(diver, WishlistPageUI.PRODUCT_IN_WISHLIST, productName);
	}

	public void clickOnWishlistUrlForSharingLink() {
		waitForElementClickable(diver, WishlistPageUI.SHARE_LINK);
		clickToElement(diver, WishlistPageUI.SHARE_LINK);
	}

	public boolean isWishlistUserAccountDisplayed() {
		waitForElementVisible(diver, WishlistPageUI.MY_WISHLIST);
		return isElementDisplayed(diver, WishlistPageUI.MY_WISHLIST);
	}

	public boolean isProductsSharingDisplayed(String productName) {
		waitForElementVisible(diver, WishlistPageUI.PRODUCT_IN_WISHLIST,productName);
		return isElementDisplayed(diver, WishlistPageUI.PRODUCT_IN_WISHLIST, productName);
	}

	public void checkToProductsAddToCart(String productName) {
		waitForElementClickable(diver,WishlistPageUI.CHECKBOX_ADD_TO_CART, productName);
		checkToCheckboxOrRadio(diver, WishlistPageUI.CHECKBOX_ADD_TO_CART, productName);
	}

	public ShopingCartPageObject clickOnAddToCartButton() {
		waitForElementClickable(diver, WishlistPageUI.ADD_TO_CART_BUTTON);
		clickToElement(diver, WishlistPageUI.ADD_TO_CART_BUTTON);
		return PageGeneratorManager.getShopingCart(diver);
	}

	public boolean isWhislistEmpty() {
		waitForElementVisible(diver, WishlistPageUI.EMPTY_WISGLIST);
		return isElementDisplayed(diver,WishlistPageUI.EMPTY_WISGLIST);
	}

	public MyAccountPageObject clickAndHoverMenu(String menuName, String subMenuName) {
		hoverMouseToElement(diver, WishlistPageUI.DYNAMIC_MENU_NAME,menuName);
		clickToElementByJS(diver, WishlistPageUI.DYNAMIC_SUBMENU_NAME,menuName, subMenuName);
		return PageGeneratorManager.getMyAccountPage(diver);
	}

	public boolean isProductAddedSuccessfully() {
		waitForElementVisible(diver, WishlistPageUI.PRODUCT_IN_WISHLIST);
		return isElementDisplayed(diver, WishlistPageUI.PRODUCT_IN_WISHLIST);
	}

	public void clickToRemoveProduct(String productName) {
		waitForElementClickable(diver, WishlistPageUI.REMOVE_PRODUCT, productName);
		clickToElement(diver, WishlistPageUI.REMOVE_PRODUCT, productName);
	}

	public void clickOnDynamicHeadMenu(String dynamicClass) {
		waitForElementClickable(diver, WishlistPageUI.DYNAMIC_HEADER_MENU, dynamicClass);
		clickToElement(diver, WishlistPageUI.DYNAMIC_HEADER_MENU, dynamicClass);
		
	}

	public void clickBackPage() {
		backToPage(diver);
		
	}
	

}
