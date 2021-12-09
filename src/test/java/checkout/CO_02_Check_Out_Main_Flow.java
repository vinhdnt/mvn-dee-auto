package checkout;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.CheckOutPageObject;
import pageObjects.HomePageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ProductListPageObject;

public class CO_02_Check_Out_Main_Flow extends BaseTest {
	WebDriver driver;
	BasePage basepage;
	private String email, password, firstName, lastName, streetInvoiceAddress, houseNumberInvoiceAddress, cityInvoiceAddress, paymentMethodSelected;
	private String emailLogin;
	private String zipCode[][] = {{"de", "12345"}, { "at", "1234"}, { "ch", "1235"}, { "fr", "1235"}, { "nl", "1234"}};
	
	
	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void initBrowser(String browserName, String url) {
		log.info("Pre-condition - Step 01:Init browser '" + browserName +"' And go to Homepage to: " + url);
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);
		email = getRandomEmail();
		password = "123123123";
		firstName = "FNtest";
		lastName = "LNtest";
		streetInvoiceAddress = "Streettest";
		houseNumberInvoiceAddress = "1";
		cityInvoiceAddress = "Citytest"; 
	}

	@Test
	public void TC_01_Check_Out_As_New_Register_User() {
		log.info("TC_01_Check_Out_As_New_Register_User - Step 2: Click on accept 'Cookie Consent'");
		homePage.clickOnAcceptCookieConsentButton();

		log.info("TC_01_Check_Out_As_New_Register_User - Step 3: Click on 'second-category'");
		productListPage = homePage.clickOnCategoryHeaderByIndex("2");
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 4: Check and close 'promo layer'");
		productListPage.checkAndClosePromoLayer(driver);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 5: Click on 'first-product-name'");
		productDetailPage = productListPage.clickOnProductName("1");
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 6: Select 'first-available-size'");
		productDetailPage.selectAvailableSizeDropdownByIndex(0);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 7: Click on 'Add2Cart' button");
		productDetailPage.clickOnAddToCartButton();
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 8: Click on 'Go to step 1' button");
		checkOutPage = productDetailPage.clickOnGoToCartButton();
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 9: Verify Product-added displayed in cart page");
		verifyTrue(checkOutPage.isProductInCartPageDisplayed());
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 10: Click on 'Go to step 2' button");
		checkOutPage.clickOnBasketSubmitButton();
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 11: Input First Name textbox at step 2 checkout");
		checkOutPage.inputFirstNameTextbox(firstName);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 12: Input Last Name textbox at step 2 checkout");
		checkOutPage.inputLastNameTextbox(lastName);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 13: Input Email textbox at step 2 checkout");
		checkOutPage.inputEmailTextbox(email);
		
		emailLogin = email;
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 14: Input Password textbox at step 2 checkout");
		checkOutPage.inputPasswordTextbox(password);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 15: Select birthday at step 2 checkout");
		checkOutPage.selectDateOfBirth("1");		
		checkOutPage.selectMonthOfBirth("1");
		checkOutPage.selectYearOfBirth("1977");
		
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 16: Input Street Invoice Address textbox at step 2 checkout");
		checkOutPage.inputStreetInvoiceAddress(streetInvoiceAddress);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 17: Input House Number Invoice Address textbox at step 2 checkout");
		checkOutPage.inputHouseNumberInvoiceAddress(houseNumberInvoiceAddress);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 18: Input Zipcode Invoice Address textbox at step 2 checkout");
		checkOutPage.inputZipCodeInvoiceAddress(zipCode);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 19: Input City Invoice Address textbox at step 2 checkout");
		checkOutPage.inputCityInvoiceAddress(cityInvoiceAddress);
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 20: Check on Confirmation checkbox at step 2 checkout");
		checkOutPage.checkConfirmationCheckbox();
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 21: Click on 'Go to step 3' button");
		checkOutPage.clickOnSubmitStep2Button();
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 22: Verify Payment method is displayed at step 3 checkout");
		verifyTrue(checkOutPage.isPaymentMethodsDispleyed());
		
		paymentMethodSelected = checkOutPage.GetFirstPaymentMethodName("1");
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 23: Select first-Payment-method checkbox at step 3 checkout: "+paymentMethodSelected);
		checkOutPage.selectPaymentMethodByIndex("1");
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 24: Click on 'Go to step 4' button");
		checkOutPage.clickOnSubmitStep3Button();
		
		log.info("TC_01_Check_Out_As_New_Register_User - Step 25: Verify Product-added displayed at step 4 checkout");
		verifyTrue(checkOutPage.isProductInCartPageDisplayed());
		
	}

	@Test
	public void TC_02_Check_Out_As_Guest_User() {
		log.info("TC_02_Check_Out_As_Guest_User - Step 2: Click on accept 'Cookie Consent'");
		homePage.clickOnAcceptCookieConsentButton();
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 3: Click on 'second-category'");
		productListPage = homePage.clickOnCategoryHeaderByIndex("2");
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 4: Check and close 'promo layer'");
		productListPage.checkAndClosePromoLayer(driver);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 5: Click on 'second-product-name'");
		productDetailPage = productListPage.clickOnProductName("2");
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 6: Select 'first-available-size'");
		productDetailPage.selectAvailableSizeDropdownByIndex(0);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 7: Click on 'Add2Cart' button");
		productDetailPage.clickOnAddToCartButton();
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 8: Click on 'Go to step 1' button");
		checkOutPage = productDetailPage.clickOnGoToCartButton();
	
		log.info("TC_02_Check_Out_As_Guest_User - Step 9: Verify Product-added displayed in cart page");
		verifyTrue(checkOutPage.isProductInCartPageDisplayed());
	
		log.info("TC_02_Check_Out_As_Guest_User - Step 10: Click on 'Go to step 2' button");
		checkOutPage.clickOnBasketSubmitButton();
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 11: Click on Check Out As Guest Checkbox");
		checkOutPage.clickOnCheckOutAsGuestCheckBox();
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 12: Input First Name textbox at step 2 checkout");
		checkOutPage.inputFirstNameTextbox(firstName);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 13: Input Last Name textbox at step 2 checkout");
		checkOutPage.inputLastNameTextbox(lastName);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 14: Input Email textbox at step 2 checkout");
		checkOutPage.inputEmailTextbox(email);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 15: Select birthday at step 2 checkout");
		checkOutPage.selectDateOfBirth("1");
		checkOutPage.selectMonthOfBirth("1");
		checkOutPage.selectYearOfBirth("1977");
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 16: Input Street Invoice Address textbox at step 2 checkout");
		checkOutPage.inputStreetInvoiceAddress(streetInvoiceAddress);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 17: Input House Number Invoice Address textbox at step 2 checkout");
		checkOutPage.inputHouseNumberInvoiceAddress(houseNumberInvoiceAddress);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 18: Input Zipcode Invoice Address textbox at step 2 checkout");
		checkOutPage.inputZipCodeInvoiceAddress(zipCode);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 19: Input City Invoice Address textbox at step 2 checkout");
		checkOutPage.inputCityInvoiceAddress(cityInvoiceAddress);
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 20: Check on Confirmation checkbox at step 2 checkout");
		checkOutPage.checkConfirmationCheckbox();
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 21: Click on 'Go to step 3' button");
		checkOutPage.clickOnSubmitStep2Button();
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 22: Verify Payment method is displayed at step 3 checkout");
		verifyTrue(checkOutPage.isPaymentMethodsDispleyed());
		
		paymentMethodSelected = checkOutPage.GetFirstPaymentMethodName("1");
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 23: Select first-Payment-method checkbox at step 3 checkout: "+ paymentMethodSelected);
		checkOutPage.selectPaymentMethodByIndex("1");
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 24: Click on 'Go to step 4' button");
		checkOutPage.clickOnSubmitStep3Button();
		
		log.info("TC_02_Check_Out_As_Guest_User - Step 25: Verify Product-added displayed at step 4 checkout");
		verifyTrue(checkOutPage.isProductInCartPageDisplayed());
	}
	
	@Test
	public void TC_03_Check_Out_As_User_Log_In_At_Step_2() {
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 2: Click on accept 'Cookie Consent'");
		homePage.clickOnAcceptCookieConsentButton();

		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 3: Click on 'second-category'");
		productListPage = homePage.clickOnCategoryHeaderByIndex("2");
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 4: Check and close 'promo layer'");
		productListPage.checkAndClosePromoLayer(driver);
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 5: Click on 'third-product-name'");
		productDetailPage = productListPage.clickOnProductName("3");
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 6: Select 'first-available-size'");
		productDetailPage.selectAvailableSizeDropdownByIndex(0);
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 7: Click on 'Add2Cart' button");
		productDetailPage.clickOnAddToCartButton();

		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 8: Click on 'Go to step 1' button");
		checkOutPage = productDetailPage.clickOnGoToCartButton();
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 9: Verify Product-added displayed in cart page");
		verifyTrue(checkOutPage.isProductInCartPageDisplayed());
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 10: Click on 'Go to step 2' button");
		checkOutPage.clickOnBasketSubmitButton();
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 11: Input Email Login textbox at step 2 checkout: " + emailLogin);
		checkOutPage.inputEmailLoginTextbox(emailLogin);
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 12: Input Password Login textbox at step 2 checkout: " + password);
		checkOutPage.inputPasswordLoginTextbox(password);
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 13: Click on 'Login submit' button");
		checkOutPage.clickOnLoginSubmitButton();
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 14: Verify Payment method is displayed at step 3 checkout");
		verifyTrue(checkOutPage.isPaymentMethodsDispleyed());
		
		paymentMethodSelected = checkOutPage.GetFirstPaymentMethodName("1");
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 15: Select first-Payment-method checkbox at step 3 checkout: "+paymentMethodSelected);
		checkOutPage.selectPaymentMethodByIndex("1");
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 16: Click on 'Go to step 4' button");
		checkOutPage.clickOnSubmitStep3Button();
		
		log.info("TC_03_Check_Out_As_User_Log_In_At_Step_2 - Step 17: Verify Product-added displayed at step 4 checkout");
		verifyTrue(checkOutPage.isProductInCartPageDisplayed());
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		log.info("Close browser");
		cleanBrowserAndDriver();
	}

	HomePageObject homePage;
	ProductListPageObject productListPage;
	ProductDetailPageObject productDetailPage;
	CheckOutPageObject checkOutPage;
}
