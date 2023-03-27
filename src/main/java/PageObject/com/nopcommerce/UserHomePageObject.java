package PageObject.com.nopcommerce;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jsoup.parser.ParseError;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageUIs.com.nopcommerce.UserHomePageUI;
import commons.BasePage;
import commons.PageGeneratorManager;

public class UserHomePageObject extends BasePage {
	WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToPageLink(String linkPageName) {
		waitForElementClickable(driver, UserHomePageUI.DYNAMIC_PAGE_LINK, linkPageName);
		clickToElement(driver, UserHomePageUI.DYNAMIC_PAGE_LINK, linkPageName);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void hoverAndClickMenu(String menuName, String subMenuName) {
		hoverMouseToElement(driver, UserHomePageUI.DYNAMIC_MENU_NAME, menuName);
		clickToElement(driver, UserHomePageUI.DYNAMIC_SUBMENU_NAME, menuName, subMenuName);

	}

	public void selectItemInSortDropdown(String valueSelected) {
		selectItemInDefautlDropDown(driver, UserHomePageUI.SORT_DROPDOWN, valueSelected);

	}
	

	public boolean isSortProductNameByAscending() {
		ArrayList<String> productNameList = new ArrayList<>();

		List<WebElement> productNames = getListWebElement(driver, UserHomePageUI.PRODUCT_NAME);
		for (WebElement productName : productNames) {
			productNameList.add(productName.getText());
			System.out.println("Product Name UI:" + productName.getText());

		}

		ArrayList<String> productNameListSort = new ArrayList<>(productNameList);
		Collections.sort(productNameListSort);
		for (String productNameSort : productNameListSort) {
			System.out.println("Product Name Sort By Biding:" + productNameSort);
		}

		return productNameListSort.equals(productNameList);
	}

	public boolean isSortProductNameByDecending() {
		ArrayList<String> productNameList = new ArrayList<String>();

		List<WebElement> productNames = getListWebElement(driver, UserHomePageUI.PRODUCT_NAME);
		for (WebElement productName : productNames) {
			productNameList.add(productName.getText());
			System.out.println("Product Name UI:" + productName.getText());

		}

		ArrayList<String> productNameListSort = new ArrayList<>(productNameList);
		Collections.sort(productNameListSort);
		Collections.reverse(productNameListSort);
		for (String productNameSort : productNameListSort) {
			System.out.println("Product Name Sort DEC:" + productNameSort);

		}
		return productNameListSort.equals(productNameList);
	}

	public void selectNumbersOfProductPerPage(String valueSelected) {
		selectItemInDefautlDropDown(driver, UserHomePageUI.PAGING_NUMBER, valueSelected);

	}

	public boolean isNumberOfProductsLessThanOrEqualTo(int number) {
		waitForAllElementVisible(driver, UserHomePageUI.NUMBERS_OF_ITEMS);
		int i = getElementSize(driver, UserHomePageUI.NUMBERS_OF_ITEMS);
		if (i <= number) {
			return true;

		} else {

			return false;
		}
	}

	public boolean isPagingDisplayed() {
		waitForElementVisible(driver, UserHomePageUI.PAGING_DISPLAYED);
		return isElementDisplayed(driver, UserHomePageUI.PAGING_DISPLAYED);
	}

	public boolean isButtonPageDisplayed(String dynamicClassButton) {
		waitForElementVisible(driver, UserHomePageUI.DYNAMIC_PAGING_BUTTON, dynamicClassButton);
		return isElementDisplayed(driver, UserHomePageUI.DYNAMIC_PAGING_BUTTON, dynamicClassButton);
	}

	public void clickToDynamicPage(String pageNumber) {
		waitForElementClickable(driver, UserHomePageUI.DYNAMIC_PAGING_NUMBER, pageNumber);
		clickToElement(driver, UserHomePageUI.DYNAMIC_PAGING_NUMBER, pageNumber);

	}

	public boolean isSortPriceByAscending() {
		ArrayList<Float> productPriceList = new ArrayList<>();

		List<WebElement> productPrices = getListWebElement(driver, UserHomePageUI.PRODUCT_PRICE);
		for (WebElement productPrice : productPrices) {
			productPriceList.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
			System.out.println("Product price list UI:" + productPrice.getText());

		}

		ArrayList<Float> productPriceListSort = new ArrayList<>(productPriceList);

		Collections.sort(productPriceListSort);
		for (Float productPriceSort : productPriceListSort) {
			System.err.println("Product price sort ASC:" + productPriceSort);
		}
		return productPriceListSort.equals(productPriceList);
	}

	public boolean isSortPriceByDecending() {
		ArrayList<Float> productPriceList = new ArrayList<>();

		List<WebElement> productPrices = getListWebElement(driver, UserHomePageUI.PRODUCT_PRICE);
		for (WebElement productPrice : productPrices) {
			productPriceList.add(Float.parseFloat(productPrice.getText().replace("$", "").replace(",", "")));
			System.out.println("Product price list UI:" + productPrice.getText());

		}

		ArrayList<Float> productPriceListSort = new ArrayList<>();
		for (Float productPriceSort : productPriceList) {
			productPriceListSort.add(productPriceSort);

		}

		Collections.sort(productPriceListSort);
		for (Float productPriceSort : productPriceListSort) {
			System.err.println("Product price sort ASC:" + productPriceSort);
		}

		Collections.reverse(productPriceListSort);
		for (Float productPriceSort : productPriceListSort) {
			System.err.println("Product price sort DSC:" + productPriceSort);
		}
		return productPriceListSort.equals(productPriceList);
	}

}
