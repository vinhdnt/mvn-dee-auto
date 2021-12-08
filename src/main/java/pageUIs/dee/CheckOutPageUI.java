package pageUIs.dee;

public class CheckOutPageUI {
	public static final String PRODUCT_INFOR_LINE = "//div[@class='row product-info-line']";
	public static final String SUBMIT_STEP_1_BUTTON = "//button[@id='basket_sbm_btn1']";
	
	public static final String GENDER_STEP_2_DROPDOWN = "//select[@id='cust_salutation']";
	public static final String FIRST_NAME_STEP_2_TEXTBOX = "//input[@id='cust_firstName']";
	public static final String LAST_NAME_STEP_2_TEXTBOX = "//input[@id='cust_lastName']";
	public static final String EMAIL_REGISTER_STEP_2_TEXTBOX = "//input[@id='cust_email']";
	public static final String PASSWORD_REGISTER_STEP_2_TEXTBOX = "//input[@id='password']";
	public static final String INVOICE_ADDRESS_STREET_STEP_2_TEXTBOX = "//input[@id='invoiceAddress_street']";
	public static final String INVOICE_ADDRESS_HOUSE_NUMBER_STEP_2_TEXTBOX = "//input[@id='invoiceAddress_houseNumber']";
	public static final String INVOICE_ADDRESS_ZIPCODE_STEP_2_TEXTBOX = "//input[@id='invoiceAddress_zip']";
	public static final String INVOICE_ADDRESS_CITY_STEP_2_TEXTBOX = "//input[@id='invoiceAddress_city']";
	public static final String CONFIRMATION_STEP_2_CHECKBOX = "//input[@id='Agree']";
	public static final String BIRTH_DAY_STEP_2_DROPDOWN = "//select[@id='birthDay']";
	public static final String BIRTH_MONTH_STEP_2_DROPDOWN = "//select[@id='birthMonth']";
	public static final String BIRTH_YEAR_STEP_2_DROPDOWN = "//select[@id='birthYear']";
	public static final String SUBMIT_STEP_2_BUTTON = "//input[@id='checkout_submit_btn']";
	public static final String PASSWORD_CHECKBOX = "//input[@id='passwordFieldCheckbox']";
	public static final String EMAIL_LOGIN_TEXTBOX = "//input[@id='qs_login']";
	public static final String PASSWORD_LOGIN_TEXTBOX = "//input[@id='qs_password']";
	public static final String LOGIN_SUBMIT_BUTTON = "//input[@id='checkoutLoginSubmit']";

	public static final String PAYMENT_METHODS = "//div[@class='panel payment-method']";
	public static final String DYNAMIC_PAYMENT_METHOD_BY_INDEX = "//div[@class='panel payment-method'][%s]";
	public static final String DYNAMIC_PAYMENT_METHOD_BY_NAME = "//label[contains(text(),'%s')]";
	public static final String SUBMIT_STEP_3_BUTTON = "//input[@id='payment_submit_btn']";
	
}
