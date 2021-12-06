package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.dee.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectGenderDropdown() {
		waitForElementVisible(driver, RegisterPageUI.GENDER_DROPDOWN);
		selectDropdownByValue(driver, RegisterPageUI.GENDER_DROPDOWN, "MR");
	}

	public void inputFirstNameTextbox(String string) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, string);
	}

	public void inputLastNameTextbox(String string) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, string);

	}

	public void inputEmailTextbox(String string) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, string);

	}

	public void inputPasswordTextbox(String string) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, string);

	}

	public void checkConfirmationCheckbox() {
		checkToCheckboxByJs(driver, RegisterPageUI.CONFIRMATION_CHECKBOX);
	}

	public MyAccountPageObject clickOnRegisterSubmitButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_SUBMIT_BUTTON);
		clickOnElement(driver, RegisterPageUI.REGISTER_SUBMIT_BUTTON);
		return PageGeneratorManager.getMyAccountPage(driver);

	}

}
