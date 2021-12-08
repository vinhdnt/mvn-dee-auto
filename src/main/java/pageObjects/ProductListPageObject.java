package pageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.dee.BasePageUI;
import pageUIs.dee.ProductListPageUI;

public class ProductListPageObject extends BasePage {
	private WebDriver driver;

	public ProductListPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getProductNameByIndex(String indexItem) {
		waitForElementVisible(driver, ProductListPageUI.DYNAMIC_PRODUCT_NAME, indexItem);
		return getElementText(driver, ProductListPageUI.DYNAMIC_PRODUCT_NAME, indexItem);
	}

	public boolean isDropdownSizeDisplayed() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if (getElementsSize(driver, ProductListPageUI.PRODUCT_SIZE_DROPDOWN) >= 1) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return true;
		} else {
			return false;
		}
	}

	public String getProductSize() {
		if (isDropdownSizeDisplayed()) {
			return getSelectedDropdown(driver, ProductListPageUI.PRODUCT_SIZE_DROPDOWN);
		} else {
			return getElementText(driver, ProductListPageUI.PRODUCT_ONE_SIZE);
		}

	}

	public String getProductColor(String indexItem) {
		waitForElementVisible(driver, ProductListPageUI.DYNAMIC_PRODUCT_COLOR, indexItem);
		return getElementText(driver, ProductListPageUI.DYNAMIC_PRODUCT_COLOR, indexItem);
	}

	public String getProductPriceByIndex(String indexItem) {
		waitForElementVisible(driver, ProductListPageUI.DYNAMIC_PRODUCT_PRICE, indexItem);
		return getElementText(driver, ProductListPageUI.DYNAMIC_PRODUCT_PRICE, indexItem);
	}

	public void clickOnShowPopupAddToCartProductByIndex(String indexItem) {
		waitForElementClickable(driver, ProductListPageUI.DYNAMIC_SHOW_EXPAND_PRODUCT_BUTTON, indexItem);
		clickOnElement(driver, ProductListPageUI.DYNAMIC_SHOW_EXPAND_PRODUCT_BUTTON, indexItem);
	}

	public void selectAvailableSize() {
		if (isDropdownSizeDisplayed()) {
			waitForElementVisible(driver, ProductListPageUI.PRODUCT_SIZE_DROPDOWN);
			List<WebElement> allSizeElements = getOptionsDropdown(driver, ProductListPageUI.PRODUCT_SIZE_DROPDOWN);
			allSizeElements.remove(0);
			for (WebElement sizeElement : allSizeElements) {
				if (sizeElement.isEnabled()) {
					selectDropdownByValue(driver, ProductListPageUI.PRODUCT_SIZE_DROPDOWN, sizeElement.getText());
					break;
				}
			}
		}
	}

	public void clickOnAddToCartButton() {
		waitForElementClickable(driver, ProductListPageUI.ADD2CART_BUTTON);
		clickOnElement(driver, ProductListPageUI.ADD2CART_BUTTON);
	}

	public String getProductAddedName() {
		waitForElementVisible(driver, BasePageUI.PRODUCT_ADDED_NAME);
		return getElementText(driver, BasePageUI.PRODUCT_ADDED_NAME);
	}

	public String getProductAddedSize() {
		waitForElementVisible(driver, BasePageUI.PRODUCT_ADDED_SIZE);
		return getElementText(driver, BasePageUI.PRODUCT_ADDED_SIZE);
	}

	public String getProductAddedPrice() {
		waitForElementVisible(driver, BasePageUI.PRODUCT_ADDED_PRICE);
		return getElementText(driver, BasePageUI.PRODUCT_ADDED_PRICE);
	}

	public String getProductPriceOnIconBasket() {
		waitForElementVisible(driver, BasePageUI.CART_TOTAL_PRICE_HEADER);
		return getElementText(driver, BasePageUI.CART_TOTAL_PRICE_HEADER);
	}

	public int getProductsListSize() {
		return getElementsSize(driver, ProductListPageUI.PRODUCTS_LIST);
	}

	public boolean isProductsListDisplayed() {
		return (getProductsListSize() >= 1);
	}

	public boolean isCartSlideLayerDisplayed() {
		waitForElementVisible(driver, BasePageUI.MINI_CART_SLIDE);
		return isElementDisplayed(driver, BasePageUI.MINI_CART_SLIDE);
	}

	public boolean isCartSlideLayerUndisplayed() {
		waitForElementInvisible(driver, BasePageUI.MINI_CART_SLIDE);
		return !isElementDisplayed(driver, BasePageUI.MINI_CART_SLIDE);
	}

	public String getCartItemCount() {
		waitForElementVisible(driver, BasePageUI.CART_ITEM_COUNT_HEADER);
		return getElementText(driver, BasePageUI.CART_ITEM_COUNT_HEADER);
	}

	public void clickOnCartIconHeader() {
		waitForElementClickable(driver, BasePageUI.CART_ICON_HEADER);
		clickOnElement(driver, BasePageUI.CART_ICON_HEADER);
	}

	public void clickOnRemoveIconByIndex(String indexItem) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_REMOVE_ICON_CART_SLIDE, indexItem);
		clickOnElement(driver, BasePageUI.DYNAMIC_REMOVE_ICON_CART_SLIDE, indexItem);

	}

	public boolean isCartItemCountBlank() {
		waitForElementInvisible(driver, BasePageUI.CART_ITEM_COUNT_HEADER);
		if (!isElementDisplayed(driver, BasePageUI.CART_ITEM_COUNT_HEADER)) {
			return true;
		} else {
			return false;
		}
	}

	public ProductDetailPageObject clickOnProductName(String indexItem) {
		waitForElementVisible(driver, ProductListPageUI.DYNAMIC_PRODUCT_NAME, indexItem);
		clickOnElement(driver, ProductListPageUI.DYNAMIC_PRODUCT_NAME, indexItem);
		return PageGeneratorManager.getProductDetailPage(driver);
	}

}
