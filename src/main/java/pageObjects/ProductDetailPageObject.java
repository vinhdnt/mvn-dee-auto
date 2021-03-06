package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.dee.BasePageUI;
import pageUIs.dee.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage {
	private WebDriver driver;

	public ProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDropdownSizeDisplayed() {
		overrideGlobalTimeOut(driver, shortTimeOut);
		int dropDownSize = getElementsSize(driver, ProductDetailPageUI.PRODUCT_SIZE_PARENT);
		overrideGlobalTimeOut(driver, longTimeOut);
		if (dropDownSize >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public String getProductSize() {
		if (isDropdownSizeDisplayed()) {
			return getElementText(driver, ProductDetailPageUI.SELECTED_SIZE_DROPDOWN);
		} else {
			return getElementText(driver, ProductDetailPageUI.PRODUCT_ONE_SIZE);
		}

	}

	public void selectAvailableSizeDropdownByIndex(int indexSize) {
		if (isDropdownSizeDisplayed()) {
			waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_SIZE_PARENT);
			selectSizeInPDPByIndex(driver, ProductDetailPageUI.PRODUCT_SIZE_PARENT, ProductDetailPageUI.SIZE_AVAILABLE_LIST, indexSize);
		}
	}

	public void clickOnAddToCartButton() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD2CART_BUTTON);
		clickOnElement(driver, ProductDetailPageUI.ADD2CART_BUTTON);
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

	public String getProductName() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_NAME);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_NAME);
	}

	public String getProductPrice() {
		waitForElementVisible(driver, ProductDetailPageUI.PRODUCT_PRICE);
		return getElementText(driver, ProductDetailPageUI.PRODUCT_PRICE);
	}

	public void closeCartSlideLayer() {
		sleepInSecond(3);
		if (isElementDisplayed(driver, BasePageUI.MINI_CART_SLIDE)) {
			clickOnElement(driver, BasePageUI.CLOSE_CART_SLIDE_BUTTON);
		}
	}

	public CheckOutPageObject clickOnGoToCartButton() {
		waitForElementVisible(driver, BasePageUI.PRODUCT_ADDED_PANEL);
		waitForElementClickable(driver, BasePageUI.GO_TO_CART_BUTTON);
		clickOnElement(driver, BasePageUI.GO_TO_CART_BUTTON);
		return PageGeneratorManager.getCheckOutPage(driver);
	}

    public void clickOnAdd2WishList() {
		waitForElementVisible(driver, ProductDetailPageUI.ADD2WISHLIST_BUTTON);
		clickOnElement(driver, ProductDetailPageUI.ADD2WISHLIST_BUTTON);
    }

	public boolean isAdd2WishListMsgPDLDisplayed() {
		waitForElementVisible(driver, ProductDetailPageUI.ADD2WISHLIST_SUCCESSFUL_MSG);
		return isElementDisplayed(driver, ProductDetailPageUI.ADD2WISHLIST_SUCCESSFUL_MSG);
	}


	public boolean isAdd2WishListMsgPDPDisplayed() {
		waitForElementVisible(driver, ProductDetailPageUI.ADD2WISHLIST_SUCCESSFUL_MSG_PDP);
		return isElementDisplayed(driver, ProductDetailPageUI.ADD2WISHLIST_SUCCESSFUL_MSG_PDP);
	}

	public int getWishListItemCount() {
		waitForElementVisible(driver, BasePageUI.WISH_LIST_ITEM_COUNT);
		return Integer.parseInt(getElementText(driver, BasePageUI.WISH_LIST_ITEM_COUNT));
	}
}