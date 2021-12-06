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
	

}
