package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.dee.CheckOutPageUI;

public class CheckOutPageObject extends BasePage {
	private WebDriver driver;

	public CheckOutPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getProductsListSize() {
		waitForAllElementsVisible(driver, CheckOutPageUI.PRODUCT_INFOR_LINE);
		return getElementsSize(driver, CheckOutPageUI.PRODUCT_INFOR_LINE);
	}

	public boolean isProductInCartPageDisplayed() {
		return (getProductsListSize() >= 1);
	}

	public void clickOnBasketSubmitButton() {
		waitForElementClickable(driver, CheckOutPageUI.SUBMIT_STEP_1_BUTTON);
		clickOnElement(driver, CheckOutPageUI.SUBMIT_STEP_1_BUTTON);
	}

	public void inputFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, CheckOutPageUI.FIRST_NAME_STEP_2_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.FIRST_NAME_STEP_2_TEXTBOX, firstName);
	}

	public void inputLastNameTextbox(String lastName) {
		waitForElementVisible(driver, CheckOutPageUI.LAST_NAME_STEP_2_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.LAST_NAME_STEP_2_TEXTBOX, lastName);
	}

	public void inputEmailTextbox(String email) {
		waitForElementVisible(driver, CheckOutPageUI.EMAIL_REGISTER_STEP_2_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.EMAIL_REGISTER_STEP_2_TEXTBOX, email);
	}

	public void inputPasswordTextbox(String password) {
		waitForElementVisible(driver, CheckOutPageUI.PASSWORD_REGISTER_STEP_2_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.PASSWORD_REGISTER_STEP_2_TEXTBOX, password);
	}

	public void selectDateOfBirth(String birthDay) {
		selectDropdownByValue(driver, CheckOutPageUI.BIRTH_DAY_STEP_2_DROPDOWN, birthDay);
	}

	public void selectMonthOfBirth(String birthMonth) {
		selectDropdownByValue(driver, CheckOutPageUI.BIRTH_MONTH_STEP_2_DROPDOWN, birthMonth);
	}

	public void selectYearOfBirth(String birthYear) {
		selectDropdownByValue(driver, CheckOutPageUI.BIRTH_YEAR_STEP_2_DROPDOWN, birthYear);
	}

	public void inputStreetInvoiceAddress(String streetInvoiceAddress) {
		waitForElementVisible(driver, CheckOutPageUI.INVOICE_ADDRESS_STREET_STEP_2_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_STREET_STEP_2_TEXTBOX, streetInvoiceAddress);
	}

	public void inputHouseNumberInvoiceAddress(String houseNumberInvoiceAddress) {
		waitForElementVisible(driver, CheckOutPageUI.INVOICE_ADDRESS_HOUSE_NUMBER_STEP_2_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_HOUSE_NUMBER_STEP_2_TEXTBOX, houseNumberInvoiceAddress);

	}

	public void inputZipCodeInvoiceAddress(String[][] zipCode) {
		waitForElementVisible(driver, CheckOutPageUI.INVOICE_ADDRESS_ZIPCODE_STEP_2_TEXTBOX);
		switch (getCurrentlyTenant(driver)) {
		case "de":
			sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_ZIPCODE_STEP_2_TEXTBOX, zipCode[0][1]);
			break;
		case "at":
			sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_ZIPCODE_STEP_2_TEXTBOX, zipCode[1][1]);
			break;
		case "ch":
			sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_ZIPCODE_STEP_2_TEXTBOX, zipCode[2][1]);
			break;
		case "fr":
			sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_ZIPCODE_STEP_2_TEXTBOX, zipCode[3][1]);
			break;
		case "nl":
			sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_ZIPCODE_STEP_2_TEXTBOX, zipCode[4][1]);
			break;
		case "be":
			sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_ZIPCODE_STEP_2_TEXTBOX, zipCode[5][1]);
			break;
		}
	}

	public void inputCityInvoiceAddress(String cityInvoiceAddress) {
		waitForElementVisible(driver, CheckOutPageUI.INVOICE_ADDRESS_CITY_STEP_2_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.INVOICE_ADDRESS_CITY_STEP_2_TEXTBOX, cityInvoiceAddress);
	}

	public void checkConfirmationCheckbox() {
		checkToCheckboxByJs(driver, CheckOutPageUI.CONFIRMATION_STEP_2_CHECKBOX);
	}

	public void clickOnSubmitStep2Button() {
		waitForElementClickable(driver, CheckOutPageUI.SUBMIT_STEP_2_BUTTON);
		clickOnElement(driver, CheckOutPageUI.SUBMIT_STEP_2_BUTTON);
	}

	public boolean isPaymentMethodsDispleyed() {
		waitForAllElementsVisible(driver, CheckOutPageUI.PAYMENT_METHODS);
		return (getElementsSize(driver, CheckOutPageUI.PAYMENT_METHODS) >= 1);
	}

	public void selectPaymentMethodByIndex(String paymentIndex) {
		waitForElementClickable(driver, CheckOutPageUI.DYNAMIC_PAYMENT_METHOD_BY_INDEX, paymentIndex);
		clickOnElement(driver, CheckOutPageUI.DYNAMIC_PAYMENT_METHOD_BY_INDEX, paymentIndex);
	}

	public void clickOnSubmitStep3Button() {
		waitForElementClickable(driver, CheckOutPageUI.SUBMIT_STEP_3_BUTTON);
		clickOnElement(driver, CheckOutPageUI.SUBMIT_STEP_3_BUTTON);
	}

	public void clickOnCheckOutAsGuestCheckBox() {
		waitForElementPresentInDOM(driver, CheckOutPageUI.PASSWORD_CHECKBOX);
		removeAttributeInDOM(driver, CheckOutPageUI.PASSWORD_CHECKBOX, "style");
		clickOnElement(driver, CheckOutPageUI.PASSWORD_CHECKBOX);
	}

	public void inputEmailLoginTextbox(String emailLogin) {
		waitForElementVisible(driver, CheckOutPageUI.EMAIL_LOGIN_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.EMAIL_LOGIN_TEXTBOX, emailLogin);
	}

	public void inputPasswordLoginTextbox(String password) {
		waitForElementVisible(driver, CheckOutPageUI.PASSWORD_LOGIN_TEXTBOX);
		sendKeyToElement(driver, CheckOutPageUI.PASSWORD_LOGIN_TEXTBOX, password);
	}

	public void clickOnLoginSubmitButton() {
		waitForElementClickable(driver, CheckOutPageUI.LOGIN_SUBMIT_BUTTON);
		clickOnElement(driver, CheckOutPageUI.LOGIN_SUBMIT_BUTTON);
	}

	public String GetFirstPaymentMethodName(String paymentIndex) {
		waitForElementVisible(driver, CheckOutPageUI.DYNAMIC_PAYMENT_METHOD_BY_INDEX, paymentIndex);
		return getElementText(driver, CheckOutPageUI.DYNAMIC_PAYMENT_METHOD_BY_INDEX, paymentIndex);
	}

	

}
