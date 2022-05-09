package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.dee.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickOnRegisterButton() {
		waitForElementVisible(driver, LoginPageUI.REGISTER_BUTTON);
		clickOnElement(driver, LoginPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputEmailTextbox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputPasswordTextbox(String passWord) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passWord);

	}

	public void loginWithUserNamePassword(String email, String passWord) {
		inputEmailTextbox(email);
		inputPasswordTextbox(passWord);
		clickOnLoginButton();
	}

	public MyAccountPageObject clickOnLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_SUBMIT_BUTTON);
		clickOnElement(driver, LoginPageUI.LOGIN_SUBMIT_BUTTON);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

}
