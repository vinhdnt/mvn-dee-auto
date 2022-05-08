package user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_01_Register_Login extends BaseTest {
	WebDriver driver;
	BasePage basepage;
	private String firstName, lastName;
	public static String EMAIL, PASSWORD;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void initBrowser(String browserName, String url) {
		log.info("Pre-condition - Step 01:Init browser and go to Homepage to: " + url);
		driver = getBrowserDriver(browserName, url);
		log.info("Browser name and version is: " + getBrowserInitName());
		homePage = PageGeneratorManager.getHomePage(driver);
		EMAIL = getRandomEmail();
		PASSWORD = "123123123";
		firstName = "FNtest";
		lastName = "LNtest";
	}

	@Test
	public void TC_01_Register_With_Valid_User_Name_Password() {		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 2: Verify 'Wordpress page home page' is displayed");
		verifyTrue(homePage.isWordPressDisplayed());
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 3: Verify 'Cookie consent' is displayed");
		verifyTrue(homePage.isCookieConsentDisplayed());
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 4: Click on 'accept Cookie Consent' button");
		homePage.clickOnAcceptCookieConsentButton();

		log.info("TC_01_Register_With_Valid_User_Name_Password - Check and close 'promo layer'");
		homePage.checkAndClosePromoLayer(driver);

		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 6: Click on 'My account' on header");
		loginPage = homePage.clickOnMyAccountOnHeader(driver);

		log.info("TC_01_Register_With_Valid_User_Name_Password - Check and close 'promo layer'");
		loginPage.checkAndClosePromoLayer(driver);

		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 7: Click on 'Register' button");
		registerPage = loginPage.clickOnRegisterButton();
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 8: Select 'Gender' dropdown");
		registerPage.selectGenderDropdown("MR");
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 9: Input 'First name' textbox");
		registerPage.inputFirstNameTextbox(firstName);
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 10: Input 'Last name' textbox");
		registerPage.inputLastNameTextbox(lastName);
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 11: Input 'Email' textbox: "+EMAIL);
		registerPage.inputEmailTextbox(EMAIL);
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 12: Input 'Password' textbox: "+PASSWORD);
		registerPage.inputPasswordTextbox(PASSWORD);
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 13: Check on 'Comfirmation' checkbox");
		registerPage.checkConfirmationCheckbox();
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 14: Click on 'Register submit' button");
		myAccountPage = registerPage.clickOnRegisterSubmitButton();
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 15: Verify 'Logout' button on header is displayed");
		verifyTrue(myAccountPage.isLogoutFieldOnHeaderDisplayed());
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 16: Verify 'Register success msg' is displayed");
		verifyTrue(myAccountPage.isRegisterSuccessMsgDisplayed());
		
		log.info("TC_01_Register_With_Valid_User_Name_Password - Step 17: Verify 'User's full name'");
		verifyEquals(myAccountPage.getUserFullNameGreeting(), firstName + " " + lastName);
	}
	
	@Test
	public void TC_02_Logout() {
		log.info("TC_02_Logout - Step 1: Click on 'Logout' button on header");
		homePage = myAccountPage.clickOnLogoutHeader(driver);
		
		log.info("TC_02_Logout - Step 2: Verify 'Wordpress page home page' is displayed");
		verifyTrue(homePage.isHomePageDisplayed());
		
		log.info("TC_02_Logout - Step 3: Verify 'Logout' button on header is undisplayed");
		verifyTrue(homePage.isLogoutFieldOnHeaderUndisplayed());
	}

	@Test
	public void TC_03_Login_With_Valid_User_Name_Password() {
		log.info("TC_03_Login_With_Valid_User_Name_Password - Step 1: Click on 'My account' on header");
		loginPage = homePage.clickOnMyAccountOnHeader(driver);
		
		log.info("TC_03_Login_With_Valid_User_Name_Password - Step 2: Input 'Email' textbox: "+EMAIL);
		loginPage.inputEmailTextbox(EMAIL);
		
		log.info("TC_03_Login_With_Valid_User_Name_Password - Step 3: Input 'Password' textbox: "+PASSWORD);
		loginPage.inputPasswordTextbox(PASSWORD);
		
		log.info("TC_03_Login_With_Valid_User_Name_Password - Step 4: Click on 'Login' submit button");
		myAccountPage = loginPage.clickOnLoginButton();

		log.info("TC_03_Login_With_Valid_User_Name_Password - Step 5: Verify 'Logout' button on header is displayed");
		verifyTrue(myAccountPage.isLogoutFieldOnHeaderDisplayed()); 
		
		log.info("TC_03_Login_With_Valid_User_Name_Password - Step 6: Verify 'User's full name'");
		verifyEquals(myAccountPage.getUserFullNameGreeting(), firstName + " " + lastName);

	}


	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Close browser");
		cleanBrowserAndDriver();
	}

	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MyAccountPageObject myAccountPage;

}
