package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.dee.BasePageUI;
import pageUIs.dee.MyAccountPageUI;

public class MyAccountPageObject extends BasePage {
	private WebDriver driver;

	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

    public String getProductAddedWishListNameByIndex(String indexItem) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_PRODUCT_ADDED_NAME_IN_WISH_LIST , indexItem);
		return getElementText(driver, MyAccountPageUI.DYNAMIC_PRODUCT_ADDED_NAME_IN_WISH_LIST , indexItem);
    }

    public boolean isRegisterSuccessMsgDisplayed() {
		waitForElementVisible(driver, MyAccountPageUI.REGISTER_SUCCESS_MSG);
		return isElementDisplayed(driver, MyAccountPageUI.REGISTER_SUCCESS_MSG);
		
	}

	public String getUserFullNameGreeting() {
		waitForElementVisible(driver, MyAccountPageUI.USER_FULL_NAME_GREETING);
		return getElementText(driver, MyAccountPageUI.USER_FULL_NAME_GREETING);
	}
	
	public boolean isLogoutFieldOnHeaderDisplayed() {
		return isElementDisplayed(driver, BasePageUI.LOGOUT_FIELD);
	}


	public void removeWishListItemByIndex(String indexItem) {
		waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_REMOVE_PRODUCT_ADDED_ICON_IN_WISH_LIST , indexItem);
		clickOnElement(driver, MyAccountPageUI.DYNAMIC_REMOVE_PRODUCT_ADDED_ICON_IN_WISH_LIST , indexItem);
	}


	public boolean isNoItemWishListMsgDisplayed() {
		waitForElementVisible(driver, MyAccountPageUI.NO_ITEM_WISH_LIST_MSG);
		return isElementDisplayed(driver, MyAccountPageUI.NO_ITEM_WISH_LIST_MSG);
	}
}
