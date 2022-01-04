package pageObjects;

import java.util.List;
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
		overrideGlobalTimeOut(driver, shortTimeOut);
		int dropdownSize = getElementsSize(driver, ProductListPageUI.PRODUCT_SIZE_DROPDOWN);
		overrideGlobalTimeOut(driver, longTimeOut);
		return  (dropdownSize >= 1);
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
		return !isElementDisplayed(driver, BasePageUI.CART_ITEM_COUNT_HEADER);
	}

	public ProductDetailPageObject clickOnProductName(String indexItem) {
		waitForElementVisible(driver, ProductListPageUI.DYNAMIC_PRODUCT_NAME, indexItem);
		clickOnElement(driver, ProductListPageUI.DYNAMIC_PRODUCT_NAME, indexItem);
		return PageGeneratorManager.getProductDetailPage(driver);
	}

	public void clickOnWishListIconByIndex(String indexItem) {
		waitForAllElementsVisible(driver, ProductListPageUI.DYNAMIC_WISH_LIST_ICON, indexItem);
		clickOnElement(driver, ProductListPageUI.DYNAMIC_WISH_LIST_ICON, indexItem);
	}

	public boolean isWishListIconSelected(String indexItem) {
		waitForElementVisible(driver, ProductListPageUI.DYNAMIC_WISH_LIST_SELECTED_ICON, indexItem);
		return isElementDisplayed(driver, ProductListPageUI.DYNAMIC_WISH_LIST_SELECTED_ICON, indexItem);
	}

	public boolean isWishListIconUnselected(String indexItem) {
		waitForElementVisible(driver, ProductListPageUI.DYNAMIC_WISH_LIST_ICON, indexItem);
		return isElementDisplayed(driver, ProductListPageUI.DYNAMIC_WISH_LIST_ICON, indexItem);
	}

	public MyAccountPageObject clickOnWishListIconOnHeader() {
		waitForElementVisible(driver, BasePageUI.WISH_LIST_ICON_HEADER);
		clickOnElement(driver, BasePageUI.WISH_LIST_ICON_HEADER);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

	public boolean isAdd2WishListMsgDisplayed() {
		waitForElementVisible(driver, ProductListPageUI.ADD2WISHLIST_MSG);
		return isElementDisplayed(driver, ProductListPageUI.ADD2WISHLIST_MSG);
	}


	public int getWishListItemCount() {
		sleepInSecond(2);
		waitForElementVisible(driver, BasePageUI.WISH_LIST_ITEM_COUNT);
		return Integer.parseInt(getElementText(driver, BasePageUI.WISH_LIST_ITEM_COUNT));
	}

	public void clickOnWishListSelectedIconByIndex(String indexItem) {
		overrideGlobalTimeOut(driver, 1);
		int refreshCount = 1;
		while (refreshCount <= 10 && (getElementsSize(driver, ProductListPageUI.DYNAMIC_WISH_LIST_SELECTED_ICON, indexItem) == 0)){
			refreshCurrentPage(driver);
			refreshCount++;
		}
		overrideGlobalTimeOut(driver, 60);
		waitForAllElementsVisible(driver, ProductListPageUI.DYNAMIC_WISH_LIST_SELECTED_ICON, indexItem);
		clickOnElement(driver, ProductListPageUI.DYNAMIC_WISH_LIST_SELECTED_ICON, indexItem);
	}
}
